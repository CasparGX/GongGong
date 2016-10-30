package cc.xaabb.gonggong.module.search_mate;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import cc.xaabb.gonggong.R;
import cc.xaabb.gonggong.model.MateInfoModel;

import java.util.List;

/**
 * Created by root on 16-6-12.
 */
public class SearchMateListAdapter extends BaseQuickAdapter<MateInfoModel.DataEntity> {
    public SearchMateListAdapter(Context context, List<MateInfoModel.DataEntity> data) {
        super(context, R.layout.item_searchmate_stuinfo, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MateInfoModel.DataEntity dataEntity) {
        baseViewHolder.setText(R.id.searchmate_item_name, dataEntity.getName())
                .setText(R.id.searchmate_item_stunum, dataEntity.getSid())
                .setText(R.id.searchmate_item_classx, dataEntity.getClassX());
    }

    public void clearData() {
        int size = this.getData().size();
        if (size > 1) {
            for (int i = 1; i < size; i++) {
                this.remove(1);
            }
            this.notifyItemRangeRemoved(1, size);
        }
    }
}
