package hui.demo.widget;


import java.util.Calendar;

public class DateUtils {


    public static boolean isLeapYear(int year) {
        Calendar c = Calendar.getInstance();
        c.clone();
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static int getYear(Calendar c) {
        return c.get(Calendar.YEAR);
    }

    public static int getCalendarToMyMonth(Calendar c) {//Calendar（0-11） 转自己习惯的日期表示月（1-12）
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Calendar c) {
        return c.get(Calendar.DATE);
    }

    public static int getCalendarToMyWeek(Calendar c) {//Calendar(2-7-1) 转自己习惯的日期表示周(1-7)
        if (c.get(Calendar.DAY_OF_WEEK) - 1 <= 0) {
            return 7;
        }
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static int getMyMonthToCalendar(int month) { //习惯的月份转Calendar月
        return month - 1;
    }

    public static int getMyWeekToCalendar(int week) {//习惯的周转Calendar周
        if (week - 1 <= 0) {
            return 7;
        }
        return week - 1;
    }

    public static int getDaysOneMonth(int year, int month) {//获取一个月多少天
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }
        if (month == 2) {
            if (isLeapYear(year)) { //闰年2月29天
                return 29;
            } else {
                return 28;
            }
        }
        return 30;
    }
}
