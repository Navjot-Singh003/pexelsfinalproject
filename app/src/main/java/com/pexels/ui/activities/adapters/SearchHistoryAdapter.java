package com.pexels.ui.activities.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.pexels.R;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder> {
   @NotNull
   private List<String> historyList;
   @NotNull
   private ClickedTypeInt clickedTypeInt;

   public SearchHistoryAdapter(@NotNull List<String> historyList, ClickedTypeInt clickedTypeInt) {
      this.historyList = historyList;
      this.clickedTypeInt = clickedTypeInt;
   }

   @NotNull
   @Override
   public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_history, parent, false);
      return new ViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
      holder.bind(historyList.get(position));
   }

   @Override
   public int getItemCount() {
      return historyList.size();
   }

   public void updateData(@NotNull List<String> newHistory) {
      this.historyList = newHistory;
      notifyDataSetChanged();
   }

   public final class ViewHolder extends RecyclerView.ViewHolder {
      @NotNull
      private final TextView textView;

      public ViewHolder(@NotNull View itemView) {
         super(itemView);
         textView = itemView.findViewById(R.id.tvQueryHistory);
         itemView.setOnClickListener(v -> clickedTypeInt.onItemClicked(historyList.get(getAdapterPosition())));
      }

      public void bind(String historyItem) {
         textView.setText(historyItem);
      }
   }
}
