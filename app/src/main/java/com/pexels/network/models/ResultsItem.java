package com.pexels.network.models;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Objects;

// ResultsItem.java
public final class ResultsItem implements Serializable {
   @SerializedName("color")
   @Nullable
   private final String color;

   @SerializedName("created_at")
   @Nullable
   private final String createdAt;

   @SerializedName("description")
   @Nullable
   private final String description;

   @SerializedName("liked_by_user")
   @Nullable
   private final Boolean likedByUser;

   @SerializedName("urls")
   @Nullable
   private final Urls urls;

   @SerializedName("links")
   @Nullable
   private final Links links;

   @SerializedName("id")
   @Nullable
   private final String id;

   @SerializedName("user")
   @Nullable
   private final User user;

   public ResultsItem(@Nullable String color, @Nullable String createdAt, @Nullable String description,
                      @Nullable Boolean likedByUser, @Nullable Urls urls, @Nullable Links links,
                      @Nullable String id, @Nullable User user) {
      this.color = color;
      this.createdAt = createdAt;
      this.description = description;
      this.likedByUser = likedByUser;
      this.urls = urls;
      this.links = links;
      this.id = id;
      this.user = user;
   }

   @Nullable
   public String getColor() {
      return color;
   }

   @Nullable
   public String getCreatedAt() {
      return createdAt;
   }

   @Nullable
   public String getDescription() {
      return description;
   }

   @Nullable
   public Boolean getLikedByUser() {
      return likedByUser;
   }

   @Nullable
   public Urls getUrls() {
      return urls;
   }

   @Nullable
   public Links getLinks() {
      return links;
   }

   @Nullable
   public String getId() {
      return id;
   }

   @Nullable
   public User getUser() {
      return user;
   }

   public ResultsItem copy(@Nullable String color, @Nullable String createdAt, @Nullable String description,
                           @Nullable Boolean likedByUser, @Nullable Urls urls, @Nullable Links links,
                           @Nullable String id, @Nullable User user) {
      return new ResultsItem(color, createdAt, description, likedByUser, urls, links, id, user);
   }

   @NotNull
   @Override
   public String toString() {
      return "ResultsItem(color=" + color + ", createdAt=" + createdAt + ", description=" + description +
              ", likedByUser=" + likedByUser + ", urls=" + urls + ", links=" + links + ", id=" + id + ", user=" + user + ')';
   }

   @Override
   public int hashCode() {
      int result = color != null ? color.hashCode() : 0;
      result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
      result = 31 * result + (description != null ? description.hashCode() : 0);
      result = 31 * result + (likedByUser != null ? likedByUser.hashCode() : 0);
      result = 31 * result + (urls != null ? urls.hashCode() : 0);
      result = 31 * result + (links != null ? links.hashCode() : 0);
      result = 31 * result + (id != null ? id.hashCode() : 0);
      result = 31 * result + (user != null ? user.hashCode() : 0);
      return result;
   }

   @Override
   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      }
      if (other == null || getClass() != other.getClass()) {
         return false;
      }

      ResultsItem that = (ResultsItem) other;

      if (!Objects.equals(color, that.color)) return false;
      if (!Objects.equals(createdAt, that.createdAt)) return false;
      if (!Objects.equals(description, that.description)) return false;
      if (!Objects.equals(likedByUser, that.likedByUser)) return false;
      if (!Objects.equals(urls, that.urls)) return false;
      if (!Objects.equals(links, that.links)) return false;
      if (!Objects.equals(id, that.id)) return false;
      return Objects.equals(user, that.user);
   }
}
