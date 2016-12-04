package com.example.tservice.tservice.MyPostList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tservice.tservice.R;

import java.util.ArrayList;

/**
 * Created by MASANORI on 2016/12/04.
 */

public class MyPostAdapter extends RecyclerView.Adapter<MyPostViewHolder>{

    private ArrayList<Post> posts;

    public MyPostAdapter(ArrayList<Post> myposts){
        this.posts = myposts;
    }

    // 세로운 View를 만듬
    @Override
    public MyPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myposts_layout, parent, false);

        return new MyPostViewHolder(view);
    }

    // View의 내용을 교환한다
    @Override
    public void onBindViewHolder(final MyPostViewHolder holder,final int position) {

        holder.tviewTitle.setText("글 제목:" + posts.get(position).getTitle());
        holder.tviewPerName.setText(" 공연 이름:" + posts.get(position).getName());
        holder.tviewPerDate.setText(" 공연 날짜:" + posts.get(position).getShow_datetime());
        holder.tviewCost.setText(" 가격:" + posts.get(position).getCost());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
