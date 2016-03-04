package com.sky31.gonggong.model;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.util.ACache;

/**
 * Created by root on 16-3-4.
 */
public class CampusNetwork {

    /**
     * code : 0
     * msg : OK
     * data : {"name":"黄云清 ","package":"学生校园网包月","status":"正常","next_statement_date":"2016-03-26","balance":"0.00"}
     */

    private int code;
    private String msg;
    /**
     * name : 黄云清
     * package : 学生校园网包月
     * status : 正常
     * next_statement_date : 2016-03-26
     * balance : 0.00
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
        @SerializedName("package")
        private String packageX;
        private String status;
        private String next_statement_date;
        private String balance;

        public void setName(String name) {
            this.name = name;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setNext_statement_date(String next_statement_date) {
            this.next_statement_date = next_statement_date;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getName() {
            return name;
        }

        public String getPackageX() {
            return packageX;
        }

        public String getStatus() {
            return status;
        }

        public String getNext_statement_date() {
            return next_statement_date;
        }

        public String getBalance() {
            return balance;
        }
    }

    public void setCache(Context context){
        ACache aCache = ACache.get(context);
        aCache.put(Constants.Key.CAMPUS_NETWORK_BALANCE,data.getBalance());
        aCache.put(Constants.Key.CAMPUS_NETWORK_STATUS,data.getStatus());
        aCache.put(Constants.Key.CAMPUS_NETWORK_NEXT_STATEMENT_DATE,data.getNext_statement_date());
        aCache.put(Constants.Key.CAMPUS_NETWORK_PACKAGEX,data.getPackageX());
    }

}
