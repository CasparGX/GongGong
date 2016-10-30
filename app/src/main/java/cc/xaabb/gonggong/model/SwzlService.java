package cc.xaabb.gonggong.model;

import cc.xaabb.gonggong.config.Constants;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by wukunguang on 16-3-1.
 */
public interface SwzlService {

    String defaultParm = "&role="+Constants.Value.ROLE+"&hash="+Constants.Value.HASH+"&cache=OFF";
    String submitLostParm = "ask_method="+Constants.Value.SWZL_ASK_METHOD_JSON+"&"+"action="+Constants.Value.SWZL_SUBMIT_LOST+defaultParm;
    String submitGetParm = "ask_method="+Constants.Value.SWZL_ASK_METHOD_JSON+"&"+"action="+Constants.Value.SWZL_SUBMIT_FOUND+defaultParm;

    String search_lost_thing = "ask_method="+Constants.Value.SWZL_ASK_METHOD_JSON+"&action="+Constants.Value.SWZL_SEARCH_LOST+defaultParm;
    String search_get_thing = "ask_method="+Constants.Value.SWZL_ASK_METHOD_JSON+"&action="+Constants.Value.SWZL_SEARCH_FOUND+defaultParm;
    String search = "ask_method="+Constants.Value.SWZL_ASK_METHOD_JSON+"&action="+Constants.Value.SWZL_SEARCH+defaultParm;

    @GET(Constants.Api.SWZL_ACTION+"?"+search_get_thing)
    Observable<SwzlSearchResult> getSerResultByGet( );



    @GET(Constants.Api.SWZL_ACTION+"?"+search_lost_thing)
    Observable<SwzlSearchResult> getSerResultByLost( );

    @FormUrlEncoded
    @POST(Constants.Api.SWZL_ACTION+"?"+submitLostParm)
    Observable<SwzlResModel> getSubResultByLost(@FieldMap Map<String,String> map);


    @FormUrlEncoded
    @POST(Constants.Api.SWZL_ACTION+"?"+submitGetParm)
    Observable<SwzlResModel> getSubResultByGet(@FieldMap  Map<String,String> map);


    @GET(Constants.Api.SWZL_ACTION+"?"+search)
    Observable<SwzlSearchResult> getSearchResult(@Query("key") String key);



}
