package com.sky31.gonggong.module.ecard;

import android.widget.Toast;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.EcardModel;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.util.Debug;

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
        password = UserModel.getaCache().getAsString(Constants.Key.PASSWORD);
        this.ecardView = ecardView;
    }

    //获取校园卡信息
    public void getBalance() {
        Debug.i("getBalance", this.sid + " " + this.password);
        Call<EcardModel> call = apiService.getBalance(sid, password);
        call.enqueue(new Callback<EcardModel>() {
            @Override
            public void onResponse(Response<EcardModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    EcardModel ecardModel = response.body();
                    ecardModel.setCache(ecardView.getViewContext());
                    ecardView.getBalance(code, ecardModel);
                } else {
                    ecardView.getBalance(code, null);
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
