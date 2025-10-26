package com.iam.service.mapper;

public class PageOption {
    private int startIndex;
    private int endIndex;
    private String sortBy;
    private String sortOder;

    public PageOption(int startIndex, int endIndex, String sortBy, String sortOder) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.sortBy = sortBy;
        this.sortOder = sortOder;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortOder() {
        return sortOder;
    }

    public void setSortOder(String sortOder) {
        this.sortOder = sortOder;
    }
}
