package Annotation;

import java.lang.annotation.*;

/**
 * @Auther: shenyafeng
 * @Date: 2020/11/5 14:38
 * @Description:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String name() default "id";
}
