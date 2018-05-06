package org.aspectj.test;

import org.springframework.util.StringUtils;

/**
 * Created by hero on 16-5-21.
 */
public class Hello {
    public void foo() {
        System.out.println("执行HELLO组件中的foo方法");
    }

    public int addUser(String name, int pass) {
        if (null == name) {
            return 0;
        }
        System.out.println("执行HELLO组件中的addUser： 添加用户---" + name);
        return name.length();
    }
}
