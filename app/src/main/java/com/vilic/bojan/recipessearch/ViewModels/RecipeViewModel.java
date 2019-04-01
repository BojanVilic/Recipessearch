package com.vilic.bojan.recipessearch.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.vilic.bojan.recipessearch.models.Recipe;
import com.vilic.bojan.recipessearch.repositories.RecipeRepository;

import java.util.List;

public class RecipeViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;

    public RecipeViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes(){
        return mRecipeRepository.getRecipes();
    }

    public void getData(String query){
        mRecipeRepository.getData(query);
    }
}
