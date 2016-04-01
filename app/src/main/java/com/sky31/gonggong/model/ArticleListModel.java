package com.sky31.gonggong.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wukunguang on 16-3-26.
 */

public class ArticleListModel{



    private int code;

    private String msg;

    private String checkID;

    private List<Data> data ;

    public ArticleListModel(int code, String msg, String checkID, List<Data> data) {
        this.code = code;
        this.msg = msg;
        this.checkID = checkID;
        this.data = data;
    }

    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setCheckID(String checkID){
        this.checkID = checkID;
    }
    public String getCheckID(){
        return this.checkID;
    }
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }

    public static class Data{

        private String id;

        private String title;

        private String thumb;

        private String description;

        private String url;

        private String inputtime;

        private String updatetime;

        private String act_time;

        private String username;


        public String getAct_time() {
            return act_time;
        }

        public void setAct_time(String act_time) {
            this.act_time = act_time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getInputtime() {
            return inputtime;
        }

        public void setInputtime(String inputtime) {
            this.inputtime = inputtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}
