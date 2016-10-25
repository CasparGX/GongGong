package com.sky31.gonggong.model;

import com.sky31.gonggong.config.Constants;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by wukunguang on 16-3-26.
 */
public interface ArticleService {

    String defaultParm = Constants.Key.ROLE+ "=" + Constants.Value.ROLE +
            "&"+Constants.Key.HASH + "=" + Constants.Value.HASH;

    @GET(Constants.Api.CMS_LIST+"?"+defaultParm)
    Call<ArticleListModel> getArticleList(@QueryMap HashMap<String,String> map);
}
