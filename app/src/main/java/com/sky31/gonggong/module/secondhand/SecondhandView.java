package com.sky31.gonggong.module.secondhand;

import com.google.gson.JsonArray;

/**
 * Created by wukunguang on 16-5-10.
 */
public interface SecondhandView {

    void getSecondhandData(JsonArray secondHandModelList, int code);
}
