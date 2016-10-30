package cc.xaabb.gonggong.module.search_mate;

import android.support.annotation.Nullable;

import cc.xaabb.gonggong.model.MateInfoModel;

/**
 * Created by root on 16-3-25.
 */
public interface SearchMateView {
    void onGetSearchMate();

    void finishGetSearchMate(@Nullable MateInfoModel mateInfoModel);
}
