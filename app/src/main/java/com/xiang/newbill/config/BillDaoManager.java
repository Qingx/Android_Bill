package com.xiang.newbill.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.greendao.gen.BillEntityDao;
import com.greendao.gen.DaoMaster;
import com.greendao.gen.DaoSession;
import com.xiang.newbill.entity.BillEntity;
import com.xiang.newbill.entity.TimeEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Xiang on 2020/5/14 14:58
 *
 * @email Cymbidium@outlook.com
 */
public class BillDaoManager {
    private DaoMaster.DevOpenHelper helper;
    private Context context;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private BillEntityDao billDao;

    private static BillDaoManager billDaoManager;

    public static BillDaoManager getInstance(Context context) {
        if (billDaoManager == null) {
            synchronized (BillDaoManager.class) {
                if (billDaoManager == null) {
                    billDaoManager = new BillDaoManager(context);
                }
            }
        }
        return billDaoManager;
    }

    private BillDaoManager(Context context) {
        this.context = context;

        helper = new DaoMaster.DevOpenHelper(context, "bill_db", null);
        daoMaster = new DaoMaster(getWritableDatabase());
        daoSession = daoMaster.newSession();
        billDao = daoSession.getBillEntityDao();
    }

    private SQLiteDatabase getWritableDatabase() {
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(context, "bill_db", null);
        }
        return helper.getWritableDatabase();
    }

    public void insertBill(BillEntity billEntity) {
        billDao.insert(billEntity);
    }

    public void updateBill(Long time, String date, String remark, String type, Double money) {
        BillEntity billEntity = billDao.queryBuilder().where(BillEntityDao.Properties.Time.eq(time)).build().unique();
        billEntity.setDate(date);
        billEntity.setRemark(remark);
        billEntity.setType(type);
        billEntity.setMoney(money);
        billDao.update(billEntity);
    }

    public void deleteBill(Long time) {
        BillEntity billEntity = billDao.queryBuilder().where(BillEntityDao.Properties.Time.eq(time)).build().unique();
        billDao.delete(billEntity);
    }


    public List<TimeEntity> findTimeList(String date) {
        List<BillEntity> billEntityList = billDao.queryBuilder().where(BillEntityDao.Properties.Date.like("%" + date + "%")).build().list();
        List<TimeEntity> timeEntityList = new ArrayList<>();
        billEntityList.forEach(billEntity -> timeEntityList.add(new TimeEntity(billEntity.getDate())));

        Set<TimeEntity> set = new HashSet<>(timeEntityList);
        timeEntityList.clear();
        timeEntityList.addAll(set);
        Collections.sort(timeEntityList);

        return timeEntityList;
    }

    public Double findSumMoney(String date) {
        final double[] money = {0.0};
        List<BillEntity> billEntityList = billDao.queryBuilder().where(BillEntityDao.Properties.Date.like("%" + date + "%")).build().list();
        billEntityList.forEach(billEntity -> money[0] = Tools.Companion.doubleAdd(money[0], billEntity.getMoney()));

        return money[0];
    }

    public List<BillEntity> findBillList(String date) {
        List<BillEntity> list = billDao.queryBuilder().where(BillEntityDao.Properties.Date.eq(date)).build().list();
        Collections.sort(list);

        return list;
    }

    public BillEntity findDetailByTime(Long time) {
        return billDao.queryBuilder().where(BillEntityDao.Properties.Time.eq(time)).build().unique();
    }
}
