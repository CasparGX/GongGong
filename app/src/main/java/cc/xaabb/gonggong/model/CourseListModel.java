package cc.xaabb.gonggong.model;

import com.google.gson.Gson;
import cc.xaabb.gonggong.config.Constants;
import cc.xaabb.gonggong.util.ACache;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wukunguang on 16-4-25.
 */
public class CourseListModel implements Serializable {
    /**
     * code : 0
     * msg : OK
     * data : [[{"course":"数据库原理","location":"逸夫楼-310","teacher":"谢勇","week":"1,2,3,4,5,6,7,8,9,10,11,12","week_string":"第1-12周","day":"1","section_start":"1","section_end":"2"}],[{"course":"大学体育4","location":"体育馆","teacher":"雷辉旭","week":"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18","week_string":"第1-18周","day":"1","section_start":"5","section_end":"6"}],[{"course":"古希腊文学欣赏","location":"南山一阶梯","teacher":"李志雄","week":"1,2,3,4,5,6,7,8,9,10","week_string":"第1-10周","day":"6","section_start":"1","section_end":"3"},{"course":"古希腊文学欣赏","location":"南山一阶梯","teacher":"李志雄","week":"11","week_string":"第11周","day":"6","section_start":"1","section_end":"2"}]]
     */

    private int code;
    private String msg;

    /**
     * course : 数据库原理
     * location : 逸夫楼-310
     * teacher : 谢勇
     * week : 1,2,3,4,5,6,7,8,9,10,11,12
     * week_string : 第1-12周
     * day : 1
     * section_start : 1
     * section_end : 2
     */

    private List<List<DataBean>> data;

    /**
     * code : 0
     * msg : OK
     * data : {"1":[[{"course":"数据库原理","location":"逸夫楼-310","teacher":"谢勇","week":"1,2,3,4,5,6,7,8,9,10,11,12","week_string":"第1-12周","section_start":"1","section_end":"2"}],[{"course":"大学体育4","location":"体育馆","teacher":"雷辉旭","week":"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18","week_string":"第1-18周","section_start":"5","section_end":"6"}]],"2":[[{"course":"古希腊文学欣赏","location":"南山一阶梯","teacher":"李志雄","week":"1,2,3,4,5,6,7,8,9,10","week_string":"第1-10周","section_start":"1","section_end":"3"},{"course":"古希腊文学欣赏","location":"南山一阶梯","teacher":"李志雄","week":"11","week_string":"第11周","section_start":"1","section_end":"2"}]]}
     */





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



    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private String course;
        private String location;
        private String teacher;
        private String week;
        private String week_string;
        private String day;
        private String section_start;
        private String section_end;
        private int flag;

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWeek_string() {
            return week_string;
        }

        public void setWeek_string(String week_string) {
            this.week_string = week_string;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getSection_start() {
            return section_start;
        }

        public void setSection_start(String section_start) {
            this.section_start = section_start;
        }

        public String getSection_end() {
            return section_end;
        }

        public void setSection_end(String section_end) {
            this.section_end = section_end;
        }
    }

    public void setCache(){

        ACache aCache = UserModel.getaCache();
        Gson gson = new Gson();
        aCache.put(Constants.Key.COURSE_LIST,gson.toJson(data),ACache.TIME_DAY);


    }

}
