package com.sky31.gonggong.module.ecard;

import android.content.Context;
import android.support.annotation.Nullable;

import com.sky31.gonggong.model.EcardModel;

/**
 * Created by root on 16-3-7.
 */
public interface EcardView {
    void doneGetBalance(int code, @Nullable EcardModel ecardModel);

    void onGetBalance();
    Context getViewContext();
}
