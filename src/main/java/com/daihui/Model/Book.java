package com.daihui.Model;

import java.util.List;


public class Book {

    private String uuid;

    private String name;

    private String url;

    private int state;

    private List<Catelog> catelogList;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public List<Catelog> getCatelogList() {
        return catelogList;
    }

    public void setCatelogList(List<Catelog> catelogList) {
        this.catelogList = catelogList;
    }
}
