package cc.xaabb.gonggong.module.grade.report;

import cc.xaabb.gonggong.model.GradeDetailsModel;

/**
 * Created by root on 16-4-29.
 */
public interface ReportDetailView {
    void onGetReport();

    void finishGetReport(int code, GradeDetailsModel gradeDetailsModel);
}
