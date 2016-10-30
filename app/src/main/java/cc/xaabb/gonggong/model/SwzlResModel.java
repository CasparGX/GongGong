package cc.xaabb.gonggong.model;

import java.io.Serializable;

/**
 * Created by wukunguang on 16-3-1.
 *
 * 用于接受招领平台的信息返回。
 *
 */
public class SwzlResModel implements Serializable{

    private String code;

    private String msg;

    private LostAndFoundModel model;

    public LostAndFoundModel getModel() {
        return model;
    }

    public void setModel(LostAndFoundModel model) {
        this.model = model;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
