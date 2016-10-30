package cc.xaabb.gonggong.model;

import java.util.List;

/**
 * Created by root on 16-6-22.
 */
public class TermCodeModel {

    /**
     * code : 0
     * msg : 成功
     * data : [{"term":"1","code":"201402"},{"term":"2","code":"201501"},{"term":"3","code":"201502"},{"term":"4","code":"201601"}]
     */

    private int code;
    private String msg;
    /**
     * term : 1
     * code : 201402
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
        private String term;
        private String code;

        public void setTerm(String term) {
            this.term = term;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTerm() {
            return term;
        }

        public String getCode() {
            return code;
        }
    }
}
