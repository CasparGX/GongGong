package com.sky31.gonggong.presenter;

import android.content.Context;
import android.widget.Toast;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.EcardModel;
import com.sky31.gonggong.model.StudentInfoModel;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.view.EcardView;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 16-2-29.
 */
public class ApiPresenter {
    private EcardView apiView;
    private ApiService apiService;

    private String sid = null;
    private String password = null;

    public ApiPresenter(EcardView apiView) {
        this.apiView = apiView;

        //init Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        //init sid and password
        UserModel userModel = UserModel.getUserModel();
        if (userModel.getSid()!=null && userModel.getPassword()!=null) {
            sid = userModel.getSid();
            password = userModel.getPassword();
        }
    }


    public void getBalance() {

        Call<EcardModel> call = apiService.getBalance(sid,password);
        call.enqueue(new Callback<EcardModel>() {
            @Override
            public void onResponse(Response<EcardModel> response, Retrofit retrofit) {
                if (response.body().getCode()!=-1){
                    EcardModel ecardModel = response.body();
                    apiView.getBalance(ecardModel);
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText((Context) apiView,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void login(){
        getStudentInfo();
    }

    public void getStudentInfo() {
        Call<StudentInfoModel> call = apiService.getStudentInfo(sid,password);
        call.enqueue(new Callback<StudentInfoModel>() {
            @Override
            public void onResponse(Response<StudentInfoModel> response, Retrofit retrofit) {
                StudentInfoModel studentInfoModel = response.body();
                //apiView.getStudentInfoModel(studentInfoModel);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText((Context) apiView,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
