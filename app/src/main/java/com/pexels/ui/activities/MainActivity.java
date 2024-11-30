package com.pexels.ui.activities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import com.pexels.R;
import com.pexels.databinding.ActivityMainBinding;
import com.pexels.downloadHelper.jv.DownloadHelper;
import com.pexels.downloadHelper.jv.SocialCallbackInt;
import com.pexels.network.apiHelper.PexelsSearchRepo;
import com.pexels.network.models.PexelsResponse;
import com.pexels.network.models.Photo;
import com.pexels.network.models.ResultsItem;
import com.pexels.network.models.UnsplashResponseModel;
import com.pexels.sharedPref.SharedPrefHelper;
import com.pexels.ui.activities.adapters.ClickedTypeInt;
import com.pexels.ui.activities.adapters.PexelsImagesAdapter;
import com.pexels.ui.activities.adapters.SearchHistoryAdapter;
import com.pexels.ui.activities.adapters.UnsplashImagesAdapter;
import com.pexels.ui.fragments.FullImageFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private ActivityMainBinding binding;
   private SharedPrefHelper sharedPrefHelper;
   private SearchHistoryAdapter adapter;
   private PexelsImagesAdapter adapterPexels;
   private UnsplashImagesAdapter adapterUnsplash;

   private String[] permissions = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ?
           new String[]{android.Manifest.permission.READ_MEDIA_IMAGES} :
           new String[]{
                   android.Manifest.permission.READ_EXTERNAL_STORAGE,
                   android.Manifest.permission.WRITE_EXTERNAL_STORAGE
           };

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
      setContentView(binding.getRoot());

      initView();
   }

   private void initView() {
      sharedPrefHelper = new SharedPrefHelper();
      setHistoryAdapter();
      searchQuery();

      binding.btnDownloadImages.setOnClickListener(v ->
              startActivity(new Intent(this, SavedImagesActivity.class))
      );
   }

   private void openImage(Photo photo) {
      FullImageFragment fragment = FullImageFragment.newInstance(photo.getSrc().getOriginal());
      FragmentManager manager = getSupportFragmentManager();
      manager.beginTransaction()
              .replace(R.id.main, fragment)
              .addToBackStack(null)
              .commit();
   }

   private void openImageUnsplash(ResultsItem photo) {
      FullImageFragment fragment = FullImageFragment.newInstance(photo.getUrls().getFull());
      FragmentManager manager = getSupportFragmentManager();
      manager.beginTransaction()
              .replace(R.id.main, fragment)
              .addToBackStack(null)
              .commit();
   }

   private void setHistoryAdapter() {
      List<String> searchHistory = sharedPrefHelper.getSearchHistory();
      adapter = new SearchHistoryAdapter(searchHistory, clickedTypeInt);
      binding.recyclerView.setAdapter(adapter);
   }

   private void searchQuery() {
      binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
         @Override
         public boolean onQueryTextSubmit(String query) {
            if (query != null) {
               binding.searchView.setQuery("", false);
               sharedPrefHelper.saveHistory(query);
               updateRecyclerView();

               boolean checkedRadioButtonId = binding.rbUnplash.isChecked();

               searchPhotos(query , checkedRadioButtonId);
               binding.searchView.setIconified(true);
            }
            return true;
         }

         @Override
         public boolean onQueryTextChange(String newText) {
            return false;
         }
      });

      binding.searchView.setOnClickListener(v -> binding.searchView.setIconified(false));

      binding.searchView.setOnQueryTextFocusChangeListener((v, hasFocus) ->
              binding.recyclerView.setVisibility( hasFocus ? View.VISIBLE : View.GONE)
      );
   }

   private void showToast(String text) {
      runOnUiThread(() -> Toast.makeText(this, text, Toast.LENGTH_SHORT).show());
   }

   private void searchPhotos(String query, boolean isUnsplashClicked) {
      new Thread(() -> {

         showProgress();

         if (isUnsplashClicked) {
            Call<UnsplashResponseModel> call = PexelsSearchRepo.getResultUnsplash(query);

            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<UnsplashResponseModel> call, Response<UnsplashResponseModel> response) {
                    if (response.isSuccessful()) {
                        ArrayList<ResultsItem> photos = response.body().getResults();
                        if (photos.isEmpty())
                            showToast(getString(R.string.images_not_found));
                        else
                            updateUnsplashAdapter(photos);
                    } else {
                        showToast("Somehtiong went wrong");
                    }
                }

                @Override
                public void onFailure(Call<UnsplashResponseModel> call, Throwable t) {
                    showToast(t.getMessage());
                }
            });

         } else {
            // may be pexels di website nho work kr rhi

            Call<PexelsResponse> call = PexelsSearchRepo.getResult(query);

            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<PexelsResponse> call, Response<PexelsResponse> response) {
                    if (response.isSuccessful()) {
                        ArrayList<Photo> photos = response.body().getPhotos();
                        if (photos.isEmpty())
                            showToast(getString(R.string.images_not_found));
                        else
                            updatePexelsAdapter(photos);

                    } else {
                        showToast("Somehtiong went wrong");
                    }
                }

                @Override
                public void onFailure(Call<PexelsResponse> call, Throwable t) {
                    showToast(t.getMessage());
                }
            });
         }

         hideProgress();
      }).start();
   }

   private void updateUnsplashAdapter(ArrayList<ResultsItem> photos) {
      runOnUiThread(() -> setUnsplashAdapter(photos));
   }

   @SuppressLint("NotifyDataSetChanged")
   private void setUnsplashAdapter(ArrayList<ResultsItem> photos) {
      adapterUnsplash = new UnsplashImagesAdapter(photos, clickedTypeInt);
      binding.recyclerItems.setAdapter(adapterUnsplash);
      adapterUnsplash.notifyDataSetChanged();
   }

   private final ClickedTypeInt clickedTypeInt = new ClickedTypeInt() {
      @Override
      public void onItemClicked(ResultsItem resultsItem) {
         openImageUnsplash(resultsItem);
      }

      @Override
      public void onItemClicked(Photo resultsItem) {
         openImage(resultsItem);
      }

      @Override
      public void onDownloadClicked(String imageURL) {
         checkAndRequestStoragePermission(imageURL);
      }

      @Override
      public void onItemClicked(String query) {
         binding.searchView.setQuery(query, false);
      }
   };

   private void updatePexelsAdapter(ArrayList<Photo> photos) {
      runOnUiThread(() -> setPexelsAdapter(photos));
   }

   @SuppressLint("NotifyDataSetChanged")
   private void setPexelsAdapter(ArrayList<Photo> photos) {
      adapterPexels = new PexelsImagesAdapter(photos, clickedTypeInt);
      binding.recyclerItems.setAdapter(adapterPexels);
      adapterPexels.notifyDataSetChanged();
   }

   private void showProgress() {
      runOnUiThread(() -> binding.progress.setVisibility(View.VISIBLE));
   }

   private void hideProgress() {
      runOnUiThread(() -> binding.progress.setVisibility(View.GONE));
   }

   private void updateRecyclerView() {
      List<String> updatedHistory = sharedPrefHelper.getSearchHistory();
      adapter.updateData(updatedHistory);
   }

   private void showLog(String msg) {
      runOnUiThread(() -> Log.e("DOWNLOAD_", "showLog: " + msg));
   }

   private void refreshGallery(String filePath) {
      MediaScannerConnection.scanFile(this, new String[]{filePath}, null, (path, uri) -> {
         Log.d("ExternalStorage", "Scanned " + path + ":");
         Log.d("ExternalStorage", "-> uri=" + uri);
      });
   }

   private void startDownloadImage(String imageUrl) {
      new Thread(() -> new DownloadHelper().downloadImage(MainActivity.this , imageUrl , socialCallbackInt)).start();
   }

   private final SocialCallbackInt socialCallbackInt = new SocialCallbackInt() {
      @Override
      public void onSuccess(String msg) {
         showLog(msg);
         showToast(msg);
      }

      @Override
      public void onError(String error) {
         showLog(error);
         showToast(error);
         runOnUiThread(new Runnable() {
            @Override
            public void run() {
               // Uncomment the below lines if needed
               // binding.btnDownload.setEnabled(true);
               // binding.pbProgress.setVisibility(View.GONE);
               // binding.tvPercentage.setVisibility(View.GONE);
            }
         });
      }

      @Override
      public void afterSavePath(String filePath) {
         refreshGallery(filePath);
         runOnUiThread(() -> {
            // Uncomment the below lines if needed
            // showToast(filePath);
            // binding.btnDownload.setEnabled(true);
            // binding.pbProgress.setVisibility(View.GONE);
            // binding.tvPercentage.setVisibility(View.GONE);
         });
      }

      @Override
      public void downloadPercentage(final int percentage) {
         runOnUiThread(() -> {
            // Uncomment the below lines if needed
            // binding.pbProgress.setVisibility(View.VISIBLE);
            // binding.tvPercentage.setVisibility(View.VISIBLE);
            // binding.pbProgress.setProgress(percentage);
            // binding.tvPercentage.setText(percentage + "%");
         });
      }
   };


   private void checkAndRequestStoragePermission(String imageUrl) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
         startDownloadImage(imageUrl);
      } else {
         if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            startDownloadImage(imageUrl);
         } else {
            requestPermissionLauncher.launch(permissions);
         }
      }
   }

   private final ActivityResultLauncher<String[]> requestPermissionLauncher = registerForActivityResult(
           new ActivityResultContracts.RequestMultiplePermissions(), isGranted -> {
              boolean allGranted = isGranted.values().stream().allMatch(Boolean::booleanValue);
              if (allGranted) {
                 Toast.makeText(this, "All permissions granted", Toast.LENGTH_SHORT).show();
              } else {
                 Toast.makeText(this, "Some permissions are denied", Toast.LENGTH_SHORT).show();
              }
           }
   );
}

