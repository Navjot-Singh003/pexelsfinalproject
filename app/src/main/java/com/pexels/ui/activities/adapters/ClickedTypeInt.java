package com.pexels.ui.activities.adapters;

import com.pexels.network.models.Photo;
import com.pexels.network.models.ResultsItem;

public interface ClickedTypeInt {
   void onItemClicked(ResultsItem resultsItem);
   void onItemClicked(Photo resultsItem);
   void onDownloadClicked(String imageURL);
   void onItemClicked(String imageURL);
}