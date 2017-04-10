package com.heisenbear.musicplayer.viewers;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.heisenbear.musicplayer.R;

public class VideoViewerActivity extends AppCompatActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video_player);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        VideoView videoView = (VideoView) findViewById(R.id.fullscreen_video);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        String itemPath = getIntent().getStringExtra(PlaceholderGalleryFragment.ITEM_PATH);
        videoView.setVideoURI(Uri.parse(itemPath));
        videoView.requestFocus();
        videoView.start();
    }*/

}
