package com.sky31.gonggong.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sky31.gonggong.R;

/**
 *  Listview适配器。
 *
 */
public class SwzlListviewAdapter extends BaseAdapter{


    private Context context;

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
        convertView = inflater.inflate(R.layout.swzl_item_layout,parent,false);

        return convertView;
    }
}
