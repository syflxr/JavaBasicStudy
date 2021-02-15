package Other;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import jdk.nashorn.internal.codegen.SpillObjectCreator;
import org.apache.http.client.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;
import java.net.HttpCookie;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: shenyafeng
 * @Date: 2020/12/4 16:11
 * @Description:
 */

public class HttpTest {
    //极客时间url
    private static final String url3="https://time.geekbang.org/serv/v1/article";
    private static final String COOKIE_STR="GCID=56ce2f0-5469b13-87be5a4-8097da7; GRID=56ce2f0-5469b13-87be5a4-8097da7; _ga=GA1.2.722215204.1612246686; LF_ID=1612246685820-4407229-6021311; GCESS=BQQEAC8NAAYEVReMGQkBAQIEs.4YYAEIZyAlAAAAAAALAgUACgQAAAAACAEDAwSz7hhgBwSMbJRABQQAAAAADAEB; gksskpitn=0ac63066-de8b-43d3-a7e6-16bcacaffd85; Hm_lvt_59c4ff31a9ee6263811b23eb921a5083=1612246686,1612246708,1612247136,1612498890; Hm_lvt_022f847c4e3acd44d4a2481d9187f1e6=1612246686,1612246708,1612247136,1612498890; _gid=GA1.2.538040426.1612498890; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%222433127%22%2C%22first_id%22%3A%221776164179f2dd-0265b51e31ae2b-13e3563-2073600-177616417a0419%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_landing_page%22%3A%22https%3A%2F%2Ftime.geekbang.org%2Fcolumn%2Farticle%2F268253%22%2C%22%24latest_utm_source%22%3A%22related_read%22%2C%22%24latest_utm_medium%22%3A%22article%22%2C%22%24latest_utm_term%22%3A%22pc_interstitial_854%22%7D%2C%22%24device_id%22%3A%221776164179f2dd-0265b51e31ae2b-13e3563-2073600-177616417a0419%22%7D; _gat=1; Hm_lpvt_59c4ff31a9ee6263811b23eb921a5083=1612578231; gk_process_ev={%22count%22:49%2C%22target%22:%22%22}; Hm_lpvt_022f847c4e3acd44d4a2481d9187f1e6=1612578231; SERVERID=1fa1f330efedec1559b3abbcb6e30f50|1612578233|1612576143";
    private static final String HTML_HEADER="<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "</head>\n" +
            "\n" +
            "<body>";
    private static final String HTML_TAIL="</body>\n" +
            "</html>";
    public static void main(String[] args) throws Exception {
        //System.out.println(url);
       // String s = HttpUtil.get(url2+"&timestamp="+System.currentTimeMillis());
        //String s1 = HttpUtil.get(url);
        //.out.println(s1);
        System.out.println(System.currentTimeMillis());
    }

    class PostData{
        @JSONField
        int id;

        @JSONField
        boolean include_neighbors;

        @JSONField
        boolean is_freelyread;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isInclude_neighbors() {
            return include_neighbors;
        }

        public void setInclude_neighbors(boolean include_neighbors) {
            this.include_neighbors = include_neighbors;
        }

        public boolean isIs_freelyread() {
            return is_freelyread;
        }

        public void setIs_freelyread(boolean is_freelyread) {
            this.is_freelyread = is_freelyread;
        }
    }

    @Test
    public void printPostData(){
        PostData postData=new PostData();
        postData.setId(268253);
        postData.setInclude_neighbors(true);
        postData.setIs_freelyread(true);
        System.out.println(JSON.toJSONString(postData));
    }
    /*











    User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36
     */
    @Test
    public void grabFromJike() throws IOException {
        HttpRequest post = HttpUtil.createPost(url3);
        post.setConnectionTimeout((int)TimeUnit.SECONDS.toMillis(10));


        //设置请求头
        post.header("Accept","application/json, text/plain, */*")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Accept-Language","zh-CN,zh;q=0.9")
                .header("Connection","keep-alive")
                .header("Content-Length","59")
                .header("Content-Type","application/json")
                .header("Cookie",COOKIE_STR)
                .header("Host","time.geekbang.org")
                .header("Origin","https://time.geekbang.org")
                //.header("Referer"," https://time.geekbang.org/column/article/268247")
                .header("User-Agent","User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:78.0) Gecko/20100101 Firefox/78.0");

        //设置post参数
        PostData postData=new PostData();
        postData.setId(268253);
        postData.setInclude_neighbors(true);
        postData.setIs_freelyread(true);
        //StringBuilder sb=new StringBuilder("{\"id\":268247,\"include_neighbors\":true,\"is_freelyread\":true}");
        Writer writer=new FileWriter("C:\\"+postData.getId()+".html");
        PrintWriter printer=new PrintWriter(writer);

        //请求数据
        post.body(JSON.toJSONString(postData));
        HttpResponse execute = post.execute();
        String cookieStr = execute.getCookieStr();

        JSONObject originJson = JSONObject.parseObject(execute.body());
        JSONObject data = originJson.getJSONObject("data");
        String article_content = data.getString("article_content");
        String htmlResult=HTML_HEADER+article_content+HTML_TAIL;
        Document document = Jsoup.parse(htmlResult);
        Elements elements = document.getElementsByTag("img");

        for(int i=0;i<elements.size();++i){
            Element element = elements.get(i);
            element.attr("height",600+"");
            element.attr("width",800+"");
            element.attr("align","center");

        }
        printer.println(document.toString());
        printer.flush();
    }
}
