package com.xiang.newbill.config;

import com.blankj.utilcode.util.TimeUtils;

import java.util.Date;

/**
 * Created by Xiang on 2020/3/28 10:29
 *
 * @email Cymbidium@outlook.com
 */
public class DateFormat {

    public static final String DATEFORMAT_yyyy_MM_dd_HHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String DATEFORMAT_HHmmss = "HH:mm:ss";
    public static final String DATEFORMAT_yyyy_MM_dd_HHmm = "yyyy-MM-dd HH:mm";
    public static final String DATEFORMAT_yyyyMMdd_HHmm = "yyyyMMdd HH:mm";
    public static final String DATEFORMAT_yyyyzMMzddz_HHmm = "yyyy年MM月dd日 HH:mm";
    public static final String DATEFORMAT_yyyyzMMzddz = "yyyy年MM月dd日";
    public static final String DATEFORMAT_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String DATEFORMAT_yyyyMMdd = "yyyyMMdd";
    public static final String DATEFORMAT_yyyyMMdd2 = "yyyy.MM.dd";
    public static final String DATEFORMAT_yMd_Hm = "yyyy.MM.dd HH:mm";
    public static final String DATEFORMAT_MM_dd_HHmm = "yyyy-MM-dd HH:mm";
    public static final String DATEFORMAT_MMz_ddz_HHmm = "MM月dd日 HH:mm";
    public static final String DATEFORMAT_MMdd = "MMdd";
    public static final String DATEFORMAT_yyyy = "yyyy";
    public static final String DATEFORMAT_mm = "MM";
    public static final String DATEFORMAT_dd = "dd";
    public static final String DATEFORMAT_HHmm = "HH:mm";
    public static final String DATEFORMAT_MM_dd = "MM-dd";
    public static final String DATEFORMAT_MM_dd_HH_mm = "MM-dd HH:mm";
    public static final String DATEFORMAT_MMz_ddz = "MM月dd日";


    public final static long SCEND_1 = 1000;// 1秒钟
    public final static long MINUTE_1 = 60 * 1000;// 1分钟
    public final static long MINUTE_5 = 5 * MINUTE_1;// 5分钟
    public final static long HOUR_1 = 60 * MINUTE_1;// 1小时
    public final static long DAY_1 = 24 * HOUR_1;// 1天
    public final static long WEEK_1 = 7 * DAY_1; // 1周
    public final static long MONTH_1 = 31 * DAY_1;// 月
    public final static long YEAR_1 = 12 * MONTH_1;// 年

    /**
     * 格式化时间为 yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String date2YY_MM_dd_HH_mm_ss(long time) {
        return TimeUtils.date2String(new Date(time),
                DATEFORMAT_yyyy_MM_dd_HHmmss);
    }

    public static String date2YY_MM_DD(long time) {
        return TimeUtils.date2String(new Date(time),
                DATEFORMAT_yyyy_MM_dd);
    }

    /**
     * 格式化时间为yyyy年mm月dd天 HH:MM
     *
     * @param time
     * @return
     */
    public static String yearMonthDayTime(long time) {
        return TimeUtils.date2String(new Date(time),
                DATEFORMAT_yyyyzMMzddz_HHmm);
    }

    /**
     * 格式化时间为mm月dd日
     *
     * @param time
     * @return
     */
    public static String monthDay(long time) {
        return TimeUtils.date2String(new Date(time), DATEFORMAT_MMz_ddz);
    }

    /**
     * 格式化时间为yyyy
     *
     * @param time
     * @return
     */
    public static String dateToYear(long time) {
        return TimeUtils.date2String(new Date(time), DATEFORMAT_yyyy);
    }

    /**
     * 格式化时间为mm
     *
     * @param time
     * @return
     */
    public static String dateToMonth(long time) {
        return TimeUtils.date2String(new Date(time), DATEFORMAT_mm);
    }

    /**
     * 格式化时间为dd
     *
     * @param time
     * @return
     */
    public static String dateToDay(long time) {
        return TimeUtils.date2String(new Date(time), DATEFORMAT_dd);
    }
}
