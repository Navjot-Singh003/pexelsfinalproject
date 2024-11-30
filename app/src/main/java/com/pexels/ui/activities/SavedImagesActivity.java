package com.pexels.ui.activities;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.pexels.R.layout;
import com.pexels.databinding.ActivitySavedImagesBinding;
import com.pexels.ui.activities.adapters.ImagesAdapter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.Nullable;

public final class SavedImagesActivity extends AppCompatActivity {
   private ActivitySavedImagesBinding binding;
   private ImagesAdapter adapter;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this, layout.activity_saved_images);
      initView();
   }

   private void initView() {
      binding.ivBack.setOnClickListener(view -> finish());
      refreshImages();
   }

   private void showToast(String text) {
      runOnUiThread(() -> Toast.makeText(SavedImagesActivity.this, text, Toast.LENGTH_SHORT).show());
   }

   private void refreshImages() {
      new Thread(() -> {
         showProgress();
         List<Uri> newImages = getSavedImagesFromFolder();
         if (newImages.isEmpty()) {
            showToast("Images not found");
         }

         runOnUiThread(new Runnable() {
            @Override
            public void run() {
               adapter = new ImagesAdapter(newImages, uri -> {
                  boolean success = deleteImage(uri);
                  if (success) {
                     showToast("Image deleted successfully");
                     refreshImages();
                  } else {
                     showToast("Failed to delete image");
                  }
                  return 0;
               });

               binding.recyclerItems.setAdapter(adapter);
               hideProgress();
            }
         });
      }).start();


   }

   private void showProgress() {
      runOnUiThread(() -> binding.progress.setVisibility(View.VISIBLE));
   }

   private void hideProgress() {
      runOnUiThread(() -> binding.progress.setVisibility(View.GONE));
   }


   private boolean deleteImage(Uri uri) {
      try {
         return getContentResolver().delete(uri, null, null) > 0;
      } catch (Exception e) {
         e.printStackTrace();
         return false;
      }
   }

   private List<Uri> getSavedImagesFromFolder() {
      List<Uri> imagesList = new ArrayList<>();
      Uri collection = Media.EXTERNAL_CONTENT_URI;
      String[] projection = {"_id"};
      String selection = "relative_path LIKE ?";
      String[] selectionArgs = {"Pictures/PexelsImage%"};
      String sortOrder = "date_added DESC";

      try (Cursor cursor = getContentResolver().query(collection, projection, selection, selectionArgs, sortOrder)) {
         if (cursor != null) {
            int idColumn = cursor.getColumnIndexOrThrow("_id");
            while (cursor.moveToNext()) {
               long id = cursor.getLong(idColumn);
               Uri uri = ContentUris.withAppendedId(collection, id);
               imagesList.add(uri);
            }
         }
      }


      return imagesList;
   }
}
