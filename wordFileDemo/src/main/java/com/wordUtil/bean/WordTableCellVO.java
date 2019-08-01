package com.wordUtil.bean;

//表格的单元格信息,一个单元格就是一个 td
public class WordTableCellVO {
    // 文本
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}