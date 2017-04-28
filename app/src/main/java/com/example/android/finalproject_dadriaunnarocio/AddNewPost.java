package com.example.android.finalproject_dadriaunnarocio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddNewPost extends AppCompatActivity {

    private ImageView postPhoto;
    private EditText postDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);

        postPhoto = (ImageView) findViewById(R.id.post_photo);
        postDescription = (EditText) findViewById(R.id.post_description);

        Post post = new Post(R.drawable.cheeseburger, "This is so amazing omg!");
        Intent intent = new Intent();
        intent.getSerializableExtra(Keys.POST);
        postPhoto.setImageResource(post.postPhotoID);
        postDescription.setText(post.postDescription);
    }


    public void addPostToProfile(View view) {


    }
}
