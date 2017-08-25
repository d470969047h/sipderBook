package com.daihui.Model;

import java.util.List;


public class Video {

    private String name;

    private String url;

    private int state;

    private List<VideoCatelog> catelogList;

    public List<VideoCatelog> getCatelogList() {
        return catelogList;
    }

    public void setCatelogList(List<VideoCatelog> catelogList) {
        this.catelogList = catelogList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


}
