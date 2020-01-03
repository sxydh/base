package fun.ehe.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Note that this tool class has a date format of yyyy-MM-dd HH:mm:ss and cannot
 * be changed.
 *
 * @author Administrator
 */
public abstract class MyDateUtils {

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        T.t(null, add(Calendar.DAY_OF_MONTH, 2));
    }

    public static String get() {
        String date = null;
        try {
            date = df.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date strToDate(String date) {
        try {
            return df.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String format(Date date) {
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
    public static String add(Integer field, Integer amount) {
        Calendar c = Calendar.getInstance();
        c.add(field, amount);
        return df.format(c.getTime());
    }

}
