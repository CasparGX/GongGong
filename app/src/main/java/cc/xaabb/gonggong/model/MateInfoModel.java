package cc.xaabb.gonggong.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 16-3-25.
 * 查找学生信息
 */
public class MateInfoModel {

    /**
     * code : 0
     * msg : OK
     * data : [{"sid":"2012345678","name":"黄云清","class":"12软件工程8","card":"6217002930101234567"}]
     */

    private int code;
    private String msg;
    /**
     * sid : 2012345678
     * name : 黄云清
     * class : 12软件工程8
     * card : 6217002930101234567
     */

    private List<DataEntity> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String sid;
        private String name;
        @SerializedName("class")
        private String classX;
        private String card;

        public void setSid(String sid) {
            this.sid = sid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getSid() {
            return sid;
        }

        public String getName() {
            return name;
        }

        public String getClassX() {
            return classX;
        }

        public String getCard() {
            return card;
        }
    }
}
