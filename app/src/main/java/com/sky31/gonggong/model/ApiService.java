package com.sky31.gonggong.model;

import com.sky31.gonggong.config.Constants;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by root on 16-2-28.
 */
public interface ApiService {
    String defaultParam = Constants.Key.ROLE + "=" + Constants.Value.ROLE + "&"
            + Constants.Key.HASH + "=" + Constants.Value.HASH;

    @GET(Constants.Api.ECARD + "?" + defaultParam)
    Call<EcardModel> getBalance(@Query(Constants.Key.SID) String sid, @Query(Constants.Key.PASSWORD) String password);

    @GET(Constants.Api.STU_INFO + "?" + defaultParam)
    Call<StudentInfoModel> getStudentInfo(@Query(Constants.Key.SID) String sid, @Query(Constants.Key.PASSWORD) String password);



}
