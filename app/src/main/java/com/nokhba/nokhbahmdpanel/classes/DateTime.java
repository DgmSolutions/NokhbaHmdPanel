package com.nokhba.nokhbahmdpanel.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    public static String getDateTime() {
        SimpleDateFormat outputFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = outputFmt.format(new Date());
        return date;
    }
}
