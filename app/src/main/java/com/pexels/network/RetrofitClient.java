package com.pexels.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import org.jetbrains.annotations.NotNull;

// RetrofitClient for providing PexelsApiService and UnsplashApiService
public final class RetrofitClient {

   @NotNull
   public static final RetrofitClient INSTANCE = new RetrofitClient();

   @NotNull
   private static final String BASE_URL = "https://api.pexels.com/";
   @NotNull
   private static final String BASE_URL_UNSPLASH = "https://api.unsplash.com/";

   // Lazy initialization of the apiService using standard Java approach
   private static PexelsApiService apiService;
   private static PexelsApiService apiServiceUnsplash;

   private RetrofitClient() {}

   // Method to get the Pexels API service with lazy initialization
   @NotNull
   public PexelsApiService getApiService() {
      if (apiService == null) {
         synchronized (RetrofitClient.class) {
            if (apiService == null) {
               apiService = createService(BASE_URL);
            }
         }
      }
      return apiService;
   }

   // Method to get the Unsplash API service with lazy initialization
   @NotNull
   public PexelsApiService getApiServiceUnsplash() {
      if (apiServiceUnsplash == null) {
         synchronized (RetrofitClient.class) {
            if (apiServiceUnsplash == null) {
               apiServiceUnsplash = createService(BASE_URL_UNSPLASH);
            }
         }
      }
      return apiServiceUnsplash;
   }

   // Common method to create Retrofit service
   private PexelsApiService createService(String baseUrl) {
      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(baseUrl)
              .addConverterFactory(GsonConverterFactory.create())
              .build();

      return retrofit.create(PexelsApiService.class);
   }
}
