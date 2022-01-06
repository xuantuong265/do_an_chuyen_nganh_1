package com.example.yo7a.healthwatcher.Models;

public class Covid {
    private int cases;
    private int deaths;
    private int recovered;
    private int todayCases;
    private int todayDeaths;
    private int todayRecovered;
    private int active;
    private int critical;

    public Covid(double aCase, double deaths, double recovered, double todayCases, double todayDeaths, double todayRecovered, double active, double critical) {
    }

    public Covid(int cases, int deaths, int recovered, int todayCases, int todayDeaths, int todayRecovered, int active, int critical) {
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
        this.todayCases = todayCases;
        this.todayDeaths = todayDeaths;
        this.todayRecovered = todayRecovered;
        this.active = active;
        this.critical = critical;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(int todayCases) {
        this.todayCases = todayCases;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(int todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public int getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(int todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }
}
