package com.sky31.gonggong.model;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.util.ACache;

import java.io.Serializable;
import java.util.ArrayList;

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

    public ArrayList<DataEntity> getData() {
        return data;
    }

    public void setData(ArrayList<DataEntity> data) {
        this.data = data;
    }

    public void setCache() {
        ACache aCache = UserModel.getaCache();
        //只能使用List的子类
        //ArrayList<StudentInfoModel.DataEntity> arrayList = new ArrayList();
        //注意：一定要序列化
        //StudentInfoModel.DataEntity dataEntity = data.get(0);
        //arrayList.add(dataEntity);
        int size = data != null ? data.size() : 0;
        aCache.put(Constants.Key.LIBRARY_RENT_LIST, data);
        aCache.put(Constants.Key.LIBRARY_RENT_NUM, size + "");
        //使用getAsObject()，直接进行强转
        /*ArrayList<DataEntity> rentList = (ArrayList<DataEntity>) aCache.getAsObject(Constants.Key.LIBRARY_RENT_LIST);
        for (int i = 0; i < data.size(); i++) {

            Debug.i("rentList", rentList.get(i).getName() + "");
        }*/
    }

    public static class DataEntity implements Serializable {

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

        public DataEntity() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getDept_id() {
            return dept_id;
        }

        public void setDept_id(String dept_id) {
            this.dept_id = dept_id;
        }

        public String getLibrary_id() {
            return library_id;
        }

        public void setLibrary_id(String library_id) {
            this.library_id = library_id;
        }

    }
}
