package com.example.abhishek.rajasthane_paryatanam;

import android.graphics.Bitmap;

/**
 * Created by ABHISHEK on 12/02/2017.
 */
public class ListData {
    String Description;
    String title;
    Bitmap imgResId;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImgResId() {
        return imgResId;
    }

    public void setImgResId(Bitmap imgResId) {
        this.imgResId = imgResId;
    }
}
