package com.wordUtil.bean;

import java.util.*;

//表格行信息,一个行就是一个 tr
public class WordTableRowVO {
    // 索引
    private int index = 0;
    // 单元格
    private List<WordTableCellVO> cells;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<WordTableCellVO> getCells() {
        return cells;
    }

    public void setCells(List<WordTableCellVO> cells) {
        this.cells = cells;
    }
}