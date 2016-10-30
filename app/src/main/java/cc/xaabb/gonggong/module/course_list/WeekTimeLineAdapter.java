package cc.xaabb.gonggong.module.course_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sky31.gonggong.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wukunguang on 16-5-8.
 */
public class WeekTimeLineAdapter extends BaseAdapter {

    private Context context;

    private static final int DAY_OF_WEEK =  7;
    public WeekTimeLineAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return DAY_OF_WEEK;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.day_of_week_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int day = position+1;

        viewHolder.dayOfWeek.setText(getDayOfWeekWord(day));




        return convertView;
    }


    private String getDayOfWeekWord(int day){

        switch (day){
            case 1:
                return "周一";

            case 2:
                return "周二";

            case 3:
                return "周三";

            case 4:
                return "周四";

            case 5:
                return "周五";

            case 6:
                return "周六";

            case 7:
                return "周日";
            default:
                return "";
        }
    }

    static class ViewHolder {
        @Bind(R.id.day_of_week)
        TextView dayOfWeek;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
