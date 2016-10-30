package cc.xaabb.gonggong.module.holiday;

import cc.xaabb.gonggong.model.HolidayAllModel;
import cc.xaabb.gonggong.model.HolidayNextModel;

/**
 * Created by root on 16-4-12.
 */
public interface HolidayView {
    void onGetHolidayNext();
    void finishGetHolidayNext(HolidayNextModel holidayNextModel);

    void onGetHolidayAll();

    void finishGetHolidayAll(HolidayAllModel holidayAllModel);
}
