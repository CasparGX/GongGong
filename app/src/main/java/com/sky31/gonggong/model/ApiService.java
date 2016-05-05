package com.sky31.gonggong.model;

import com.sky31.gonggong.config.Constants;

import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Created by root on 16-2-28.
 */
public interface ApiService {
    String defaultParam = Constants.Key.ROLE + "=" + Constants.Value.ROLE + "&"
            + Constants.Key.HASH + "=" + Constants.Value.HASH;

    //获取校园卡余额
    @GET(Constants.Api.ECARD + "?" + defaultParam)
    Call<EcardModel> getBalance(@Query(Constants.Key.SID) String sid, @Query(Constants.Key.PASSWORD) String password);

    //获取个人信息
    @GET(Constants.Api.STU_INFO + "?" + defaultParam)
    Call<StudentInfoModel> getStudentInfo(@Query(Constants.Key.SID) String sid, @Query(Constants.Key.PASSWORD) String password);

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
    @GET(Constants.Api.SEARCH_MATE + "?" + defaultParam)
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
    @GET(Constants.Api.CURRENT_WEEK+"?"+defaultParam)
    Call<CurrentWeekModel> getCurrentWeek();

    //成绩报表
    @GET(Constants.Api.GRADE_REPORT + "?" + defaultParam)
    Call<GradeReportModel> getGradeReport(@Query(Constants.Key.SID) String sid, @Query(Constants.Key.PASSWORD) String password);


    //成绩祥单
    @GET(Constants.Api.GRADE_DETAILS + "?" + defaultParam)
    Call<GradeDetailsModel> getGradeDetails(@Query(Constants.Key.SID) String sid, @Query(Constants.Key.PASSWORD) String password);


}
