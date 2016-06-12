package com.sky31.gonggong.config;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.sky31.gonggong.R;
import com.sky31.gonggong.module.main.MainActivity;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by root on 16-3-1.
 */
public class CommonFunction {
    private static final CommonFunction comminFunction = new CommonFunction();

    public static final long ALL_SECOND_OF_MOUTH = 24*3600*30;
    public static final long ALL_SECOND_OF_DAY = 3600*24;

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
        float px = dp * ((float) metrics.densityDpi / (float) DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /*px转换为dp*/
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / (float) DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }



    /*time Util*/

    /**
     *
     * @param mss   //ms 毫秒
     * @return  时间戳 ，字符串
     */
    public static String ms2Timestamp(String mss){

        long ms = Long.parseLong(mss)*1000;

        Date date = new Date(ms);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);

    }


    public static int homeLayoutHeight;
    public static int homeLayoutWidth;

    public static int getHomeLayoutWidth() {
        return homeLayoutWidth;
    }

    public static void setHomeLayoutWidth(int i) {
        homeLayoutWidth = i;
    }

    public static int getHomeLayoutHeight() {
        return homeLayoutHeight;
    }

    public static void setHomeLayoutHeight(int i) {
        homeLayoutHeight = i;
    }

    // 获取今天是周几。

    /***
     *
     * @param pushDay 往后推的天数
     * @param currentWeek   当前周数
     * @return  当前周数
     */
    public static int countCurrentWeek(int pushDay, int currentWeek){

        int resultWeek = pushDay/7+currentWeek;

        return resultWeek;
    }

    /**
     *
     * @param pushDay 往后推的天数
     * @return 当前周几。7周日，1为周一，2周二
     */
    public static int countCurrentDay(int pushDay){

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK)+6)%7;
        if (dayOfWeek == 0){
            dayOfWeek = 7;
        }
        return (dayOfWeek + pushDay)%7;
    }


    /**
     * 比较当前时间与输入时间。当前时间比较小返回 true否则返回false
     * @param timestamp     //时间戳 格式为 yyyy-MM-dd;
     * @return  boolean
     */
    public static boolean compareCurrentTimeByStr(String timestamp){

        //如果当前时间大于事件时间，那么返回false，否则返回true，
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(timestamp);
            return  System.currentTimeMillis() <=  date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static String timeAgo(String timeStr){

        long timeMi = Long.parseLong(timeStr);
        long cTime = (long)System.currentTimeMillis()/1000 - timeMi;

        if (cTime > 0 && cTime < 60){
            return "发布于"+cTime+"之前";
        }
        else if(cTime >= 60 && cTime <3600){
            return "发布于"+cTime/60+"分钟之前";
        }
        else if(cTime >=3600 && cTime < ALL_SECOND_OF_DAY){
            return "发布于"+cTime/3600 + "小时之前";
        }
        else if (cTime >= ALL_SECOND_OF_DAY && cTime < ALL_SECOND_OF_MOUTH){
            return "发布于"+cTime/ALL_SECOND_OF_DAY + "天之前";
        }
        else if (cTime >= ALL_SECOND_OF_MOUTH){
            return "发布于"+cTime/ALL_SECOND_OF_MOUTH + "个月之前";
        }
        else {
            return "N/A";
        }

    }


    /***
     * 用于图片压缩，减少内存消耗。
     * @param ResID  资源ID
     * @param scalSize  压缩倍数
     * @param res   Resource
     * @return  bitmap
     */
    public static Bitmap getBitmapByRes(int ResID, int scalSize, Resources res){
        BitmapFactory.Options factory = new BitmapFactory.Options();
        factory.inJustDecodeBounds = false;
        factory.inSampleSize = scalSize;
        Bitmap bitmap = BitmapFactory.decodeResource(res,ResID,factory);

        return bitmap;
    }

    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap resToBitmap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 隐藏键盘
     */
    public static void hiddenKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
