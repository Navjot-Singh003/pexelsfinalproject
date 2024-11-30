package com.pexels.network.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class UnsplashResponseModel {
   @SerializedName("total")
   @Nullable
   private final Integer total;

   @SerializedName("total_pages")
   @Nullable
   private final Integer totalPages;

   @SerializedName("results")
   @NotNull
   private final ArrayList<ResultsItem> results;

    public UnsplashResponseModel(@Nullable Integer total, @Nullable Integer totalPages, @NotNull ArrayList<ResultsItem> results) {
        this.total = total;
        this.totalPages = totalPages;
        this.results = results;
    }

   @Nullable
   public Integer getTotal() {
      return total;
   }

   @Nullable
   public Integer getTotalPages() {
      return totalPages;
   }

   @NotNull
   public ArrayList<ResultsItem> getResults() {
      return results;
   }
}

