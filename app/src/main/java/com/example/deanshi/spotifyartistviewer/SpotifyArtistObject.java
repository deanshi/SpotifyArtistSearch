package com.example.deanshi.spotifyartistviewer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SpotifyArtistObject {

    @SerializedName("artists")
    @Expose
    private Artists artists;


    public class Artists {
        @SerializedName("items")
        @Expose
        private List<Items> items = new ArrayList<Items>();
    }

    public class Items {
        @SerializedName("name")
        @Expose
        private String name;
    }

}
