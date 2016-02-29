package com.sky31.gonggong.model;

/**
 * Created by root on 16-2-28.
 */
public class EcardModel {
    public int code;
    public String msg;
    public Data data;

    public class Data{
        public String name;
        public float balance;
        public float unclaimed;
    }
}
