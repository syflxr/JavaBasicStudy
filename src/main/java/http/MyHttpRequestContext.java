package http;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: shenyafeng
 * @Date: 2021/2/4 21:27
 * @Description: http请求体，
 */


public class MyHttpRequestContext {
    private String host;
    private String url;
    private String connection;
    private String refer;
    private String userAgent;
    private String cookie;
    private  Map<String, String> paramMap = new HashMap<>();
    private  Map<String, String> httpRequestHeaderMap = new HashMap<>();
    private static final Logger myLogger= LoggerFactory.getLogger(MyHttpRequestContext.class);

    public String getParam(String key){
        if(StringUtils.isEmpty(key)){
            return "";
        }
        return paramMap.get(key);
    }
    public MyHttpRequestContext(String requestStr){
        if (StringUtils.isEmpty(requestStr)) {
            return;
        }
        //找到数据体
        int dataEndIndex = requestStr.lastIndexOf("\r\n");
        String data = requestStr.substring(dataEndIndex+2, requestStr.length());
        String[] params = data.split("&");
        for(int i=0;i<params.length;i++){
            if(params[i].contains("=")){
                String[] split1 = params[i].split("=");
                paramMap.put(split1[0],split1.length==2?split1[1]:"");
            }
        }
        String headString=requestStr.substring(0,dataEndIndex);
        String[] split = headString.split("\r\n");
        String[] split1 = split[0].split(" ");
        paramMap.put("url",split1[1]);
        for(int i=0;i<split.length;i++){
            if(split[i].contains(":")){
                String[] split2 = split[i].split(":");
                httpRequestHeaderMap.put(split2[0].trim(),split2.length==2?split2[1].trim():"");
            }
        }
        myLogger.info("打印post数据"+ JSON.toJSONString(paramMap));
        myLogger.info("打印http请求头"+ JSON.toJSONString(httpRequestHeaderMap));
    }

    public static void getHttpPostParams(String requestStr) {


    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
