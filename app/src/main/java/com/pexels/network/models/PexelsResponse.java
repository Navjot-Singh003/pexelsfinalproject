// PexelsResponse.java
package com.pexels.network.models;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.util.Objects;

public final class PexelsResponse {
   private final int totalResults;
   private final int page;
   private final int perPage;
   @NotNull
   private final ArrayList<Photo> photos;
   @Nullable
   private final String nextPage;

   public PexelsResponse(int totalResults, int page, int perPage, @NotNull ArrayList<Photo> photos, @Nullable String nextPage) {
      this.totalResults = totalResults;
      this.page = page;
      this.perPage = perPage;
      this.photos = photos;
      this.nextPage = nextPage;
   }

   public int getTotalResults() {
      return totalResults;
   }

   public int getPage() {
      return page;
   }

   public int getPerPage() {
      return perPage;
   }

   @NotNull
   public ArrayList<Photo> getPhotos() {
      return photos;
   }

   @Nullable
   public String getNextPage() {
      return nextPage;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof PexelsResponse)) return false;
      PexelsResponse that = (PexelsResponse) o;
      return totalResults == that.totalResults &&
              page == that.page &&
              perPage == that.perPage &&
              photos.equals(that.photos) &&
              Objects.equals(nextPage, that.nextPage);
   }

   @Override
   public int hashCode() {
      return Objects.hash(totalResults, page, perPage, photos, nextPage);
   }

   @Override
   public String toString() {
      return "PexelsResponse{" +
              "totalResults=" + totalResults +
              ", page=" + page +
              ", perPage=" + perPage +
              ", photos=" + photos +
              ", nextPage='" + nextPage + '\'' +
              '}';
   }
}

// PhotoSource.java

