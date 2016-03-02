package com.sky31.gonggong.view;

import android.content.Context;
import android.support.annotation.Nullable;

import com.sky31.gonggong.model.EcardModel;
import com.sky31.gonggong.model.StudentInfoModel;

/**
 * Created by root on 16-2-29.
 */
public interface ApiView {
    void getBalance(int code, @Nullable EcardModel ecardModel);
    void login(int code, @Nullable StudentInfoModel studentInfoModel);

    Context getViewContext();

}
