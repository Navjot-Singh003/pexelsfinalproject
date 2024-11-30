package com.pexels.network.models;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.internal.Intrinsics;

public final class ProfileImage {
   @SerializedName("small")
   @Nullable
   private final String small;

   @SerializedName("large")
   @Nullable
   private final String large;

   @SerializedName("medium")
   @Nullable
   private final String medium;

   public ProfileImage(@Nullable String small, @Nullable String large, @Nullable String medium) {
      this.small = small;
      this.large = large;
      this.medium = medium;
   }

   @Nullable
   public final String getSmall() {
      return this.small;
   }

   @Nullable
   public final String getLarge() {
      return this.large;
   }

   @Nullable
   public final String getMedium() {
      return this.medium;
   }

   @Nullable
   public final String component1() {
      return this.small;
   }

   @Nullable
   public final String component2() {
      return this.large;
   }

   @Nullable
   public final String component3() {
      return this.medium;
   }

   @NotNull
   public final ProfileImage copy(@Nullable String small, @Nullable String large, @Nullable String medium) {
      return new ProfileImage(small, large, medium);
   }

   @NotNull
   @Override
   public String toString() {
      return "ProfileImage(small=" + this.small + ", large=" + this.large + ", medium=" + this.medium + ")";
   }

   @Override
   public int hashCode() {
      int result = this.small == null ? 0 : this.small.hashCode();
      result = result * 31 + (this.large == null ? 0 : this.large.hashCode());
      result = result * 31 + (this.medium == null ? 0 : this.medium.hashCode());
      return result;
   }

   @Override
   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      }
      if (!(other instanceof ProfileImage)) {
         return false;
      }
      ProfileImage that = (ProfileImage) other;
      return Intrinsics.areEqual(this.small, that.small) &&
              Intrinsics.areEqual(this.large, that.large) &&
              Intrinsics.areEqual(this.medium, that.medium);
   }

   public ProfileImage() {
      this(null, null, null);
   }
}
