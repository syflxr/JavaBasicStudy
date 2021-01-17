package io;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Auther: shenyafeng
 * @Date: 2020/12/25 22:48
 * @Description:
 */


public class IOTest {
    public static final String BYTEREADPATH="C:\\Download\\ch.exe";

    @Test
    public void testIntputStream() throws Exception {
        InputStream inputStream=new FileInputStream(BYTEREADPATH);
        int n=0;
        Long startTime=System.currentTimeMillis();
        while((n=inputStream.read())!=-1){
            //System.out.println(n);//有时候100多有时候几十字节，每次都要用户态切换内核态
        }
        Long endTime=System.currentTimeMillis();
        System.out.println(endTime-startTime);//68秒，52mb
    }

    @Test
    public void testIntputStreamArray() throws Exception {
        InputStream inputStream=new FileInputStream(BYTEREADPATH);
        byte[] arr=new byte[50000];
        Long startTime=System.currentTimeMillis();
        int bytesRead=0;
        while((bytesRead=inputStream.read(arr))!=-1){

        }
        Long endTime=System.currentTimeMillis();
        System.out.println(endTime-startTime);//0.018秒，52mb
    }

    @Test
    public void testBufferedInputStream() throws Exception {
        InputStream inputStream=new FileInputStream(BYTEREADPATH);
        BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream,50000);//1000
        byte[] arr=new byte[1000];
        Long startTime=System.currentTimeMillis();
        while(bufferedInputStream.read(arr)!=-1){

        }
        Long endTime=System.currentTimeMillis();
        System.out.println(endTime-startTime);//最快0.019秒
    }

}
