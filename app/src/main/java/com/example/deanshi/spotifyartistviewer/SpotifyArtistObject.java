package com.example.deanshi.spotifyartistviewer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SpotifyArtistObject {


    @SerializedName("artists")
    @Expose
    private Artists artists;
    private List<String> artistNames = new ArrayList<>();

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

    public void setArtistNamesList() {
        for (int i = 0; i < artists.getItems().size(); i++) {
            artistNames.add(artists.getItems().get(i).getName());
        }
    }

    public List<String> getArtistsNamesList() {
        setArtistNamesList();
        return artistNames;
    }

}
