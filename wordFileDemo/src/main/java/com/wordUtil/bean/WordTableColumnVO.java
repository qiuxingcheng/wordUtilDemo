package com.wordUtil.bean;

//表头信息,一个表头就是一个列 th
public class WordTableColumnVO {
    // 宽度
    private int width;
    // 名称
    private String title;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}