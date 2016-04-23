package com.sky31.gonggong.model;

import com.google.gson.Gson;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.util.ACache;

/**
 * Created by root on 16-4-11.
 */
public class HolidayNextModel {

    /**
     * code : 0
     * msg : OK
     * data : {"name":"清明节","start_date":"2016-04-02","end_date":"2016-04-04","total_days":3,"interval":11}
     */

    private int code;
    private String msg;
    /**
     * name : 清明节
     * start_date : 2016-04-02
     * end_date : 2016-04-04
     * total_days : 3
     * interval : 11
     */

    private DataEntity data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private String name;
        private String start_date;
        private String end_date;
        private int total_days;
        private int interval;

        public void setName(String name) {
            this.name = name;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public void setTotal_days(int total_days) {
            this.total_days = total_days;
        }

        public void setInterval(int interval) {
            this.interval = interval;
        }

        public String getName() {
            return name;
        }

        public String getStart_date() {
            return start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public int getTotal_days() {
            return total_days;
        }

        public int getInterval() {
            return interval;
        }
    }

    public void setCache(){
        ACache aCache = UserModel.getaCache();
        Gson gson = new Gson();
        aCache.put(Constants.Key.HOLIDAY_CACHE_NEXT,gson.toJson(data),ACache.TIME_DAY);
    }
}
