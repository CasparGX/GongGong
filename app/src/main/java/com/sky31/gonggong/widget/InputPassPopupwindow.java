package com.sky31.gonggong.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.gc.materialdesign.views.ButtonRectangle;
import com.sky31.gonggong.R;

import static com.sky31.gonggong.config.CommonFunction.backgroundAlpha;

/**
 * Created by root on 16-3-27.
 */
public class InputPassPopupwindow extends PopupWindow implements View.OnClickListener {
    AppCompatEditText etInputPassword;
    ButtonRectangle btnConfirm;
    Context context;
    Activity activity;

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        backgroundAlpha(0.5f, activity);
    }

    public InputPassPopupwindow(Context context, final Activity activity) {
        super(context);
        this.context = context;
        this.activity = activity;
    }

    public PopupWindow getPopupWindow(String title) {
        View contentView = LayoutInflater.from(context).inflate(
                R.layout.popupwindow_input_password, null);
        etInputPassword = (AppCompatEditText) contentView.findViewById(R.id.et_input_password);
        etInputPassword.setHint(title + context.getResources().getString(R.string.password));
        btnConfirm = (ButtonRectangle) contentView.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(this);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
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
                backgroundAlpha(1f, activity);
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_default));
        return popupWindow;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:

                break;
        }
    }
}
