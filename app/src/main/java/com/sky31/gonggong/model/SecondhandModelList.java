package com.sky31.gonggong.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wukunguang on 16-5-10.
 */
public class SecondhandModelList implements Serializable{

    private ArrayList<SecondhandModel> models;


    public ArrayList<SecondhandModel> getModels() {
        return models;
    }

    public void setModels(ArrayList<SecondhandModel> models) {
        this.models = models;
    }
}
