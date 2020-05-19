package com.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.xiang.newbill.entity.BillEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BILL_ENTITY".
*/
public class BillEntityDao extends AbstractDao<BillEntity, Long> {

    public static final String TABLENAME = "BILL_ENTITY";

    /**
     * Properties of entity BillEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Time = new Property(0, Long.class, "time", true, "_id");
        public final static Property Date = new Property(1, String.class, "date", false, "DATE");
        public final static Property Remark = new Property(2, String.class, "remark", false, "REMARK");
        public final static Property Type = new Property(3, String.class, "type", false, "TYPE");
        public final static Property Money = new Property(4, Double.class, "money", false, "MONEY");
    }


    public BillEntityDao(DaoConfig config) {
        super(config);
    }
    
    public BillEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BILL_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: time
                "\"DATE\" TEXT," + // 1: date
                "\"REMARK\" TEXT," + // 2: remark
                "\"TYPE\" TEXT," + // 3: type
                "\"MONEY\" REAL);"); // 4: money
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BILL_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BillEntity entity) {
        stmt.clearBindings();
 
        Long time = entity.getTime();
        if (time != null) {
            stmt.bindLong(1, time);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(2, date);
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(3, remark);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(4, type);
        }
 
        Double money = entity.getMoney();
        if (money != null) {
            stmt.bindDouble(5, money);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BillEntity entity) {
        stmt.clearBindings();
 
        Long time = entity.getTime();
        if (time != null) {
            stmt.bindLong(1, time);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(2, date);
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(3, remark);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(4, type);
        }
 
        Double money = entity.getMoney();
        if (money != null) {
            stmt.bindDouble(5, money);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BillEntity readEntity(Cursor cursor, int offset) {
        BillEntity entity = new BillEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // time
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // date
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // remark
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // type
            cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4) // money
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BillEntity entity, int offset) {
        entity.setTime(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDate(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setRemark(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMoney(cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BillEntity entity, long rowId) {
        entity.setTime(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BillEntity entity) {
        if(entity != null) {
            return entity.getTime();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BillEntity entity) {
        return entity.getTime() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
