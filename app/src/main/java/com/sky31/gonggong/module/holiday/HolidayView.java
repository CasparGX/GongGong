package com.sky31.gonggong.module.holiday;

import com.sky31.gonggong.model.HolidayNextModel;

/**
 * Created by root on 16-4-12.
 */
public interface HolidayView {
    void onGetHolidayNext();
    void finishGetHolidayNext(HolidayNextModel holidayNextModel);

    void onGetHolidayAll();
    void finishGetHolidayAll(HolidayNextModel holidayNextModel);
}
