package com.sky31.gonggong;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.sky31.gonggong.app.App;
import com.sky31.gonggong.base.BaseActivity;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.EcardModel;
import com.sky31.gonggong.model.StudentInfoModel;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.presenter.ApiPresenter;
import com.sky31.gonggong.presenter.HomeViewPagerAdapter;
import com.sky31.gonggong.util.ACache;
import com.sky31.gonggong.util.Debug;
import com.sky31.gonggong.view.ApiView;
import com.sky31.gonggong.view.activity.LoginActivity;
import com.sky31.gonggong.view.activity.SwzlActivity;
import com.sky31.gonggong.view.fragment.FirstFragment;
import com.sky31.gonggong.view.fragment.SecondFragment;

import org.apache.http.auth.NTUserPrincipal;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sky31.gonggong.base.CommonFunction.errorToast;

public class MainActivity extends BaseActivity implements ApiView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.drawer_menu)
    LinearLayout drawerView;
    @Bind(R.id.drawer)
    DrawerLayout drawer;
    @Bind(R.id.header)
    LinearLayout header;
    @Bind(R.id.user_avatar)
    ImageView userAvatar;
    @Bind(R.id.username)
    TextView username;
    @Bind(R.id.header_content)
    LinearLayout headerContent;
    @Bind(R.id.user_avatar_bg)
    FrameLayout userAvatarBg;
    @Bind(R.id.header_avatar)
    LinearLayout headerAvatar;
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.top_index)
    TableRow drawerMenuItem1;
    @Bind(R.id.function)
    TableRow drawerMenuItem2;
    @Bind(R.id.drawer_menu_item3)
    TableRow drawerMenuItem3;
    @Bind(R.id.home_layout)
    RelativeLayout homeLayout;
    @Bind(R.id.stu_num)
    TextView stuNum;
    @Bind(R.id.ecard_balance)
    TextView ecardBalance;
    @Bind(R.id.ecard_unclaimed)
    TextView ecardUnclaimed;
    @Bind(R.id.btn_login)
    TextView btnLogin;
    @Bind(R.id.ecard_info)
    LinearLayout ecardInfo;
    @Bind(R.id.ecard)
    LinearLayout ecard;

    @OnClick(R.id.btn_login)
    void gotoLogin() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(loginIntent, Constants.Value.RESULT_LOGIN);
        MainActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @OnClick(R.id.top_index)
    void DrawerMenuItem1() {

    }

    @OnClick(R.id.function)
    void jumpToLostAndFound() {
        //调转到失物招领
        Intent intent = new Intent(MainActivity.this, SwzlActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ecard)
    void onCLickEcard() {
        ApiPresenter apiPresenter = new ApiPresenter(this);
        apiPresenter.getBalance(null, null);
    }


    /* 变量 */
    private ActionBarDrawerToggle mDrawerToggle;
    private int mCurrentPageIndex;
    private int headerHeight = -1;
    private int homeLayoutHeight = -1;
    private Context context;
    private Resources resources;
    private ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        context = MainActivity.this;
        resources = getResources();
        aCache = ACache.get(this);
        initToolbar();
        initView();
        autoLogin();
    }

    //初始化控件
    private void initView() {
        //ecardInfo.setVisibility(View.GONE);
        ecard.setClickable(false);

        List<Fragment> mDatas = new ArrayList<Fragment>();
        final FirstFragment mFirstFragment = new FirstFragment();
        SecondFragment mSecondFragment = new SecondFragment();
        mDatas.add(mFirstFragment);
        mDatas.add(mSecondFragment);
        pager.setOffscreenPageLimit(mDatas.size());
        HomeViewPagerAdapter mAdpater = new HomeViewPagerAdapter(getSupportFragmentManager(), mDatas);
        pager.setAdapter(mAdpater);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mCurrentPageIndex == 0 && position == 0) { //0->1
                    onChangeHeaderHeight(positionOffset);
                } else if (mCurrentPageIndex == 1 && position == 0) {    //1->0
                    onChangeHeaderHeight(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPageIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * @param param ViewPager切换的进度
     *              改变头部高度，内容透明度
     */
    public void onChangeHeaderHeight(float param) {
        if (headerHeight != -1) {
            float a = 1.5f;
            ViewGroup.LayoutParams headerAvatarParam = headerAvatar.getLayoutParams();
            float headerAvatarSize = getResources().getDimension(R.dimen.avatar_bg_size);
            float b = (1.0f - a * param) <= 0 ? 0 : (1.0f - a * param);
            float height = b * headerAvatarSize;

            headerAvatarParam.height = (int) height;
            headerAvatarParam.width = headerAvatarParam.height;
            headerAvatar.setLayoutParams(headerAvatarParam);

            ViewGroup.LayoutParams headerParam = header.getLayoutParams();
            headerParam.height = (int) (headerHeight * (1 - param));
            header.setLayoutParams(headerParam);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                //userAvatar.setRotation(480 * (1 - b));
                headerContent.setAlpha(b);
                Debug.i("alpha",userAvatar.getRotation()+"");
            }
        }
    }

    //初始化toolbar
    private void initToolbar() {
        //toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);//设置导航栏图标
        toolbar.setTitle(R.string.app_name);//设置主标题
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        drawer.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        homeLayoutHeight = homeLayout.getHeight();
        ViewGroup.LayoutParams headerParam = header.getLayoutParams();
        headerParam.height = homeLayoutHeight / 3;
        header.setLayoutParams(headerParam);
        headerHeight = homeLayoutHeight / 3;
        App.getApp().setHomeLayoutHeight(homeLayoutHeight);
        FirstFragment.getInstance().initLayoutHeight();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.Value.RESULT_LOGIN:
                if (resultCode == RESULT_OK) {
                    isLogined(data.getStringExtra("name"));
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        ButterKnife.unbind(this);
    }

    //准备登录
    private void readyLogin() {
        btnLogin.setClickable(false);
        btnLogin.setText(resources.getString(R.string.logining));
    }

    //已登录
    private void isLogined(String name) {
        //个人信息
        stuNum.setText(aCache.getAsString(Constants.Key.SID));
        username.setText(name);
        btnLogin.setVisibility(View.GONE);
        stuNum.setVisibility(View.VISIBLE);

        //校园卡
        ecard.setClickable(true);

        //图书馆

        //校园网
    }

    //自动登录，如果有缓存
    public void autoLogin() {
        if (aCache.getAsString(Constants.Key.SID) != null || aCache.getAsString(Constants.Key.PASSWORD) != null) {
            isLogined(aCache.getAsString(Constants.Key.NAME));
            getBalance(0,null);
            autoGetData(aCache.getAsString(Constants.Key.SID), aCache.getAsString(Constants.Key.PASSWORD));
        }
    }

    public void autoGetData(String sid, String password) {
        ApiPresenter apiPresenter = new ApiPresenter(this);
        apiPresenter.getBalance(sid, password);
    }

    public void logout() {
        //个人信息
        username.setText(resources.getString(R.string.default_username));
        btnLogin.setText(resources.getString(R.string.login));
        btnLogin.setVisibility(View.VISIBLE);
        stuNum.setVisibility(View.GONE);

        //校园卡
        ecard.setClickable(false);
        ecardBalance.setText(resources.getString(R.string.default_money));
        ecardUnclaimed.setText(resources.getString(R.string.default_money));

        //图书馆

        //校园网
    }

    @Override
    public void getBalance(int code, EcardModel ecardModel) {
        if (code == 0) {
            try {
                ecardBalance.setText(aCache.getAsString(Constants.Key.BALANCE));
                ecardUnclaimed.setText(aCache.getAsString(Constants.Key.UNCLAIMED));
                //ecardNone.setVisibility(View.GONE);
                //ecardInfo.setVisibility(View.VISIBLE);
            } catch (NullPointerException e) {

            }
        } else {
            errorToast(this, code);
        }
    }

    @Override
    public void login(int code, StudentInfoModel studentInfoModel) {
        if (code == 0 ) {
            isLogined(studentInfoModel.getData().getName());
        } else {
            errorToast(this, code);
        }
    }

    @Override
    public Context getViewContext() {
        return context;
    }
}
