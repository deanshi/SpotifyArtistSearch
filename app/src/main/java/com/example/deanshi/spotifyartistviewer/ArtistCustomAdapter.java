package com.example.deanshi.spotifyartistviewer;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArtistCustomAdapter extends ArrayAdapter<String> {

    HashMap<Integer, String> artistNameMapping = new HashMap<Integer, String>();
    List<String> artistMapToList = new ArrayList<String>();


    public ArtistCustomAdapter(Context context, int resource, List<Item> artistItems) {
        super(context, resource, artistMapToList);
        for (int i = 0; i < artistItems.size(); i++) {
            artistNameMapping.put(i, artistItems.get(i).getName());
        }
        artistMapToList = new ArrayList<String>(artistNameMapping.values());

    }

    public void generateMapToList() {

    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
