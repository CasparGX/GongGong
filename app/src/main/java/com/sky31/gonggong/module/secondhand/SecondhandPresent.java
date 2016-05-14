package com.sky31.gonggong.module.secondhand;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.util.Debug;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by wukunguang on 16-5-10.
 */
public class SecondhandPresent {

    private SecondhandView secondhandView;
    private ApiService apiService;

    public SecondhandPresent(SecondhandView secondhandView) {
        this.secondhandView = secondhandView;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.SECOND_HAND)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    public void requestServer(){

        Call<JSONObject> call = apiService.getScondModelList();

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Response<JSONObject> response, Retrofit retrofit) {
                JSONObject modelList = response.body();

                UserModel.getaCache().put(Constants.Key.S_GOODS_LIST, modelList);
                //Log.e("TAG",response.body().getModels().size()+"");
                /*JSONObject jsonArray = null;
                try {
                    jsonArray = new JSONArray(modelList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                //secondhandView.getSecondhandData(jsonArray, 0);

            }

            @Override
            public void onFailure(Throwable t) {
                Debug.d("secondhand", t.getCause() + "   " + t.getMessage());
                secondhandView.getSecondhandData(null,2);
            }
        });

    }


}
