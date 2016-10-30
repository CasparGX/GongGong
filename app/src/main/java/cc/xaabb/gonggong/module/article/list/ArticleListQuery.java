package cc.xaabb.gonggong.module.article.list;

import java.util.HashMap;

/**
 * Created by wukunguang on 16-3-26.
 */
public class ArticleListQuery {

    private String catname;
    private Integer checkID;
    private Integer limit;
    private String co = "";
    private String order;

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public Integer getCheckID() {
        return checkID;
    }

    public void setCheckID(Integer checkID) {
        this.checkID = checkID;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public HashMap<String,String> getHashMap(){

        HashMap<String,String> map = new HashMap<>();
        map.put("catname",catname);
        map.put("checkID",checkID+"");
        map.put("limit",limit+"");
        map.put("co",co);
        map.put("order",order);


        return map;
    }
}
