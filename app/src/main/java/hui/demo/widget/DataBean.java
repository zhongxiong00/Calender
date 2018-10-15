package hui.demo.widget;

public class DataBean {
    public int year, month, day, week;//年，月，日，周几(1-7表示周1-周日)。
    public boolean isToday;//是否是今日
    public boolean isNowMonth;//是否是当月的日子
    public boolean hasRepay;//有回款

    public DataBean(int year, int month, int day, int week, boolean isToday, boolean isNowMonth, boolean hasRepay) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.week = week;
        this.isToday = isToday;
        this.isNowMonth = isNowMonth;
        this.hasRepay = hasRepay;
    }
}
