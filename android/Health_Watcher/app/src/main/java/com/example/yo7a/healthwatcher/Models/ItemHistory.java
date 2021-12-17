package com.example.yo7a.healthwatcher.Models;

import java.util.List;

public class ItemHistory {
    private String time;
    private List<SubItemHistory> list;

    public ItemHistory(String time, List<SubItemHistory> list) {
        this.time = time;
        this.list = list;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<SubItemHistory> getList() {
        return list;
    }

    public void setList(List<SubItemHistory> list) {
        this.list = list;
    }
}
