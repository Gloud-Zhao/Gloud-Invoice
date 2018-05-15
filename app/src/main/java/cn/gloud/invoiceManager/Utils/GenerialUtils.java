package cn.gloud.invoiceManager.Utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import cn.gloud.invoiceManager.Entity.UserInfo;

public class GenerialUtils {

    public static HashMap<String,String> GetBaseMap(Context ctx){
        HashMap<String,String> map=new HashMap<String,String>();
        if (null!=UserInfoUtils.getInstances(ctx).GetUserinfo()){
            UserInfo tUserinfo=UserInfoUtils.getInstances(ctx).GetUserinfo();
            if (!TextUtils.isEmpty(tUserinfo.getLogintoken())){
                map.put(ConstanValues.LOGINTOEKN,tUserinfo.getLogintoken());
            }
            map.put(ConstanValues.ACCOUNT,tUserinfo.getAccount_name());
        }
        return map;
    }

    public static String GetUrlParams(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url + "?");
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }

        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
