package cc.xaabb.gonggong.model;

import java.util.List;

/**
 * Created by root on 16-4-25.
 */
public class GradeReportModel {

    /**
     * code : 0
     * msg : OK
     * data : [{"course":"C语言程序设计实验","grade":"优秀","term":"1"},{"course":"大学生心理健康教育","grade":"75","term":"1"}]
     */

    private int code;
    private String msg;
    /**
     * course : C语言程序设计实验
     * grade : 优秀
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
        private String grade;
        private String term;

        public void setCourse(String course) {
            this.course = course;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public String getCourse() {
            return course;
        }

        public String getGrade() {
            return grade;
        }

        public String getTerm() {
            return term;
        }
    }
}
