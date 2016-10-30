package cc.xaabb.gonggong.module.campusnet;

import android.content.Context;
import android.support.annotation.Nullable;

import cc.xaabb.gonggong.model.CampusNetwork;

/**
 * Created by root on 16-3-7.
 */
public interface CampusNetView {
    void getCampusNetwork(int code, @Nullable CampusNetwork campusNetwork);

    Context getViewContext();
}
