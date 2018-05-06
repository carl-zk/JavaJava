/**
 * Created by hero on 18/11/2017
 */
public class GCTest {
    static int _1MB = 1024 * 1024;

    public static void fun(){
        fun();
    }
    public static void main(String[] args) {
        byte[] a = new byte[2 * _1MB];
        byte[] b = new byte[2 * _1MB];
        byte[] c = new byte[2 * _1MB];
        byte[] d = new byte[4 * _1MB];
        fun();
    }
}
