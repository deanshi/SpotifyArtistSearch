package com.example.deanshi.spotifyartistviewer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpotifyManager {
    @GET("search?q=artistName&type=artist")
    Call<List<SpotifyArtistObject>> getArtists(@Query("artistName") String artistName);
}
