package com.sky31.gonggong.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wukunguang on 16-3-26.
 */
public class ArticleListModel implements Serializable{

    private int code;

    private String msg;

    private String checkID;

    private List<Data> data ;

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

    public class Data{
        private String id;

        private String catid;

        private String typeid;

        private String title;

        private String style;

        private String thumb;

        private String keywords;

        private String description;

        private String posids;

        private String url;

        private String listorder;

        private String status;

        private String sysadd;

        private String islink;

        private String username;

        private String inputtime;

        private String updatetime;

        private String act_time;

        public String getAct_time() {
            return act_time;
        }

        public void setAct_time(String act_time) {
            this.act_time = act_time;
        }

        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
        }
        public void setCatid(String catid){
            this.catid = catid;
        }
        public String getCatid(){
            return this.catid;
        }
        public void setTypeid(String typeid){
            this.typeid = typeid;
        }
        public String getTypeid(){
            return this.typeid;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }
        public void setStyle(String style){
            this.style = style;
        }
        public String getStyle(){
            return this.style;
        }
        public void setThumb(String thumb){
            this.thumb = thumb;
        }
        public String getThumb(){
            return this.thumb;
        }
        public void setKeywords(String keywords){
            this.keywords = keywords;
        }
        public String getKeywords(){
            return this.keywords;
        }
        public void setDescription(String description){
            this.description = description;
        }
        public String getDescription(){
            return this.description;
        }
        public void setPosids(String posids){
            this.posids = posids;
        }
        public String getPosids(){
            return this.posids;
        }
        public void setUrl(String url){
            this.url = url;
        }
        public String getUrl(){
            return this.url;
        }
        public void setListorder(String listorder){
            this.listorder = listorder;
        }
        public String getListorder(){
            return this.listorder;
        }
        public void setStatus(String status){
            this.status = status;
        }
        public String getStatus(){
            return this.status;
        }
        public void setSysadd(String sysadd){
            this.sysadd = sysadd;
        }
        public String getSysadd(){
            return this.sysadd;
        }
        public void setIslink(String islink){
            this.islink = islink;
        }
        public String getIslink(){
            return this.islink;
        }
        public void setUsername(String username){
            this.username = username;
        }
        public String getUsername(){
            return this.username;
        }
        public void setInputtime(String inputtime){
            this.inputtime = inputtime;
        }
        public String getInputtime(){
            return this.inputtime;
        }
        public void setUpdatetime(String updatetime){
            this.updatetime = updatetime;
        }
        public String getUpdatetime(){
            return this.updatetime;
        }

    }
}
