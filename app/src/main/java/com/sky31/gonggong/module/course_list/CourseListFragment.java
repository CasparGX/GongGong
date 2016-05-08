package com.sky31.gonggong.module.course_list;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sky31.gonggong.R;
import com.sky31.gonggong.config.CommonFunction;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.CourseListModel;
import com.sky31.gonggong.model.CurrentWeekModel;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.module.current_week.CurrentWeekView;
import com.sky31.gonggong.util.ACache;
import com.sky31.gonggong.util.Debug;
import com.sky31.gonggong.widget.ListViewWithoutScroll;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CourseListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseListFragment extends Fragment implements CourseListView, CurrentWeekView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    @Bind(R.id.course_list_timeline)
    ListViewWithoutScroll courseListTimeline;
    @Bind(R.id.course_list_content)
    LinearLayout courseListContent;


    // TODO: Rename and change types of parameters

    private CourseListModel courseList;
    private int currentWeek = 0;

    List<TextView> textViewList;


    //private WindowManager manager = null;

    public CourseListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CourseListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseListFragment newInstance(int currentWeek) {

        CourseListFragment fragment = new CourseListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, currentWeek);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentWeek = getArguments().getInt(ARG_PARAM1);
            Debug.i("currentWeek", currentWeek + "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);

        View fragmentView = inflater.inflate(R.layout.fragment_course_list, container, false);
        //fragmentView.invalidate();

        ButterKnife.bind(this, fragmentView);

        initData();

        //initView();



        //manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Log.e("Fragment", "log!!!");

        return fragmentView;
    }


    @Override
    public void onResume() {

        super.onResume();
    }

    /***
     * 加载列表
     */
    private void initView() {

        final List<CourseListModel.DataBean> dataBeen = new ArrayList<>();

        for (List<CourseListModel.DataBean> dataBeanList : courseList.getData()) {
            for (CourseListModel.DataBean bean : dataBeanList) {

                String strs[] = bean.getWeek().split(",");
                for (String str : strs) {
                    if (str.equals(currentWeek+"")){
                        dataBeen.add(bean);
                    }
                }

            }
        }



    }


    //private  List<Integer>

    private void drawTextViewsByCourse(List<CourseListModel.DataBean> dataBeen) {

        //设置每个格子宽度。
       // List<Integer> integers = new ArrayList<>();

        //courseListContent.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
        int width = 150;
        int height = (int) CommonFunction.convertDpToPixel(68, getContext());
        int len = dataBeen.size();

        Log.e("parm ->minheight", height + "");
        Log.e("parm ->width", width + "");
        for (int i = 0; i < len; i++) {

            CourseListModel.DataBean bean = dataBeen.get(i);

            int x = Integer.parseInt(bean.getSection_start());
            int y = Integer.parseInt(bean.getSection_end());
            int day = Integer.parseInt(bean.getDay());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height * (y - x + 1));
            //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            //params.setMargins((day - 1) * width, (int) ((x - 1) * CommonFunction.convertDpToPixel(68, getContext())), 0, 0);

            TextView textView = new TextView(getContext());
            textView.setLayoutParams(params);
            textView.getLayoutParams().width = 150;
            //textView.setPadding(1, 1, 1, 1);
            //textView.setBackground(getResources().getDrawable(R.drawable.ic_course_bg_bohelv));
            textView.setBackgroundColor(Color.BLACK);
            //textView.setAlpha(0.5f);
            textView.setTextSize(getResources().getDimension(R.dimen.course_list_item_fontsize));
            textView.setTextColor(Color.WHITE);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            //textView.setVisibility(View.VISIBLE);


            textView.setText(bean.getCourse() + "#" + bean.getLocation());

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "呵呵呵", Toast.LENGTH_SHORT).show();
                }
            });



//            textView.setHeight(height);
//            textView.setHeight(width);

            //textView.setY((int) );


            courseListContent.addView(textView);

        }


    }

    /****
     * 首先获取课表数据，再根据周数加载视图。
     */
    private void initData() {
        courseList = new CourseListModel();
        ACache aCache = UserModel.getaCache();

        //CurrentWeekProxy
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
            CourseTimeHeaderAdapter adapter = new CourseTimeHeaderAdapter(getContext());
            courseListTimeline.setAdapter(adapter);
            initView();
        } else {
            CourseListRequestProxy requestProxy = new CourseListRequestProxy(getContext(), this);
            requestProxy.setReauestProxy();
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public CourseListModel courseList(CourseListModel courseList, int code) {
        if (code == 0) {
            this.courseList = courseList;
            courseList.setCache();
            initData();
        } else {
            CommonFunction.errorToast(getContext(), code);
        }

        return courseList;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void currentWeek(CurrentWeekModel model, int code) {

    }


//    public void resetWeek(int week){
//
//        currentWeek = week;
//
//
//
//        Log.d("linearlayout:",courseListContent.getChildCount()+"");
//        //清除当前layout节点下所有子元素。
//        for (TextView textView : textViewList){
//
//            courseListContent.removeView(textView);
//
//        }
//
//        textViewList = null;
//        courseListContent.removeAllViewsInLayout();
//        courseListContent.invalidate();
//        courseListContent.requestLayout();
//        //ViewParent parent = courseListContent.getParent();
//        //parent.requestLayout();
//        Log.d("linearlayout:",courseListContent.getChildCount()+"");
//        //courseListContent.removeAllViews();
//        //courseListContent.getParent().in
//        initData();
//        initView();
//
//    }

}
