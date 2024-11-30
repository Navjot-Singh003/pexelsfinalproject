package com.pexels.downloadHelper.jv;

import org.jetbrains.annotations.NotNull;

public interface SocialCallbackInt {
   void onSuccess(@NotNull String var1);

   void onError(@NotNull String var1);

   void afterSavePath(@NotNull String var1);

   void downloadPercentage(int var1);
}
