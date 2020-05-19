package com.xiang.newbill.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Xiang on 2020/5/14 13:27
 *
 * @email Cymbidium@outlook.com
 */
@Entity
public class BillEntity implements Comparable<BillEntity> {
    @Id
    private Long time;
    @Property(nameInDb = "DATE")
    private String date;
    @Property(nameInDb = "REMARK")
    private String remark;
    @Property(nameInDb = "TYPE")
    private String type;
    @Property(nameInDb = "MONEY")
    private Double money;

    @Generated(hash = 1061464716)
    public BillEntity(Long time, String date, String remark, String type,
                      Double money) {
        this.time = time;
        this.date = date;
        this.remark = remark;
        this.type = type;
        this.money = money;
    }

    @Generated(hash = 635178700)
    public BillEntity() {
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMoney() {
        return this.money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public int compareTo(BillEntity o) {
        return o.getTime().compareTo(this.getTime());
    }
}
