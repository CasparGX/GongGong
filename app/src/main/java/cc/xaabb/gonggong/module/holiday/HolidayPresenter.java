package cc.xaabb.gonggong.module.holiday;

import cc.xaabb.gonggong.base.BasePresenter;
import cc.xaabb.gonggong.config.ApiErrorCode;
import cc.xaabb.gonggong.config.Constants;
import cc.xaabb.gonggong.network.ApiService;
import cc.xaabb.gonggong.model.HolidayAllModel;
import cc.xaabb.gonggong.model.HolidayNextModel;
import cc.xaabb.gonggong.model.UserModel;
import cc.xaabb.gonggong.network.ApiException;
import cc.xaabb.gonggong.network.BaseSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 16-4-12.
 */
public class HolidayPresenter extends BasePresenter {

    private ApiService apiService;
    private String sid, password;
    private HolidayView holidayView;

    public HolidayPresenter(HolidayView holidayView) {
        //init Retrofit
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        apiService = mRetrofit.create(ApiService.class);
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.ECARD_PASSWORD);
        this.holidayView = holidayView;
    }

    public void getHolidayNext(){
        holidayView.onGetHolidayNext();
        apiService.getHolidayNext()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HolidayNextModel>() {

                    @Override
                    public void onNext(HolidayNextModel holidayNextModel) {
                        HolidayNextModel _holidayNextModel = holidayNextModel;
                        _holidayNextModel.setCache();
                        holidayView.finishGetHolidayNext(_holidayNextModel);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        if (e.getErrorCode()== ApiErrorCode.ERROR_NO_HOLIDAY) {
                            holidayView.finishGetHolidayNext(null);
                        }
                    }

                });

        /*Call<HolidayNextModel> call = apiService.getHolidayNext();
        call.enqueue(new Callback<HolidayNextModel>() {
            @Override
            public void onResponse(Response<HolidayNextModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    HolidayNextModel holidayNextModel = response.body();
                    holidayNextModel.setCache();
                    holidayView.finishGetHolidayNext(holidayNextModel);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                holidayView.finishGetHolidayNext(null);
            }
        });*/
    }

    public void getHolidayAll(){

        apiService.getHolidayAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HolidayAllModel>() {

                    @Override
                    public void onNext(HolidayAllModel holidayNextModel) {
                        HolidayAllModel _holidayAllModel = holidayNextModel;
                        //holidayNextModel.setCache();
                        holidayView.finishGetHolidayAll(_holidayAllModel);
                    }

                    @Override
                    public void onApiException(ApiException e) {

                    }

                });
        /*Call<HolidayAllModel> call = apiService.getHolidayAll();
        call.enqueue(new Callback<HolidayAllModel>() {
            @Override
            public void onResponse(Response<HolidayAllModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    HolidayAllModel holidayAllModel = response.body();
                    //holidayNextModel.setCache();
                    holidayView.finishGetHolidayAll(holidayAllModel);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });*/
    }
}
