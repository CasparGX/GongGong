package cc.xaabb.gonggong.module.secondhand;

import com.google.gson.JsonArray;
import cc.xaabb.gonggong.BuildConfig;
import cc.xaabb.gonggong.base.BasePresenter;
import cc.xaabb.gonggong.config.Constants;
import cc.xaabb.gonggong.model.ApiService;
import cc.xaabb.gonggong.util.ApiException;
import cc.xaabb.gonggong.util.BaseSubscriber;
import cc.xaabb.gonggong.util.MyGsonConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wukunguang on 16-5-10.
 */
public class SecondhandPresent extends BasePresenter {

    private SecondhandView secondhandView;
    private ApiService apiService;

    public SecondhandPresent(SecondhandView secondhandView) {
        this.secondhandView = secondhandView;
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.SECOND_HAND)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        OkHttpClient httpClient = new OkHttpClient();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.SECOND_HAND)
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    public void requestServer(){

        apiService.getScondModelList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<JsonArray>() {

                    @Override
                    public void onNext(JsonArray jsonArray) {
                        JsonArray modelList = jsonArray;
                        secondhandView.getSecondhandData(modelList, 0);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        secondhandView.getSecondhandData(null,2);
                    }

                });

        /*Call<JsonArray> call = apiService.getScondModelList();

        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Response<JsonArray> response, Retrofit retrofit) {
                JsonArray modelList = response.body();
                Debug.i("secondlist", modelList + "");
                //UserModel.getaCache().put(Constants.Key.S_GOODS_LIST, modelList);
                //Log.e("TAG",response.body().getModels().size()+"");
                *//*JSONObject jsonArray = null;
                try {
                    jsonArray = new JSONArray(modelList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*//*

                secondhandView.getSecondhandData(modelList, 0);

            }

            @Override
            public void onFailure(Throwable t) {
                Debug.d("secondhand", t.getCause() + "   " + t.getMessage());
                secondhandView.getSecondhandData(null,2);
            }
        });*/

    }


}
