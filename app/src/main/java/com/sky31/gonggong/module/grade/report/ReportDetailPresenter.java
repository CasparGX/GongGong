package com.sky31.gonggong.module.grade.report;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.GradeDetailsModel;
import com.sky31.gonggong.model.GradeReportModel;
import com.sky31.gonggong.model.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by root on 16-4-29.
 */
public class ReportDetailPresenter {
    private ReportDetailView ReportDetailView;
    private ApiService apiService;
    private String sid, password;

    public ReportDetailPresenter(ReportDetailView ReportDetailView) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        this.ReportDetailView = ReportDetailView;
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.PASSWORD);
    }

    public void getGradeReport() {
        ReportDetailView.onGetReport();
        Call<GradeDetailsModel> call = apiService.getGradeDetails(sid, password);
        call.enqueue(new Callback<GradeDetailsModel>() {
            @Override
            public void onResponse(Response<GradeDetailsModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    GradeDetailsModel gradeDetailsModel = response.body();
                    ReportDetailView.finishGetReport(code, gradeDetailsModel);
                } else {
                    ReportDetailView.finishGetReport(1, null);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                ReportDetailView.finishGetReport(1, null);
            }
        });
    }
}
