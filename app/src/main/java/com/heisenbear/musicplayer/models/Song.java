package com.heisenbear.musicplayer.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by rorod on 07/04/2017.
 */

public class Song {

    private long id;
    private String title;
    private String artist;
    private String path;
    private String duration;

    public Song(long songID, String songTitle, String songArtist, String songPath, String songDuration) {
        id=songID;
        title=songTitle;
        artist=songArtist;
        path=songPath;
        duration=songDuration;
    }

    //getter
    public long getID(){return id;}
    public String getTitle(){return title;}
    public String getArtist(){return artist;}
    public String getPath(){return path;}
    public String getDuration(){
        //actuellement la durée est un timestamp, on veut un affichage en min
        long millis = Long.parseLong(duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis));

        //une musique de 3 min 5 seconds serait afficher 3:5
        //avec cette ligne elle est afficher 3:05
        //seconds = (seconds>=0) ? ((seconds<10) ? Long.parseLong("0"+seconds) : seconds) : 0 ;
        //EDIT: la condition ternaire ne marche pas mais j'ai trouvé une autre solution -> %02

        String parseDuration = String.format("%d:%02d",
                minutes,
                seconds
        );
        return parseDuration;
    }
    public String getDurationTimestamp(){
        return duration;
    }
    //pas de setter car pas besoin d'ajouter de musique dans cette appli
}