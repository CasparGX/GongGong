package com.sky31.gonggong.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-3-6.
 */
public class LibraryRentListModel {

    /**
     * code : 0
     * msg : OK
     * data : [{"name":"HTML 5+JavaScript动画基础","status":"本馆续借","deadline":"2016/03/15","book_id":"0000002895150U","dept_id":"41","library_id":"A"},{"name":"O. J.辛普森比窦娥还冤吗？","status":"本馆借出","deadline":"2016/03/15","book_id":"0002629075","dept_id":"33","library_id":"A"}]
     */

    private int code;
    private String msg;
    /**
     * name : HTML 5+JavaScript动画基础
     * status : 本馆续借
     * deadline : 2016/03/15
     * book_id : 0000002895150U
     * dept_id : 41
     * library_id : A
     */

    private ArrayList<DataEntity> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(ArrayList<DataEntity> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Parcelable {

        /**
         * name : HTML 5+JavaScript动画基础
         * status : 本馆续借
         * deadline : 2016/03/15
         * book_id : 0000002895150U
         * dept_id : 41
         * library_id : A
         */

        private String name;
        private String status;
        private String deadline;
        private String book_id;
        private String dept_id;
        private String library_id;

        public void setName(String name) {
            this.name = name;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public void setDept_id(String dept_id) {
            this.dept_id = dept_id;
        }

        public void setLibrary_id(String library_id) {
            this.library_id = library_id;
        }

        public String getName() {
            return name;
        }

        public String getStatus() {
            return status;
        }

        public String getDeadline() {
            return deadline;
        }

        public String getBook_id() {
            return book_id;
        }

        public String getDept_id() {
            return dept_id;
        }

        public String getLibrary_id() {
            return library_id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.status);
            dest.writeString(this.deadline);
            dest.writeString(this.book_id);
            dest.writeString(this.dept_id);
            dest.writeString(this.library_id);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.name = in.readString();
            this.status = in.readString();
            this.deadline = in.readString();
            this.book_id = in.readString();
            this.dept_id = in.readString();
            this.library_id = in.readString();
        }

        public static final Parcelable.Creator<DataEntity> CREATOR = new Parcelable.Creator<DataEntity>() {
            public DataEntity createFromParcel(Parcel source) {
                return new DataEntity(source);
            }

            public DataEntity[] newArray(int size) {
                return new DataEntity[size];
            }
        };
    }
}
