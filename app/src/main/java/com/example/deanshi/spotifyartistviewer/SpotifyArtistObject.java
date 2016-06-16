package com.example.deanshi.spotifyartistviewer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class SpotifyArtistObject {

    @SerializedName("artists")
    @Expose
    private Artists artists;
    private List<String> artistNames = new ArrayList<>();
    private List<String> artistImageUrls = new ArrayList<>();
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

    public List<String> getArtistsNamesList() {
        return artistNames;
    }

    public List<String> getArtistImageUrls() {
        return artistImageUrls;
    }

    public void populateArtistInfo() {
        for (int i = 0; i < artists.getItems().size(); i++) {
            artistNames.add(getArtistNames(artists, i));
            artistImageUrls.add(getImageUrl(artists, i));
        }
    }

    public String getArtistNames(Artists artist, int position) {
        return artist.getItems().get(position).getName();
    }

    public String getImageUrl(Artists artist, int position) {
        try {
            Timber.d("getImages %s", artist.getItems().get(position).getImages().get(1).getUrl());
            return artist.getItems().get(position).getImages().get(1).getUrl();
        } catch (IndexOutOfBoundsException noImage)  {
            Timber.d("Artist has no Image");
            return "No Image"; //use to display No Image Bitmap
        }
    }
}
