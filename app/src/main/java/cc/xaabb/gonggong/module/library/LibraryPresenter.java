package cc.xaabb.gonggong.module.library;

import cc.xaabb.gonggong.base.BasePresenter;
import cc.xaabb.gonggong.config.Constants;
import cc.xaabb.gonggong.network.ApiService;
import cc.xaabb.gonggong.model.LibraryReaderInfoModel;
import cc.xaabb.gonggong.model.LibraryRentListModel;
import cc.xaabb.gonggong.model.UserModel;
import cc.xaabb.gonggong.network.ApiException;
import cc.xaabb.gonggong.network.BaseSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 16-3-17.
 */
public class LibraryPresenter extends BasePresenter {
    private ApiService apiService;
    private String sid, password;
    private LibraryView libraryView;

    public LibraryPresenter(LibraryView libraryView) {
        //init Retrofit
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        apiService = mRetrofit.create(ApiService.class);
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.LIBRARY_PASSWORD);
        this.libraryView = libraryView;
    }

    public void getLibraryReaderInfo() {
        apiService.getLibraryReaderInfo(sid, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<LibraryReaderInfoModel>() {

                    @Override
                    public void onNext(LibraryReaderInfoModel libraryReaderInfoModel) {
                        libraryReaderInfoModel.setCache();
                        libraryView.onGetLibraryReaderInfo(libraryReaderInfoModel.getCode(), libraryReaderInfoModel);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        if (e.getErrorCode()==1) {
                            UserModel.getaCache().remove(Constants.Key.LIBRARY_PASSWORD);
                        }
                        libraryView.onGetLibraryReaderInfo(e.getErrorCode(), null);
                    }

                });

        /*Call<LibraryReaderInfoModel> call = apiService.getLibraryReaderInfo(sid, password);
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
        });*/
    }

    public void getLibraryRentList() {

        apiService.getLibraryRentList(sid, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<LibraryRentListModel>() {

                    @Override
                    public void onNext(LibraryRentListModel libraryRentListModel) {
                        libraryRentListModel.setCache();
                        libraryView.getLibraryRentLsit(libraryRentListModel.getCode(), libraryRentListModel);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        if (e.getErrorCode()==1) {
                            UserModel.getaCache().remove(Constants.Key.LIBRARY_PASSWORD);
                        }
                        libraryView.getLibraryRentLsit(e.getErrorCode(), null);
                    }

                });

        /*Call<LibraryRentListModel> call = apiService.getLibraryRentList(sid, password);
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
        });*/
    }
}
