package Utils;/**
 * @Auther: shenyafeng
 * @Date: 2021/1/17 19:39
 * @Description:
 */

import java.io.*;
import java.util.Properties;

/**
 *@ClassName $ {name}
 *@Description TODO
 *@Author $ {USER}
 *@Date $ {DATE} 19:39
 *@Version 1.0
 **/
public class SettingUtil {
    private static Properties helper=new Properties();
    private static Reader in;
    private static final String SETTING_FILE_NAME="setting.properties";
    private static final String SETTING_FILE_PATH= SettingUtil.class.getClassLoader().getResource(SETTING_FILE_NAME).getPath();
    static{
        try {
            System.out.println(SETTING_FILE_PATH.substring(1,SETTING_FILE_PATH.length()));
            in=new FileReader(SETTING_FILE_PATH.substring(1,SETTING_FILE_PATH.length()));
            helper.load(in);
        } catch (Exception e) {
            System.out.println("加载配置文件失败");
        }
    }

    public static String get(String key,String defaultValue){
        return helper.getProperty(key,defaultValue);
    }

    public static void main(String[] args) {
        System.out.println(helper.getProperty("REDIS_HOST"));

    }
}
