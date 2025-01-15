package com.ccamargo.mathchallenger.domain.model;

import java.util.List;

public class Paginator<T> {
    private List<T> content;
    private int totalElements;
    private int totalPages;
    private int currentPage;
    private int pageSize;

    public Paginator(List<T> content, int totalElements, int totalPages, int currentPage, int pageSize) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public List<T> getContent() {
        return content;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }
}
