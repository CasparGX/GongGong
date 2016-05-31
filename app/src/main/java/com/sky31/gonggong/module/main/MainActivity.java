package com.sky31.gonggong.module.main;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.Button;
import com.rey.material.widget.ProgressView;
import com.sky31.gonggong.R;
import com.sky31.gonggong.base.BaseActivity;
import com.sky31.gonggong.config.CommonFunction;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.CampusNetwork;
import com.sky31.gonggong.model.EcardModel;
import com.sky31.gonggong.model.LibraryReaderInfoModel;
import com.sky31.gonggong.model.LibraryRentListModel;
import com.sky31.gonggong.model.StudentInfoModel;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.module.about_us.AboutUsActivity;
import com.sky31.gonggong.module.campusnet.CampusNetPresenter;
import com.sky31.gonggong.module.campusnet.CampusNetView;
import com.sky31.gonggong.module.ecard.EcardPresenter;
import com.sky31.gonggong.module.ecard.EcardView;
import com.sky31.gonggong.module.library.LibraryActivity;
import com.sky31.gonggong.module.library.LibraryPresenter;
import com.sky31.gonggong.module.library.LibraryView;
import com.sky31.gonggong.module.login.LoginActivity;
import com.sky31.gonggong.module.login.LoginView;
import com.sky31.gonggong.module.main.fragment.FirstFragment;
import com.sky31.gonggong.module.main.fragment.InformationFragment;
import com.sky31.gonggong.module.main.fragment.SecondFragment;
import com.sky31.gonggong.util.ACache;
import com.sky31.gonggong.util.Debug;
import com.sky31.gonggong.widget.InputPassPopupwindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sky31.gonggong.config.CommonFunction.backgroundAlpha;
import static com.sky31.gonggong.config.CommonFunction.convertDpToPixel;
import static com.sky31.gonggong.config.CommonFunction.errorToast;
import static com.sky31.gonggong.config.CommonFunction.homeLayoutWidth;

public class MainActivity extends BaseActivity implements ApiView, EcardView, CampusNetView, LoginView, LibraryView {

