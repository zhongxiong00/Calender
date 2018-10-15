package hui.demo.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarHolder> {
    private List<DataBean> mDatas;
    private final int DAY_COUNT = 7;//一周7天
    private final String[] mTitles = {"日", "一", "二", "三", "四", "五", "六"};
    private Context mContext;
    private CalendarHolder mSelectHolder;

    public CalendarAdapter(Context context) {
        mContext = context;
    }

    public void setDate(List<DataBean> datas) {//传入年月日
        mDatas = datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CalendarHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_calendar_view, parent, false);
        CalendarHolder holder = new CalendarHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CalendarHolder calendarHolder, int pos) {
        calendarHolder.ivRedPoint.setVisibility(View.INVISIBLE);
        if (pos < DAY_COUNT) { //标题
            calendarHolder.tvDay.setText(mTitles[pos]);
            calendarHolder.tvDay.setTextColor(Color.GRAY);
        } else {
            DataBean now = mDatas.get(pos - DAY_COUNT);
            calendarHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSelectHolder != null && mSelectHolder.rootView == calendarHolder.rootView) {
                        return;
                    }
                    if (mSelectHolder != null) {
                        mSelectHolder.rootView.setBackgroundColor(Color.parseColor("#ffffff"));
                        mSelectHolder.tvDay.setTextColor((Integer) mSelectHolder.tvDay.getTag());
                    }
                    mSelectHolder = calendarHolder;
                    calendarHolder.rootView.setBackgroundColor(Color.parseColor("#ff0000"));
                    calendarHolder.tvDay.setTextColor(Color.parseColor("#ffffff"));
                }
            });
            if (!now.isNowMonth) { //不是当月的日子
                calendarHolder.tvDay.setText(String.valueOf(now.day));
                calendarHolder.tvDay.setTextColor(Color.parseColor("#999999"));
                calendarHolder.tvDay.setTag(calendarHolder.tvDay.getCurrentTextColor());
            } else {//当月的日子
                calendarHolder.tvDay.setText(String.valueOf(now.day));
                if (now.isToday) { //今日
                    calendarHolder.tvDay.setTextColor(Color.parseColor("#ff0000"));
                } else {
                    calendarHolder.tvDay.setTextColor(Color.parseColor("#000000"));
                }
                calendarHolder.tvDay.setTag(calendarHolder.tvDay.getCurrentTextColor());
                if (now.hasRepay) { //有回款,显示底部红点
                    calendarHolder.ivRedPoint.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return DAY_COUNT + (mDatas == null ? 0 : mDatas.size());
    }

    public static class CalendarHolder extends RecyclerView.ViewHolder {
        public CircleRelativeLayout rootView;
        public TextView tvDay;
        public ImageView ivRedPoint;

        public CalendarHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView.findViewById(R.id.bg_item);
            tvDay = itemView.findViewById(R.id.tv_day);
            ivRedPoint = itemView.findViewById(R.id.iv_redpoint);
        }
    }


}
