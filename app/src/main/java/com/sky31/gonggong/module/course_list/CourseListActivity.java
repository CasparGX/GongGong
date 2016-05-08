package com.sky31.gonggong.module.course_list;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sky31.gonggong.R;
import com.sky31.gonggong.base.BaseActivity;
import com.sky31.gonggong.config.CommonFunction;
import com.sky31.gonggong.model.CourseListModel;
import com.sky31.gonggong.model.CurrentWeekModel;
import com.sky31.gonggong.module.current_week.CurrentWeekPresent;
import com.sky31.gonggong.module.current_week.CurrentWeekProxy;
import com.sky31.gonggong.module.current_week.CurrentWeekView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CourseListActivity extends BaseActivity implements CourseListView,CurrentWeekView {


    @Bind(R.id.course_list_week_title)
    TextView courseListWeekTitle;
    @Bind(R.id.course_list_toolbar)
    Toolbar courseListToolbar;
    Dialog weekSelectorDialog;
    List<String> weekList ;

    private int currentWeek = 0;

    CourseListFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        ButterKnife.bind(this);

        initData();
        //initFragment();






    }

    private void initFragment(int week) {

        fragment = CourseListFragment.newInstance(week);
        FragmentManager manager = getSupportFragmentManager();

        manager.beginTransaction().add(R.id.course_list_fragment, fragment).commit();
        Log.e("Activity","log!!!");

    }


    private void initPopWindow() {
//        LinearLayout.LayoutParams params =
//                new LinearLayout.LayoutParams(300,
//                        400);
//
//        params.setMargins(0,0,0,0);
        weekSelectorDialog = new Dialog(this,R.style.dialog);
        //LayoutInflater inflater = LayoutInflater.from(this);


        weekSelectorDialog.setContentView(R.layout.popupwindow_week_selector);
        Window window = weekSelectorDialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        window.setGravity(Gravity.TOP);
        //Log.d("height",courseListToolbar.getMinimumHeight()+"");
        params.y = courseListToolbar.getMinimumHeight();
        window.setAttributes(params);
        //WindowManager.LayoutParams lp = window.getAttributes();
        //window.setGravity(Gravity.CENTER_HORIZONTAL);
        //window.setContentView(R.layout.popupwindow_week_selector);

        //lp.width = (int) getResources().getDimension(R.dimen.week_select_dialog_width);
        //lp.height = (int) getResources().getDimension(R.dimen.week_select_dialog_height);
        //window.setAttributes(lp);



        weekList = new ArrayList<>();
        for (int i = 1;i <=25;i++){

            weekList.add("第"+i+"周");

        }
        ListView weekSelectorList = (ListView) window.findViewById(R.id.course_week_selector);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>
                        (this,R.layout.week_selector_item,weekList);

        weekSelectorList.setAdapter(arrayAdapter);

        weekSelectorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //重新设置
                //fragment.resetWeek(position+1);

                int pos = position+1;
                initFragment(pos);
                courseListWeekTitle.setText("第"+pos+"周");

                Toast.makeText(CourseListActivity.this,weekList.get(position),
                        Toast.LENGTH_SHORT).show();
                weekSelectorDialog.dismiss();
            }
        });

    }


    //请求当前为第X周
    private void initData(){
        CurrentWeekProxy proxy = new CurrentWeekProxy(this,this);
        proxy.setRequestProxy();
    }

    private void initToolBar() {

        setSupportActionBar(courseListToolbar);
        courseListToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        courseListWeekTitle.setText("第"+currentWeek+"周");

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
                for (int i = 0; i <25;i++){
                    int w = 1+i;

                    if (currentWeek==w){
                        weekList.set(i,"第"+w+"周"+"(当前)");
                    }
                    else {
                        weekList.set(i,"第"+w+"周");
                    }
                }
            }
        });
    }

    @Override
    public CourseListModel courseList(CourseListModel courseList, int code) {

//        if (code == 0) {
//            for (List<CourseListModel.DataBean> listModels : courseList.getData()) {
//
//                for (CourseListModel.DataBean data : listModels) {
//
//                    Log.d("list", data.getWeek());
//                    Log.d("list", data.getCourse());
//
//                }
//            }
//        } else {
//            CommonFunction.errorToast(CourseListActivity.this, code);
//        }
        return courseList;
    }


    //回调借口。即请求完成后执行fragment加载。当前课表。
    @Override
    public void currentWeek(CurrentWeekModel model, int code) {

        if (code==0){
            currentWeek = model.getData().getWeek();
            initPopWindow();
            initToolBar();
            initFragment(currentWeek);

        }
        else {
            CommonFunction.errorToast(this,code);
        }

    }
}
