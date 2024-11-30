package com.pexels.network.models;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PhotoSource {
   @NotNull
   private final String original;
   @NotNull
   private final String large2x;
   @NotNull
   private final String large;
   @NotNull
   private final String medium;
   @NotNull
   private final String small;
   @NotNull
   private final String portrait;
   @NotNull
   private final String landscape;
   @NotNull
   private final String tiny;

   // Constructor
   public PhotoSource(
           @NotNull String original,
           @NotNull String large2x,
           @NotNull String large,
           @NotNull String medium,
           @NotNull String small,
           @NotNull String portrait,
           @NotNull String landscape,
           @NotNull String tiny
   ) {
      this.original = original;
      this.large2x = large2x;
      this.large = large;
      this.medium = medium;
      this.small = small;
      this.portrait = portrait;
      this.landscape = landscape;
      this.tiny = tiny;
   }

   // Getters
   @NotNull public String getOriginal() { return original; }
   @NotNull public String getLarge2x() { return large2x; }
   @NotNull public String getLarge() { return large; }
   @NotNull public String getMedium() { return medium; }
   @NotNull public String getSmall() { return small; }
   @NotNull public String getPortrait() { return portrait; }
   @NotNull public String getLandscape() { return landscape; }
   @NotNull public String getTiny() { return tiny; }

   // Copy Method
   @NotNull
   public PhotoSource copy(
           @NotNull String original,
           @NotNull String large2x,
           @NotNull String large,
           @NotNull String medium,
           @NotNull String small,
           @NotNull String portrait,
           @NotNull String landscape,
           @NotNull String tiny
   ) {
      return new PhotoSource(original, large2x, large, medium, small, portrait, landscape, tiny);
   }

   // Component Methods for Destructuring
   @NotNull public String component1() { return original; }
   @NotNull public String component2() { return large2x; }
   @NotNull public String component3() { return large; }
   @NotNull public String component4() { return medium; }
   @NotNull public String component5() { return small; }
   @NotNull public String component6() { return portrait; }
   @NotNull public String component7() { return landscape; }
   @NotNull public String component8() { return tiny; }

   // Equals and HashCode
   @Override
   public boolean equals(@Nullable Object obj) {
      if (this == obj) return true;
      if (!(obj instanceof PhotoSource)) return false;

      PhotoSource other = (PhotoSource) obj;
      return original.equals(other.original) &&
              large2x.equals(other.large2x) &&
              large.equals(other.large) &&
              medium.equals(other.medium) &&
              small.equals(other.small) &&
              portrait.equals(other.portrait) &&
              landscape.equals(other.landscape) &&
              tiny.equals(other.tiny);
   }

   @Override
   public int hashCode() {
      int result = original.hashCode();
      result = 31 * result + large2x.hashCode();
      result = 31 * result + large.hashCode();
      result = 31 * result + medium.hashCode();
      result = 31 * result + small.hashCode();
      result = 31 * result + portrait.hashCode();
      result = 31 * result + landscape.hashCode();
      result = 31 * result + tiny.hashCode();
      return result;
   }

   // ToString
   @Override
   @NotNull
   public String toString() {
      return "PhotoSource{" +
              "original='" + original + '\'' +
              ", large2x='" + large2x + '\'' +
              ", large='" + large + '\'' +
              ", medium='" + medium + '\'' +
              ", small='" + small + '\'' +
              ", portrait='" + portrait + '\'' +
              ", landscape='" + landscape + '\'' +
              ", tiny='" + tiny + '\'' +
              '}';
   }
}
