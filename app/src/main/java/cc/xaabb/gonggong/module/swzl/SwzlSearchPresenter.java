package cc.xaabb.gonggong.module.swzl;

import android.content.Context;
import android.util.Log;

import cc.xaabb.gonggong.base.BasePresenter;
import cc.xaabb.gonggong.model.SwzlSearchResult;
import cc.xaabb.gonggong.model.SwzlService;
import cc.xaabb.gonggong.util.ApiException;
import cc.xaabb.gonggong.util.BaseSubscriber;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wukunguang on 16-3-17.
 */
public class SwzlSearchPresenter extends BasePresenter {


    private SwzlSearchView searchView;
    private SwzlService service;
    private Context context;

    public SwzlSearchPresenter(SwzlSearchView searchView,Context context) {
        this.searchView = searchView;
        this.context = context;
        /*Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        service = mRetrofit.create(SwzlService.class);

    }
    /***
     *
     * @param actionCode 0 is lost,1 is get/found
     *
     */
    public void getSearchResult(int actionCode){
        Observable resultCall;
        if (actionCode==0) {
            Log.d("action:",actionCode+"");
            resultCall = service.getSerResultByLost();
        }
        else {
            Log.d("action:",actionCode+"");
            resultCall = service.getSerResultByGet();
        }
        resultCall
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<SwzlSearchResult>() {

                    @Override
                    public void onNext(SwzlSearchResult swzlSearchResult) {
                        // 回调函数传入参数
                        searchView.getSearchData(Integer.parseInt(swzlSearchResult.getCode()),swzlSearchResult);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        searchView.getSearchData(-1, null);
                    }

                });
        /*Call<SwzlSearchResult> resultCall;
        if (actionCode==0) {
            Log.d("action:",actionCode+"");
            resultCall = service.getSerResultByLost();
        }
        else {
            Log.d("action:",actionCode+"");
            resultCall = service.getSerResultByGet();
        }

        resultCall.enqueue(new Callback<SwzlSearchResult>() {
            @Override
            public void onResponse(Response<SwzlSearchResult> response, Retrofit retrofit) {

                SwzlSearchResult result;
                result = response.body();
                int code = Integer.parseInt(response.body().getCode());
                // 回调函数传入参数
                searchView.getSearchData(code,result);
            }

            @Override
            public void onFailure(Throwable t) {


                t.printStackTrace();
                searchView.getSearchData(-1, null);
            }
        });*/





    }


    public void doSearchByKey(String key){

        service.getSearchResult(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<SwzlSearchResult>() {

                    @Override
                    public void onNext(SwzlSearchResult swzlSearchResult) {
                        searchView.getSearchData(Integer.parseInt(swzlSearchResult.getCode()), swzlSearchResult);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        searchView.getSearchData(e.getErrorCode(),null);
                    }

                });

        /*Call<SwzlSearchResult> call = service.getSearchResult(key);

        call.enqueue(new Callback<SwzlSearchResult>() {
            @Override
            public void onResponse(Response<SwzlSearchResult> response, Retrofit retrofit) {
                if (response.body().getCode().equals("0")){

                    int code = Integer.parseInt(response.body().getCode());
                    searchView.getSearchData(code , response.body());

                }
                else {
                    int code = Integer.parseInt(response.body().getCode());
                    searchView.getSearchData(code,null);
                }
            }

            @Override
            public void onFailure(Throwable t) {

                t.printStackTrace();
                Toast.makeText(context,"网络请求出错，请稍后再试",Toast.LENGTH_SHORT).show();

            }
        });*/
    }

}
