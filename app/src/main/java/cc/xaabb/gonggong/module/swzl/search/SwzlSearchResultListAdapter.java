package cc.xaabb.gonggong.module.swzl.search;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import cc.xaabb.gonggong.R;
import cc.xaabb.gonggong.config.CommonFunction;
import cc.xaabb.gonggong.config.Constants;
import cc.xaabb.gonggong.model.LostAndFoundModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wukunguang on 16-5-15.
 */
public class SwzlSearchResultListAdapter extends BaseAdapter {

    private Context context;
    private List<LostAndFoundModel> models;



    public SwzlSearchResultListAdapter(Context context, List<LostAndFoundModel> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView ==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.swzl_item_layout, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LostAndFoundModel model = models.get(position);
        Resources res = context.getResources();
        viewHolder.swzlItemAnnouncer.setText(res.getString(R.string.swzl_announcer)+
        model.getAnnouncer());
        viewHolder.swzlItemDescription.setText(res.getString(R.string.swzl_description)+
        model.getDescription());
        viewHolder.swzlItemLocation.setText(res.getString(R.string.swzl_location)+
        model.getLocation());
        if (model.getThings_type().equals(Constants.Value.SWZL_SUBMIT_LOST)){
            viewHolder.swzlItemThings.setText(res.getString(R.string.swzl_search_tag_lost)+
                    model.getThings());
        }
        else {
            viewHolder.swzlItemThings.setText(res.getString(R.string.swzl_search_tag_get)+
                    model.getThings());
        }

        viewHolder.swzlItemTime.setText(CommonFunction.timeAgo(model.getCreated_at()));

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.swzl_item_things)
        TextView swzlItemThings;
        @Bind(R.id.swzl_item_location)
        TextView swzlItemLocation;
        @Bind(R.id.swzl_item_announcer)
        TextView swzlItemAnnouncer;
        @Bind(R.id.swzl_item_description)
        TextView swzlItemDescription;
        @Bind(R.id.swzl_item_time)
        TextView swzlItemTime;
        @Bind(R.id.swzl_item_view)
        LinearLayout swzlItemView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
