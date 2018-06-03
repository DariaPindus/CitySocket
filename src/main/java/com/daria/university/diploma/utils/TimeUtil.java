package com.daria.university.diploma.utils;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimeUtil {

    public static Timestamp getCurrentTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp =new Timestamp(localDateTime.toEpochSecond(ZoneOffset.UTC));
        return timestamp;
    }

    public static Timestamp getDaysBefore(int nDays){
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(nDays);
        Timestamp timestamp =new Timestamp(localDateTime.toEpochSecond(ZoneOffset.UTC));
        return timestamp;
    }
}
