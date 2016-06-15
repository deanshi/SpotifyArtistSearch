package com.example.deanshi.spotifyartistviewer;

import android.content.res.Resources;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpotifyRetrofitService {

    public static final String BASE_URL = "https://api.spotify.com/v1/search/";


    public static Retrofit spotifyRetrofit;
    public static SpotifyManager spotifyManager;

    public SpotifyRetrofitService() {
        spotifyRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        spotifyManager = spotifyRetrofit.create(SpotifyManager.class);
    }


}
