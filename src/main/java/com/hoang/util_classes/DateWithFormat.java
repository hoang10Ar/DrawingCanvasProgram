package com.hoang.util_classes;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateWithFormat {
    public static String getDateWithDayNameAndDateAndTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
        return formatter.format(new Date());
    }
}

