package com.app.grocerydemo.kotlin_demo.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.grocerydemo.kotlin_demo.model.NewCartModel;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.List;

public class SharedPref {
    static SharedPreferences prefs;

    public static void putBoolean(Context ctx, String key, boolean val) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        prefs.edit().putBoolean(key, val).commit();

    }

    public static boolean getBoolean(Context ctx, String key) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }

    public static void putInt(Context ctx, String key, int score) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        prefs.edit().putInt(key, score).apply();

    }

    public static int getInt(Context ctx, String key) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);

        return prefs.getInt(key, 0);
    }

    public static void putString(Context ctx, String key, String score) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        prefs.edit().putString(key, score).apply();

    }

    public static String getString(Context ctx, String key) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }


    public static void putListData(Context ctx, String key, String score) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        prefs.edit().putString(key, score).apply();

    }

    public static String getListData(Context ctx, String key) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public static void putFavListData(Context ctx, String score) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        prefs.edit().putString("FavString", score).apply();

    }

    public static String getFavListData(Context ctx) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        return prefs.getString("FavString", "");
    }

    public static void putFavModel(Context ctx, List<NewCartModel> newCartModelList) {
        Gson gson = new Gson();
        String score = gson.toJson(newCartModelList);
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        prefs.edit().putString("FavModel", score).apply();
    }

    public static List<NewCartModel> getFavModel(Context ctx) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        return gson.fromJson(prefs.getString("FavModel", ""), new TypeToken<List<NewCartModel>>() {
        }.getType());
    }


    public static void putFirebaseToken(Context ctx, String score) {
        prefs = ctx.getSharedPreferences("FirebaseToken", Context.MODE_PRIVATE);
        prefs.edit().putString("FireToken", score).apply();

    }

    public static String getFirebaseToken(Context ctx) {
        prefs = ctx.getSharedPreferences("FirebaseToken", Context.MODE_PRIVATE);
        return prefs.getString("FireToken", "");
    }

    /*public static void putHomePageData(Context ctx, List<HomeCate> newCartModelList) {
        Gson gson = new Gson();
        String score = gson.toJson(newCartModelList);
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        prefs.edit().putString("HomePageData", score).apply();

    }

    public static List<HomeCate> getHomePageData(Context ctx) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        String homePageData = prefs.getString("HomePageData", "");
        if(homePageData != null && homePageData.contains("")){}
        Gson gson = new Gson();
        return gson.fromJson(prefs.getString("HomePageData", ""), new TypeToken<List<HomeCate>>() {
        }.getType());
    }

    public static void putTrendingData(Context ctx, List<ProductsSubDataModel> newCartModelList) {
        Gson gson = new Gson();
        String score = gson.toJson(newCartModelList);
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        prefs.edit().putString("trendingList", score).apply();

    }

    public static List<ProductsSubDataModel> getTrendingData(Context ctx) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        return gson.fromJson(prefs.getString("trendingList", ""), new TypeToken<List<ProductsSubDataModel>>() {
        }.getType());
    }

    public static void putTopBannerData(Context ctx, List<BannerDataModel> newCartModelList) {
        Gson gson = new Gson();
        String score = gson.toJson(newCartModelList);
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        prefs.edit().putString("TopBannerList", score).apply();

    }

    public static List<BannerDataModel> getTopBannerData(Context ctx) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        return gson.fromJson(prefs.getString("TopBannerList", ""), new TypeToken<List<BannerDataModel>>() {
        }.getType());
    }

    public static void putBottomBannerData(Context ctx, List<BannerDataModel> newCartModelList) {
        Gson gson = new Gson();
        String score = gson.toJson(newCartModelList);
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        prefs.edit().putString("BottomBannerList", score).apply();

    }

    public static List<BannerDataModel> getBottomBannerData(Context ctx) {
        prefs = ctx.getSharedPreferences("GroceryDemo", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        return gson.fromJson(prefs.getString("BottomBannerList", ""), new TypeToken<List<BannerDataModel>>() {
        }.getType());
    }*/

}
