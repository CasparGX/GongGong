package com.sky31.gonggong.model;

import com.sky31.gonggong.config.Constants;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by wukunguang on 16-3-1.
 */
public interface SwzlService {

    String submitLostParm = "ask_method="+Constants.Value.SWZL_ASK_METHOD_JSON+"&"+"action="+Constants.Value.SWZL_SUBMIT_LOST;
    @POST(Constants.Api.SWZL_ACTION+"?"+submitLostParm)
    Call<SwzlResModel> getSubResult(@Body LostAndFoundModel model);



}
