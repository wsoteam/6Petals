package com.wsoteam.diet.POJOForDB;

import com.orm.SugarRecord;

public class ObjectForNotification extends SugarRecord {
    private int minute;
    private int hour;
    private int day;
    private int month;
    private int year;
    private String text;
    private String repeat;

    public ObjectForNotification() {
    }

    public ObjectForNotification(int minute, int hour, int day, int month, int year, String text, String repeat) {
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
        this.text = text;
        this.repeat = repeat;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }
}
