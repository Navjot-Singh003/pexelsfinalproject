package com.pexels.downloadHelper.jv;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DownloadHelper {

    @Nullable
    public void downloadImage(
            @NotNull final Activity mainActivity,
            @NotNull String imageUrl,
            @NotNull final SocialCallbackInt socialCallbackInt) {
        
        OkHttpClient client = new OkHttpClient();
        String imageFile = "Pexels_" + System.currentTimeMillis();
        Request request = new Request.Builder().url(imageUrl).build();

        try (ResponseBody responseBody = client.newCall(request).execute().body()) {
            if (responseBody != null) {
                saveMediaToMediaStore(mainActivity, responseBody, imageFile, socialCallbackInt);
            } else {
                socialCallbackInt.onError("Failed to fetch image: response body is null.");
            }
        } catch (IOException e) {
            socialCallbackInt.onError("Failed to fetch image: " + e.getMessage());
        }
    }

    public void  saveMediaToMediaStore(
            @NotNull final Activity context,
            @NotNull final ResponseBody responseBody,
            @NotNull final String fileName,
            @NotNull final SocialCallbackInt socialCallbackInt) {
        ContentResolver resolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Media.DISPLAY_NAME, fileName);
        contentValues.put(Media.MIME_TYPE, "image/jpeg");
        contentValues.put(Media.RELATIVE_PATH, "Pictures/PexelsImage");

        Uri uri = resolver.insert(Media.EXTERNAL_CONTENT_URI, contentValues);
        if (uri == null) {
            socialCallbackInt.onError("Failed to create MediaStore entry.");
        }

        try (InputStream inputStream = responseBody.byteStream();
             OutputStream outputStream = resolver.openOutputStream(uri)) {

            byte[] buffer = new byte[1024];
            long totalBytes = responseBody.contentLength();
            long bytesDownloaded = 0;

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                if (outputStream != null) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                bytesDownloaded += bytesRead;
                int progress = (int) (bytesDownloaded * 100 / totalBytes);
                socialCallbackInt.downloadPercentage(progress);
            }

            try (Cursor cursor = resolver.query(uri, new String[]{Media.DATA}, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    String picturePath = cursor.getString(cursor.getColumnIndexOrThrow(Media.DATA));
                    socialCallbackInt.onSuccess("Media saved successfully to " + picturePath);
                    socialCallbackInt.afterSavePath(uri.toString());
                }
            }
        } catch (IOException e) {
            socialCallbackInt.onError("Error saving media: " + e.getMessage());
        }
    }
}
