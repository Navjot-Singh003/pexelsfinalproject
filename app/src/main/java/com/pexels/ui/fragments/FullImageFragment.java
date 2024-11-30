package com.pexels.ui.fragments;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.pexels.R;
import com.pexels.databinding.FragmentImageViewBinding;
import com.pexels.downloadHelper.jv.DownloadHelper;
import com.pexels.downloadHelper.jv.SocialCallbackInt;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class FullImageFragment extends Fragment {

   private FragmentImageViewBinding binding;
   @NotNull
   private String imageUrl = "";
   @NotNull
   private final String[] permissions;
   @NotNull
   private final ActivityResultLauncher<String[]> requestPermissionLauncher;
   @NotNull
   private final SocialCallbackInt socialCallbackInt;

   public static FullImageFragment newInstance(String imageUrl) {
      FullImageFragment fragment = new FullImageFragment();
      Bundle args = new Bundle();
      args.putString("ARG_IMAGE_URL", imageUrl);
      fragment.setArguments(args);
      return fragment;
   }

   public FullImageFragment() {
      if (VERSION.SDK_INT >= 33) {
         permissions = new String[]{"android.permission.READ_MEDIA_IMAGES"};
      } else {
         permissions = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
      }

      requestPermissionLauncher = registerForActivityResult(
              new ActivityResultContracts.RequestMultiplePermissions(),
              this::handlePermissionResult
      );

      socialCallbackInt = new SocialCallbackInt() {
         @Override
         public void onSuccess(String msg) {
            showLog(msg);
            showToast(msg);
         }

         @Override
         public void onError(String error) {
            showLog(error);
            showToast(error);
         }

         @Override
         public void afterSavePath(String filePath) {
            refreshGallery(filePath);
         }

         @Override
         public void downloadPercentage(int percentage) {
            // handle download percentage
         }
      };
   }

   @NotNull
   @Override
   public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_view, container, false);
      String url = getArguments() != null ? getArguments().getString("ARG_IMAGE_URL", "") : "";
      imageUrl = url;

      binding.progressBar.setVisibility(View.VISIBLE);

      Glide.with(this)
              .load(imageUrl)
              .listener(new RequestListener<Drawable>() {
                 @Override
                 public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    binding.progressBar.setVisibility(View.GONE);
                    return false;
                 }

                 @Override
                 public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                    binding.progressBar.setVisibility(View.GONE);
                    return false;
                 }
              })
              .into(binding.fullImage);

      binding.ivBack.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());
      binding.ivPhotograph.setOnClickListener(v -> checkAndRequestStoragePermission());

      return binding.getRoot();
   }

   private void handlePermissionResult(Map<String, Boolean> isGranted) {
      boolean allGranted = !isGranted.containsValue(false);
      String message = allGranted ? "All permissions granted" : "Some permissions are denied";
      Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
   }

   private void checkAndRequestStoragePermission() {
      if (VERSION.SDK_INT >= 33 || ContextCompat.checkSelfPermission(requireContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
         startDownloadImage();
      } else {
         requestPermissionLauncher.launch(permissions);
      }
   }

   private void startDownloadImage() {
      new Thread(() ->
              new DownloadHelper().downloadImage(
              requireActivity(),
              imageUrl,
              socialCallbackInt
      )).start();
   }

   private void showToast(String text) {
      requireActivity().runOnUiThread(() -> Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show());
   }

   private void showLog(String msg) {
      requireActivity().runOnUiThread(() -> Log.e("DOWNLOAD_", "showLog: " + msg));
   }

   private void refreshGallery(String filePath) {
      MediaScannerConnection.scanFile(requireContext(), new String[]{filePath}, null, (path, uri) -> Log.d("ExternalStorage", "Scanned " + path));
   }

}
