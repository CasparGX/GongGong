package cc.xaabb.gonggong.module.grade.report;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;

import com.gc.materialdesign.widgets.ProgressDialog;
import com.sky31.gonggong.R;
import cc.xaabb.gonggong.config.CommonFunction;
import cc.xaabb.gonggong.model.GradeDetailsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GradeReportFragment extends Fragment implements ReportDetailView{


    @Bind(R.id.grade_report_listview)
    ExpandableListView gradeReportListview;

    private List<List<GradeDetailsModel.DataEntity>> model;

    private ProgressDialog dialog;

    private int expandCount = 0;



    public GradeReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grade_report, container, false);
        ButterKnife.bind(this, view);


        initData();

        return view;
    }

    private void initData() {

        ReportDetailPresenter presenter = new ReportDetailPresenter(this);
        presenter.getGradeReport();
    }


    private void sortData(GradeDetailsModel model){

        this.model = new ArrayList<>();

        List<GradeDetailsModel.DataEntity> entities = new ArrayList<>();
        List<GradeDetailsModel.DataEntity> currentData = model.getData();
        String term = currentData.get(0).getTerm();
        for (int i = 0; i < currentData.size();i++){

            //Log.d("grade-code",currentData.get(i).getTerm()+"");
            GradeDetailsModel.DataEntity entity = currentData.get(i);
            if (entity.getTerm().equals(term)){
                entities.add(entity);
            }
            else {
                term = entity.getTerm();    //切换当前学期
                this.model.add(entities);   //添加上个学期集合
                entities = new ArrayList<>();   //重新实例化
                entities.add(entity);       //添加当前学期成绩
            }
        }
        this.model.add(entities);

       initList();

    }

    private void initList() {

        GradeReportListViewAdapter adapter =
                new GradeReportListViewAdapter(getContext(),this.model);
        gradeReportListview.setAdapter(adapter);

        gradeReportListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                //收集展开状态，便于标签位移
                if (parent.isGroupExpanded(groupPosition)){

                    expandCount++;
                }
                else {
                    expandCount--;
                }

                return false;
            }
        });

        gradeReportListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        gradeReportListview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                expandCount++;

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onGetReport() {

        dialog = new ProgressDialog(getContext(),"翼宝正在加载数据");
        dialog.show();
    }

    @Override
    public void finishGetReport(int code, GradeDetailsModel gradeDetailsModel) {
        dialog.dismiss();
        if (code == 0){
            Log.d("grade-code",code+"");
            if (gradeDetailsModel.getData() !=null && gradeDetailsModel.getData().size() != 0){

                sortData(gradeDetailsModel);
            }


        }
        else {
            CommonFunction.errorToast(getContext().getApplicationContext(), code);
        }
    }
}
