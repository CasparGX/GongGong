package com.sky31.gonggong.model;

import java.util.List;

/**
 * Created by root on 16-5-2.
 */
public class GradeDetailsModel {

    /**
     * code : 0
     * msg : OK
     * data : [{"course":"C语言程序设计Ⅳ","type":"主修选课","credit":"3","daily_grade":"90","exam_grade":"80","comp_grade":"83.0","term":"1"},{"course":"C语言程序设计实验","type":"主修选课","credit":"2","daily_grade":"","exam_grade":"优秀","comp_grade":"优秀","term":"1"}]
     */

    private int code;
    private String msg;
    /**
     * course : C语言程序设计Ⅳ
     * type : 主修选课
     * credit : 3
     * daily_grade : 90
     * exam_grade : 80
     * comp_grade : 83.0
     * term : 1
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
        private String course;
        private String type;
        private String credit;
        private String daily_grade;
        private String exam_grade;
        private String comp_grade;
        private String term;

        public void setCourse(String course) {
            this.course = course;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public void setDaily_grade(String daily_grade) {
            this.daily_grade = daily_grade;
        }

        public void setExam_grade(String exam_grade) {
            this.exam_grade = exam_grade;
        }

        public void setComp_grade(String comp_grade) {
            this.comp_grade = comp_grade;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public String getCourse() {
            return course;
        }

        public String getType() {
            return type;
        }

        public String getCredit() {
            return credit;
        }

        public String getDaily_grade() {
            return daily_grade;
        }

        public String getExam_grade() {
            return exam_grade;
        }

        public String getComp_grade() {
            return comp_grade;
        }

        public String getTerm() {
            return term;
        }
    }
}
