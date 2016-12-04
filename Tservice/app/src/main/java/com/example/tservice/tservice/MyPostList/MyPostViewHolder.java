package com.example.tservice.tservice.MyPostList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.tservice.tservice.R;

/**
 * Created by MASANORI on 2016/12/04.
 */

public class MyPostViewHolder extends RecyclerView.ViewHolder {

    public View base;
    public TextView tviewTitle;
    public TextView tviewPerName;
    public TextView tviewPerDate;
    public TextView tviewCost;

    public MyPostViewHolder(View itemView) {
        super(itemView);

        this.base = itemView;
        this.tviewTitle = (TextView) itemView.findViewById(R.id.tviewTitle);
        this.tviewPerDate = (TextView) itemView.findViewById(R.id.tviewPerName);
        this.tviewPerDate = (TextView) itemView.findViewById(R.id.tviewPerDate);
        this.tviewCost = (TextView) itemView.findViewById(R.id.tviewCost);
    }
}
