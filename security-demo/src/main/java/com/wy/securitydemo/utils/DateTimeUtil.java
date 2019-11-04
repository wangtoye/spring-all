package com.wy.securitydemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理类
 *
 * @author YANG
 */
public class DateTimeUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeUtil.class);

    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT = "yyyy-MM-dd";


    /**
     * 根据格式得到格式化后的日期
     *
     * @param date   要格式化的日期
     * @param format 日期格式，如yyyy-MM-dd
     * @return String 返回格式化后的日期，格式由参数<code>format</code>
     * 定义，如yyyy-MM-dd，如2006-02-15
     * @see SimpleDateFormat#format(Date)
     */
    public static String getFormatDate(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat;
        try {
            dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        } catch (Exception e) {
            try {
                dateFormat = new SimpleDateFormat(DATE_FORMAT);
                return dateFormat.format(date);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    /**
     * 根据格式得到格式化后的时间
     *
     * @param currDate 要格式化的时间
     * @param format   时间格式，如yyyy-MM-dd HH:mm:ss
     * @return Date 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd
     * HH:mm:ss
     * @see SimpleDateFormat#parse(String)
     */
    public static Date getFormatDate(String currDate, String format) {
        if (currDate == null) {
            return null;
        }
        SimpleDateFormat dateFormat;
        try {
            dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(currDate);
        } catch (Exception e) {
            try {
                dateFormat = new SimpleDateFormat(DATE_FORMAT);
                return dateFormat.parse(currDate);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    /**
     * 得到系统当前日期的前或者后几天/月/小时/秒
     *
     * @param iDate 如果要获得当前时间前日期，该参数为负数； 如果要获得当前时间后日期，该参数为正数
     * @return Date
     * @see Calendar#add(int, int)
     */
    public static Date getDateBeforeOrAfter(int iDate, int type) {
        Calendar cal = Calendar.getInstance();
        cal.add(type, iDate);
        return cal.getTime();
    }


    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

            return Integer.parseInt(String.valueOf(betweenDays));
        } catch (Exception e) {
            LOGGER.error("parse date Exception", e);
            return 0;
        }
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            long betweenDays = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(betweenDays));
        } catch (Exception e) {
            LOGGER.error("parse date Exception", e);
            return 0;
        }
    }
}