package com.rgarcia.w2m.common.dto;

import java.util.List;

public class PaginationDto<T> {

    List<T> data;
    long total = 0;
    int page =1;
    int limit = 10;
    boolean hasNext=false;
    boolean hasPrev=false;
    int next=1;
    int prev=1;
    int pages=1;
    int last=1;
    int first=1;

    public PaginationDto() {}

    public PaginationDto(List<T> data,
                   long total,
                   int page,
                   int limit) {

        this.data = data;
        this.limit = limit;
        this.page = page;
        this.total = total;

        createPagination();

    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
        createPagination();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }



    void createPagination() {
        if (page==1) {
            hasPrev=false;
            prev = 1;
        }

        if (page > 1) {
            hasPrev=true;
            prev = page -1;
        }

        if (page * limit >=total) {
            hasNext=false;
            next = page;
        }
        if (page * limit < total) {
            hasNext=true;
            next = page +1;
        }

        pages = (int) Math.ceil(total / (double)limit);
        last = pages;
    }
}
