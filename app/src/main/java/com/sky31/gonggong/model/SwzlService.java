package com.sky31.gonggong.model;

import com.sky31.gonggong.config.Constants;

import java.util.Map;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Created by wukunguang on 16-3-1.
 */
public interface SwzlService {

    String submitLostParm = "ask_method="+Constants.Value.SWZL_ASK_METHOD_JSON+"&"+"action="+Constants.Value.SWZL_SUBMIT_LOST;
    String submitGetParm = "ask_method="+Constants.Value.SWZL_ASK_METHOD_JSON+"&"+"action="+Constants.Value.SWZL_SUBMIT_FOUND;


    @FormUrlEncoded
    @POST(Constants.Api.SWZL_ACTION+"?"+submitLostParm)
    Call<SwzlResModel> getSubResultByLost(@FieldMap Map<String,String> map);


    @FormUrlEncoded
    @POST(Constants.Api.SWZL_ACTION+"?"+submitGetParm)
    Call<SwzlResModel> getSubResultByGet(@FieldMap  Map<String,String> map);

//    @GET(Constants.Api.SWZL_ACTION+"?"+submitGetParm)
//    Call<SwzlResModel> getSubResultByNull();

}
