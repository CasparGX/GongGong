package com.sky31.gonggong.module.login;

import com.sky31.gonggong.base.BasePresenter;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.Result;
import com.sky31.gonggong.model.StudentInfoModel;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.util.ApiException;
import com.sky31.gonggong.util.BaseSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 16-3-17.
 */
public class LoginPresenter extends BasePresenter{
    private ApiService apiService;
    private String sid, password;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        //init Retrofit
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        apiService = mRetrofit.create(ApiService.class);
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.PASSWORD);
        this.loginView = loginView;
    }

    public void login(String sid, String password) {
        getStudentInfo(sid, password);
        //ToDo 获取其他信息
    }

    public void getStudentInfo(final String sid, final String password) {
        apiService.getStudentInfo(sid, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<StudentInfoModel>() {

                    @Override
                    public void onNext(StudentInfoModel studentInfoModel) {
                        StudentInfoModel _studentInfoModel = studentInfoModel;
                        _studentInfoModel.setCache();
                        UserModel.setSid(sid);
                        UserModel.setPassword(password);
                        loginView.login(_studentInfoModel.getCode(), _studentInfoModel);

                    }

                    @Override
                    public void onApiException(ApiException e) {
                        if (e.getErrorCode()==1) {
                            UserModel.setCacheNone(loginView.getViewContext());
                        }
                        loginView.login(e.getErrorCode(), null);
                    }

                });

        /*Call<StudentInfoModel> call = apiService.getStudentInfo(sid, password);
        call.enqueue(new Callback<StudentInfoModel>() {
            @Override
            public void onResponse(Response<StudentInfoModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    StudentInfoModel studentInfoModel = response.body();
                    studentInfoModel.setCache();
                    UserModel.setSid(sid);
                    UserModel.setPassword(password);
                    loginView.login(code, studentInfoModel);
                } else if (code == 1) {
                    UserModel.setCacheNone(loginView.getViewContext());
                    loginView.login(code, null);
                } else {
                    loginView.login(code, null);
                }

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });*/
    }
}
