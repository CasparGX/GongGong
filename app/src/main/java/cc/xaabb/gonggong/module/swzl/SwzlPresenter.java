package cc.xaabb.gonggong.module.swzl;

import cc.xaabb.gonggong.base.BasePresenter;
import cc.xaabb.gonggong.model.LostAndFoundModel;
import cc.xaabb.gonggong.model.SwzlResModel;
import cc.xaabb.gonggong.model.SwzlService;
import cc.xaabb.gonggong.module.swzl.publish.SwzlPublishView;
import cc.xaabb.gonggong.util.ApiException;
import cc.xaabb.gonggong.util.BaseSubscriber;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wukunguang on 16-3-1.
 */
public class SwzlPresenter extends BasePresenter {

    private SwzlPublishView swzlPublish;
    private SwzlService swzlService;
    private int actionCode;
    private LostAndFoundModel model;

    public SwzlPresenter(SwzlPublishView swzlPublish) {
        this.swzlPublish = swzlPublish;
    }

    public void publishSwzl(LostAndFoundModel model){


        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        this.model = model;
        swzlService = mRetrofit.create(SwzlService.class);
        // 传入model参数。

    }

    public void getResModel(int actionCode){

        // 如果是发布招领信息
        if (actionCode==1){
            swzlService.getSubResultByGet(setModel2Map(model))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseSubscriber<SwzlResModel>() {

                        @Override
                        public void onNext(SwzlResModel swzlResModel) {
                            swzlPublish.publish(swzlResModel.getCode(),swzlResModel);
                        }

                        @Override
                        public void onApiException(ApiException e) {
                            swzlPublish.publish(e.getErrorCode()+"",null);
                        }

                    });
            /*Call<SwzlResModel> modelCall = swzlService.getSubResultByGet(setModel2Map(model));
            modelCall.enqueue(new Callback<SwzlResModel>() {
                @Override
                public void onResponse(Response<SwzlResModel> response, Retrofit retrofit) {

                    String code = response.body().getCode();
                    if (code.equals("0")){
                        SwzlResModel model = response.body();
                        swzlPublish.publish(code,model);
                    }else {
                        swzlPublish.publish(code,null);
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                    int x = 555;
                    t.printStackTrace();
                    swzlPublish.publish("1002", null);
                }
            });*/
        }
        //如果是发布丢失信息
        else if (actionCode == 0){

            swzlService.getSubResultByLost(setModel2Map(model))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseSubscriber<SwzlResModel>() {

                        @Override
                        public void onNext(SwzlResModel swzlResModel) {
                            swzlPublish.publish(swzlResModel.getCode(),swzlResModel);
                        }

                        @Override
                        public void onApiException(ApiException e) {
                            swzlPublish.publish(e.getErrorCode()+"",null);
                        }

                    });
            /*Call<SwzlResModel> modelCall = swzlService.getSubResultByLost(setModel2Map(model));
           // Call<SwzlResModel> modelCall = swzlService.getSubResultByNull();
            modelCall.enqueue(new Callback<SwzlResModel>() {
                @Override
                public void onResponse(Response<SwzlResModel> response, Retrofit retrofit) {
                    String code = response.body().getCode();
                    if (code.equals("0")){
                        SwzlResModel model = response.body();
                        swzlPublish.publish(code,model);
                    }else {
                        swzlPublish.publish(code,null);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    int x = 555;
                    swzlPublish.publish("1002",null);
                    Log.e("connection failed",t.getMessage());
                }
            });*/
        }

    }

    private Map<String,String> setModel2Map(LostAndFoundModel model){

        Map<String,String> map = new HashMap<>();
        map.put("announcer",model.getAnnouncer());
        map.put("bank_card",model.getBank_card());
        map.put("description",model.getDescription());
        map.put("location",model.getLocation());
        map.put("mobile",model.getMobile());
        map.put("things",model.getThings());
        map.put("things_type",model.getThings_type());

        return map;
    }




}
