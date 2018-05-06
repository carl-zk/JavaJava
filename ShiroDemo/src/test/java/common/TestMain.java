package common;

import org.junit.Test;
import sun.misc.BASE64Encoder;
import sun.misc.CharacterEncoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TestMain {

    @Test
    public void test0() {
        String s = "a严";
        System.out.println(s.length());
        System.out.println(s.getBytes().length);
        s="a";
        System.out.println(s.getBytes().length);
    }

    @Test
    public void test() throws UnsupportedEncodingException {
        String s = "严";
        byte[] bytes = s.getBytes(), arr = new byte[8];
        byte j;
        for (byte b : bytes) {
            for (int i = 0; i < 8; i++) {
                arr[7 - i] = (byte) (b & 0x01);
                b >>= 1;
            }
            for (byte a : arr) {
                System.out.print(a);
            }
            System.out.println();
        }
    }

    @Test
    public void test2() throws UnsupportedEncodingException {
        String str = "以德服人";
        String e = new String(str.getBytes("utf-8"), "gbk");
        System.out.println(e);
        String d = new String(e.getBytes("gbk"), "gbk");
        System.out.println(d);
    }


    @Test
    public void test3() throws UnsupportedEncodingException {
        byte a = (byte) 0xE4;
        byte b = (byte) 0xB8;
        byte c = (byte) 0xA5;
        byte[] t = {(byte) 0xE4, b, c};
        System.out.println(new String(t, "UTF-8"));
    }

    @Test
    public void test4() throws UnsupportedEncodingException {
        BASE64Encoder en = new BASE64Encoder();
        String s = en.encode("字符编码".getBytes());
        System.out.println(s);
        System.out.println(URLEncoder.encode("字符编码", "UTF-8"));
    }

} 
