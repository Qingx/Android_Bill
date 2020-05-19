package com.xiang.newbill.entity;

/**
 * Created by Xiang on 2020/5/9 12:39
 *
 * @email Cymbidium@outlook.com
 */
public class TypeEntity {
    private String type;
    private int imageId;
    private String tag;

    public TypeEntity(String type, int imageId, String tag) {
        this.type = type;
        this.imageId = imageId;
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
