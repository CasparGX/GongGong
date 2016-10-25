package com.sky31.gonggong.model;

import com.google.gson.JsonArray;
import com.sky31.gonggong.config.Constants;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by root on 16-2-28.
 */
public interface ApiService {
    String defaultParam = Constants.Key.ROLE + "=" + Constants.Value.ROLE + "&"
            + Constants.Key.HASH + "=" + Constants.Value.HASH;

    String secondhandDefaultParm = Constants.Api.SECOND_HAND_URL_PARAM + "?" + Constants.Key.S_TYPE + "=&" + Constants.Key.S_TITLE + "=&" + Constants.Key.S_LIMIT_ID + "=0";
    //获取校园卡余额
    @GET(Constants.Api.ECARD + "?" + defaultParam)
    Call<EcardModel> getBalance(@Query(Constants.Key.SID) String sid, @Query(Constants.Key.PASSWORD) String password);

    //获取个人信息
    @GET(Constants.Api.STU_INFO + "?" + defaultParam)
    Observable<StudentInfoModel> getStudentInfo(@Query(Constants.Key.SID) String sid, @Query(Constants.Key.PASSWORD) String password);

    //获取校园网余额
    @GET(Constants.Api.CAMPUS_NET_BALANCE + "?" + defaultParam)
    Call<CampusNetwork> getCampusNetwork(@Query(Constants.Key.SID) String sid);

    //获取图书馆读者信息
    @GET(Constants.Api.LIBRARY_READER_INFO + "?" + defaultParam)
    Call<LibraryReaderInfoModel> getLibraryReaderInfo(@Query(Constants.Key.SID) String sid, @Query(Constants.Key.PASSWORD) String password);

    //获取图书馆借书列表
    @GET(Constants.Api.LIBRARY_RENT_LIST + "?" + defaultParam)
    Call<LibraryRentListModel> getLibraryRentList(@Query(Constants.Key.SID) String sid, @Query(Constants.Key.PASSWORD) String password);

    //获取校友信息
    @GET(Constants.Api.SEARCH_MATE + "?" + defaultParam + "&server=remote")
    Call<MateInfoModel> getMateInfo(@Query(Constants.Key.SID) String sid, @Query(Constants.Key.NAME) String name, @Query(Constants.Key.CARD) String card, @Query(Constants.Key.TYPE) String type);

    //获取节假日信息
    @GET(Constants.Api.HOLIDAY + "?" + defaultParam + "&" + Constants.Key.HOLIDAY_ACTION + "=" + Constants.Key.HOLIDAY_ACTION_NEXT)
    Call<HolidayNextModel> getHolidayNext();

    @GET(Constants.Api.HOLIDAY + "?" + defaultParam + "&" + Constants.Key.HOLIDAY_ACTION + "=" + Constants.Key.HOLIDAY_ACTION_ALL)
    Call<HolidayAllModel> getHolidayAll();



    //课表请求。
    @GET(Constants.Api.COURSE+"?"+defaultParam)
    Call<CourseListModel> getCourse(@QueryMap Map<String,String> map);

    //查询当前第几周。
    @GET(Constants.Api.CURRENT_WEEK + "?" + defaultParam + "&type=static")
    Call<CurrentWeekModel> getCurrentWeek();

    //成绩报表
    @GET(Constants.Api.GRADE_REPORT + "?" + defaultParam)
    Call<GradeReportModel> getGradeReport(@Query(Constants.Key.SID) String sid, @Query(Constants.Key.PASSWORD) String password);


    //成绩祥单
    @GET(Constants.Api.GRADE_DETAILS + "?" + defaultParam)
    Call<GradeDetailsModel> getGradeDetails(@Query(Constants.Key.SID) String sid, @Query(Constants.Key.PASSWORD) String password);


    //二手街
    @GET(secondhandDefaultParm)
    Call<JsonArray> getScondModelList();

    //获取学期代码
    @GET(Constants.Api.TERM_CODE + "?" + defaultParam)
    Call<TermCodeModel> getTermCodes(@Query(Constants.Key.SID) String sid, @Query(Constants.Key.PASSWORD) String password);


}
