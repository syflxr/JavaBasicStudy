package Other;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * @Auther: shenyafeng
 * @Date: 2021/2/3 10:38
 * @Description:日志打印
 */


public class LoggerTest {
    private final Logger logger=LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void logTest(){
       logger.info("hello");

    }

}