    public static MainActivity instance;
    private static ACache aCache;
    private static View inputPasswordPopupwindowContentView;
    @Bind(R.id.pv_refresh)
    ProgressView pvRefresh;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.user_avatar)
    ImageView userAvatar;
    @Bind(R.id.user_avatar_bg)
    FrameLayout userAvatarBg;
    @Bind(R.id.header_avatar)
    LinearLayout headerAvatar;
    @Bind(R.id.username)
    TextView username;
    @Bind(R.id.stu_num)
    TextView stuNum;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.header_content)
    LinearLayout headerContent;
    @Bind(R.id.ecard_balance)
    TextView ecardBalance;
    @Bind(R.id.pb_ecard_balance)
    ProgressBar pbEcardBalance;
    @Bind(R.id.ecard_unclaimed)
    TextView ecardUnclaimed;
    @Bind(R.id.pb_ecard_unclaimed)
    ProgressBar pbEcardUnclaimed;
    @Bind(R.id.ecard_info)
    LinearLayout ecardInfo;
    @Bind(R.id.ecard)
    LinearLayout ecard;
    @Bind(R.id.library_debt)
    TextView libraryDebt;
    @Bind(R.id.library_rent_num)
    TextView libraryRentNum;
    @Bind(R.id.library_info)
    LinearLayout libraryInfo;
    @Bind(R.id.library)
    LinearLayout library;
    @Bind(R.id.xtu_network_status)
    TextView xtuNetworkStatus;
    @Bind(R.id.xtu_network_balance)
    TextView xtuNetworkBalance;
    @Bind(R.id.xtu_network_info)
    LinearLayout xtuNetworkInfo;
    @Bind(R.id.xtu_net)
    LinearLayout xtuNet;
    @Bind(R.id.header_info)
    LinearLayout headerInfo;
    @Bind(R.id.header)
    LinearLayout header;
    @Bind(R.id.home_layout)
    RelativeLayout homeLayout;
    @Bind(R.id.tv_person)
    TextView tvPerson;
    @Bind(R.id.layout_person)
    LinearLayout layoutPerson;
    @Bind(R.id.tv_function)
    TextView tvFunction;
    @Bind(R.id.layout_function)
    LinearLayout layoutFunction;
    @Bind(R.id.tv_information)
    TextView tvInformation;
    @Bind(R.id.layout_information)
    LinearLayout layoutInformation;
    @Bind(R.id.home_content)
    LinearLayout homeContent;
    @Bind(R.id.drawer_menu)
    NavigationView drawerMenu;
    @Bind(R.id.drawer)
    DrawerLayout drawer;
    @Bind(R.id.btn_exit)
    TableRow btnExit;


    /* 变量 */
    private ActionBarDrawerToggle mDrawerToggle;
    private int mCurrentPageIndex;
    private int headerHeight = -1;
    private int homeLayoutHeight = -1;
    private Context context;
    private Resources resources;
    private float defualtTextSize;
    private ScaleAnimation showAnimation;
    private ScaleAnimation hiddenAnimation;

    //header
    private ViewGroup.LayoutParams headerParam;

    //头像被点击
    @OnClick(R.id.header_avatar)
    void onClickHeaderAvatar() {
        autoGetData();
    }


    //退出登录
    @OnClick(R.id.btn_exit)
    void onClickBtnExit() {
        drawer.closeDrawers();
        logout();
    }

    //登录按钮
    @OnClick(R.id.btn_login)
    void gotoLogin() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(loginIntent, Constants.Value.RESULT_LOGIN);
        MainActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /*@OnClick(R.id.top_index)
    void DrawerMenuItem1() {

    }
*/
    //@OnClick(R.id.function)


    //校园卡信息
    @OnClick(R.id.ecard)
    void onCLickEcard(View view) {
        if (aCache.getAsString(Constants.Key.ECARD_PASSWORD) != null) {
            EcardPresenter ecardPresenter = new EcardPresenter(this);
            ecardPresenter.getBalance();
        } else {
            //没有一卡通密码，先输入密码
            final InputPassPopupwindow inputPassPopupwindow = new InputPassPopupwindow(inputPasswordPopupwindowContentView, header.getWidth() - 200, LinearLayout.LayoutParams.WRAP_CONTENT, true);
            inputPassPopupwindow.initPopupWindow(resources.getString(R.string.ecard), MainActivity.this, context);
            inputPassPopupwindow.onConfirm(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatEditText etInputPassword = inputPassPopupwindow.getEtInputPassword();
                    if (etInputPassword.getText().toString().equals("")) {
                        inputPassPopupwindow.getTilPassword().setError("请输入密码");
                        inputPassPopupwindow.getTilPassword().setErrorEnabled(true);
                    } else {
                        inputPassPopupwindow.getTilPassword().setErrorEnabled(false);
                        aCache.put(Constants.Key.ECARD_PASSWORD, etInputPassword.getText().toString());
                        EcardPresenter ecardPresenter = new EcardPresenter(MainActivity.this);
                        ecardPresenter.getBalance();
                        inputPassPopupwindow.dismiss();
                    }

                }
            });
            inputPassPopupwindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        }
    }

    //图书馆信息
    @OnClick(R.id.library)
    void onClickLibrary(View view) {
        if (aCache.getAsString(Constants.Key.LIBRARY_PASSWORD) != null) {
            //跳转图书馆信息Activity
            Intent intent = new Intent();
            intent.setClass(context, LibraryActivity.class);
            startActivityForResult(intent, Constants.Value.RESULT_LIBRARY);
        } else {
            //没有图书馆密码，先输入密码
            final InputPassPopupwindow inputPassPopupwindow = new InputPassPopupwindow(inputPasswordPopupwindowContentView, header.getWidth() - 200, LinearLayout.LayoutParams.WRAP_CONTENT, true);
            inputPassPopupwindow.initPopupWindow(resources.getString(R.string.library), MainActivity.this, context);
            inputPassPopupwindow.onConfirm(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatEditText etInputPassword = inputPassPopupwindow.getEtInputPassword();
                    if (etInputPassword.getText().toString().equals("")) {
                        inputPassPopupwindow.getTilPassword().setError("请输入密码");
                        inputPassPopupwindow.getTilPassword().setErrorEnabled(true);
                    } else {
                        inputPassPopupwindow.getTilPassword().setErrorEnabled(false);
                        aCache.put(Constants.Key.LIBRARY_PASSWORD, etInputPassword.getText().toString());
                        LibraryPresenter libraryPresenter = new LibraryPresenter(MainActivity.this);
                        libraryPresenter.getLibraryReaderInfo();
                        inputPassPopupwindow.dismiss();
                    }

                }
            });
            inputPassPopupwindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        }
    }

    //校园卡信息
    @OnClick(R.id.xtu_net)
    void onClickXtuNet(View view) {
        //弹出校园卡信息
        showPopupWindowXtuNetInfo(view);
    }

    //底部nav点击
    @OnClick(R.id.layout_person)
    void onClickLayoutPerson() {
        pager.setCurrentItem(0, true);
    }

    @OnClick(R.id.layout_function)
    void onClickLayoutFunction() {
        pager.setCurrentItem(1, true);
    }

    @OnClick(R.id.layout_information)
    void onClickLayoutInformation() {
        pager.setCurrentItem(2, true);
    }

    private void showPopupWindowXtuNetInfo(View view) {
        //自定义布局
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = MainActivity.this;
        instance = this;
        resources = getResources();
        aCache = ACache.get(this);
        //if (savedInstanceState == null) {
        inputPasswordPopupwindowContentView = LayoutInflater.from(context).inflate(R.layout.popupwindow_input_password, null);
        initToolbar();
        initView();
        //} else {
        //    Debug.i("savedInstanceState", "not null");

        //}

    }

    @Override
    protected void onResume() {
        super.onResume();
        autoLogin();
    }

    //初始化控件
    private void initView() {
        //抽屉菜单
        View drawerMenuHeader = drawerMenu.inflateHeaderView(R.layout.main_drawer_header);

        drawerMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Snackbar.make(homeContent, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                String top = resources.getString(R.string.top_index);
                switch (menuItem.getItemId()) {
                    case R.id.menu_top_index:
                        break;
                    case R.id.menu_function:
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, AboutUsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_navigation_item_2:

                        break;
                }
                menuItem.setChecked(true);
                drawer.closeDrawers();
                return true;
            }
        });

        //ecardInfo.setVisibility(View.GONE);
        ecard.setClickable(false);
        library.setClickable(false);
        xtuNet.setClickable(false);

        //DrawerLayout
        drawer.setStatusBarBackground(R.color.colorPrimaryDark);

        //Fragment
        List<Fragment> mDatas = new ArrayList<Fragment>();
        final FirstFragment mFirstFragment = new FirstFragment();
        SecondFragment mSecondFragment = new SecondFragment();
        InformationFragment mInformationFagment = new InformationFragment();
        mDatas.add(mFirstFragment);
        mDatas.add(mSecondFragment);
        mDatas.add(mInformationFagment);

        //从username view获取默认text大小
        float scale = resources.getDisplayMetrics().density;
        defualtTextSize = username.getTextSize() / scale;

        showAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
        showAnimation.setDuration(500);
        hiddenAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 0.5f, 0.5f);
        showAnimation.setDuration(500);
        //bottom nav text
        tvPerson.setTextSize(0);
        tvFunction.setTextSize(0);
        tvInformation.setTextSize(0);
        //tvPerson.setVisibility(View.GONE);
        //tvFunction.setVisibility(View.GONE);
        //tvInformation.setVisibility(View.GONE);

        //ViewPager
        pager.setOffscreenPageLimit(mDatas.size());
        HomeViewPagerAdapter mAdpater = new HomeViewPagerAdapter(getSupportFragmentManager(), mDatas);
        pager.setAdapter(mAdpater);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Debug.i("positionOffset", mCurrentPageIndex + "---" + position + "---" + positionOffset + "---" + positionOffsetPixels);
                int x = 0;
                if (position == 0 && x % 2 == 0) { //0->1--" + positionOffsetPixels+"---");
                    onChangeHeaderHeight(positionOffset);
                    onChangeNavText(position, positionOffset);
                } else if (position == 1) {
                    onChangeHeaderHeight(1.0f);
                    onChangeNavText(position, positionOffset);
                } else if (position == 2) {
                    onChangeNavText(position, positionOffset);
                }
                /*if (mCurrentPageIndex == 0 && position == 0 && x % 2 == 0) { //0->1
                    Debug.i("positionOffset", position + "---" + positionOffset + "---" + positionOffsetPixels+"---");
                    onChangeHeaderHeight(positionOffset);
                } else if (mCurrentPageIndex == 1 && position == 0 && x % 2 == 0) {    //1->0
                    onChangeHeaderHeight(positionOffset);
                } else if (mCurrentPageIndex == 1 && position == 1) {    //1->2
                    Debug.i("positionOffset", position + "---" + positionOffset + "---" + positionOffsetPixels+"---");
                    if (positionOffset == 0.0f) {
                        //onChangeHeaderHeight(1.0f);
                    }
                } else if (mCurrentPageIndex == 2 && position == 1) {    //2->1
                    onChangeHeaderHeight(positionOffset);
                }*/
                x++;
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

    /* 改变底部导航 */
    public void onChangeNavText(int position, float positionOffset) {
        List<TextView> mTextViewList = new ArrayList<>();
        mTextViewList.add(tvPerson);
        mTextViewList.add(tvFunction);
        mTextViewList.add(tvInformation);
        switch (position) {
            case 0:
                mTextViewList.get(0).setTextSize(defualtTextSize * (1 - positionOffset));
                mTextViewList.get(1).setTextSize(defualtTextSize * (positionOffset));
                mTextViewList.get(2).setTextSize(0);
                break;
            case 1:
                mTextViewList.get(0).setTextSize(0);
                mTextViewList.get(1).setTextSize(defualtTextSize * (1 - positionOffset));
                mTextViewList.get(2).setTextSize(defualtTextSize * (positionOffset));
                break;
            /*case 2:
                mTextViewList.get(2).setTextSize(defualtTextSize*(1-positionOffset));
                mTextViewList.get(1).setTextSize(defualtTextSize*(positionOffset));
                break;*/

        }

    }


    /**
     * @param param ViewPager切换的进度
     *              改变头部高度，内容透明度
     */
    public void onChangeHeaderHeight(float param) {
        if (headerHeight != -1) {
            float b, y = 2.8f;
            float c, x = 1.2f;
            ViewGroup.LayoutParams headerAvatarParam = headerAvatar.getLayoutParams();
            float headerAvatarSize = getResources().getDimension(R.dimen.avatar_bg_size);
            b = (1.0f - x * param) <= 0 ? 0 : (1.0f - x * param);
            c = (1.0f - y * param) <= 0 ? 0 : (1.0f - y * param);
            float height = c * headerAvatarSize;
            headerAvatarParam.height = (int) height;
            headerAvatarParam.width = headerAvatarParam.height;
            headerAvatar.setLayoutParams(headerAvatarParam);

            //headerParam.height = (int) (headerHeight * (1 - param));
            header.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (headerHeight * (1 - param))));

            //userAvatar.setRotation(480 * (1 - b));
            headerContent.setAlpha(c);
            headerInfo.setAlpha(b);

            //销毁内存
            headerAvatarParam = null;
        }
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        initToolbar();
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
        //首页头部高度,当控件不为null，当前页为第一页并且头部高度不等于总高度三分之一时，改变头部高度为总高度三分之一
        if (pager != null && mCurrentPageIndex == 0 && homeLayoutHeight != 0 && homeLayout.getHeight() != homeLayoutHeight / 3) {
            homeLayoutHeight = homeLayout.getHeight();
            homeLayoutWidth = homeLayout.getWidth();
            //改变头部高度
            headerParam = header.getLayoutParams();
            headerParam.height = homeLayoutHeight / 3;
            header.setLayoutParams(headerParam);
            headerHeight = homeLayoutHeight / 3;

            //改变头像部分高度
            if (headerAvatar.getWidth() > headerContent.getHeight()) {

            }

            CommonFunction.setHomeLayoutHeight(homeLayoutHeight);
            CommonFunction.setHomeLayoutWidth(homeLayoutWidth);
            FirstFragment.getInstance().initLayoutHeight();
            SecondFragment.getInstance().initImgViewPagerHeight(homeLayoutWidth);
            InformationFragment.newInstance();
            InformationFragment.getInstance().layoutInit();

        }
        //抽屉菜单宽度
        int w = (int) (homeLayout.getWidth() - convertDpToPixel(android.R.attr.actionBarSize, context));
        if (drawerMenu != null && drawerMenu.getWidth() != w) {
            ViewGroup.LayoutParams param = drawerMenu.getLayoutParams();
            param.width = w;
            drawerMenu.setLayoutParams(param);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.Value.RESULT_LOGIN:
                if (resultCode == RESULT_OK) {
                    isLogined(data.getStringExtra("name"));
                    autoGetData();
                }
                break;
            case Constants.Value.RESULT_LIBRARY:
                if (resultCode == RESULT_OK) {
                    getLibraryRentLsit(0, null);
                    onGetLibraryReaderInfo(0, null);
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        //stuNum.setText(aCache.getAsString(Constants.Key.SID));
        stuNum.setText(aCache.getAsString(Constants.Key.SID));
        username.setText(name);
        btnLogin.setVisibility(View.GONE);
        stuNum.setVisibility(View.VISIBLE);

        //校园卡
        ecard.setClickable(true);

        //图书馆
        library.setClickable(true);

        //校园网
        xtuNet.setClickable(true);
    }

    //自动登录，如果有缓存
    public void autoLogin() {
        if (aCache.getAsString(Constants.Key.SID) != null || aCache.getAsString(Constants.Key.PASSWORD) != null) {
            isLogined(aCache.getAsString(Constants.Key.NAME));
            doneGetBalance(0, null);
            getCampusNetwork(0, null);
            autoGetData();
        }
    }

    public void autoGetData() {
        //一卡通余额
        if (aCache.getAsString(Constants.Key.ECARD_PASSWORD) != null) {
            EcardPresenter ecardPresenter = new EcardPresenter(this);
            ecardPresenter.getBalance();
        }

        //校园网
        CampusNetPresenter campusNetPresenter = new CampusNetPresenter(this);
        campusNetPresenter.getCampusNetwork();

        //图书馆
        if (aCache.getAsString(Constants.Key.LIBRARY_PASSWORD) != null) {
            LibraryPresenter libraryPresenter = new LibraryPresenter(this);
            libraryPresenter.getLibraryReaderInfo();
            libraryPresenter.getLibraryRentList();
        }

        //节假日
        //节假日数据在FirstFragment中加载
    }

    public void logout() {
        //个人信息
        username.setText(R.string.default_username);
        btnLogin.setVisibility(View.VISIBLE);
        //btnLogin.setText(resources.getString(R.string.login));
        stuNum.setVisibility(View.GONE);
        UserModel.setCacheNone(this);
        UserModel.getaCache().clear();
        //校园卡
        ecard.setClickable(false);
        ecardBalance.setText(R.string.default_money);
        ecardUnclaimed.setText(R.string.default_money);

        //图书馆
        library.setClickable(false);
        libraryDebt.setText(R.string.default_money);
        libraryRentNum.setText(R.string.default_money);

        //校园网
        xtuNet.setClickable(false);
        xtuNetworkStatus.setText(R.string.default_money);
        xtuNetworkBalance.setText(R.string.default_money);
    }

    @Override
    public void doneGetBalance(int code, EcardModel ecardModel) {
        try {
            if (code == 0) {

                ecardBalance.setText(aCache.getAsString(Constants.Key.BALANCE));
                ecardUnclaimed.setText(aCache.getAsString(Constants.Key.UNCLAIMED));

            } else if (code == 1) {
                ecardBalance.setText(R.string.default_money);
                ecardUnclaimed.setText(R.string.default_money);
            } else {
                ecardBalance.setText(R.string.default_money);
                ecardUnclaimed.setText(R.string.default_money);
                errorToast(this, code);
            }
            ecardBalance.setVisibility(View.VISIBLE);
            ecardUnclaimed.setVisibility(View.VISIBLE);
            pbEcardBalance.setVisibility(View.GONE);
            pbEcardUnclaimed.setVisibility(View.GONE);
        } catch (NullPointerException e) {
            //ecardBalance.setText(R.string.default_money);
            //ecardUnclaimed.setText(R.string.default_money);
        }
    }

    @Override
    public void onGetBalance() {
        ecardBalance.setVisibility(View.GONE);
        ecardUnclaimed.setVisibility(View.GONE);
        pbEcardBalance.setVisibility(View.VISIBLE);
        pbEcardUnclaimed.setVisibility(View.VISIBLE);
    }

    @Override
    public void login(int code, StudentInfoModel studentInfoModel) {
        if (code == 0) {
            isLogined(studentInfoModel.getData().getName());
        } else {
            errorToast(this, code);
        }
    }

    @Override
    public void getCampusNetwork(int code, @Nullable CampusNetwork campusNetwork) {
        if (code == 0) {
            try {
                xtuNetworkStatus.setText(aCache.getAsString(Constants.Key.CAMPUS_NETWORK_STATUS));
                xtuNetworkBalance.setText(aCache.getAsString(Constants.Key.CAMPUS_NETWORK_BALANCE));
            } catch (NullPointerException e) {
            }
        } else {
            errorToast(this, code);
        }
    }

    @Override
    public void onGetLibraryReaderInfo(int code, @Nullable LibraryReaderInfoModel libraryReaderInfoModel) {
        if (code == 0) {
            try {
                libraryDebt.setText(aCache.getAsString(Constants.Key.LIBRARY_DEBT));
            } catch (NullPointerException e) {
            }
        } else if (code == 1) {
            Toast.makeText(context, resources.getString(R.string.library) + resources.getString(R.string.password_is_wrong), Toast.LENGTH_SHORT);
        } else {
            errorToast(this, code);
        }
    }

    @Override
    public void getLibraryRentLsit(int code, @Nullable LibraryRentListModel libraryRentListModel) {
        if (code == 0) {
            try {
                libraryRentNum.setText(aCache.getAsString(Constants.Key.LIBRARY_RENT_NUM));
            } catch (NullPointerException e) {
            }
        } else {
            errorToast(this, code);
        }
    }

    @Override
    public Context getViewContext() {
        return context;
    }
}
