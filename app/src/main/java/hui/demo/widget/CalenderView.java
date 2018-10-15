package hui.demo.widget;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalenderView extends RecyclerView {
    private CalendarAdapter mAdapter;

    public CalenderView(@NonNull Context context) {
        this(context, null);
    }

    public CalenderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayoutManager(new GridLayoutManager(context, 7));
        mAdapter = new CalendarAdapter(context);
        setAdapter(mAdapter);
    }


    public void showDate(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, DateUtils.getMyMonthToCalendar(month), 1);
        List<DataBean> dates = new ArrayList<>();
        for (int i = 0; i < DateUtils.getDaysOneMonth(year, month); i++) {//随便模拟一些数据
            if (DateUtils.getDay(c) == 12) {
                dates.add(new DataBean(DateUtils.getYear(c), DateUtils.getCalendarToMyMonth(c), DateUtils.getDay(c), DateUtils.getCalendarToMyWeek(c), true, true, false));
            } else if (DateUtils.getDay(c) == 6 || DateUtils.getDay(c) == 10 || DateUtils.getDay(c) == 18) {
                dates.add(new DataBean(DateUtils.getYear(c), DateUtils.getCalendarToMyMonth(c), DateUtils.getDay(c), DateUtils.getCalendarToMyWeek(c), false, true, true));
            } else {
                dates.add(new DataBean(DateUtils.getYear(c), DateUtils.getCalendarToMyMonth(c), DateUtils.getDay(c), DateUtils.getCalendarToMyWeek(c), false, true, false));
            }
            c.add(Calendar.DATE, 1);
        }
        setData(dates);
    }

    private void setData(List<DataBean> data) {//传入当月的数据即可
        if (data != null && data.size() > 0) {
            DataBean firstDate = data.get(0);
            DataBean lastDate = data.get(data.size() - 1);
            int addHead = firstDate.week % 7;//需要在前面添加多少天
            int addTail = 6 - lastDate.week % 7;//需要在后面添加多少天
            Calendar c = Calendar.getInstance();
            c.set(firstDate.year, DateUtils.getMyMonthToCalendar(firstDate.month), firstDate.day);
            for (int i = 0; i < addHead; i++) {
                c.add(Calendar.DATE, -1);
                data.add(0, new DataBean(DateUtils.getYear(c), DateUtils.getCalendarToMyMonth(c), DateUtils.getDay(c), DateUtils.getCalendarToMyWeek(c), false, false, false));
            }
            c.set(lastDate.year, DateUtils.getMyMonthToCalendar(lastDate.month), lastDate.day);
            for (int i = 0; i < addTail; i++) {
                c.add(Calendar.DATE, 1);
                data.add(new DataBean(DateUtils.getYear(c), DateUtils.getCalendarToMyMonth(c), DateUtils.getDay(c), DateUtils.getCalendarToMyWeek(c), false, false, false));
            }
            mAdapter.setDate(data);
        }
    }
}
