package com.sky31.gonggong.module.article.list;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sky31.gonggong.R;
import com.sky31.gonggong.config.CommonFunction;
import com.sky31.gonggong.model.ArticleListModel;
import com.sky31.gonggong.widget.TimeLineView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wukunguang on 16-3-26.
 */
public class ArticleActivityListAdapter extends BaseAdapter {

    private Context context;
    private ArticleListModel model;
    private List<ArticleListModel.Data> datas;

    public ArticleActivityListAdapter(Context context, ArticleListModel model) {

        this.context = context;
        this.model = model;
        if (null != model.getData()) {
            this.datas = model.getData();
        }

    }

    @Override
    public int getCount() {
        return datas.size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

        }


        ArticleListModel.Data dataItem = datas.get(position);

        Resources res = context.getResources();

        holder.activityTime.setText(dataItem.getAct_time());
        holder.articleTitle.setText(dataItem.getTitle());
        holder.introduction.setText(res.getString(R.string.article_activity_introduction)+" "
                +dataItem.getDescription());

        holder.updateTime.setText(CommonFunction.timeAgo(dataItem.getUpdatetime()));

        LinearLayout layout = holder.linearLayout;
        TimeLineView view = new TimeLineView(context,CommonFunction.
                compareCurrentTimeByStr(dataItem.getAct_time()));
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        //view.setLayoutParams(params);

        view.setLayoutParams(params);
        layout.addView(view);


        //重绘小圆点
        //view.reDrawCircle(CommonFunction.compareCurrentTimeByStr(dataItem.getAct_time()));
        //view.invalidate();


        return convertView;
    }



    class ViewHolder {
        @Bind(R.id.activity_time_line_container)
        LinearLayout linearLayout;
        @Bind(R.id.article_activity_title)
        TextView articleTitle;
        @Bind(R.id.article_activity_time)
        TextView activityTime;
        @Bind(R.id.article_activity_introduction)
        TextView introduction;
        @Bind(R.id.article_activity_update_time)
        TextView updateTime;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
