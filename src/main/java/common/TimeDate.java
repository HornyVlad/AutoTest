package common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeDate {
    static public String getDateNowFormat(String pattern) {
        return new SimpleDateFormat(pattern).format(Calendar.getInstance().getTime());
    }
}
