package com.example.android.finalproject_dadriaunnarocio;

import android.content.Context;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import java.util.List;

/**
 * Created by ccteuser on 5/3/17.
 */

public class PostAdapter extends FirebaseRecyclerAdapter<Post, PostViewHolder> {

    private List<Post> posts;
    private Context context;


//
//    public PostAdapter(List<Post> posts, Context context) {
//        this.posts = posts;
//        this.context = context;
//
//    }


    public PostAdapter(Query ref) {
        super(Post.class, R.layout.card_view_post, PostViewHolder.class, ref);
    }

    @Override
    protected void populateViewHolder(PostViewHolder viewHolder, Post post, int position) {
        viewHolder.bind(post);
    }

//    @Override
//    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_post, parent);
//        return new PostViewHolder(view, context);
//    }
//
//    @Override
//    public void onBindViewHolder(PostViewHolder holder, int position) {
//        Post post = posts.get(position);
//        holder.bind(post);
//    }
//
//    @Override
//    public int getItemCount() {
//        return posts.size();
//    }
}
