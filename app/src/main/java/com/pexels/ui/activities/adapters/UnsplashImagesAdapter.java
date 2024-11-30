package com.pexels.ui.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pexels.databinding.ItemPexelsBinding;
import com.pexels.network.models.ResultsItem;
import com.pexels.network.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class UnsplashImagesAdapter extends RecyclerView.Adapter<UnsplashImagesAdapter.ViewHolder> {
   @NotNull
   private ArrayList<ResultsItem> photoList;
   private ClickedTypeInt clickedTypeInt;

   public UnsplashImagesAdapter(@NotNull ArrayList<ResultsItem> photoList,
                                ClickedTypeInt clickedTypeInt) {
      this.photoList = photoList;
      this.clickedTypeInt = clickedTypeInt;
   }

   @NotNull
   @Override
   public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
      ItemPexelsBinding binding = ItemPexelsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
      return new ViewHolder(binding , clickedTypeInt);
   }

   @Override
   public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
      holder.bind(photoList.get(position));
   }

   @Override
   public int getItemCount() {
      return photoList.size();
   }

   public static final class ViewHolder extends RecyclerView.ViewHolder {
      @NotNull
      private final ItemPexelsBinding binding;
      private ClickedTypeInt clickedTypeInt;


      public ViewHolder(@NotNull ItemPexelsBinding binding, ClickedTypeInt clickedTypeInt) {
         super(binding.getRoot());
         this.binding = binding;
         this.clickedTypeInt = clickedTypeInt;

      }

      public void bind(@NotNull ResultsItem photo) {
         Context context = binding.getRoot().getContext();
         User user = photo.getUser();
         binding.tvQueryHistory.setText(user != null ? user.getName() : null);

         Glide.with(context)
                 .load(user != null && user.getProfileImage() != null ? user.getProfileImage().getMedium() : null)
                 .into(binding.ivPhotograph);

         Glide.with(context)
                 .load(photo.getUrls() != null ? photo.getUrls().getThumb() : null)
                 .into(binding.ivThumb);

         itemView.setOnClickListener(v -> clickedTypeInt.onItemClicked(photo));
         binding.ivDownloadImage.setOnClickListener(v -> {
            String fullUrl = photo.getUrls() != null ? photo.getUrls().getFull() : null;
            clickedTypeInt.onDownloadClicked(fullUrl);
         });
      }
   }
}


