package com.sky31.gonggong.module.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.CampusNetwork;
import com.sky31.gonggong.model.EcardModel;
import com.sky31.gonggong.model.LibraryReaderInfoModel;
import com.sky31.gonggong.model.LibraryRentListModel;
import com.sky31.gonggong.model.StudentInfoModel;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.module.ecard.EcardView;
import com.sky31.gonggong.module.library.LibraryView;
import com.sky31.gonggong.module.login.LoginView;
import com.sky31.gonggong.util.Debug;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 16-2-29.
 */
public class ApiPresenter {
    private ApiView apiView;
    private EcardView ecardView;
    private CampusNetView campusNetView;
    private LibraryView libraryView;
    private LoginView loginView;
    private ApiService apiService;
    private Context context;

    private String sid = null;
    private String password = null;

    public void init(){
        //init Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        initSidAndPassword();
    }

    public ApiPresenter(ApiView apiView) {
        this.apiView = apiView;
        init();
    }

    public ApiPresenter(EcardView ecardView) {
        this.ecardView = ecardView;
        init();
    }

    public ApiPresenter(CampusNetView campusNetView) {
        this.campusNetView = campusNetView;
        init();
    }

    public ApiPresenter(LibraryView libraryView) {
        this.libraryView = libraryView;
        init();
    }

    public ApiPresenter(LoginView loginView) {
        this.loginView = loginView;
        init();
    }

    private void initSidAndPassword() {
        //init sid and password
        //UserModel userModel = UserModel.getUserModel();
        if (UserModel.getSid() != null && UserModel.getPassword() != null) {
            sid = UserModel.getSid();
            password = UserModel.getPassword();
        }
    }

    //获取校园卡信息
    public void getBalance(@Nullable String sid, @Nullable String password) {
        Debug.i("getBalance", this.sid + " " + this.password);
        sid = (sid != null) ? sid : this.sid;
        password = (password != null) ? password : this.password;
        Call<EcardModel> call = apiService.getBalance(sid, password);
        call.enqueue(new Callback<EcardModel>() {
            @Override
            public void onResponse(Response<EcardModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    EcardModel ecardModel = response.body();
                    ecardModel.setCache(ecardView.getViewContext());
                    ecardView.getBalance(code, ecardModel);
                } else {
                    ecardView.getBalance(code, null);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(ecardView.getViewContext(), "Error", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void login(String sid, String password) {
        getStudentInfo(sid, password);
        //initSidAndPassword();
        //getBalance(sid, password);
    }

    public void getStudentInfo(final String sid, final String password) {
        Call<StudentInfoModel> call = apiService.getStudentInfo(sid, password);
        call.enqueue(new Callback<StudentInfoModel>() {
            @Override
            public void onResponse(Response<StudentInfoModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    StudentInfoModel studentInfoModel = response.body();
                    studentInfoModel.setCache(loginView.getViewContext());
                    UserModel.setSid(sid);
                    UserModel.setPassword(password);
                    //UserModel.setLibraryPassword(password);
                    UserModel.setCache(loginView.getViewContext());
                    loginView.login(code, studentInfoModel);
                } else if (code == 1) {
                    UserModel.setCacheNone(loginView.getViewContext());
                    loginView.login(code, null);
                } else {
                    loginView.login(code, null);
                }

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getCampusNetwork(String sid) {
        final Call<CampusNetwork> call = apiService.getCampusNetwork(sid);
        call.enqueue(new Callback<CampusNetwork>() {
            @Override
            public void onResponse(Response<CampusNetwork> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    CampusNetwork campusNetwork = response.body();
                    campusNetwork.setCache(campusNetView.getViewContext());
                    campusNetView.getCampusNetwork(code, campusNetwork);
                } else if (code == 1) {
                    UserModel.setCacheNone(campusNetView.getViewContext());
                    campusNetView.getCampusNetwork(code, null);
                } else {
                    campusNetView.getCampusNetwork(code, null);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    public void getLibraryReaderInfo(String sid, String password){
        Call<LibraryReaderInfoModel> call = apiService.getLibraryReaderInfo(sid, password);
        call.enqueue(new Callback<LibraryReaderInfoModel>() {
            @Override
            public void onResponse(Response<LibraryReaderInfoModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    LibraryReaderInfoModel libraryReaderInfoModel = response.body();
                    libraryReaderInfoModel.setCache(libraryView.getViewContext());
                    libraryView.getLibraryReaderInfo(code, libraryReaderInfoModel);
                } else if (code == 1) {
                    //UserModel.setCacheNone(apiView.getViewContext());
                    //apiView.login(code, null);
                } else {
                    libraryView.getLibraryReaderInfo(code, null);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void getLibraryRentList(String sid, String password){
        Call<LibraryRentListModel> call = apiService.getLibraryRentList(sid, password);
        call.enqueue(new Callback<LibraryRentListModel>() {
            @Override
            public void onResponse(Response<LibraryRentListModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    LibraryRentListModel libraryRentListModel = response.body();
                    libraryRentListModel.setCache(libraryView.getViewContext());
                    libraryView.getLibraryRentLsit(code, libraryRentListModel);
                } else if (code == 1) {
                    //UserModel.setCacheNone(apiView.getViewContext());
                    //apiView.login(code, null);
                } else {
                    libraryView.getLibraryRentLsit(code, null);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void errorCode(int code) {
        switch (code) {
            case -1:

                break;
        }
    }
}
