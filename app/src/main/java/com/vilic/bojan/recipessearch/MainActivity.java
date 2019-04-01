package com.vilic.bojan.recipessearch;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.vilic.bojan.recipessearch.ViewModels.RecipeViewModel;
import com.vilic.bojan.recipessearch.adapters.RecipesAdapter;
import com.vilic.bojan.recipessearch.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecipeViewModel mRecipeViewModel;
    private RecyclerView mRecyclerView;
    private RecipesAdapter mRecipesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecipesAdapter = new RecipesAdapter();
        mRecyclerView.setAdapter(mRecipesAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void subscribeObservers() {
        mRecipeViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                mRecipesAdapter.setRecipesList(recipes);
            }
        });
    }

//    private void printInfo(ArrayList<Hits> hitsArrayList) {
//        for(Hits hits : hitsArrayList) {
//            Log.i("RECEPT", String.valueOf(hits.getRecipe()));
//            mRecipe.add(hits.getRecipe());
//            System.out.println("Title: " + hits.getRecipe().getTitle());
//            System.out.println("Image url: " + hits.getRecipe().getImage_url());
//            System.out.println("Url: " + hits.getRecipe().getUrl());
//            System.out.println("Source: " + hits.getRecipe().getSource());
//            System.out.println("Diet Labels: ");
//            String[] dietLabels = hits.getRecipe().getDietLabels();
//            for(String diet : dietLabels){
//                System.out.println(diet);
//            }
//            System.out.println("Number of servings: " + hits.getRecipe().getNumberOfServings());
//            System.out.println("Health Labels: ");
//            String[] healthLabels = hits.getRecipe().getHealthLabels();
//            for(String health : healthLabels){
//                System.out.println(health);
//            }
//            System.out.println("Cautions: ");
//            String[] cautions = hits.getRecipe().getCautions();
//            for(String c : cautions){
//                System.out.println(c);
//            }
//            System.out.println("Ingredients: ");
//            String[] ingredients = hits.getRecipe().getIngredients();
//            for(String i : ingredients){
//                System.out.println(i);
//            }
//            System.out.println("Calories: " + hits.getRecipe().getCalories());
//            System.out.println("Time: " + hits.getRecipe().getTime());
//            System.out.println("\n\nNEXT RECIPE\n\n");
//        }
//        initRecyclerView();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_toolbar, menu);
        MenuItem searchItem = menu.findItem(R.id.search_item);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search recipes...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mRecipeViewModel.getData(query);
                subscribeObservers();
                searchView.clearFocus();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}
