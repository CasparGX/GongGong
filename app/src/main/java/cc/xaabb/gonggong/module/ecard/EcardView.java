package cc.xaabb.gonggong.module.ecard;

import android.content.Context;
import android.support.annotation.Nullable;

import cc.xaabb.gonggong.model.EcardModel;

/**
 * Created by root on 16-3-7.
 */
public interface EcardView {
    void doneGetBalance(int code, @Nullable EcardModel ecardModel);

    void onGetBalance();
    Context getViewContext();
}
