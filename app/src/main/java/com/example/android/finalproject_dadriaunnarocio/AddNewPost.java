package com.example.android.finalproject_dadriaunnarocio;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.UUID;

import static com.example.android.finalproject_dadriaunnarocio.R.id.school;

public class AddNewPost extends AppCompatActivity {

    private ImageView postPhoto;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private EditText postDescription;
    private static final int REQUEST_PICK_PHOTO = 111;
    private DatabaseReference studentRef = database.getReference(auth.getCurrentUser().getUid());
    private DatabaseReference postRef = database.getReference(auth.getCurrentUser().getUid() + "/post");
    private Post post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);

        postPhoto = (ImageView) findViewById(R.id.post_photo);
        postDescription = (EditText) findViewById(R.id.post_description);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_new_post, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //add the save and delete functions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_save:
                if (post == null) {
                    String id = UUID.randomUUID().toString();
                    Random random = new Random();
                    String postDescription1 = postDescription.getText().toString();
                    String postPhotoStr = (ImageUtil.bitmapToByteString(((BitmapDrawable) postPhoto.getDrawable()).getBitmap()));

                    postRef.child(id).setValue(new Post(id, postDescription1, postPhotoStr));
                } else {
                    savePost();
                }

                Intent intent = new Intent(this, StudentProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_item_delete:
                if (post != null)
                    postRef.child(post.id).removeValue();
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);


        }
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


        String postText = postDescription.getText().toString();
        String postPhotoStr = (ImageUtil.bitmapToByteString(((BitmapDrawable) postPhoto.getDrawable()).getBitmap()));

        studentRef.child("post").push().setValue(new Post(postPhotoStr, postText));

        Intent intent = new Intent(this, StudentProfileActivity.class);
        startActivity(intent);

    }

    public void takePicture(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void savePost() {

        post.postDescription = postDescription.getText().toString();
        post.postPhotoStr = (ImageUtil.bitmapToByteString(((BitmapDrawable) postPhoto.getDrawable()).getBitmap()));
        postRef.child(post.id).setValue(school);
    }
}
