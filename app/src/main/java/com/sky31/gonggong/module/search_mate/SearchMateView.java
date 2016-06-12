package com.sky31.gonggong.module.search_mate;

import android.support.annotation.Nullable;

import com.sky31.gonggong.model.MateInfoModel;

/**
 * Created by root on 16-3-25.
 */
public interface SearchMateView {
    void onGetSearchMate();

    void finishGetSearchMate(@Nullable MateInfoModel mateInfoModel);
}
