package cc.xaabb.gonggong.module.campusnet;

import android.content.Context;

import cc.xaabb.gonggong.base.BasePresenter;
import cc.xaabb.gonggong.config.Constants;
import cc.xaabb.gonggong.model.ApiService;
import cc.xaabb.gonggong.model.CampusNetwork;
import cc.xaabb.gonggong.model.UserModel;
import cc.xaabb.gonggong.util.ApiException;
import cc.xaabb.gonggong.util.BaseSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 16-2-29.
 */
public class CampusNetPresenter extends BasePresenter {
    private CampusNetView campusNetView;
    private ApiService apiService;
    private Context context;

    private String sid = null;
    private String password = null;

    public void init() {
        //init Retrofit
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        apiService = mRetrofit.create(ApiService.class);
        initSidAndPassword();
    }

    public CampusNetPresenter(CampusNetView campusNetView) {
        this.campusNetView = campusNetView;
        init();
    }

    private void initSidAndPassword() {
        //init sid and password
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.PASSWORD);
    }


    public void getCampusNetwork() {
        apiService.getCampusNetwork(sid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<CampusNetwork>() {

                    @Override
                    public void onNext(CampusNetwork campusNetwork) {
                        CampusNetwork _campusNetwork = campusNetwork;
                        _campusNetwork.setCache();
                        campusNetView.getCampusNetwork(_campusNetwork.getCode(), _campusNetwork);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        if (e.getErrorCode()==1) {
                            UserModel.setCacheNone(campusNetView.getViewContext());
                        }
                        campusNetView.getCampusNetwork(e.getErrorCode(), null);
                    }

                });
        /*final Call<CampusNetwork> call = apiService.getCampusNetwork(sid);
        call.enqueue(new Callback<CampusNetwork>() {
            @Override
            public void onResponse(Response<CampusNetwork> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    CampusNetwork campusNetwork = response.body();
                    campusNetwork.setCache();
                    campusNetView.getCampusNetwork(code, campusNetwork);
                } else if (code == 1) {
                    UserModel.setCacheNone(campusNetView.getViewContext());
                    campusNetView.getCampusNetwork(code, null);
                } else {
                    campusNetView.getCampusNetwork(code, null);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });*/

    }
}
