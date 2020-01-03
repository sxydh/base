package cn.net.bhe.utils.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Note that this tool class has a date format of yyyy-MM-dd HH:mm:ss and cannot
 * be changed.
 *
 * @author Administrator
 */
public enum DateUtils {
    ;
    static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
    static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        System.out.println(format(new Date(), "E"));
    }
    
    public static int weekOfMonth(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY); 
        calendar.setTime(strToDate(date));
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    public static String get() {
        String date = null;
        try {
            date = DF.format(new Date());
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return date;
    }

    public static Date strToDate(String date) {
        try {
            return DF.parse(date);
        } catch (ParseException e) {
            LOGGER.error(e.getLocalizedMessage());
            return null;
        }
    }

    public static String format(Date date) {
        if (date != null) {
            return DF.format(date);
        } else {
            return null;
        }
    }
    
    /**
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html">ref</a>
     */
    public static String format(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * Adds or subtracts the specified amount of time to the given calendar
     * field, based on the calendar's rules. For example, to subtract 5 days
     * from the current time of the calendar, you can achieve it by calling:
     *
     * <p>
     * <code>add(Calendar.DAY_OF_MONTH, -5)</code>
     * </p>
     *
     * @param field
     *            the calendar field
     * @param amount
     *            the amount of date or time to be added to the field
     * @return
     */
    public static String add(int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.add(field, amount);
        return DF.format(c.getTime());
    }
    
    public static String startDateOfWeek(LocalDate date, String dateFormat) {
        return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .format(DateTimeFormatter.ofPattern(dateFormat));
    }

    public static String endDateOfWeek(LocalDate date, String dateFormat) {
        return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
                .format(DateTimeFormatter.ofPattern(dateFormat));
    }
    
    public static String getWeek(Date date) {
        SimpleDateFormat weekf = new SimpleDateFormat("EEEE");
        return weekf.format(date);
    }
    
}
