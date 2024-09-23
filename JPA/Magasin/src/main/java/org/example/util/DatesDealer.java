package org.example.util;

import java.util.Calendar;
import java.util.Date;

public class DatesDealer {

    public static Date getNextWeekDate(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        cal.add(Calendar.DATE, 6);
        Date nextWeekDate = cal.getTime();
        return nextWeekDate;
    }

    public static Date getTodaysDate(){
        return Calendar.getInstance().getTime();
    }


}
