package com.sky31.gonggong.module.library;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.LibraryReaderInfoModel;
import com.sky31.gonggong.model.LibraryRentListModel;
import com.sky31.gonggong.model.UserModel;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 16-3-17.
 */
public class LibraryPresenter {
    private ApiService apiService;
    private String sid, password;
    private LibraryView libraryView;

    public LibraryPresenter(LibraryView libraryView) {
        //init Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.LIBRARY_PASSWORD);
        this.libraryView = libraryView;
    }

    public void getLibraryReaderInfo() {
        Call<LibraryReaderInfoModel> call = apiService.getLibraryReaderInfo(sid, password);
        call.enqueue(new Callback<LibraryReaderInfoModel>() {
            @Override
            public void onResponse(Response<LibraryReaderInfoModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    LibraryReaderInfoModel libraryReaderInfoModel = response.body();
                    libraryReaderInfoModel.setCache();
                    libraryView.onGetLibraryReaderInfo(code, libraryReaderInfoModel);
                } else if (code == 1) {
                    UserModel.getaCache().remove(Constants.Key.LIBRARY_PASSWORD);
                    libraryView.onGetLibraryReaderInfo(code, null);
                } else {
                    libraryView.onGetLibraryReaderInfo(code, null);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void getLibraryRentList() {
        Call<LibraryRentListModel> call = apiService.getLibraryRentList(sid, password);
        call.enqueue(new Callback<LibraryRentListModel>() {
            @Override
            public void onResponse(Response<LibraryRentListModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    LibraryRentListModel libraryRentListModel = response.body();
                    libraryRentListModel.setCache();
                    libraryView.getLibraryRentLsit(code, libraryRentListModel);
                } else if (code == 1) {
                    UserModel.getaCache().remove(Constants.Key.LIBRARY_PASSWORD);
                    libraryView.onGetLibraryReaderInfo(code, null);
                } else {
                    libraryView.getLibraryRentLsit(code, null);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
