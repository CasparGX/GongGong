package com.sky31.gonggong.module.search_mate;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;

import com.rey.material.widget.Button;
import com.sky31.gonggong.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by root on 16-3-18.
 */
public class SearchMateActivity extends Activity implements SearchMateView {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_sid)
    AppCompatEditText etSid;
    @Bind(R.id.til_sid)
    TextInputLayout tilSid;
    @Bind(R.id.et_name)
    AppCompatEditText etName;
    @Bind(R.id.til_name)
    TextInputLayout tilName;
    @Bind(R.id.et_card)
    AppCompatEditText etCard;
    @Bind(R.id.til_card)
    TextInputLayout tilCard;
    @Bind(R.id.check_benbu)
    com.rey.material.widget.CheckBox checkBenbu;
    @Bind(R.id.check_xingxiang)
    com.rey.material.widget.CheckBox checkXingxiang;
    @Bind(R.id.btn_search)
    Button btnSearch;

    @OnClick(R.id.btn_search)
    void onClickBtnSearch() {
        String sid = etSid.getText().toString();
        String name = etName.getText().toString();
        String card = etCard.getText().toString();
        String type = null;
        if (checkBenbu.isChecked())
            type = "xtu";
        else
            type = "xx";
        onGetSearchMate();
        SearchMatePresenter searchMatePresenter = new SearchMatePresenter(this);
        searchMatePresenter.getMateInfo(sid, name, card, type);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_mate);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        etName.getBackground().mutate().setColorFilter(getResources().getColor(R.color.textColorPrimary), PorterDuff.Mode.SRC_ATOP);
        tilCard.setHint(getResources().getString(R.string.card));
        tilSid.setHint(getResources().getString(R.string.sid));
        tilName.setHint(getResources().getString(R.string.name));
        initCheckBox();
    }

    private void initCheckBox() {
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBenbu.setChecked(checkBenbu == buttonView);
                    checkXingxiang.setChecked(checkXingxiang == buttonView);
                }
            }
        };

        checkBenbu.setOnCheckedChangeListener(listener);
        checkXingxiang.setOnCheckedChangeListener(listener);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);

        //toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);//设置导航栏图标
        toolbar.setTitle(R.string.app_name);//设置主标题
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onGetSearchMate() {
        btnSearch.setVisibility(View.GONE);
    }

    @Override
    public void finishGetSearchMate() {
        btnSearch.setVisibility(View.VISIBLE);
    }
}
