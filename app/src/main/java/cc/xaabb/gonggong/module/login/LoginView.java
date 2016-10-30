package cc.xaabb.gonggong.module.login;

import android.content.Context;
import android.support.annotation.Nullable;

import cc.xaabb.gonggong.model.StudentInfoModel;

/**
 * Created by root on 16-2-29.
 */
public interface LoginView {
    void login(int code, @Nullable StudentInfoModel studentInfoModel);

    Context getViewContext();
}
