package com.vilic.bojan.recipessearch.models;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Recipe {

    //Data
    @SerializedName("label")
    private String title;

    @SerializedName("image")
    private String image_url;

    private String url;

    private String source;

    private String[] dietLabels;

    @SerializedName("yield")
    private int numberOfServings;

    private String[] healthLabels;

    private String[] cautions;

    @SerializedName("ingredientLines")
    private String[] ingredients;

    private double calories;

    @SerializedName("totalTime")
    private int time;

    //GETTERS
    public int getNumberOfServings() {
        return numberOfServings;
    }

    public String[] getHealthLabels() {
        return healthLabels;
    }

    public String[] getCautions() {
        return cautions;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public double getCalories() {
        return calories;
    }

    public int getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }

    public String[] getDietLabels() {
        return dietLabels;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


}
