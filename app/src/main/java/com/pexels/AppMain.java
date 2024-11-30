package com.pexels;

import android.app.Application;
import android.content.Context;

import kotlin.UninitializedPropertyAccessException;

public class AppMain extends Application {

   private static AppMain instance;

   public static Context getContext() {
      if (instance == null) {
         throw new UninitializedPropertyAccessException("AppMain instance is not initialized");
      }
      return instance.getApplicationContext();
   }

   @Override
   public void onCreate() {
      super.onCreate();
      instance = this;
   }
}
