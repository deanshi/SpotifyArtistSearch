package com.example.deanshi.spotifyartistviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.spotify_search_view)
    SearchView spotifySearchView;

    @BindView(R.id.spotify_artist_list)
    ListView spotifyArtistList;

    List<String> namesOfArtists = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        getArtistNames();
    }


    public void getArtistNames() {
        SpotifyRetrofitService spotifyRetrofit = new SpotifyRetrofitService();

        spotifyRetrofit.spotifyManager.getArtists("Franklin").enqueue(new Callback<SpotifyArtistObject>() {
            @Override
            public void onResponse(Call<SpotifyArtistObject> call, Response<SpotifyArtistObject> response) {
                Timber.d("Recieved response from server: %s", response.body().getArtistsNamesList().toString());
                ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, response.body().getArtistsNamesList());
                spotifyArtistList.setAdapter(nameAdapter);
            }

            @Override
            public void onFailure(Call<SpotifyArtistObject> call, Throwable t) {
                Timber.d(t, "Failed to get response from server");
            }
        });
    }


}
