package cc.xaabb.gonggong.module.library;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.rey.material.widget.ProgressView;
import cc.xaabb.gonggong.R;
import cc.xaabb.gonggong.base.BaseActivity;
import cc.xaabb.gonggong.config.Constants;
import cc.xaabb.gonggong.model.LibraryReaderInfoModel;
import cc.xaabb.gonggong.model.LibraryRentListModel;
import cc.xaabb.gonggong.model.UserModel;
import cc.xaabb.gonggong.util.ACache;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LibraryActivity extends BaseActivity implements LibraryView {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rec_library_list)
    RecyclerView recLibraryList;
    @Bind(R.id.pv_refresh)
    ProgressView pvRefresh;

    private LibraryListAdapter recLibraryListAdapter;
    private MenuItem refreshMenuItem;
    private ACache aCache;
    private LibraryReaderInfoModel.DataEntity readerInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        aCache = UserModel.getaCache();

        recLibraryList.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<LibraryRentListModel.DataEntity> list = new ArrayList<>();
        LibraryRentListModel.DataEntity item = new LibraryRentListModel.DataEntity();
        recLibraryListAdapter = new LibraryListAdapter(this, (ArrayList<LibraryRentListModel.DataEntity>) aCache.getAsObject(Constants.Key.LIBRARY_RENT_LIST));
        recLibraryList.setAdapter(recLibraryListAdapter);

        //读取缓存->用户信息
        readerInfo = new LibraryReaderInfoModel.DataEntity();
        readerInfo.setName(aCache.getAsString(Constants.Key.NAME));
        readerInfo.setValid_date_start(aCache.getAsString(Constants.Key.LIBRARY_VALID_DATE_START));
        readerInfo.setValid_date_end(aCache.getAsString(Constants.Key.LIBRARY_VALID_DATE_START));
        readerInfo.setDebt(aCache.getAsString(Constants.Key.LIBRARY_DEBT));

        //设置列表头部信息
        recLibraryListAdapter.setHeaderView(new View(this));
        recLibraryListAdapter.setHeaderInfo(readerInfo);
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
                        refreshMenuItem = item;
                        item.setActionView(pvRefresh);
                        pvRefresh.setVisibility(View.VISIBLE);
                        pvRefresh.start();
                        LibraryPresenter libraryPresenter = new LibraryPresenter(LibraryActivity.this);
                        libraryPresenter.getLibraryReaderInfo();
                        libraryPresenter.getLibraryRentList();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.finish();
    }

    @Override
    public void onGetLibraryReaderInfo(int code, @Nullable LibraryReaderInfoModel libraryReaderInfoModel) {
        if (libraryReaderInfoModel != null) {
            readerInfo = libraryReaderInfoModel.getData();
        }
        recLibraryListAdapter.setHeaderInfo(readerInfo);
    }


    @Override
    public void getLibraryRentLsit(int code, @Nullable LibraryRentListModel libraryRentListModel) {
        recLibraryListAdapter.updateData((ArrayList<LibraryRentListModel.DataEntity>) UserModel.getaCache().getAsObject(Constants.Key.LIBRARY_RENT_LIST));
        refreshMenuItem.setActionView(null);
        pvRefresh.setVisibility(View.GONE);
        pvRefresh.stop();
    }

    @Override
    public Context getViewContext() {
        return this;
    }
}
