package com.sky31.gonggong.module.swzl;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sky31.gonggong.R;
import com.sky31.gonggong.model.LostAndFoundModel;
import com.sky31.gonggong.model.SwzlSearchResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Listview适配器。
 */
public class SwzlListviewAdapter extends BaseAdapter implements SwzlSearchView {


    private Context context;
    private SwzlSearchResult result;

    public SwzlListviewAdapter(Context context) {

        this.context = context;

    }

    @Override
    public int getCount() {
        return 1;
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

        // init
        LayoutInflater inflater = LayoutInflater.from(context);

        SwzlViewHolder viewHolder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.swzl_item_layout, parent);
            viewHolder = new SwzlViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (SwzlViewHolder) convertView.getTag();
        }


        List<LostAndFoundModel> modelList = result.getDatas();

        viewHolder.swzlItemAnnouncer.

        return convertView;
    }

    @Override
    public void getSearchData(SwzlSearchResult data) {

        //int code = data.getCode();



    }

    public static class SwzlViewHolder {

        @Bind(R.id.swzl_item_announcer)
        TextView swzlItemAnnouncer;
        @Bind(R.id.swzl_item_things)
        TextView swzlItemThings;
        @Bind(R.id.swzl_item_location)
        TextView swzlItemLocation;
        @Bind(R.id.swzl_item_description)
        TextView swzlItemDescription;

        SwzlViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}

