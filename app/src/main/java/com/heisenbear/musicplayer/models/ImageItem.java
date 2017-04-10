package com.heisenbear.musicplayer.models;

import android.graphics.Bitmap;

public class ImageItem {

    public enum Type {
        VIDEO, PHOTO
    }

    private int id;
    private Bitmap image;
    private Type type;
    private String duration = "00:00:00";
    private String path;

    public ImageItem(Bitmap thumbnail, Type type, String duration, String path) {
        this(thumbnail, type, path);
        this.duration = duration;
    }

    public ImageItem( Bitmap thumbnail, Type type, String path) {
        this.image = thumbnail;
        this.type = type;
        this.path = path;
    }

    public Bitmap getImage() {
        return image;
    }

    public Type getType() {
        return type;
    }

    public String getDuration() {
        return duration;
    }

    public String getPath() {
        return path;
    }
}
