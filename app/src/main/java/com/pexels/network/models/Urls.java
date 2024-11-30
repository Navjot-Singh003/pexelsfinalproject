package com.pexels.network.models;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

// Urls.java
public final class Urls {
   @SerializedName("small")
   @Nullable
   private final String small;

   @SerializedName("small_s3")
   @Nullable
   private final String smallS3;

   @SerializedName("thumb")
   @Nullable
   private final String thumb;

   @SerializedName("raw")
   @Nullable
   private final String raw;

   @SerializedName("regular")
   @Nullable
   private final String regular;

   @SerializedName("full")
   @Nullable
   private final String full;

   public Urls(@Nullable String small, @Nullable String smallS3, @Nullable String thumb,
               @Nullable String raw, @Nullable String regular, @Nullable String full) {
      this.small = small;
      this.smallS3 = smallS3;
      this.thumb = thumb;
      this.raw = raw;
      this.regular = regular;
      this.full = full;
   }

   @Nullable public String getSmall() { return small; }
   @Nullable public String getSmallS3() { return smallS3; }
   @Nullable public String getThumb() { return thumb; }
   @Nullable public String getRaw() { return raw; }
   @Nullable public String getRegular() { return regular; }
   @Nullable public String getFull() { return full; }

   @NotNull
   public Urls copy(@Nullable String small, @Nullable String smallS3, @Nullable String thumb,
                    @Nullable String raw, @Nullable String regular, @Nullable String full) {
      return new Urls(small, smallS3, thumb, raw, regular, full);
   }

   @Override
   @NotNull
   public String toString() {
      return String.format("Urls(small=%s, smallS3=%s, thumb=%s, raw=%s, regular=%s, full=%s)",
              small, smallS3, thumb, raw, regular, full);
   }

   @Override
   public int hashCode() {
      return Objects.hash(small, smallS3, thumb, raw, regular, full);
   }

   @Override
   public boolean equals(@Nullable Object other) {
      if (this == other) return true;
      if (!(other instanceof Urls)) return false;
      Urls that = (Urls) other;
      return Objects.equals(small, that.small) &&
              Objects.equals(smallS3, that.smallS3) &&
              Objects.equals(thumb, that.thumb) &&
              Objects.equals(raw, that.raw) &&
              Objects.equals(regular, that.regular) &&
              Objects.equals(full, that.full);
   }
}
