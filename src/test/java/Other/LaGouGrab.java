package Other;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: shenyafeng
 * @Date: 2021/2/6 15:01
 * @Description:抓取拉勾课程
 */


public class LaGouGrab {
    //cookie，每个人不一样
    private static final String LAGOU_COOKIE_STR = "EDUJSESSIONID=ABAAABAAAECABEH685F0727F83AEF65556ABB5AA7D9F9A0; X_HTTP_TOKEN=42daf4b72327b2814654952161bf5e71415983ed09; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218816427%22%2C%22first_id%22%3A%22177761fdc7062-08986fdd42ed31-4c302372-921600-177761fdc713b2%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E8%87%AA%E7%84%B6%E6%90%9C%E7%B4%A2%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fwww.baidu.com%2Flink%22%2C%22%24os%22%3A%22Windows%22%2C%22%24browser%22%3A%22Firefox%22%2C%22%24browser_version%22%3A%2278.0%22%7D%2C%22%24device_id%22%3A%22177761fdc7062-08986fdd42ed31-4c302372-921600-177761fdc713b2%22%7D; sajssdk_2015_cross_new_user=1; sensorsdata2015session=%7B%7D; thirdDeviceIdInfo=%5B%7B%22channel%22%3A1%2C%22thirdDeviceId%22%3A%22WC39ZUyXRgdHGsI59ZneDcLppg7r+ewiaBqQforZPadSHZ271XqAUqmMYEcjfl63fvwgLYvl9OJEBd9srFjLPvt56d1KIs5GjtL/WmrP2Tav+DYF2YqyHqyTXhOLxGUe53fVLMpg6KnK+4M2GQH+t5J4mCbdPQ1BRajW7hxiYtqPeiyJPWjuvJK8TRPE3LzCNvWb/p8SPBDeOlA627LWEpSxwbQwuQzw6KhHqSAOAClpi41ErNDft+w%3D%3D1487577677129%22%7D%2C%7B%22channel%22%3A2%7D%5D; smidV2=202102061455351406cf065e32e492ec3429c129c44c4e0064aeb3a3ce45890; user-finger=683490474e5cc5a85c0ff0408bcb3655; LG_LOGIN_USER_ID=507f203412b9766233e7cf893f41780529b6ed428f7f5adf643409a56e4d61dc; LG_HAS_LOGIN=1; _putrc=E1AED9D5596D6F97123F89F2B170EADC; login=true; unick=%E7%94%A8%E6%88%B76322; gate_login_token=4fe54a13d4158720bfe8cabd4581943e8a46b50a84c328f6fc46541f30ed0834; JSESSIONID=0680E53C0BC28B5B1B2BF9907B90E187";
    //存储目录
    private static String SAVE_DIR = "C:\\netty核心原理剖析与rpc实践";
    //文章内容url
    private static final String DETAIL_URL = "https://gate.lagou.com/v1/neirong/kaiwu/getCourseLessonDetail?lessonId=ID";
    //某专栏的地址
    private static final String COURSE_URL="https://gate.lagou.com/v1/neirong/kaiwu/getCourseLessons?courseId=COURSE_ID";
    //获取用户加入学习计划的课程列表
    private static final String USER_COURSE_URL="https://gate.lagou.com/v1/neirong/kaiwu/getAllCoursePurchasedRecordForPC";
    static class LessonInfo{
        public LessonInfo(int id,String name){
            this.id=id;
            this.sectionName=name;
        }
        //课程id
        @JSONField
        int id;
        //课程所属章节名称
        @JSONField
        String sectionName;
    }
    private static void GrabFromLaGou(){
        List<Integer> courseIdList=new ArrayList<>();
        HttpRequest request = HttpUtil.createRequest(Method.GET, USER_COURSE_URL);
        //Pragma: no-cache
        //Cache-Control: no-cache
        request.header("Host", "gate.lagou.com")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:78.0) Gecko/20100101 Firefox/78.0")
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Authorization", "4fe54a13d4158720bfe8cabd4581943e8a46b50a84c328f6fc46541f30ed0834")
                .header("X-L-REQ-HEADER", "{deviceType:1}")
                .header("Origin", "https://kaiwu.lagou.com")
                .header("Connection", "keep-alive")
                .header("Referer", "https://kaiwu.lagou.com/course/courseInfo.htm?courseId=6")
                .header("Cookie", LAGOU_COOKIE_STR)
                .header("TE", "Trailers")
                .header("Pragma", "no-cache")
                .header("Cache-Control", "no-cache");
        String body = request.execute().body();
        System.out.println(body);
        JSONObject jsonObject = JSON.parseObject(body);
        JSONObject content = jsonObject.getJSONObject("content");
        JSONArray allCoursePurchasedRecord = content.getJSONArray("allCoursePurchasedRecord");
        JSONObject jsonObject1 = allCoursePurchasedRecord.getJSONObject(0);
        JSONArray courseRecordList = jsonObject1.getJSONArray("courseRecordList");
        for(int i=0;i<courseRecordList.size();i++){
            JSONObject jsonObject2 = courseRecordList.getJSONObject(i);
            courseIdList.add(jsonObject2.getInteger("id"));
        }
        courseIdList.forEach(item->{
            List<LessonInfo> lessonInfoByCourseId = getLessonInfoByCourseId(item);
            try {
                grabFromLaGou(lessonInfoByCourseId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    //构造html的前面的部分
    private static final String HTML_HEADER = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "</head>\n" +
            "\n" +
            "<body>";
    //构造html后面的部分
    private static final String HTML_TAIL = "</body>\n" +
            "</html>";

    /**
     * @param path
     * @description: 判断一个目录是否存在，不存在则创建，否则什么都不干
     * @return: void
     * @author shenyafeng
     * @date: 2021/2/6 16:15
     */
    private static void createDirIfNotExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * @description:
     * @param lessonInfos
     * @return: void
     * @author shenyafeng
     * @date: 2021/2/7 9:56
     */
    private static void grabFromLaGou(List<LessonInfo> lessonInfos) throws InterruptedException, IOException {
       if(CollectionUtil.isEmpty(lessonInfos)){
           return;
       }
        for (LessonInfo info:lessonInfos) {
            int i=info.id;
            String sectionName=info.sectionName;
            String detailUrlNow = DETAIL_URL.replace("ID", i + "");
            System.out.println(detailUrlNow);
            HttpRequest request = HttpUtil.createRequest(Method.GET, detailUrlNow);
            //Pragma: no-cache
            //Cache-Control: no-cache
            request.header("Host", "gate.lagou.com")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:78.0) Gecko/20100101 Firefox/78.0")
                    .header("Accept", "application/json, text/plain, */*")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Authorization", "4fe54a13d4158720bfe8cabd4581943e8a46b50a84c328f6fc46541f30ed0834")
                    .header("X-L-REQ-HEADER", "{deviceType:1}")
                    .header("Origin", "https://kaiwu.lagou.com")
                    .header("Connection", "keep-alive")
                    .header("Referer", "https://kaiwu.lagou.com/course/courseInfo.htm?courseId=6")
                    .header("Cookie", LAGOU_COOKIE_STR)
                    .header("TE", "Trailers")
                    .header("Pragma", "no-cache")
                    .header("Cache-Control", "no-cache");
            String body = request.execute().body();
            writeHtmlFromResponse(body,sectionName);
            System.out.println("课程id" + i + "已抓取");
            //抓取一个后60s+随机数后的时间再抓第二个
            Random random = new Random();
            int i1 = random.nextInt(15);
            System.out.println("随机睡眠时间为" + i1);
            Thread.sleep(TimeUnit.SECONDS.toMillis(10 + i1));
        }
    }

    /**
     * @param response
     * @description:解析响应体得到文章内容和标题
     * @return: void
     * @author shenyafeng
     * @date: 2021/2/6 16:11
     */
    private static void writeHtmlFromResponse(String response,String sectionName) throws IOException {
        if (StringUtils.isEmpty(response)) {
            System.out.println("响应体为空");
            return;
        }
        System.out.println(response);
        JSONObject thisJson = JSONObject.parseObject(response);
        JSONObject textContent = thisJson.getJSONObject("content");
        if (textContent == null) {
            return;
        }
        //获取文章标题
        String theme = textContent.getString("theme");
        String textContent1 = textContent.getString("textContent");
        theme = theme.replace("?", "").replace("|", "")
                .replace("/","").replace("：","");
        System.out.println(theme);
        createDirIfNotExist(SAVE_DIR + File.separator+sectionName);
        Writer writer = new FileWriter(SAVE_DIR + File.separator+sectionName + File.separator+theme + ".html");
        PrintWriter printWriter = new PrintWriter(writer);
        String htmlResult = HTML_HEADER + textContent1 + HTML_TAIL;
        Document document = resetHtml(htmlResult);
        printWriter.println(document.toString());
        printWriter.flush();
        printWriter.close();
    }

    /**
     * @description:
     * @param courseId 某一专栏的id
     * @return: java.util.List<javafx.util.Pair<java.lang.String,java.lang.String>>
     * @author shenyafeng
     * @date: 2021/2/7 9:06
     */
    private static List<LessonInfo> getLessonInfoByCourseId(int courseId){
        List<LessonInfo> result=new ArrayList<>();
        String courseUrl = COURSE_URL.replace("COURSE_ID", courseId + "");
        System.out.println(courseUrl);
        HttpRequest request = HttpUtil.createRequest(Method.GET, courseUrl);
        //Pragma: no-cache
        //Cache-Control: no-cache
        request.header("Host", "gate.lagou.com")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:78.0) Gecko/20100101 Firefox/78.0")
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Authorization", "4fe54a13d4158720bfe8cabd4581943e8a46b50a84c328f6fc46541f30ed0834")
                .header("X-L-REQ-HEADER", "{deviceType:1}")
                .header("Origin", "https://kaiwu.lagou.com")
                .header("Connection", "keep-alive")
                .header("Referer", "https://kaiwu.lagou.com/course/courseInfo.htm?courseId=393")
                .header("Cookie", LAGOU_COOKIE_STR)
                .header("TE", "Trailers")
                .header("Pragma", "no-cache")
                .header("Cache-Control", "no-cache");
        String body = request.execute().body();
        JSONObject thisJson = JSONObject.parseObject(body);
        System.out.println(thisJson);
        JSONObject content = thisJson.getJSONObject("content");
        SAVE_DIR="C:\\"+content.getString("courseName");
        createDirIfNotExist(SAVE_DIR);
        //不同章节，如基础篇，进阶篇等
        JSONArray sectionArray=content.getJSONArray("courseSectionList");
        System.out.println(sectionArray);
        for(int i=0;i<sectionArray.size();i++){
            JSONObject tempJson = sectionArray.getJSONObject(i);
            String sectionName = tempJson.getString("sectionName");
            JSONArray courseLessons = tempJson.getJSONArray("courseLessons");
            for(int j=0;j<courseLessons.size();j++){
                JSONObject jsonObject = courseLessons.getJSONObject(j);
                System.out.println(jsonObject);
                Integer id = jsonObject.getInteger("id");
                result.add(new LessonInfo(id,sectionName));
            }
        }
        return result;
    }

    /**
     * @param htmlResult
     * @description:重新设置html中图片的大小
     * @return: org.jsoup.nodes.Document
     * @author shenyafeng
     * @date: 2021/2/6 16:11
     */
    private static Document resetHtml(String htmlResult) {
        Document document = Jsoup.parse(htmlResult);
        Elements elements = document.getElementsByTag("img");
        for (int i = 0; i < elements.size(); ++i) {
            Element element = elements.get(i);
            element.attr("style", "display: inline-block; width: 100%; max-width: 100%; height: auto;");
        }
        return document;
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        GrabFromLaGou();
    }

}
