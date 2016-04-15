package com.sky31.gonggong.module.library;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.rey.material.widget.ProgressView;
import com.sky31.gonggong.R;
import com.sky31.gonggong.base.BaseActivity;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.LibraryRentListModel;
import com.sky31.gonggong.model.UserModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LibraryActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rec_library_list)
    RecyclerView recLibraryList;
    @Bind(R.id.pv_refresh)
    ProgressView pvRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        recLibraryList.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<LibraryRentListModel.DataEntity> list = new ArrayList<>();
        LibraryRentListModel.DataEntity item = new LibraryRentListModel.DataEntity();
        recLibraryList.setAdapter(new LibraryListAdapter(this, (ArrayList<LibraryRentListModel.DataEntity>) UserModel.getaCache().getAsObject(Constants.Key.LIBRARY_RENT_LIST)));
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
        //设置toolbar菜单
        toolbar.inflateMenu(R.menu.base_toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                switch (menuItemId) {
                    case R.id.action_refresh:
                        item.setActionView(pvRefresh);
                        //setProgressBarIndeterminate(true);
                        pvRefresh.setVisibility(View.VISIBLE);
                        pvRefresh.start();
                        break;
                }
                return true;
            }
        });
    }
}
