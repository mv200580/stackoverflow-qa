import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * Get third friday of a month
 * http://stackoverflow.com/questions/39528331/get-third-friday-of-a-month
 *
 * Created by smv on 19/09/16.
 */
public class NweekdayOfMonthTest {

    @Test
    public void run() {
        System.out.println(nthWeekdayOfMonth(Calendar.FRIDAY, Calendar.SEPTEMBER, 2016, 3, TimeZone.getTimeZone("Europe/London")));
        System.out.println(nthWeekdayOfMonth(Calendar.FRIDAY, Calendar.OCTOBER, 2016, 3, TimeZone.getTimeZone("Europe/London")));
        System.out.println(nthWeekdayOfMonth(Calendar.FRIDAY, Calendar.NOVEMBER, 2016, 3, TimeZone.getTimeZone("Europe/London")));

        System.out.println(nthWeekdayOfMonth2(Calendar.FRIDAY, Calendar.SEPTEMBER, 2016, 3, TimeZone.getTimeZone("Europe/London")));
        System.out.println(nthWeekdayOfMonth2(Calendar.FRIDAY, Calendar.OCTOBER, 2016, 3, TimeZone.getTimeZone("Europe/London")));
        System.out.println(nthWeekdayOfMonth2(Calendar.FRIDAY, Calendar.NOVEMBER, 2016, 3, TimeZone.getTimeZone("Europe/London")));

        System.out.println(getNDayOfMonth(5, 3, 9, 2016));
    }

    /**
     * My answer
     *
     * @param dayOfWeek
     * @param month
     * @param year
     * @param week
     * @param timeZone
     * @return
     */
    public Date nthWeekdayOfMonth(int dayOfWeek, int month, int year, int week, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(timeZone);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        // add +1 to week if first weekday of mounth > dayOfWeek
        int localWeek = week;
        if (calendar.get(calendar.DAY_OF_WEEK) > dayOfWeek) {
            localWeek++;
        }
        calendar.set(Calendar.WEEK_OF_MONTH, localWeek);
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        return calendar.getTime();
    }


    /**
     * px06 answer
     *
     * @param dayOfWeek
     * @param month
     * @param year
     * @param week
     * @param timeZone
     * @return
     */
    public Date nthWeekdayOfMonth2(int dayOfWeek, int month, int year, int week, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(timeZone);
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        //calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, week);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * Joda time
     *
     * @param dayweek
     * @param nthweek
     * @param month
     * @param year
     * @return
     */
    public static LocalDate getNDayOfMonth(int dayweek, int nthweek, int month, int year)  {
        LocalDate d = new LocalDate(year, month, 1).withDayOfWeek(dayweek);
        if(d.getMonthOfYear() != month) d = d.plusWeeks(1);
        return d.plusWeeks(nthweek-1);
    }




}