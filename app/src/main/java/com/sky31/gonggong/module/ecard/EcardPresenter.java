package com.sky31.gonggong.module.ecard;

import android.widget.Toast;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.EcardModel;
import com.sky31.gonggong.model.UserModel;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 16-3-17.
 */
public class EcardPresenter {
    private ApiService apiService;
    private String sid, password;
    private EcardView ecardView;

    public EcardPresenter(EcardView ecardView) {
        //init Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.ECARD_PASSWORD);
        this.ecardView = ecardView;
    }

    //获取校园卡信息
    public void getBalance() {
        //通知ui设为等待状态
        ecardView.onGetBalance();
        Call<EcardModel> call = apiService.getBalance(sid, password);
        call.enqueue(new Callback<EcardModel>() {
            @Override
            public void onResponse(Response<EcardModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    EcardModel ecardModel = response.body();
                    ecardModel.setCache();
                    ecardView.doneGetBalance(code, ecardModel);
                } else if (code == 1) {
                    //一卡通密码错误
                    UserModel.getaCache().remove(Constants.Key.ECARD_PASSWORD);
                    ecardView.doneGetBalance(code, null);
                    Toast.makeText(ecardView.getViewContext(), "ecard password is wrong", Toast.LENGTH_SHORT).show();
                } else {
                    ecardView.doneGetBalance(code, null);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(ecardView.getViewContext(), "Error", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
