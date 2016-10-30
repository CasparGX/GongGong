package cc.xaabb.gonggong.model;

import java.util.List;

/**
 * Created by root on 16-4-11.
 */
public class HolidayAllModel {

    /**
     * code : 0
     * msg : 成功
     * data : [{"name":"元旦","start_date":"2016-01-01","end_date":"2016-01-03","total_days":3,"interval":-81},{"name":"春节","start_date":"2016-02-07","end_date":"2016-02-13","total_days":7,"interval":-44},{"name":"清明节","start_date":"2016-04-02","end_date":"2016-04-04","total_days":3,"interval":11},{"name":"劳动节","start_date":"2016-04-30","end_date":"2016-05-02","total_days":3,"interval":39},{"name":"端午节","start_date":"2016-06-09","end_date":"2016-06-11","total_days":3,"interval":79},{"name":"中秋节","start_date":"2016-09-15","end_date":"2016-09-17","total_days":3,"interval":177},{"name":"国庆节","start_date":"2016-10-01","end_date":"2016-10-07","total_days":7,"interval":193}]
     */

    private int code;
    private String msg;
    /**
     * name : 元旦
     * start_date : 2016-01-01
     * end_date : 2016-01-03
     * total_days : 3
     * interval : -81
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
        private String name;
        private String start_date;
        private String end_date;
        private int total_days;
        private int interval;

        public void setName(String name) {
            this.name = name;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public void setTotal_days(int total_days) {
            this.total_days = total_days;
        }

        public void setInterval(int interval) {
            this.interval = interval;
        }

        public String getName() {
            return name;
        }

        public String getStart_date() {
            return start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public int getTotal_days() {
            return total_days;
        }

        public int getInterval() {
            return interval;
        }
    }
}
