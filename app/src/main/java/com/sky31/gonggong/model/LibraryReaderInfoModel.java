package com.sky31.gonggong.model;

import android.content.Context;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.util.ACache;

/**
 * Created by root on 16-3-6.
 */
public class LibraryReaderInfoModel {

    /**
     * code : 0
     * msg : OK
     * data : {"name":"黄云清","valid_date_start":"2015-10-13","valid_date_end":"2019-06-15","debt":"0"}
     */

    private int code;
    private String msg;
    /**
     * name : 黄云清
     * valid_date_start : 2015-10-13
     * valid_date_end : 2019-06-15
     * debt : 0
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
        private String valid_date_start;
        private String valid_date_end;
        private String debt;

        public void setName(String name) {
            this.name = name;
        }

        public void setValid_date_start(String valid_date_start) {
            this.valid_date_start = valid_date_start;
        }

        public void setValid_date_end(String valid_date_end) {
            this.valid_date_end = valid_date_end;
        }

        public void setDebt(String debt) {
            this.debt = debt;
        }

        public String getName() {
            return name;
        }

        public String getValid_date_start() {
            return valid_date_start;
        }

        public String getValid_date_end() {
            return valid_date_end;
        }

        public String getDebt() {
            return debt;
        }
    }

    public void setCache(Context context){
        ACache aCache = ACache.get(context);
        aCache.put(Constants.Key.LIBRARY_VALID_DATE_START,data.getValid_date_start());
        aCache.put(Constants.Key.LIBRARY_VALID_DATE_END,data.getValid_date_end());
        aCache.put(Constants.Key.LIBRARY_DEBT,data.getDebt());
    }
}
