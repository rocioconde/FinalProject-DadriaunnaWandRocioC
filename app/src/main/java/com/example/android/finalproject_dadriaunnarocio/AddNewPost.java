package com.example.android.finalproject_dadriaunnarocio;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class AddNewPost extends AppCompatActivity {

    private ImageView postPhoto;
    private EditText postDescription;
    private static final int REQUEST_PICK_PHOTO = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);

        postPhoto = (ImageView) findViewById(R.id.post_photo);
        postDescription = (EditText) findViewById(R.id.post_description);

//        Post post = new Post(R.drawable.cheeseburger, "This is so amazing omg!");
//        Intent intent = new Intent();
//        intent.getSerializableExtra(Keys.POST);
//        postPhoto.setImageResource(post.postPhotoID);
//        postDescription.setText(post.postDescription);
    }

    public void selectImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_PICK_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;

        if (requestCode == REQUEST_PICK_PHOTO) {
            Uri photoUrl = data.getData();
            try {
                decodeUri(photoUrl);
            } catch (FileNotFoundException e) {
                Toast.makeText(this, "Error decoding photo", Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void decodeUri(Uri uri) throws FileNotFoundException {


        BitmapFactory.Options bmOptions = new BitmapFactory.Options();

        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = (int) Math.ceil(Math.min(photoW / 500, photoH / 500));

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;


        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, bmOptions);
        postPhoto.setImageBitmap(bitmap);
    }

    public void addPostToProfile(View view) {

        postPhoto = (ImageView) findViewById(R.id.post_photo);
        postDescription = (EditText) findViewById(R.id.post_description);

        String postText = String.valueOf(postDescription);
        String postPhoto1 = String.valueOf(postPhoto);
        int postPhoto2 = Integer.parseInt(postPhoto1);

        Post post = new Post(R.drawable.cheespizza, postText);
        Intent intent = new Intent(this, StudentProfileActivity.class);
        intent.getSerializableExtra(Keys.POST);
        startActivity(intent);

    }


    public void takePicture(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
