package com.sky31.gonggong.model;

import java.io.Serializable;

/**
 * Created by wukunguang on 16-3-1.
 */
public class LostAndFoundModel implements Serializable {

    private String things_type;
    private String announcer;
    private String location;
    private String things;  // 当丢失物品为校园卡时，thing可为null。
    private String description; //物品描述
    private String mobile ; //手机号码
    private String bank_card;   //当丢失物品为校园卡时候，必须非空。
    private int expireDays = 5;//默认为5天。

    public String getThings_type() {
        return things_type;
    }

    public void setThings_type(String things_type) {
        this.things_type = things_type;
    }

    public String getAnnouncer() {
        return announcer;
    }

    public void setAnnouncer(String announcer) {
        this.announcer = announcer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getThings() {
        return things;
    }

    public void setThings(String things) {
        this.things = things;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBank_card() {
        return bank_card;
    }

    public void setBank_card(String bank_card) {
        this.bank_card = bank_card;
    }

    public int getExpireDays() {
        return expireDays;
    }

    public void setExpireDays(int expireDays) {
        this.expireDays = expireDays;
    }
}
