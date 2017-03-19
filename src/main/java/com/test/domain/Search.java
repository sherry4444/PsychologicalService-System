package com.test.domain;

/**
 * Created by Administrator on 2017/3/15.
 */
public class Search {
    private String title;
    private int currentPage;
    private Integer flag;
    private  Integer num;

    public Search() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Search{" +
                "title='" + title + '\'' +
                ", currentPage=" + currentPage +
                ", flag=" + flag +
                ", num=" + num +
                '}';
    }
}
