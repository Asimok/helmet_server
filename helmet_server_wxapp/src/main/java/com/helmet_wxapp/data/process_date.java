package com.helmet_wxapp.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class process_date {
    //将时间转换为时间戳
    public static boolean check_dateToStamp(String begin, String end, String check_date_String) throws ParseException {
        long check_date = Long.parseLong(check_date_String);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date begin_date = simpleDateFormat.parse(begin);
        Date end_date = simpleDateFormat.parse(end);
        long begin_ts = begin_date.getTime();
        long end_ts = end_date.getTime();
        if (check_date > begin_ts && check_date < end_ts) {
//            System.out.println("合法");
            return true;
        } else {
//            System.out.println("不合法");
            return false;
        }

    }

//    public  static void main(String args[])
//    {
//        try {
//            if(check_dateToStamp("2020-5-20 00:00","2020-7-20 00:00","1591287966530"))
//            {
//                System.out.println("合法");
//            }
//            else System.out.println("不合法");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
}
