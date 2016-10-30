package cc.xaabb.gonggong.module.holiday;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sky31.gonggong.R;
import cc.xaabb.gonggong.model.HolidayAllModel;

import java.util.List;

/**
 * Created by root on 16-5-23.
 */
public class HolidayAllListAdapter extends BaseQuickAdapter<HolidayAllModel.DataEntity> {


    public HolidayAllListAdapter(Context context, List<HolidayAllModel.DataEntity> data) {
        super(context, R.layout.item_holiday_list, data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, HolidayAllModel.DataEntity dataEntity) {
        baseViewHolder.setText(R.id.tv_holiday_name, dataEntity.getName())
                .setText(R.id.tv_holiday_countdown, dataEntity.getInterval() + "天");
    }
}
