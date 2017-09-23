package com.hero.test;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hero on 17-7-16.
 */
public class ClassLoad {
    @Test
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name){
                InputStream is = null;
                try {
                    String fileName = name + ".class";
                    System.out.println(fileName);
                    is = new FileInputStream("/home/hero/workspace/JavaJava/SPDBXS/src/main/test/com/hero/test/common/StringUtils.class");
                    /*if(is == null){
                        return super.loadClass(name);
                    }*/
                    byte[] bs = new byte[is.available()];
                    is.read(bs);
                    return defineClass(name, bs, 0, bs.length);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }
        };

        Object obj = loader.loadClass("com.hero.test.common.StringUtils").newInstance();

        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.hero.test.common.StringUtils);
    }
}
