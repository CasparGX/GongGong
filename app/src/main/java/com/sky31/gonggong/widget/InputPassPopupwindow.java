package com.sky31.gonggong.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sky31.gonggong.R;
import com.sky31.gonggong.config.Constants;

import static com.sky31.gonggong.config.CommonFunction.backgroundAlpha;

/**
 * Created by root on 16-3-27.
 */
public class InputPassPopupwindow extends PopupWindow {
    public InputPassPopupwindow(Context context) {
        super(context);
        View contentView = LayoutInflater.from(context).inflate(
                R.layout.popupwindow_xtu_net_info, null);
        TextView xtuNetworkPackagex = (TextView) contentView.findViewById(R.id.xtu_network_packagex);
        TextView xtuNetworkStatus = (TextView) contentView.findViewById(R.id.xtu_network_status);
        TextView xtuNetworkNextStatementDate = (TextView) contentView.findViewById(R.id.xtu_network_next_statement_date);
        TextView xtuNetworkBalance = (TextView) contentView.findViewById(R.id.xtu_network_balance);
        xtuNetworkPackagex.setText(resources.getString(R.string.xtu_network_packagex) + aCache.getAsString(Constants.Key.CAMPUS_NETWORK_PACKAGEX));
        xtuNetworkStatus.setText(resources.getString(R.string.xtu_network_status) + aCache.getAsString(Constants.Key.CAMPUS_NETWORK_STATUS));
        xtuNetworkNextStatementDate.setText(resources.getString(R.string.xtu_network_next_statement_date) + aCache.getAsString(Constants.Key.CAMPUS_NETWORK_NEXT_STATEMENT_DATE));
        xtuNetworkBalance.setText(resources.getString(R.string.xtu_network_balance) + aCache.getAsString(Constants.Key.CAMPUS_NETWORK_BALANCE));

        final PopupWindow popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        backgroundAlpha(0.5f, MainActivity.this);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f, MainActivity.this);
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_default));

        // 设置好参数之后再show
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    public void setTitle(String title) {

    }
}
