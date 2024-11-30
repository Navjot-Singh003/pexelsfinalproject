package com.pexels.network.models;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

public final class Links {
   @SerializedName("portfolio")
   @Nullable
   private final String portfolio;

   @SerializedName("following")
   @Nullable
   private final String following;

   @SerializedName("self")
   @Nullable
   private final String self;

   @SerializedName("html")
   @Nullable
   private final String html;

   @SerializedName("photos")
   @Nullable
   private final String photos;

   @SerializedName("likes")
   @Nullable
   private final String likes;

   @SerializedName("download")
   @Nullable
   private final String download;

   @SerializedName("download_location")
   @Nullable
   private final String downloadLocation;

   public Links(@Nullable String portfolio, @Nullable String following, @Nullable String self,
                @Nullable String html, @Nullable String photos, @Nullable String likes,
                @Nullable String download, @Nullable String downloadLocation) {
      this.portfolio = portfolio;
      this.following = following;
      this.self = self;
      this.html = html;
      this.photos = photos;
      this.likes = likes;
      this.download = download;
      this.downloadLocation = downloadLocation;
   }

   @Nullable
   public String getPortfolio() {
      return portfolio;
   }

   @Nullable
   public String getFollowing() {
      return following;
   }

   @Nullable
   public String getSelf() {
      return self;
   }

   @Nullable
   public String getHtml() {
      return html;
   }

   @Nullable
   public String getPhotos() {
      return photos;
   }

   @Nullable
   public String getLikes() {
      return likes;
   }

   @Nullable
   public String getDownload() {
      return download;
   }

   @Nullable
   public String getDownloadLocation() {
      return downloadLocation;
   }

   public Links copy(@Nullable String portfolio, @Nullable String following, @Nullable String self,
                     @Nullable String html, @Nullable String photos, @Nullable String likes,
                     @Nullable String download, @Nullable String downloadLocation) {
      return new Links(portfolio, following, self, html, photos, likes, download, downloadLocation);
   }

   @Override
   public String toString() {
      return "Links{" +
              "portfolio='" + portfolio + '\'' +
              ", following='" + following + '\'' +
              ", self='" + self + '\'' +
              ", html='" + html + '\'' +
              ", photos='" + photos + '\'' +
              ", likes='" + likes + '\'' +
              ", download='" + download + '\'' +
              ", downloadLocation='" + downloadLocation + '\'' +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Links links = (Links) o;
      return java.util.Objects.equals(portfolio, links.portfolio) &&
              java.util.Objects.equals(following, links.following) &&
              java.util.Objects.equals(self, links.self) &&
              java.util.Objects.equals(html, links.html) &&
              java.util.Objects.equals(photos, links.photos) &&
              java.util.Objects.equals(likes, links.likes) &&
              java.util.Objects.equals(download, links.download) &&
              java.util.Objects.equals(downloadLocation, links.downloadLocation);
   }

   @Override
   public int hashCode() {
      return java.util.Objects.hash(portfolio, following, self, html, photos, likes, download, downloadLocation);
   }
}
