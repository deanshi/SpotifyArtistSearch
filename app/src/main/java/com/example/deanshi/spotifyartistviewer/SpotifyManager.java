package com.example.deanshi.spotifyartistviewer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpotifyManager {
    @GET("https://api.spotify.com/v1/search?q=Andre&type=artist")
    Call<SpotifyArtistObject> getArtists();
}
