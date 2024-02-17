package com.cz.czapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.cz.czapiclientsdk.model.Sentence;
import com.cz.czapiclientsdk.model.User;
import java.util.HashMap;
import java.util.Map;
import static com.cz.czapiclientsdk.utils.SignUtils.getSign;

/**
 * 调用第三方接口的客户端
 * @author 李钟意
 */
public class CzApiClient {
    public static final String GATEWAY_HOST = "http://localhost:8090";
    private String accessKey;
    private String secretKey;

    public CzApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",name);
        String result = HttpUtil.get(GATEWAY_HOST+"/api/name/", map);
        System.out.println(result);
        return result;
    }
    public String getNameByPost(String name){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",name);
        String result = HttpUtil.post(GATEWAY_HOST+"/api/name/", map);
        System.out.println(result);
        return result;
    }
    public String getUsernameByPost(User user){
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST+"/api/name/user")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }
    public String getSentence(Sentence sentence){
        String json = JSONUtil.toJsonStr(sentence);
        String result = HttpRequest.get(GATEWAY_HOST + "/api/name/user")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute()
                .body();
        return result;
    }
    /**
     * 该方法用于往请求头里加参数
     * @param body
     * @return
     */
    private Map<String,String> getHeaderMap(String body){
        Map<String,String> map = new HashMap<String, String>();
        //用户名
        map.put("accessKey",accessKey);
        //秘钥 一定不能已明文形式发送给后端,
        //map.put("secretKey",secretKey);
        //随机数
        map.put("nonce", RandomUtil.randomNumbers(4));
        //用户参数
        map.put("body",body);
        //时间戳
        map.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
        //签名认证算法
        map.put("sign",getSign(body,secretKey));
        return map;
    }
}
