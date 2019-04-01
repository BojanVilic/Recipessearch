package com.vilic.bojan.recipessearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.SearchView;

import com.vilic.bojan.recipessearch.adapters.RecipesAdapter;
import com.vilic.bojan.recipessearch.models.Recipe;
import com.vilic.bojan.recipessearch.requests.RecipeAPI;
import com.vilic.bojan.recipessearch.requests.RecipeInfo;
import com.vilic.bojan.recipessearch.requests.hits.Hits;
import com.vilic.bojan.recipessearch.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecipesAdapter mRecipesAdapter;
    private List<Recipe> mRecipe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    private void retrofitCall(String text) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipeAPI recipeAPI = retrofit.create(RecipeAPI.class);

        Call<RecipeInfo> call = recipeAPI.getRecipe(text, Constants.APP_ID, Constants.APP_KEY);

        call.enqueue(new Callback<RecipeInfo>() {
            @Override
            public void onResponse(Call<RecipeInfo> call, Response<RecipeInfo> response) {
                ArrayList<Hits> hitsArrayList = response.body().getHits();
                printInfo(hitsArrayList);
            }

            @Override
            public void onFailure(Call<RecipeInfo> call, Throwable t) {
                System.out.println("onFailure");
                System.out.println(t.fillInStackTrace());
            }
        });
    }

    private void printInfo(ArrayList<Hits> hitsArrayList) {
        for (Hits hits : hitsArrayList) {
            Log.i("RECEPT", String.valueOf(hits.getRecipe()));
            mRecipe.add(hits.getRecipe());
            System.out.println("Title: " + hits.getRecipe().getTitle());
            System.out.println("Image url: " + hits.getRecipe().getImage_url());
            System.out.println("Url: " + hits.getRecipe().getUrl());
            System.out.println("Source: " + hits.getRecipe().getSource());
            System.out.println("Diet Labels: ");
            String[] dietLabels = hits.getRecipe().getDietLabels();
            for(String diet : dietLabels){
                System.out.println(diet);
            }
            System.out.println("Number of servings: " + hits.getRecipe().getNumberOfServings());
            System.out.println("Health Labels: ");
            String[] healthLabels = hits.getRecipe().getHealthLabels();
            for(String health : healthLabels){
                System.out.println(health);
            }
            System.out.println("Cautions: ");
            String[] cautions = hits.getRecipe().getCautions();
            for(String c : cautions){
                System.out.println(c);
            }
            System.out.println("Ingredients: ");
            String[] ingredients = hits.getRecipe().getIngredients();
            for(String i : ingredients){
                System.out.println(i);
            }
            System.out.println("Calories: " + hits.getRecipe().getCalories());
            System.out.println("Time: " + hits.getRecipe().getTime());
            System.out.println("\n\nNEXT RECIPE\n\n");
        }
        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_toolbar, menu);
        MenuItem searchItem = menu.findItem(R.id.search_item);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search recipes...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!query.equals("")) {
                    retrofitCall(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    private void initRecyclerView(){
        mRecipesAdapter = new RecipesAdapter();
        mRecyclerView.setAdapter(mRecipesAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecipesAdapter.setRecipesList(mRecipe);
    }
}
