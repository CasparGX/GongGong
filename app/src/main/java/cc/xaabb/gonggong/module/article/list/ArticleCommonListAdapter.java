package cc.xaabb.gonggong.module.article.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cc.xaabb.gonggong.R;
import cc.xaabb.gonggong.config.CommonFunction;
import cc.xaabb.gonggong.model.ArticleListModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wukunguang on 16-5-5.
 */
public class ArticleCommonListAdapter extends BaseAdapter {

    private Context context;
    private List<ArticleListModel.Data> dataList;

    public ArticleCommonListAdapter(Context context, List<ArticleListModel.Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);


    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        ViewHolder viewHolder = null;
        ArticleListModel.Data data = dataList.get(position);

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.article_list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.articleInfoIntroduction.setText(data.getDescription());
        viewHolder.articleInfoTitle.setText(data.getTitle());
        viewHolder.articleInfoUpdateTime.setText(CommonFunction.timeAgo(data.getUpdatetime()));
        //viewHolder.articleItemTitle.setText();
//        if (data.getThumb() != null) {
//            Picasso.with(context).load(data.getThumb()).error(context.getResources().
//                    getDrawable(R.drawable.icon_exit)).into(viewHolder.articleThumb);
//        }

        return convertView;
    }


     class ViewHolder {
        @Bind(R.id.article_info_title)
        TextView articleInfoTitle;
        @Bind(R.id.article_info_introduction)
        TextView articleInfoIntroduction;
        @Bind(R.id.article_info_update_time)
        TextView articleInfoUpdateTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
