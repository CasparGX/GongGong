package com.sky31.gonggong.model;

import java.io.Serializable;

/**
 * Created by wukunguang on 16-4-25.
 */
public class CurrentWeekModel implements Serializable {


    /**
     * code : 0
     * msg : OK
     * data : {"week":9,"first_week_monday":"2016-02-29"}
     */

    private int code;
    private String msg;
    /**
     * week : 9
     * first_week_monday : 2016-02-29
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int week;
        private String first_week_monday;

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }

        public String getFirst_week_monday() {
            return first_week_monday;
        }

        public void setFirst_week_monday(String first_week_monday) {
            this.first_week_monday = first_week_monday;
        }
    }
}
