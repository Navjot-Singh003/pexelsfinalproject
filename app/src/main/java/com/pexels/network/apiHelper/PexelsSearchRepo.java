package com.pexels.network.apiHelper;

import com.pexels.network.RetrofitClient;
import com.pexels.network.models.PexelsResponse;
import com.pexels.network.models.UnsplashResponseModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import retrofit2.Call;

public final class PexelsSearchRepo {

    private static final String PEXELS_API_KEY = "HylTNwr7lJv53y3ocDv9c4CWSZKxROwR7opQJJwM0tKxlsF0kjDpFei4";
    private static final String UNSPLASH_API_KEY = "Client-ID 8KP76pmxq5pt-00qqyb8UxzQGC9_42XfPv3NUHap5uw";

    @Nullable
    public static Call<PexelsResponse> getResult(@NotNull String searchQuery) {
        return RetrofitClient.INSTANCE.getApiService().searchPhotos(PEXELS_API_KEY, searchQuery , 20);
    }

    @Nullable
    public static Call<UnsplashResponseModel> getResultUnsplash(@NotNull String searchQuery) {
        return RetrofitClient.INSTANCE.getApiServiceUnsplash().searchPhotosUnsplash(UNSPLASH_API_KEY , searchQuery , 20);
    }

}
