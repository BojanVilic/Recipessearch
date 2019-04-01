package com.vilic.bojan.recipessearch.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vilic.bojan.recipessearch.R;

public class RecipesViewHolder extends RecyclerView.ViewHolder {

    TextView titleText;
    ImageView image;

    public RecipesViewHolder(@NonNull View itemView) {
        super(itemView);

        titleText = itemView.findViewById(R.id.single_text_title);
        image = itemView.findViewById(R.id.single_image);
    }
}
