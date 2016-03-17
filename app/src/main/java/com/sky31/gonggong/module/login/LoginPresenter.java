package com.sky31.gonggong.module.login;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.StudentInfoModel;
import com.sky31.gonggong.model.UserModel;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 16-3-17.
 */
public class LoginPresenter {
    private ApiService apiService;
    private String sid, password;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        //init Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.PASSWORD);
        this.loginView = loginView;
    }

    public void login(String sid, String password) {
        getStudentInfo(sid, password);
        //initSidAndPassword();
        //getBalance(sid, password);
    }

    public void getStudentInfo(final String sid, final String password) {
        Call<StudentInfoModel> call = apiService.getStudentInfo(sid, password);
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
        });
    }
}
