package com.sky31.gonggong.module.search_mate;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.StudentInfoModel;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.module.login.LoginView;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 16-3-25.
 */
public class SearchMatePresenter {
    private ApiService apiService;
    private String sid, password;
    private SearchMateView searchMateView;

    public SearchMatePresenter(LoginView loginView) {
        //init Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.PASSWORD);
        this.searchMateView = searchMateView;
    }


    public void getMateInfo(final String sid, final String password) {
        Call<StudentInfoModel> call = apiService.getStudentInfo(sid, password);
        call.enqueue(new Callback<StudentInfoModel>() {
            @Override
            public void onResponse(Response<StudentInfoModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {

                } else if (code == 1) {


                } else {

                }

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
