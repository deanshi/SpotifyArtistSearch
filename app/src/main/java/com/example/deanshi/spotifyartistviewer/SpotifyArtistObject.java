package com.example.deanshi.spotifyartistviewer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SpotifyArtistObject {


    @SerializedName("artists")
    @Expose
    private Artists artists;

    /**
     * @return The artists
     */
    public Artists getArtists() {
        return artists;
    }

    /**
     * @param artists The artists
     */
    public void setArtists(Artists artists) {
        this.artists = artists;
    }

}
