package com.vilic.bojan.recipessearch.repositories;

import android.arch.lifecycle.LiveData;

import com.vilic.bojan.recipessearch.models.Recipe;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository instance;
    private LiveData<List<Recipe>> mRecipes;

    public static RecipeRepository getInstance() {
        if(instance == null){
            instance = new RecipeRepository();
        }
        return instance;
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }
}
