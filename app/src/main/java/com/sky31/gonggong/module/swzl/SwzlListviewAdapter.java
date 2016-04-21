package com.sky31.gonggong.module.swzl;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sky31.gonggong.R;
import com.sky31.gonggong.model.LostAndFoundModel;
import com.sky31.gonggong.model.SwzlSearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Listview适配器。
 */
public class SwzlListviewAdapter extends BaseAdapter{


    private Context context;
    private SwzlSearchResult result;
    ArrayList<LostAndFoundModel> modelList;
    public SwzlListviewAdapter(Context context,ArrayList<LostAndFoundModel> result) {


        this.context = context;
        this.modelList = result;

    }

    @Override
    public int getCount() {
        return modelList.size();
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
            convertView = inflater.inflate(R.layout.swzl_item_layout, null);
            viewHolder = new SwzlViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (SwzlViewHolder) convertView.getTag();
        }


        Resources resources = context.getResources();

        LostAndFoundModel model = modelList.get(position);
        viewHolder.swzlItemAnnouncer.setText(resources.getString(R.string.swzl_announcer)+model.getAnnouncer());
        viewHolder.swzlItemDescription.setText(resources.getString(R.string.swzl_announcer)+model.getDescription());
        viewHolder.swzlItemLocation.setText(resources.getString(R.string.swzl_location)+model.getLocation());
        viewHolder.swzlItemThings.setText(resources.getString(R.string.swzl_thing_name)+model.getThings());


        return convertView;
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

