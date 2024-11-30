package com.pexels.network;

import com.pexels.network.models.PexelsResponse;
import com.pexels.network.models.UnsplashResponseModel;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface PexelsApiService {

   @GET("v1/search")
   @Nullable
   Call<PexelsResponse> searchPhotos(@Header("Authorization") @NotNull String authorization,
                               @Query("query") @NotNull String query,
                               @Query("per_page") int perPage);

   @GET("search/photos")
   @Nullable
   Call<UnsplashResponseModel> searchPhotosUnsplash(@Header("Authorization") @NotNull String authorization,
                                                   @Query("query") @NotNull String query,
                                                   @Query("page") int page);
}
