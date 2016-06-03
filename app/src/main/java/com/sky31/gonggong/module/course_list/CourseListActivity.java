package com.sky31.gonggong.module.course_list;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.widgets.ProgressDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sky31.gonggong.R;
import com.sky31.gonggong.base.BaseActivity;
import com.sky31.gonggong.config.CommonFunction;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.CourseListModel;
import com.sky31.gonggong.model.CurrentWeekModel;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.module.current_week.CurrentWeekProxy;
import com.sky31.gonggong.module.current_week.CurrentWeekView;
import com.sky31.gonggong.util.ACache;
import com.sky31.gonggong.util.Debug;
import com.sky31.gonggong.widget.ListViewWithoutScroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.sky31.gonggong.config.CommonFunction.resToBitmap;

public class CourseListActivity extends BaseActivity implements CourseListView, CurrentWeekView {


    @Bind(R.id.course_list_week_title)
    TextView courseListWeekTitle;
    @Bind(R.id.course_list_toolbar)
    Toolbar courseListToolbar;
    Dialog weekSelectorDialog;
    List<String> weekList;
    @Bind(R.id.course_list_timeline)
    ListViewWithoutScroll courseListTimeline;
    @Bind(R.id.course_list_content)
    RelativeLayout courseListContent;
    @Bind(R.id.day_of_week_grid)
    GridView dayOfWeekGrid;
    @Bind(R.id.course_content)
    LinearLayout courseContent;
    private CourseListModel courseList;

    private ListView weekSelectorList;
    List<TextView> textViewList;
    private int currentWeek = 0;
    private int currenTrueWeek = 0;



    private ProgressDialog dialog;

    private Bitmap bmBg = null;


    private Map<String, Integer> courseToColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        ButterKnife.bind(this);


        dialog = new ProgressDialog(this,getResources().getString(R.string.get_data_now));

