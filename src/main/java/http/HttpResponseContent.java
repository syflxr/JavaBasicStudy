package http;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: shenyafeng
 * @Date: 2021/2/11 21:20
 * @Description:
 */

public class HttpResponseContent {
    private static final Map<String,String> responseMap=new HashMap<>();
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
    private static final String LOGIN_HTML="<!DOCTYPE html>\n"+"<html>\n" +
            "<head>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "    <title>请先登录</title>\n" +
            "    <script type=\"text/javascript\">\n" +
            "        var reg=/^[a-zA-Z]\\w*$/;\n" +
            "        var flag1=false,flag2=false,flag3=false;\n" +
            "        function chang1(obj){\n" +
            "            var name=obj.value;\n" +
            "            if(name.length<3||name.length>8){\n" +
            "                //alert(\"密码要求3-8位！\");\n" +
            "                document.getElementById(\"usName\").innerHTML=\"<font color='red'> 用户名要求3-8位！</font>\";\n" +
            "            }\n" +
            "            else if(!reg.test(name)){\n" +
            "                //alert(\"用户名由字母开头，后字母、数字或下划线!\");\n" +
            "                document.getElementById(\"usName\").innerHTML=\"<font color='red'> 用户名由字母开头，后字母、数字或下划线</font>\";\n" +
            "                flag1=false;\n" +
            "            }\n" +
            "            else{\n" +
            "                flag1=true;\n" +
            "                document.getElementById(\"usName\").innerHTML=\"<font color='green'>可以使用</font>\";\n" +
            "            }\n" +
            "        }\n" +
            "        function chang2(obj){\n" +
            "            var pwd=obj.value;\n" +
            "            if(pwd.length<3||pwd.length>8){\n" +
            "                //alert(\"密码要求3-8位！\");\n" +
            "                document.getElementById(\"ps1\").innerHTML=\"<font color='red'>密码要求3-8位！</font>\";\n" +
            "                flag2=false;\n" +
            "            }\n" +
            "            else{\n" +
            "                document.getElementById(\"ps1\").innerHTML=\"<font color='green'>可以使用</font>\";\n" +
            "                flag2=true;\n" +
            "            }\n" +
            "        }\n" +
            "        function chang3(obj){\n" +
            "            var pwd1=obj.value;\n" +
            "            var pwd=document.regist.password.value;\n" +
            "            if(pwd1.length<6||pwd1.length>8){\n" +
            "                //alert(\"密码要求3-8位！\");\n" +
            "                document.getElementById(\"ps2\").innerHTML=\"<font color='red'>密码要求3-8位！</font>\";\n" +
            "            }\n" +
            "            else if(pwd!=pwd1){\n" +
            "                //alert(\"两次输入密码不一致！\");\n" +
            "                document.getElementById(\"ps2\").innerHTML=\"<font color='red'>两次输入密码不一致！</font>\";\n" +
            "                flag3=false;\n" +
            "            }\n" +
            "            else{\n" +
            "                document.getElementById(\"ps2\").innerHTML=\"<font color='green'>可以使用</font>\";\n" +
            "                flag3=true;\n" +
            "            }\n" +
            "        }\n" +
            "        function sub(){\n" +
            "            if(flag1&&flag2){\n" +
            "                document.regist.submit();\n" +
            "            }\n" +
            "            else{\n" +
            "                alert(\"请填写注册信息\");\n" +
            "            }\n" +
            "        }\n" +
            "        function res(){\n" +
            "            document.regist.username.value=\"\";\n" +
            "            document.regist.password.value=\"\";\n" +
            "            document.regist.passwordAgain.value=\"\";\n" +
            "            document.getElementById(\"usName\").innerHTML=\"\";\n" +
            "            document.getElementById(\"ps1\").innerHTML=\"\";\n" +
            "            document.getElementById(\"ps2\").innerHTML=\"\";\n" +
            "        }\n" +
            "    </script>\n" +
            "</head>\n" +
            "<body>\n" +
            "<form method=\"POST\" name=\"regist\" action=\"/\">\n" +
            "    <table style=\"height: 100%; width: 100%\">\n" +
            "        <tr align=\"center\" valign=\"middle\">\n" +
            "            <td>\n" +
            "                <TABLE width=\"392\" height=\"200\" border=0 align=\"center\" cellPadding=0\n" +
            "                       cellSpacing=0\n" +
            "                        height: 200; width: 392\">\n" +
            "                    <TBODY>\n" +
            "\n" +
            "                    <TR>\n" +
            "                        <td width=\"80\" height=\"20\" class=\"login_td\" >   用户名：</td>\n" +
            "                        <td width=\"120\" height=\"20\" class=\"login_td\"><input\n" +
            "                                type=\"text\" name=\"username\" value=\"\" style=\"WIDTH: 110px\"\n" +
            "                                onChange=\"chang1(this)\"></td>\n" +
            "                        <td id=\"usName\"></td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td height=\"20\" class=\"login_td\">   密 码：</td>\n" +
            "                        <td height=\"20\" class=\"login_td\"><input type=\"password\"\n" +
            "                                                                name=\"password\" value=\"\" style=\"WIDTH: 110px\"\n" +
            "                                                                onChange=\"chang2(this)\"></td>\n" +
            "                        <td id=\"ps1\"></td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td height=\"20\" colspan=\"2\" align=\"center\">\n" +
            "                            <button class=\"login_button\" onClick=sub();>登录(未注册时登录即注册)</button>\n" +
            "                             </td>\n" +
            "                    </TR>\n" +
            "                    </TBODY>\n" +
            "                </TABLE>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "    </table>\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";
    static{
        responseMap.put("/login",LOGIN_HTML);
        responseMap.put("/",HTML_HEADER+"<h2 size=\"24\">这是标题</h2>\n"+"<p>这是文字内容</p>"+HTML_TAIL);
    }
    public static String getResponseByUrl(String url){
        if(StringUtils.isEmpty(url)){
            return "";
        }
        return responseMap.get(url);
    }
}
