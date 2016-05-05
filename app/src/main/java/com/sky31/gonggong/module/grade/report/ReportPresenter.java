package com.sky31.gonggong.module.grade.report;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.GradeReportModel;
import com.sky31.gonggong.model.UserModel;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 16-4-29.
 */
public class ReportPresenter {
    private ReportView reportView;
    private ApiService apiService;
    private String sid, password;

    public ReportPresenter(ReportView reportView) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        this.reportView = reportView;
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.PASSWORD);
    }

    public void getGradeReport() {
        reportView.onGetReport();
        Call<GradeReportModel> call = apiService.getGradeReport(sid, password);
        call.enqueue(new Callback<GradeReportModel>() {
            @Override
            public void onResponse(Response<GradeReportModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    GradeReportModel gradeReportModel = response.body();
                    reportView.finishGetReport(code, gradeReportModel);
                } else {
                    reportView.finishGetReport(1, null);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                reportView.finishGetReport(1, null);
            }
        });
    }
}
