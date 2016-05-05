package com.sky31.gonggong.module.grade.report;

import com.sky31.gonggong.model.GradeReportModel;

/**
 * Created by root on 16-4-29.
 */
public interface ReportView {
    void onGetReport();

    void finishGetReport(int code, GradeReportModel gradeReportModel);
}