        init();

        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        Debug.i("CourseListActivity", "onDestroy");
        setContentView(R.layout.view_null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void init() {
        if (bmBg == null) {
            bmBg = resToBitmap(getApplicationContext(), R.drawable.bg_course);
            Drawable drawable = new BitmapDrawable(bmBg);
            courseContent.setBackground(drawable);
        }
    }


    private void initPopWindow() {
        weekSelectorDialog = new Dialog(this, R.style.dialog);
        //LayoutInflater inflater = LayoutInflater.from(this);

        weekSelectorDialog.setContentView(R.layout.popupwindow_week_selector);
        Window window = weekSelectorDialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        window.setGravity(Gravity.TOP);

        params.y = courseListToolbar.getMinimumHeight();
        window.setAttributes(params);


        weekList = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {

            weekList.add("第" + i + "周");

        }
        weekSelectorList = (ListView) window.findViewById(R.id.course_week_selector);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>
                        (this, R.layout.week_selector_item, weekList);

        weekSelectorList.setAdapter(arrayAdapter);

        weekSelectorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //重新设置
                //fragment.resetWeek(position+1);

                int pos = position + 1;
                courseListWeekTitle.setText("第" + pos + "周");

                Toast.makeText(CourseListActivity.this, weekList.get(position),
                        Toast.LENGTH_SHORT).show();
                currentWeek = pos;
                courseListContent.removeAllViews();
                initCourseData();
                weekSelectorDialog.dismiss();
            }
        });

    }


    //请求当前为第X周
    private void initData() {
        CurrentWeekProxy proxy = new CurrentWeekProxy(getApplicationContext(), this);
        proxy.setRequestProxy();
        dialog.show();
    }

    private void initToolBar() {

        //setSupportActionBar(courseListToolbar);
        courseListToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        courseListToolbar.inflateMenu(R.menu.base_toolbar_menu);
        courseListToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_refresh:
                        initData();

                }
                return false;
            }
        });
        courseListWeekTitle.setText("第" + currenTrueWeek + "周");

        //点击周数开头设置，
        courseListWeekTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weekSelectorDialog.show();
            }
        });



        //设置当时展开周数选择时候，设置当前周数。
        weekSelectorDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                for (int i = 0; i < 25; i++) {
                    int w = 1 + i;

                    if (currenTrueWeek == w) {
                        weekList.set(i, "第" + w + "周" + "(当前)");
                    } else {
                        weekList.set(i, "第" + w + "周");
                    }
                }

                weekSelectorList.setSelection(currentWeek - 3);

            }
        });
    }

    @Override
    public CourseListModel courseList(CourseListModel courseList, int code) {

        if (code == 0) {
            this.courseList = courseList;
            courseList.setCache();
            initView();
        } else {
            CommonFunction.errorToast(getApplicationContext(), code);
        }

        return courseList;
    }


    //回调借口。即请求完成后执行fragment加载。当前课表。

    /***
     * 首先优先获取当前第几周。如果出现错误再读取缓存内的第几周。
     *
     * @param model
     * @param code
     */
    @Override
    public void currentWeek(CurrentWeekModel model, int code) {

        dialog.dismiss();
        ACache aCache = UserModel.getaCache();
        if (code == 0) {
            currentWeek = model.getData().getWeek();
            currenTrueWeek = currentWeek;
            aCache.put(Constants.Key.CURRENT_WEEK, currenTrueWeek + "", ACache.TIME_DAY);

            Log.d("current_week", aCache.getAsString(Constants.Key.CURRENT_WEEK));
            initPopWindow();
            initToolBar();
            initGridView();
            initCourseData();
        } else {
            if (aCache.getAsString(Constants.Key.CURRENT_WEEK) == null) {
                CommonFunction.errorToast(getApplicationContext(), code);
            } else {
                currentWeek = Integer.parseInt(aCache.getAsString(Constants.Key.CURRENT_WEEK));
                initPopWindow();
                initToolBar();
                initGridView();
                initCourseData();
            }

        }

    }

    private void initGridView() {

        WeekTimeLineAdapter adapter = new WeekTimeLineAdapter(this);
        dayOfWeekGrid.setAdapter(adapter);

    }

    private void initView() {

        List<CourseListModel.DataBean> dataBeen = new ArrayList<>();

        int[] colors = getResources().getIntArray(R.array.course_list_bg_color);
        int arrPosition = 1;
        int lenth = colors.length;
        courseToColor = new HashMap<>();
        for (List<CourseListModel.DataBean> dataBeanList : courseList.getData()) {
            for (CourseListModel.DataBean bean : dataBeanList) {
                String strs[] = bean.getWeek().split(",");
                for (String str : strs) {
                    if (str.equals(currentWeek + "")) {
                        //设置一个 k,v形式。课程对应一种背景色。
                        if (!courseToColor.containsKey(bean.getCourse())) {
                            courseToColor.put(bean.getCourse(), colors[arrPosition % lenth]);
                            arrPosition++;
                        }
                        dataBeen.add(bean);
                    }
                }

            }
        }

        drawTextViewsByCourse(dataBeen);

    }


    //private  List<Integer>

    private void drawTextViewsByCourse(List<CourseListModel.DataBean> dataBeen) {

        //设置每个格子宽度。
        // List<Integer> integers = new ArrayList<>();

        //courseListContent.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
        int width = courseListContent.getWidth() / 7;
        //int height = (int) getResources().getDimension(R.dimen.course_list_item_height);
        int height = (int) getResources().getDimension(R.dimen.course_list_item_height);
        //int borderHeight = (int) CommonFunction.convertDpToPixel(1.0f, this);
        int len = dataBeen.size();

        Log.e("parm ->minheight", height + "");
        Log.e("parm ->width", width + "");
        for (int i = 0; i < len; i++) {
            CourseListModel.DataBean bean = dataBeen.get(i);
            int x = Integer.parseInt(bean.getSection_start());
            int y = Integer.parseInt(bean.getSection_end());
            int day = Integer.parseInt(bean.getDay());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height * (y - x + 1));
            //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            //params.setMargins((day - 1) * width, (int) ((x - 1) * (height + borderHeight)), 0, 0);

            TextView textView = new TextView(CourseListActivity.this);
            textView.setLayoutParams(params);
            textView.setPadding(13, 5, 5, 5);
            //textView.setBackground(getResources().getDrawable(R.drawable.ic_course_bg_bohelv));
            textView.setBackgroundColor(courseToColor.get(bean.getCourse()));
            textView.setAlpha(0.8f);
            textView.setTextSize(getResources().getDimension(R.dimen.course_list_item_fontsize));

            textView.setTextColor(Color.WHITE);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            //textView.setVisibility(View.VISIBLE);


            textView.setText(bean.getCourse() + "#" + bean.getLocation());

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CourseListActivity.this, "courseList", Toast.LENGTH_SHORT).show();
                }
            });


            courseListContent.addView(textView);

        }


    }

    /****
     * 首先获取课表数据，再根据周数加载视图。
     */
    private void initCourseData() {
        courseList = new CourseListModel();
        ACache aCache = UserModel.getaCache();


        if (aCache.getAsString(Constants.Key.COURSE_LIST) != null) {
            Gson gson = new Gson();
            Log.w("Course_list", aCache.getAsString(Constants.Key.COURSE_LIST));
            List<List<CourseListModel.DataBean>> dataList = gson.fromJson(
                    aCache.getAsString(Constants.Key.COURSE_LIST),
                    new TypeToken<List<List<CourseListModel.DataBean>>>() {
                    }.getType());

            courseList.setData(dataList);
            //View view = LayoutInflater.from(getContext()).inflate(R.layout.course_list_timeline_header, null);
            //courseListTimeline.addHeaderView(view);
            CourseTimeHeaderAdapter adapter = new CourseTimeHeaderAdapter(CourseListActivity.this);
            courseListTimeline.setAdapter(adapter);
            initView();
        } else {
            CourseListRequestProxy requestProxy = new CourseListRequestProxy(CourseListActivity.this, this);
            requestProxy.setReauestProxy();
        }
    }
}
