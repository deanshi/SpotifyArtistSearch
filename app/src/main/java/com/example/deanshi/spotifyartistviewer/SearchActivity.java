package com.example.deanshi.spotifyartistviewer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class SearchActivity extends ListActivity {

    @BindView(R.id.spotify_search_view)
    SearchView spotifySearchView;

    @BindView(android.R.id.list)
    ListView spotifyArtistList;

    String artistSearchString;
    List<String> artistNameList = new ArrayList<>();
    List<String> artistImageList = new ArrayList<>();
    ArrayAdapter<String> artistNameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        setupAdapter();
        setupSearchView();
    }

    public void setupAdapter() {
        artistNameAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, artistNameList);
        spotifyArtistList.setAdapter(artistNameAdapter);
    }

    public void getArtistNames(String nameOfArtist) {

        SpotifyRetrofitService spotifyRetrofit = new SpotifyRetrofitService();
        spotifyRetrofit.spotifyManager.getArtists(nameOfArtist).enqueue(new Callback<SpotifyArtistObject>() {
            @Override
            public void onResponse(Call<SpotifyArtistObject> call, Response<SpotifyArtistObject> response) {
                updateAdapter(response);
            }

            @Override
            public void onFailure(Call<SpotifyArtistObject> call, Throwable t) {
                Timber.d(t, "Failed to get response from server");
            }
        });
    }

    public void updateAdapter(Response<SpotifyArtistObject> response) {
        artistNameAdapter.clear();
        if (response.body() != null) {
            response.body().populateArtistInfo();
            artistNameList = response.body().getArtistsNamesList();
            artistImageList = response.body().getArtistImageUrls();

            Timber.d("Received response from server: %s", artistNameList);
            Timber.d("Adapter: %s", spotifyArtistList.getAdapter().toString());

            artistNameAdapter.clear();
            artistNameAdapter.addAll(artistNameList);
            artistNameAdapter.notifyDataSetChanged();
        }
    }

    public void setupSearchView() {
        spotifySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String artistNameText) {
                artistSearchString = TextUtils.isEmpty(artistNameText) ? "" : artistNameText;
                Timber.d("SearchView text has been modified: %s", artistSearchString);
                getArtistNames(artistSearchString);
                return true;
            }
        });
    }

    public void onListItemClick(ListView l, View v, int position, long id) {

        Timber.d("You picked %s", artistNameList.get(position));
        Timber.d("URL for this artist image is %s", artistImageList.get(position));

        Intent sendArtistName = new Intent(getBaseContext(), DisplayInformationActivity.class);
        sendArtistName.putExtra("ARTIST_NAME", artistNameList.get(position));
        sendArtistName.putExtra("IMAGE_URL", artistImageList.get(position));
        startActivity(sendArtistName);
    }
}
