package com.example.recyclerviewwithpaging.model;

import java.util.List;

public class StackApiResponse {
    private String quota_max;

    private String quota_remaining;

    private boolean has_more;

    private List<Item> items;

    public StackApiResponse(String quota_max, String quota_remaining, boolean has_more, List<Item> items) {
        this.quota_max = quota_max;
        this.quota_remaining = quota_remaining;
        this.has_more = has_more;
        this.items = items;
    }

    public String getQuota_max() {
        return quota_max;
    }

    public void setQuota_max(String quota_max) {
        this.quota_max = quota_max;
    }

    public String getQuota_remaining() {
        return quota_remaining;
    }

    public void setQuota_remaining(String quota_remaining) {
        this.quota_remaining = quota_remaining;
    }

    public boolean getHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
