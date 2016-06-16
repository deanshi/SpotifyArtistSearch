package com.example.deanshi.spotifyartistviewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class DisplayInformationActivity extends AppCompatActivity {

    @BindView(R.id.artist_name)
    TextView artistClickedName;

    @BindView(R.id.artist_picture)
    ImageView artistClickedPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        setArtistClickedName();
        setArtistClickedPicture();
    }

    public void setArtistClickedName() {
        String getArtistName = getIntent().getStringExtra("ARTIST_NAME");
        Timber.d("getArtistName %s", getArtistName);
        artistClickedName.setText(getArtistName);
    }

    public void setArtistClickedPicture() {
        String getImageUrl = getIntent().getStringExtra("IMAGE_URL");
        Timber.d("image URL for this is %s", getImageUrl);
        if (!getImageUrl.equals("No Image")) {
            Picasso.with(this)
                    .load(getImageUrl)
                    .into(artistClickedPicture);
        }
    }
}
