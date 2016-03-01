package com.sky31.gonggong.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 16-2-29.
 */
public class StudentInfoModel {

    /*private StudentInfoModel() {}
    private static final StudentInfoModel studentInfoModel = new StudentInfoModel();
    public static StudentInfoModel getStudentInfoModel(){
        return studentInfoModel;
    }*/

    /**
     * code : 0
     * msg : OK
     * data : {"name":"黄云清","sex":"男","date_of_birth":"19621201","location":"湖南省","college":"信息工程学院","major":"软件工程","class":"12软件工程8"}
     */

    private int code;
    private String msg;
    /**
     * name : 黄云清
     * sex : 男
     * date_of_birth : 19621201
     * location : 湖南省
     * college : 信息工程学院
     * major : 软件工程
     * class : 12软件工程8
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
        private String sex;
        private String date_of_birth;
        private String location;
        private String college;
        private String major;
        @SerializedName("class")
        private String classX;

        public void setName(String name) {
            this.name = name;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setDate_of_birth(String date_of_birth) {
            this.date_of_birth = date_of_birth;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setCollege(String college) {
            this.college = college;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getName() {
            return name;
        }

        public String getSex() {
            return sex;
        }

        public String getDate_of_birth() {
            return date_of_birth;
        }

        public String getLocation() {
            return location;
        }

        public String getCollege() {
            return college;
        }

        public String getMajor() {
            return major;
        }

        public String getClassX() {
            return classX;
        }
    }
}
