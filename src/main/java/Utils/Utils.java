package Utils;

import Annotation.Column;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Long getCurrentTimeStamp(){
        return System.currentTimeMillis();
    }
    public static String getCurrentTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long currentTimeStamp = getCurrentTimeStamp();
        Date date=new Date(currentTimeStamp);
        return simpleDateFormat.format(date);
    }

    public static void readAnnotation(Class<?> clazz){
        Field[] fields = clazz.getFields();
        for(int i=0;i<fields.length;++i){
           String name = fields[i].getAnnotation(Column.class).name();




       }


    }

}
