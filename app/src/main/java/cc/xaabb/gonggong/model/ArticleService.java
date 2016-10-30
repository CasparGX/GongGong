package cc.xaabb.gonggong.model;

import cc.xaabb.gonggong.config.Constants;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by wukunguang on 16-3-26.
 */
public interface ArticleService {

    String defaultParm = Constants.Key.ROLE+ "=" + Constants.Value.ROLE +
            "&"+Constants.Key.HASH + "=" + Constants.Value.HASH;

    @GET(Constants.Api.CMS_LIST+"?"+defaultParm)
    Observable<ArticleListModel> getArticleList(@QueryMap HashMap<String,String> map);
}
