package com.wordUtil.bean;

import java.util.List;

//表头信息,一个表头就是一个列 th
public class WordTableHeaderVO {
    // 列信息
    private List<WordTableColumnVO> columns;

    public List<WordTableColumnVO> getColumns() {
        return columns;
    }

    public void setColumns(List<WordTableColumnVO> columns) {
        this.columns = columns;
    }
}