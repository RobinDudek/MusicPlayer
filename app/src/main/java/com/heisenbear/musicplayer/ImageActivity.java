package com.heisenbear.musicplayer;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.heisenbear.musicplayer.models.Song;
import com.heisenbear.musicplayer.viewers.*;
import com.heisenbear.musicplayer.models.ImageItem;

import java.util.ArrayList;


import static com.heisenbear.musicplayer.gallery.FileLister.Filter.ALL;
import static com.heisenbear.musicplayer.gallery.FileLister.Filter.PHOTO_ONLY;
import static com.heisenbear.musicplayer.gallery.FileLister.Filter.VIDEO_ONLY;

public class ImageActivity extends AppCompatActivity {
    private ArrayList<ImageItem> imageList;

    private GridView gridView;
    private Button buttonFilterAll;
    private Button buttonFilterPhotos;
    private Button buttonFilterVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageList = new ArrayList<ImageItem>();


        gridView = (GridView) findViewById(R.id.gridView);
        ViewStub emptyView = (ViewStub) findViewById(R.id.empty);
        gridView.setEmptyView(emptyView);

    }

    public void getAllImages() {
        //retrieve song info

        ContentResolver imageResolver = getContentResolver();
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor imageCursor = imageResolver.query(imageUri, null, null, null, null);

        if(imageCursor!=null && imageCursor.moveToFirst()){
            //get columns
            int titleColumn = imageCursor.getColumnIndex
                    (MediaStore.Images.Media.TITLE);
            int idColumn = imageCursor.getColumnIndex
                    (MediaStore.Images.Media._ID);
            int pathColumn= imageCursor.getColumnIndex
                    (MediaStore.Images.Media.DATA);
            //add songs to list
            do {
                long thisId = imageCursor.getLong(idColumn);
                String thisTitle = imageCursor.getString(titleColumn);
                String thisPath = imageCursor.getString(pathColumn);
                //imageList.add(new ImageItem(thisId, thisTitle,  thisPath));
            }
            while (imageCursor.moveToNext());
        }
    }

}
