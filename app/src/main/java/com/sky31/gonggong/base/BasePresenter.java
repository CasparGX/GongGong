package com.sky31.gonggong.base;

import com.sky31.gonggong.util.RetrofitUtils;
import retrofit2.Retrofit;

/**
 * Created by caspar on 16-9-12.
 */
public class BasePresenter {
    protected Retrofit mRetrofit = RetrofitUtils.getInstance().getmRetrofit();

}
