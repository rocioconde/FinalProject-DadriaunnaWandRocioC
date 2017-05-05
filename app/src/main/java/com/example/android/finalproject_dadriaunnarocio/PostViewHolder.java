package com.example.android.finalproject_dadriaunnarocio;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ccteuser on 5/3/17.
 */

public class PostViewHolder extends RecyclerView.ViewHolder {


    private CardView cardView;
    private ImageView postPhoto;
    private TextView postDescription;
    private Context context;


    public PostViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view_post);
        postPhoto = (ImageView) itemView.findViewById(R.id.card_view_post_photo);
        postDescription = (TextView) itemView.findViewById(R.id.card_view_post_description);
        this.context = itemView.getContext();
    }

    public void bind(final Post post) {

        postDescription.setText(String.valueOf(post.postDescription));
        postPhoto.setImageBitmap(ImageUtil.byteStringToBitmap(post.postPhotoStr));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, AddNewPost.class);
                intent.putExtra(Keys.POST, post);
                context.startActivity(intent);
            }

        });

    }
}

