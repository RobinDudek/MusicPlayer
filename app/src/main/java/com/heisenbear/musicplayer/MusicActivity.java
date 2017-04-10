package com.heisenbear.musicplayer;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;

import com.heisenbear.musicplayer.adapters.SongAdapter;
import com.heisenbear.musicplayer.models.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MusicActivity extends AppCompatActivity {
    private ArrayList<Song> songList;
    private ListView songView;
    //private SeekBar songBar;
    //private EditText search;

    private MediaPlayer player = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        grantUriPermission("com.android.providers.media.MediaProvider", MediaStore.Audio.Media.getContentUri("EXTERNAL_CONTENT_URI"), Intent.FLAG_GRANT_READ_URI_PERMISSION);
        checkAppVersion();

        songView = (ListView)findViewById(R.id.songList);
        //search = (EditText)findViewById(R.id.search);
        songList = new ArrayList<Song>();

        getAllSongs();
        alphabeticalSort();

        SongAdapter songAdt = new SongAdapter(this, songList);
        songView.setAdapter(songAdt);

        /*search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                this.songAdt.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });*/
    }

    public void getAllSongs() {
        //retrieve song info

        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.ARTIST);
            int pathColumn= musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.DATA);
            int durationColumn= musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.DURATION);
            //add songs to list
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                String thisPath = musicCursor.getString(pathColumn);
                String thisDuration = musicCursor.getString(durationColumn);
                songList.add(new Song(thisId, thisTitle, thisArtist, thisPath, thisDuration));
            }
            while (musicCursor.moveToNext());
        }
    }

    public void songPicked(View view){
        /*
        Je récupère l'id de la musique
        Context context = getApplicationContext();
        String text = view.getTag().toString();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        */
        player.reset();
        int selectedSongID = Integer.parseInt(view.getTag().toString());
        Uri songUri = Uri.parse("file:///" + songList.get(selectedSongID).getPath());
        //updateSongBar(songList.get(selectedSongID));
        player = MediaPlayer.create(getApplicationContext(), songUri);
        player.start();
    }

    //should be asynchronous
    /*public void updateSongBar(Song song) {
        songBar.setMax(Integer.parseInt(song.getDurationTimestamp()));
    }*/

    public void alphabeticalSort()
    {
        Collections.sort(songList, new Comparator<Song>(){
            public int compare(Song a, Song b){
                return a.getTitle().compareTo(b.getTitle());
            }
        });
    }


    public void checkAppVersion()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

                // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                // app-defined int constant

                return;
            }
        }
    }

}
