package com.sky31.gonggong.module.swzl;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.SwzlSearchResult;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by wukunguang on 16-3-17.
 */
public class SwzlSearchPresenter {

    private SwzlSearchResult swzlSearchResult;
    private SwzlSearchView searchView;

    public SwzlSearchPresenter(SwzlSearchView searchView) {

        this.searchView = searchView;

        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public SwzlSearchResult getSearchResult(){



        return null;

    }
}
