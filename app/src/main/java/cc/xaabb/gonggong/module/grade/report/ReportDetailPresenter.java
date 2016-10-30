package cc.xaabb.gonggong.module.grade.report;

import cc.xaabb.gonggong.base.BasePresenter;
import cc.xaabb.gonggong.config.Constants;
import cc.xaabb.gonggong.model.ApiService;
import cc.xaabb.gonggong.model.GradeDetailsModel;
import cc.xaabb.gonggong.model.UserModel;
import cc.xaabb.gonggong.util.ApiException;
import cc.xaabb.gonggong.util.BaseSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 16-4-29.
 */
public class ReportDetailPresenter extends BasePresenter {
    private ReportDetailView ReportDetailView;
    private ApiService apiService;
    private String sid, password;

    public ReportDetailPresenter(ReportDetailView ReportDetailView) {
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        apiService = mRetrofit.create(ApiService.class);
        this.ReportDetailView = ReportDetailView;
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.PASSWORD);
    }

    public void getGradeReport() {
        ReportDetailView.onGetReport();

        apiService.getGradeDetails(sid, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<GradeDetailsModel>() {

                    @Override
                    public void onNext(GradeDetailsModel gradeDetailsModel) {
                        GradeDetailsModel _gradeDetailsModel = gradeDetailsModel;
                        ReportDetailView.finishGetReport(_gradeDetailsModel.getCode(), _gradeDetailsModel);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        ReportDetailView.finishGetReport(1, null);
                    }

                });

        /*Call<GradeDetailsModel> call = apiService.getGradeDetails(sid, password);
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
        });*/
    }
}
