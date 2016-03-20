package com.sky31.gonggong.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wukunguang on 16-3-17.
 */
public class SwzlSearchResult implements Serializable{

    private String code;
    private String msg;

    private List<LostAndFoundModel> data;
    private String total;



    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<LostAndFoundModel> getData() {
        return data;
    }

    public void setDatas(List<LostAndFoundModel> data) {
        this.data = data;
    }


    public static class DataEntity implements Serializable{

        private String id;

        private String things;

        private String announcer;

        private String created_at;

        private String location;

        private String description;

        private String mobile;

        private String bank_card;

        private String things_type;

        private String expireDate;

        private String display;

        private String del;

        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
        }
        public void setThings(String things){
            this.things = things;
        }
        public String getThings(){
            return this.things;
        }
        public void setAnnouncer(String announcer){
            this.announcer = announcer;
        }
        public String getAnnouncer(){
            return this.announcer;
        }
        public void setCreated_at(String created_at){
            this.created_at = created_at;
        }
        public String getCreated_at(){
            return this.created_at;
        }
        public void setLocation(String location){
            this.location = location;
        }
        public String getLocation(){
            return this.location;
        }
        public void setDescription(String description){
            this.description = description;
        }
        public String getDescription(){
            return this.description;
        }
        public void setMobile(String mobile){
            this.mobile = mobile;
        }
        public String getMobile(){
            return this.mobile;
        }
        public void setBank_card(String bank_card){
            this.bank_card = bank_card;
        }
        public String getBank_card(){
            return this.bank_card;
        }
        public void setThings_type(String things_type){
            this.things_type = things_type;
        }
        public String getThings_type(){
            return this.things_type;
        }
        public void setExpireDate(String expireDate){
            this.expireDate = expireDate;
        }
        public String getExpireDate(){
            return this.expireDate;
        }
        public void setDisplay(String display){
            this.display = display;
        }
        public String getDisplay(){
            return this.display;
        }
        public void setDel(String del){
            this.del = del;
        }
        public String getDel(){
            return this.del;
        }

    }

}
