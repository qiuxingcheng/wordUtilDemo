package com.wordUtil.bean;

import java.util.List;

//导出VO
public class ExportVO {

    private String name;
    private Double xxxScore;
    private WordTableHeaderVO header;
    private List<WordTableRowVO> rows;

    public static ExportVO newInstance(){
        ExportVO exportVO = new ExportVO();
        return  exportVO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getXxxScore() {
        return xxxScore;
    }

    public void setXxxScore(Double xxxScore) {
        this.xxxScore = xxxScore;
    }

    public WordTableHeaderVO getHeader() {
        return header;
    }

    public void setHeader(WordTableHeaderVO header) {
        this.header = header;
    }

    public List<WordTableRowVO> getRows() {
        return rows;
    }

    public void setRows(List<WordTableRowVO> rows) {
        this.rows = rows;
    }
}