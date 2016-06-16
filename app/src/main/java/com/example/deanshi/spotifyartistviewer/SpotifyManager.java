package com.example.deanshi.spotifyartistviewer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpotifyManager {
    @GET("https://api.spotify.com/v1/search?type=artist")
    Call<SpotifyArtistObject> getArtists(@Query("q") String name);
}
