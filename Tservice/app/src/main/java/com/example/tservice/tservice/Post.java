package com.example.tservice.tservice;

import java.util.Date;

/**
 * Created by MASANORI on 2016/11/30.
 *
 * 글 클래스
 */

public class Post {
    private int post_pk;
    private String title;
    private String name;
    private String place;
    private int cost;
    private String link;
    private String memo;
    private boolean sellout;
    private Date post_datetime;
    private int buyer_pk;

    // post_pk 있는 constractor
    public Post(int post_pk, String title, String name, String place, int cost,
                String link, String memo, boolean sellout, Date post_datetime){
        this.post_pk = post_pk;
        this.title = title;
        this.name = name;
        this.place = place;
        this.cost = cost;
        this.link = link;
        this.memo = memo;
        this.sellout = sellout;
        this.post_datetime = post_datetime;
    }

    // post_pk 없는 constractor
    public Post(String title, String name, String place, int cost,
                String link, String memo, boolean sellout, Date post_datetime){
        this.title = title;
        this.name = name;
        this.place = place;
        this.cost = cost;
        this.link = link;
        this.memo = memo;
        this.sellout = sellout;
        this.post_datetime = post_datetime;
    }

    public int getPost_pk() {
        return post_pk;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public int getCost() {
        return cost;
    }

    public String getLink() {
        return link;
    }

    public String getMemo() {
        return memo;
    }

    public boolean isSellout() {
        return sellout;
    }

    public Date getPost_datetime() {
        return post_datetime;
    }

    public int getBuyer_pk() {
        return buyer_pk;
    }
}
