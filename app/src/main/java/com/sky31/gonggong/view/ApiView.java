package com.sky31.gonggong.view;

import com.sky31.gonggong.model.EcardModel;
import com.sky31.gonggong.model.StudentInfoModel;

/**
 * Created by root on 16-2-29.
 */
public interface ApiView {
    void getBalance(EcardModel ecardModel);
    void login(StudentInfoModel studentInfoModel);

}
