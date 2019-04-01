package com.vilic.bojan.recipessearch.requests;

import com.vilic.bojan.recipessearch.requests.hits.Hits;

import java.util.ArrayList;

public class RecipeInfo {

    private int count;
    private ArrayList<Hits> hits;

    public int getCount() {
        return count;
    }

    public ArrayList<Hits> getHits() {
        return hits;
    }

    @Override
    public String toString() {
        return "RecipeInfo{" +
                "count=" + count +
                '}';
    }
}
