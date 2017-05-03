package com.example.android.finalproject_dadriaunnarocio;

import java.io.Serializable;

/**
 * Created by ccteuser on 4/28/17.
 */

public class Post implements Serializable {

    public String postPhotoStr;
    public String postDescription;

    public Post(String postPhotoStr, String postDescription) {
        this.postPhotoStr = postPhotoStr;
        this.postDescription = postDescription;
    }

    public Post() {

    }

    public String getPostPhotoStr() {
        return postPhotoStr;
    }

    public void setPostPhotoID(String postPhotoStr) {
        this.postPhotoStr = postPhotoStr;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }
}
