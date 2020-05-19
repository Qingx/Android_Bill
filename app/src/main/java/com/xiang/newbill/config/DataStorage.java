package com.xiang.newbill.config;

import com.xiang.newbill.entity.TypeEntity;

import java.util.List;

import cn.wl.android.lib.config.BaseConfig;

/**
 * Created by Xiang on 2020/5/9 15:35
 *
 * @email Cymbidium@outlook.com
 */
public class DataStorage extends BaseConfig {
    private static DataStorage ds;
    public List<TypeEntity> list;

    private interface KEY {
        String KEY_TYPE_LIST = "KEY_TYPE_LIST";
    }

    public DataStorage() {
        super("data_storage");
    }

    public static DataStorage get() {
        if (ds == null) {
            ds = new DataStorage();
        }
        return ds;
    }

    public List<TypeEntity> getList() {
        return list;
    }

    public void setList(List<TypeEntity> list) {
        this.list = list;
    }
}
