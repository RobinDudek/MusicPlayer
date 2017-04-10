package com.heisenbear.musicplayer.adapters;

import java.util.ArrayList;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heisenbear.musicplayer.R;
import com.heisenbear.musicplayer.models.Song;

/**
 * Created by rorod on 07/04/2017.
 */

public class SongAdapter extends BaseAdapter {
    private ArrayList<Song> songs;
    private LayoutInflater songInf;

    //constructor
    public SongAdapter(Context c, ArrayList<Song> theSongs){
        songs=theSongs;
        songInf=LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //map to song layout
        LinearLayout songLay = (LinearLayout)songInf.inflate
                (R.layout.song_item, parent, false);
        //get title and artist views
        TextView songView = (TextView)songLay.findViewById(R.id.songTitle);
        TextView artistView = (TextView)songLay.findViewById(R.id.songArtist);
        TextView durationView = (TextView)songLay.findViewById(R.id.songDuration);
        //get song using position
        Song currSong = songs.get(position);
        //get title and artist strings
        songView.setText(currSong.getTitle());
        artistView.setText(currSong.getArtist());
        durationView.setText(currSong.getDuration());
        //set position as tag
        songLay.setTag(position);
        return songLay;
    }

}