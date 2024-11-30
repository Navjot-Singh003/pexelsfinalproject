package com.pexels.network.models;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// Photo.java
public final class Photo {
   private final int id;
   private final int width;
   private final int height;
   @NotNull
   private final String url;
   @NotNull
   private final String photographer;
   @NotNull
   private final String photographerUrl;
   private final int photographerId;
   @Nullable
   private final String avgColor;
   @NotNull
   private final PhotoSource src;
   private final boolean liked;
   @NotNull
   private final String alt;

   public Photo(
           int id, int width, int height, @NotNull String url, @NotNull String photographer,
           @NotNull String photographerUrl, int photographerId, @Nullable String avgColor,
           @NotNull PhotoSource src, boolean liked, @NotNull String alt
   ) {
      this.id = id;
      this.width = width;
      this.height = height;
      this.url = url;
      this.photographer = photographer;
      this.photographerUrl = photographerUrl;
      this.photographerId = photographerId;
      this.avgColor = avgColor;
      this.src = src;
      this.liked = liked;
      this.alt = alt;
   }

   public int getId() {
      return id;
   }

   public int getWidth() {
      return width;
   }

   public int getHeight() {
      return height;
   }

   @NotNull
   public String getUrl() {
      return url;
   }

   @NotNull
   public String getPhotographer() {
      return photographer;
   }

   @NotNull
   public String getPhotographerUrl() {
      return photographerUrl;
   }

   public int getPhotographerId() {
      return photographerId;
   }

   @Nullable
   public String getAvgColor() {
      return avgColor;
   }

   @NotNull
   public PhotoSource getSrc() {
      return src;
   }

   public boolean isLiked() {
      return liked;
   }

   @NotNull
   public String getAlt() {
      return alt;
   }

   @Override
   public String toString() {
      return "Photo{" +
              "id=" + id +
              ", width=" + width +
              ", height=" + height +
              ", url='" + url + '\'' +
              ", photographer='" + photographer + '\'' +
              ", photographerUrl='" + photographerUrl + '\'' +
              ", photographerId=" + photographerId +
              ", avgColor='" + avgColor + '\'' +
              ", src=" + src +
              ", liked=" + liked +
              ", alt='" + alt + '\'' +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Photo)) return false;

      Photo photo = (Photo) o;
      return id == photo.id &&
              width == photo.width &&
              height == photo.height &&
              photographerId == photo.photographerId &&
              liked == photo.liked &&
              url.equals(photo.url) &&
              photographer.equals(photo.photographer) &&
              photographerUrl.equals(photo.photographerUrl) &&
              (avgColor != null ? avgColor.equals(photo.avgColor) : photo.avgColor == null) &&
              src.equals(photo.src) &&
              alt.equals(photo.alt);
   }

   @Override
   public int hashCode() {
      int result = Integer.hashCode(id);
      result = 31 * result + Integer.hashCode(width);
      result = 31 * result + Integer.hashCode(height);
      result = 31 * result + url.hashCode();
      result = 31 * result + photographer.hashCode();
      result = 31 * result + photographerUrl.hashCode();
      result = 31 * result + Integer.hashCode(photographerId);
      result = 31 * result + (avgColor != null ? avgColor.hashCode() : 0);
      result = 31 * result + src.hashCode();
      result = 31 * result + Boolean.hashCode(liked);
      result = 31 * result + alt.hashCode();
      return result;
   }
}
