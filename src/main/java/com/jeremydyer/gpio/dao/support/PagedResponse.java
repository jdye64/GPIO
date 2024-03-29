package com.jeremydyer.gpio.dao.support;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 1/14/14
 * Time: 3:19 PM
 */
public class PagedResponse<T> {

    private List<T> items;
    private int totalPages;
    private long totalItems;

    public PagedResponse() {
        super();
    }

    public PagedResponse(List<T> items, int totalPages, int totalItems) {
        super();
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

}
