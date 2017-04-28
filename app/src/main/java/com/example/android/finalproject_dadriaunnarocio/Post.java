package com.example.android.finalproject_dadriaunnarocio;

import java.io.Serializable;

/**
 * Created by ccteuser on 4/28/17.
 */

public class Post implements Serializable {

    public int postPhotoID;
    public String postDescription;

    public Post(int postPhotoID, String postDescription) {
        this.postPhotoID = postPhotoID;
        this.postDescription = postDescription;
    }

    public Post() {

    }

    public int getPostPhotoID() {
        return postPhotoID;
    }

    public void setPostPhotoID(int postPhotoID) {
        this.postPhotoID = postPhotoID;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }
}
