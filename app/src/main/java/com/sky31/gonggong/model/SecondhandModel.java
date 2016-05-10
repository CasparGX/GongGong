package com.sky31.gonggong.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wukunguang on 16-5-10.
 */
public class SecondhandModel implements Serializable{

    /**
     * picsrc : /Public/uploadfile/415/1462870492/
     * picname : {"1":"5731a1dccd648.jpg","2":"5731a1ddd6ebe.jpg","3":"5731a1de8d9a2.jpg"}
     * title : 自行车、电动车出租
     * type : 租赁
     * price : 1
     * describe : 自行车、电动车出租;
     承接班级户外活动：露营、烧烤、素拓等
     地址：湘大北苑千里达自行车行
     酷风户外俱乐部

     * bargain : 1
     * trading : 1
     * seller : 酷风户外骑行
     * sellerid : 415
     * qq : 201983683
     * phone : 18975266193
     * id : 2395
     * creat_time : 2016-05-10 16:54:55
     * last_time : 2016-05-10 16:54:55
     * end_time : 2016-06-09 00:00:00
     */

    private String picsrc;
    /**
     * 1 : 5731a1dccd648.jpg
     * 2 : 5731a1ddd6ebe.jpg
     * 3 : 5731a1de8d9a2.jpg
     */

    private PicnameBean picname;
    private String title;
    private String type;
    private String price;
    private String describe;
    private String bargain;
    private String trading;
    private String seller;
    private String sellerid;
    private String qq;
    private String phone;
    private String id;
    private String creat_time;
    private String last_time;
    private String end_time;

    public String   getPicsrc() {
        return picsrc;
    }

    public void setPicsrc(String picsrc) {
        this.picsrc = picsrc;
    }

    public PicnameBean getPicname() {
        return picname;
    }

    public void setPicname(PicnameBean picname) {
        this.picname = picname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getBargain() {
        return bargain;
    }

    public void setBargain(String bargain) {
        this.bargain = bargain;
    }

    public String getTrading() {
        return trading;
    }

    public void setTrading(String trading) {
        this.trading = trading;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }

    public String getLast_time() {
        return last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public static class PicnameBean implements Serializable{
        @SerializedName("1")
        private String value1;
        @SerializedName("2")
        private String value2;
        @SerializedName("3")
        private String value3;

        public String getValue1() {
            return value1;
        }

        public void setValue1(String value1) {
            this.value1 = value1;
        }

        public String getValue2() {
            return value2;
        }

        public void setValue2(String value2) {
            this.value2 = value2;
        }

        public String getValue3() {
            return value3;
        }

        public void setValue3(String value3) {
            this.value3 = value3;
        }
    }
}
