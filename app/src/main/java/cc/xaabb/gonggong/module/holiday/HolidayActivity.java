package cc.xaabb.gonggong.module.holiday;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cc.xaabb.gonggong.R;
import cc.xaabb.gonggong.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HolidayActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);
        ButterKnife.bind(this);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        //设置toolbar返回按钮
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //设置toolbar标题
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

    }
}
