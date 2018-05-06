package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class T {
    public static final Logger logger = LoggerFactory.getLogger(T.class);

    public static byte[] hexStrToBytes(String hex) {
        int sz = hex.length() >> 1;
        byte[] buff = new byte[sz];
        for (int i = 0, j = 0; i < sz; i++) {
            buff[i] = (byte) (Character.digit(hex.charAt(j++), 16) << 4 |
                    Character.digit(hex.charAt(j++), 16));
        }
        return buff;
    }


    public static void streamToList(List list, InputStream input) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(input))) {
            while (in.ready()) {
                list.add(in.readLine().toLowerCase().trim());
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static String byteToString(byte b) {
        StringBuilder sb = new StringBuilder();
        byte[] buff = new byte[8];
        for (int i = 7; i > -1; i--) {
            buff[i] = (byte) (b & 01);
            b >>= 1;
        }
        for (byte e : buff) {
            sb.append(e & 0x01);
        }
        return sb.toString();
    }

    public static String utf8ToUnicode(byte[] buff) {
        StringBuilder sb = new StringBuilder();
        sb.append(byteToString(buff[0]).substring(buff.length, 8));
        for (int i = 1; i < buff.length; i++) {
            sb.append(byteToString(buff[i]).substring(2, 8));
        }
        int t = Integer.parseInt(sb.toString(), 2);
        String s = Integer.toHexString(t);
        sb.delete(0, sb.length());
        for (int i = 0; i < 4 - s.length(); i++)
            sb.append("0");
        sb.append(s);
        return sb.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        InputStream unicodeStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("unicode");
        InputStream gbkStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("gbk");
        LinkedList<String> uni = new LinkedList<>();
        LinkedList<String> gbk = new LinkedList<>();
        streamToList(uni, unicodeStream);
        streamToList(gbk, gbkStream);
        String s = "严";
        String unicode = utf8ToUnicode(s.getBytes());
        int i = uni.indexOf(unicode.toLowerCase());
        byte[] buff = hexStrToBytes(gbk.get(i));
        System.out.println(new String(buff, "GBK"));
    }
} 
