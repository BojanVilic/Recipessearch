package com.vilic.bojan.recipessearch.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.vilic.bojan.recipessearch.R;
import com.vilic.bojan.recipessearch.models.Recipe;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Recipe> recipesList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_recipe_view, viewGroup, false);
        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Picasso.get()
                .load(recipesList.get(i).getImage_url())
                .placeholder(R.drawable.ic_launcher_background)
                .into(((RecipesViewHolder)viewHolder).image);
        ((RecipesViewHolder)viewHolder).titleText.setText(recipesList.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        if (recipesList != null) {
            return recipesList.size();
        }
        return 0;
    }

    public void setRecipesList(List<Recipe> recipes){
        recipesList = recipes;
        notifyDataSetChanged();
    }
}
