package cc.xaabb.gonggong.module.grade.report;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky31.gonggong.R;
import cc.xaabb.gonggong.model.GradeDetailsModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wukunguang on 16-5-17.
 */
public class GradeReportListViewAdapter extends BaseExpandableListAdapter {


    private Context context;
    private List<List<GradeDetailsModel.DataEntity>> lists;

    private int[] colors;
    public GradeReportListViewAdapter(Context context, List<List<GradeDetailsModel.DataEntity>> lists) {
        this.context = context;
        this.lists = lists;
        colors = context.getResources().getIntArray(R.array.course_list_bg_color);
    }

    @Override
    public int getGroupCount() {
        return lists.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return lists.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return lists.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return lists.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        List<GradeDetailsModel.DataEntity> models = lists.get(groupPosition);
        ViewHolderGroup holderGroup = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grade_detail_list_parent, null);
            holderGroup = new ViewHolderGroup(convertView);
            convertView.setTag(holderGroup);
        } else {
            holderGroup = (ViewHolderGroup) convertView.getTag();
        }

        //当前学期 = 当前第X组数据+1；
        int currentTerm = groupPosition + 1;
        holderGroup.gradeTermTile.setText("第" + currentTerm + "学期");
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        GradeDetailsModel.DataEntity dataEntity = lists.get(groupPosition).get(childPosition);

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grade_detail_child, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Resources res = context.getResources();
        viewHolder.gradeCourseName.
                setText(dataEntity.getCourse());
        viewHolder.gradeDetailCompGrade.
                setText(res.getString(R.string.grade_detail_comp_grade) +
                        dataEntity.getComp_grade());
        viewHolder.gradeDetailCredit.
                setText(res.getString(R.string.grade_detail_credit) + dataEntity.getCredit());

        viewHolder.gradeDetailType.
                setText(res.getString(R.string.grade_detail_type)+ dataEntity.getType());

        viewHolder.gradeDetailExamGrade.
                setText(res.getString(R.string.grade_detail_exam_grade) +
                        dataEntity.getExam_grade());
        viewHolder.gradeDetailDailyGrade.
                setText(res.getString(R.string.grade_detail_daily_grade) +
                        dataEntity.getDaily_grade());
        viewHolder.gradeUnderLine.setBackgroundColor(colors[childPosition%colors.length]);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class ViewHolderGroup {
        @Bind(R.id.grade_term_title)
        TextView gradeTermTile;
        @Bind(R.id.grade_term_arrow)
        ImageView gradeTermArrow;

        ViewHolderGroup(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder {
        @Bind(R.id.grade_course_name)
        TextView gradeCourseName;
        @Bind(R.id.grade_detail_type)
        TextView gradeDetailType;
        @Bind(R.id.grade_detail_credit)
        TextView gradeDetailCredit;
        @Bind(R.id.grade_detail_daily_grade)
        TextView gradeDetailDailyGrade;
        @Bind(R.id.grade_detail_exam_grade)
        TextView gradeDetailExamGrade;
        @Bind(R.id.grade_detail_comp_grade)
        TextView gradeDetailCompGrade;
        @Bind(R.id.grade_under_line)
        ImageView gradeUnderLine;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
