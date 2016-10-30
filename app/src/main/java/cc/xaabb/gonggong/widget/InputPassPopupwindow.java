package cc.xaabb.gonggong.widget;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.rey.material.widget.Button;
import cc.xaabb.gonggong.R;

import cc.xaabb.gonggong.config.CommonFunction;

/**
 * Created by root on 16-3-27.
 */
public class InputPassPopupwindow extends PopupWindow {
    private AppCompatEditText etInputPassword;
    private TextInputLayout tilPassword;
    private Button btnConfirm;
    private String title;
    private Context context;
    private Activity activity;
    private InputPassPopupwindow inputPassPopupwindow;

    public InputPassPopupwindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        etInputPassword = (AppCompatEditText) contentView.findViewById(R.id.et_input_password);
        btnConfirm = (Button) contentView.findViewById(R.id.btn_confirm);
        tilPassword = (TextInputLayout) contentView.findViewById(R.id.til_password);
    }

    public InputPassPopupwindow(Context context, final Activity activity, String title) {
        super(context);

    }

    public TextInputLayout getTilPassword() {
        return tilPassword;
    }

    public AppCompatEditText getEtInputPassword() {
        return etInputPassword;
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        CommonFunction.backgroundAlpha(0.5f, activity);
    }

    public void initPopupWindow(String title, final Activity activity, Context context) {
        this.activity = activity;
        this.title = title;
        this.context = context;
        tilPassword.setHint(title + context.getResources().getString(R.string.password));
        this.setTouchable(true);
        //设置输入法不遮挡
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);

        //this.setWidth(this.getWidth()-100);

        this.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        this.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                CommonFunction.backgroundAlpha(1f, activity);
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        this.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_default));
    }


    public void onConfirm(View.OnClickListener listener) {
        btnConfirm.setOnClickListener(listener);
    }
}
