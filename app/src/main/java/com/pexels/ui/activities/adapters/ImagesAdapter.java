package com.pexels.ui.activities.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.pexels.databinding.ItemImageBinding;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageViewHolder> {
   @NotNull
   private final List<Uri> images;
   @NotNull
   private final Function1<Uri, ?> onDeleteClick;

   public ImagesAdapter(@NotNull List<Uri> images, @NotNull Function1<Uri, Integer> onDeleteClick) {
      Intrinsics.checkNotNullParameter(images, "images");
      Intrinsics.checkNotNullParameter(onDeleteClick, "onDeleteClick");
      this.images = images;
      this.onDeleteClick = onDeleteClick;
   }

   @NotNull
   @Override
   public ImageViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
      ItemImageBinding binding = ItemImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
      Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
      return new ImageViewHolder(binding);
   }

   @Override
   public void onBindViewHolder(@NotNull ImageViewHolder holder, int position) {
      holder.bind(images.get(position));
   }

   @Override
   public int getItemCount() {
      return images.size();
   }

   public final class ImageViewHolder extends RecyclerView.ViewHolder {
      @NotNull
      private final ItemImageBinding binding;

      public ImageViewHolder(@NotNull ItemImageBinding binding) {
         super(binding.getRoot());
         this.binding = binding;
      }

      public void bind(@NotNull Uri uri) {
         Intrinsics.checkNotNullParameter(uri, "uri");
         binding.imageView.setImageURI(uri);
         binding.deleteButton.setOnClickListener(v -> onDeleteClick.invoke(uri));
      }
   }
}
