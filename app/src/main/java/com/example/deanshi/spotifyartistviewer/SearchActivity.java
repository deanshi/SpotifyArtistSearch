package com.example.deanshi.spotifyartistviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        SpotifyRetrofitService spotifyRetrofit = new SpotifyRetrofitService();

        spotifyRetrofit.spotifyManager.getArtists().enqueue(new Callback<SpotifyArtistObject>() {
            @Override
            public void onResponse(Call<SpotifyArtistObject> call, Response<SpotifyArtistObject> response) {
                if (response == null) Timber.d("No response from server");

                Timber.d("Recieved response from server: %s", response.body().getArtists().getItems().get(0).getName());
            }

            @Override
            public void onFailure(Call<SpotifyArtistObject> call, Throwable t) {
                Timber.d(t, "Failed to get response from server");
            }
        });
    }
}
