package eu.codlab.chat.utils;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateUtils {

    private static DateTimeFormatter YYYYMMDD = DateTimeFormat.forPattern("yyyyMMdd");

    private DateUtils() {

    }

    public static int getYYYYMMDD(@NonNull Date date) {
        DateTime dateTime = new DateTime(date);
        return Integer.parseInt(dateTime.toString(YYYYMMDD));
    }
}
