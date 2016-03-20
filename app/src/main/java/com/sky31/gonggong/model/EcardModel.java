package com.sky31.gonggong.model;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.util.ACache;

/**
 * Created by root on 16-2-28.
 */
public class EcardModel {

    /**
     * code : 0
     * msg : OK
     * data : {"name":"黄云清","balance":"3.10","unclaimed":"100.00"}
     */

    private int code;
    private String msg;
    /**
     * name : 黄云清
     * balance : 3.10
     * unclaimed : 100.00
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
        private String balance;
        private String unclaimed;

        public String getEcard_id() {
            return ecard_id;
        }

        public void setEcard_id(String ecard_id) {
            this.ecard_id = ecard_id;
        }

        private String ecard_id;

        public void setName(String name) {
            this.name = name;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public void setUnclaimed(String unclaimed) {
            this.unclaimed = unclaimed;
        }

        public String getName() {
            return name;
        }

        public String getBalance() {
            return balance;
        }

        public String getUnclaimed() {
            return unclaimed;
        }
    }

    public void setCache() {
        ACache aCache = UserModel.getaCache();
        aCache.put(Constants.Key.BALANCE,getData().getBalance());
        aCache.put(Constants.Key.UNCLAIMED,getData().getUnclaimed());
        aCache.put(Constants.Key.ECARD_ID,getData().getEcard_id());
    }
}
