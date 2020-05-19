package com.xiang.newbill.entity;

import java.util.Objects;

/**
 * Created by Xiang on 2020/5/14 13:39
 *
 * @email Cymbidium@outlook.com
 */
public class TimeEntity implements Comparable<TimeEntity> {
    private String date;
    private Long time;

    public TimeEntity(String date) {
        this.date = date;
        String a=date.substring(0,10);
        a=a.replace("年", "");
        a=a.replace("月", "");
        a=a.replace("日", "");
        this.time = Long.valueOf(a);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public int compareTo(TimeEntity o) {
        return o.getTime().compareTo(this.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntity that = (TimeEntity) o;
        return date.equals(that.date) &&
                time.equals(that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time);
    }
}
