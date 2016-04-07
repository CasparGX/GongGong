package com.sky31.gonggong.config;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import com.sky31.gonggong.R;
import com.sky31.gonggong.module.main.MainActivity;

import java.util.HashMap;

/**
 * Created by root on 16-3-1.
 */
public class CommonFunction {
    private static final CommonFunction comminFunction = new CommonFunction();

    private CommonFunction() {

    }

    /**
     * -1	Wrong Hash
     * 0	OK
     * 1	Wrong Password
     * 2	Timeout
     * 3	Network Outage
     * 4	Unknown Error
     * 5	No Such Username
     * 6	Already Renew
     * 7	Bad Book ID
     * 65535	Missing Parameters
     */
    public static void errorToast(Context context, int code) {
        Resources resources = context.getResources();
        HashMap<String, String> errMsg = new HashMap<String, String>();
        errMsg.put("-1", resources.getString(R.string.err_msg__1));
        errMsg.put("1", resources.getString(R.string.err_msg_1));
        errMsg.put("2", resources.getString(R.string.err_msg_2));
        errMsg.put("3", resources.getString(R.string.err_msg_3));
        errMsg.put("4", resources.getString(R.string.err_msg_4));
        errMsg.put("5", resources.getString(R.string.err_msg_5));
        errMsg.put("6", resources.getString(R.string.err_msg_6));
        errMsg.put("7", resources.getString(R.string.err_msg_7));
        errMsg.put("65535", resources.getString(R.string.err_msg_65535));
        if (code == 1) {
            MainActivity.instance.logout();
        }
        Toast.makeText(context, errMsg.get(code + ""), Toast.LENGTH_SHORT).show();

    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public static void backgroundAlpha(float bgAlpha, Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

    /*dp转换为px*/
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /*px转换为dp*/
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}
