package org.aspectj.test;

/**
 * Created by hero on 16-5-21.
 */
public class World {
    public void bar() throws Exception {
        System.out.println("执行组件WORLD中的bar方法");

        throw new Exception("world exception");
    }
}
