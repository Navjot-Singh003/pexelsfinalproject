package com.pexels.ui.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.pexels.databinding.ItemPexelsBinding;
import com.pexels.network.models.Photo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class PexelsImagesAdapter extends RecyclerView.Adapter<PexelsImagesAdapter.ViewHolder> {
   @NotNull
   private final List<Photo> photoList;

   private ClickedTypeInt clickedTypeInt;
   public PexelsImagesAdapter(@NotNull List<Photo> photoList,
                              ClickedTypeInt clickedTypeInt) {
      this.photoList = photoList;
      this.clickedTypeInt = clickedTypeInt;
   }

   @NotNull
   @Override
   public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
      ItemPexelsBinding binding = ItemPexelsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
      return new ViewHolder(binding);
   }

   @Override
   public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
      holder.bind(photoList.get(position));
   }

   @Override
   public int getItemCount() {
      return photoList.size();
   }

   public final class ViewHolder extends RecyclerView.ViewHolder {
      @NotNull
      private final ItemPexelsBinding binding;

      public ViewHolder(@NotNull ItemPexelsBinding binding) {
         super(binding.getRoot());
         this.binding = binding;
      }

      public void bind(@NotNull Photo photo) {
         Context context = binding.getRoot().getContext();
         binding.tvQueryHistory.setText(photo.getPhotographer());
         Glide.with(context).load(photo.getPhotographerUrl()).into(binding.ivPhotograph);
         Glide.with(context).load(photo.getSrc().getSmall()).into(binding.ivThumb);

         itemView.setOnClickListener(v -> clickedTypeInt.onItemClicked(photo));
         binding.ivDownloadImage.setOnClickListener(v -> clickedTypeInt.onDownloadClicked(photo.getSrc().getLarge()));
      }
   }
}
