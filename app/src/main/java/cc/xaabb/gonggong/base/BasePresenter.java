package cc.xaabb.gonggong.base;

import cc.xaabb.gonggong.network.RetrofitUtils;
import retrofit2.Retrofit;

/**
 * Created by caspar on 16-9-12.
 */
public class BasePresenter {
    protected Retrofit mRetrofit = RetrofitUtils.getInstance().getmRetrofit();

}
