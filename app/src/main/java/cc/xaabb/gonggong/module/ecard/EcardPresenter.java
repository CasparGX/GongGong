package cc.xaabb.gonggong.module.ecard;

import android.widget.Toast;

import cc.xaabb.gonggong.base.BasePresenter;
import cc.xaabb.gonggong.config.Constants;
import cc.xaabb.gonggong.network.ApiService;
import cc.xaabb.gonggong.model.EcardModel;
import cc.xaabb.gonggong.model.UserModel;
import cc.xaabb.gonggong.network.ApiException;
import cc.xaabb.gonggong.network.BaseSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 16-3-17.
 */
public class EcardPresenter extends BasePresenter{
    private ApiService apiService;
    private String sid, password;
    private EcardView ecardView;

    public EcardPresenter(EcardView ecardView) {
        //init Retrofit
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        apiService = mRetrofit.create(ApiService.class);
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.ECARD_PASSWORD);
        this.ecardView = ecardView;
    }

    //获取校园卡信息
    public void getBalance() {
        //通知ui设为等待状态
        ecardView.onGetBalance();
        apiService.getBalance(sid, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<EcardModel>() {

                    @Override
                    public void onNext(EcardModel ecardModel) {
                        EcardModel _ecardModel = ecardModel;
                        _ecardModel.setCache();
                        ecardView.doneGetBalance(_ecardModel.getCode(), _ecardModel);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        if (e.getErrorCode()==1) {
                            UserModel.getaCache().remove(Constants.Key.ECARD_PASSWORD);
                            Toast.makeText(ecardView.getViewContext(), "ecard password is wrong", Toast.LENGTH_SHORT).show();
                        }
                        ecardView.doneGetBalance(e.getErrorCode(), null);
                    }

                });

        /*Call<EcardModel> call = apiService.getBalance(sid, password);
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
                ecardView.doneGetBalance(2, null);
            }
        });*/
    }
}
