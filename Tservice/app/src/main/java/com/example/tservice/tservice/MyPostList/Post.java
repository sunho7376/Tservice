package com.example.tservice.tservice.MyPostList;

import java.util.Date;

/**
 * Created by MASANORI on 2016/11/30.
 *
 * 글 클래스
 */

public class Post {
    private int post_pk; // 글 Table ID
    private String title; // 글 제목
    private String name; // 공연 이름
    private String place; // 공연 장소
    private String show_datetime; // 공연 날짜
    private int cost; // 공연 가격
    private String link; // 공연 사이트 링크
    private String memo; // 글 작성자 메모
    private String sellout; // 팔이면 true, 판매중이면 false
    private String post_datetime; // 글 작성한 날짜
    private int buyer_pk; // 구매자 Table ID

    // post_pk 있는 constractor
    public Post(int post_pk, String title, String name, String place, String show_datetime , int cost,
                String link, String memo, String sellout, String post_datetime){
        this.post_pk = post_pk;
        this.title = title;
        this.name = name;
        this.place = place;
        this.show_datetime = show_datetime;
        this.cost = cost;
        this.link = link;
        this.memo = memo;
        this.sellout = sellout;
        this.post_datetime = post_datetime;
    }

    // post_pk 없는 constractor
    public Post(String title, String name, String show_datetime, String place, int cost,
                String link, String memo, String sellout, String post_datetime){
        this.title = title;
        this.name = name;
        this.show_datetime = show_datetime;
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

    public String getShow_datetime() {
        return show_datetime;
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

    public String isSellout() {
        return sellout;
    }

    public String getPost_datetime() {
        return post_datetime;
    }

    public int getBuyer_pk() {
        return buyer_pk;
    }
}
