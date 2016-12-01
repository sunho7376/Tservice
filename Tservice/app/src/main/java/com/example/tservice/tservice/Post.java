package com.example.tservice.tservice;

import java.util.Date;

/**
 * Created by MASANORI on 2016/11/30.
 *
 * 글 클래스
 */

public class Post {
    private int user_pk;
    private String title;
    private String name;
    private String place;
    private int cost;
    private String link;
    private String memo;
    private boolean sellout;
    private Date post_datetime;
    private int buyer_pk;

    public Post(int user_pk, String title, String name, String place, int cost,
                String link, String memo, boolean sellout, Date post_datetime){
        this.user_pk = user_pk;
        this.title = title;
        this.name = name;
        this.place = place;
        this.cost = cost;
        this.link = link;
        this.memo = memo;
        this.sellout = sellout;
        this.post_datetime = post_datetime;
    }
}
