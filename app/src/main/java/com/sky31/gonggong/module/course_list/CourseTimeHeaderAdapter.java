package com.sky31.gonggong.module.course_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sky31.gonggong.R;
import com.sky31.gonggong.config.CommonFunction;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wukunguang on 16-4-27.
 */
public class CourseTimeHeaderAdapter extends BaseAdapter {

    private Context context;

    public CourseTimeHeaderAdapter(Context context) {
        this.context = context;


    }



    @Override
    public int getCount() {
        return 12;
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


        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.course_timeline_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int pos = 1 +position;
        viewHolder.courseItemTitle.setText(pos+"");

//        AbsListView.LayoutParams params = new
//                AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
//                (int) CommonFunction.convertDpToPixel(65,context));
//        viewHolder.courseItemTitle.setLayoutParams(params);
        //viewHolder.courseItemTitle.setHeight((int) CommonFunction.convertDpToPixel(65,context));

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.course_item_title)
        TextView courseItemTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
