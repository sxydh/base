package cn.net.bhe.basics.date;

import java.util.Calendar;
import static cn.net.bhe.utils.main.PrintUtils.*;

import org.junit.jupiter.api.Test;

public class CalendarTest {

    @Test
    public void add() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        pln(calendar.getTime());
    }
    
}
