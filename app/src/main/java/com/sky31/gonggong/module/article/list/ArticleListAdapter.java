package com.sky31.gonggong.module.article.list;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sky31.gonggong.R;
import com.sky31.gonggong.model.ArticleListModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wukunguang on 16-3-26.
 */
public class ArticleListAdapter extends BaseAdapter {

    private Context context;
    private ArticleListModel model;
    private List<ArticleListModel.Data> datas;

    public ArticleListAdapter(Context context, ArticleListModel model) {

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
            convertView = inflater.inflate(R.layout.article_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {

            holder = (ViewHolder) convertView.getTag();

        }


        ArticleListModel.Data dataItem = datas.get(position);
        Log.d("ArticleList",dataItem.getAct_time());
        holder.articleItemDescription.setText(context.getResources().getString(R.string.article_item_description) + dataItem.getDescription());
        holder.articleItemTitle.setText(dataItem.getTitle());
        holder.articleItemUsername.setText(context.getResources().getString(R.string.article_item_updatetime)+ dataItem.getUpdatetime());
        holder.articleItemUpdatetime.setText(R.string.article_item_description + dataItem.getAct_time());
        return convertView;
    }


    class ViewHolder {
        @Bind(R.id.article_item_title)
        TextView articleItemTitle;
        @Bind(R.id.article_item_username)
        TextView articleItemUsername;
        @Bind(R.id.article_item_updatetime)
        TextView articleItemUpdatetime;
        @Bind(R.id.article_item_description)
        TextView articleItemDescription;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
