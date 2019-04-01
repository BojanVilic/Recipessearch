package com.vilic.bojan.recipessearch.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;

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

public class RecipeRepository {

    private static RecipeRepository instance;
    private MediatorLiveData<List<Recipe>> mRecipes = new MediatorLiveData<>();
    private List<Recipe> mRecipe = new ArrayList<>();

    public static RecipeRepository getInstance() {
        if(instance == null){
            instance = new RecipeRepository();
        }
        return instance;
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }

    public void getData(String query){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipeAPI recipeAPI = retrofit.create(RecipeAPI.class);

        Call<RecipeInfo> call = recipeAPI.getRecipe(query, Constants.APP_ID, Constants.APP_KEY);

        call.enqueue(new Callback<RecipeInfo>() {
            @Override
            public void onResponse(Call<RecipeInfo> call, Response<RecipeInfo> response) {
                ArrayList<Hits> hitsArrayList = response.body().getHits();
                mRecipe.clear();
                for(Hits hits : hitsArrayList){
                    mRecipe.add(hits.getRecipe());
                }
                mRecipes.postValue(mRecipe);
            }

            @Override
            public void onFailure(Call<RecipeInfo> call, Throwable t) {
                System.out.println("onFailure");
                System.out.println(t.fillInStackTrace());
            }
        });
    }
}
