package com.pexels.network.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

// User.java
public final class User {
   @SerializedName("last_name")
   @Nullable
   private final String lastName;

   @SerializedName("profile_image")
   @Nullable
   private final ProfileImage profileImage;

   @SerializedName("name")
   @Nullable
   private final String name;

   @SerializedName("id")
   @Nullable
   private final String id;

   @SerializedName("first_name")
   @Nullable
   private final String firstName;

   @SerializedName("username")
   @Nullable
   private final String username;

    public User(@Nullable String lastName, @Nullable ProfileImage profileImage, @Nullable String name, @Nullable String id, @Nullable String firstName, @Nullable String username) {
        this.lastName = lastName;
        this.profileImage = profileImage;
        this.name = name;
        this.id = id;
        this.firstName = firstName;
        this.username = username;
    }


    @Nullable
   public String getLastName() {
      return lastName;
   }

   @Nullable
   public ProfileImage getProfileImage() {
      return profileImage;
   }

   @Nullable
   public String getName() {
      return name;
   }

   @Nullable
   public String getId() {
      return id;
   }

   @Nullable
   public String getFirstName() {
      return firstName;
   }

   @Nullable
   public String getUsername() {
      return username;
   }
}
