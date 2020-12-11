import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WebCrawler {
    public static void main(String[] args) throws IOException {
        HttpClient httpClient= HttpClients.createDefault();
        HttpGet httpGet=new HttpGet("http://www.itcast.cn");
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode()==200){
            HttpEntity httpEntity=response.getEntity();
            String content = EntityUtils.toString(httpEntity,"utf8");
            System.out.println(content);

        }

    }
    @Test
    public void postWithPatams() throws UnsupportedEncodingException {
        CloseableHttpClient httpClient=HttpClients.createDefault();
        HttpPost httpPost=new HttpPost("http://yun.itheima.com/search");
        //声明list集合，封装表单中的参数
        List<NameValuePair> params=new ArrayList<>();
        params.add(new BasicNameValuePair("so","java"));
        params.add(new BasicNameValuePair("type","course"));
        params.add(new BasicNameValuePair("realhash","18fc53a52a033fe75f7d0542124ca8c0_97af8e0241c4a1d98f9b4d6d64e8b78b"));
        //设置表单中的entity
        UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(params,"utf8");
        httpPost.setEntity(formEntity);
        CloseableHttpResponse response=null;
        try {
            response=httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode()==200){
                String content=EntityUtils.toString(response.getEntity(),"utf8");
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
