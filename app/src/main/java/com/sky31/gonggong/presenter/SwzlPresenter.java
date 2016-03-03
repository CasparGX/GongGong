package com.sky31.gonggong.presenter;

import com.sky31.gonggong.model.LostAndFoundModel;
import com.sky31.gonggong.model.SwzlService;
import com.sky31.gonggong.view.SwzlPublishView;

/**
 * Created by wukunguang on 16-3-1.
 */
public class SwzlPresenter {

    private SwzlPublishView swzlPublish;
    private SwzlService swzlService;


    public SwzlPresenter(SwzlPublishView swzlPublish) {
        this.swzlPublish = swzlPublish;
    }

    public void publishSwzl(LostAndFoundModel model){

        // 传入model参数。

    }

}
