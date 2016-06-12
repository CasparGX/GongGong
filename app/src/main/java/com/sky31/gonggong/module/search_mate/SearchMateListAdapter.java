package com.sky31.gonggong.module.search_mate;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sky31.gonggong.R;
import com.sky31.gonggong.model.SearchMateModel;

import java.util.List;

/**
 * Created by root on 16-6-12.
 */
public class SearchMateListAdapter extends BaseQuickAdapter<SearchMateModel.DataEntity> {
    public SearchMateListAdapter(Context context, List<SearchMateModel.DataEntity> data) {
        super(context, R.layout.item_searchmate_stuinfo, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SearchMateModel.DataEntity dataEntity) {
        baseViewHolder.setText(R.id.searchmate_item_name, dataEntity.getName())
                .setText(R.id.searchmate_item_stunum, dataEntity.getSid())
                .setText(R.id.searchmate_item_classx, dataEntity.getClassX());
    }
}
