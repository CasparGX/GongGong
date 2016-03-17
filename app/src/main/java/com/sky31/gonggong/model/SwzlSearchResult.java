package com.sky31.gonggong.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wukunguang on 16-3-17.
 */
public class SwzlSearchResult implements Serializable {

    private int code;
    private String msg;


    @SerializedName("data")
    private List<LostAndFoundModel> datas;
    private String total;


    public SwzlSearchResult(int code, String msg, List<LostAndFoundModel> datas, String total) {
        this.code = code;
        this.msg = msg;
        this.datas = datas;
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

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

    public List<LostAndFoundModel> getDatas() {
        return datas;
    }

    public void setDatas(List<LostAndFoundModel> datas) {
        this.datas = datas;
    }
}
