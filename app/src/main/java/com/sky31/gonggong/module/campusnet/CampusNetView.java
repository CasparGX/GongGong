package com.sky31.gonggong.module.campusnet;

import android.content.Context;
import android.support.annotation.Nullable;

import com.sky31.gonggong.model.CampusNetwork;

/**
 * Created by root on 16-3-7.
 */
public interface CampusNetView {
    void getCampusNetwork(int code, @Nullable CampusNetwork campusNetwork);

    Context getViewContext();
}
