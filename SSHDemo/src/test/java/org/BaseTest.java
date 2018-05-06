package org;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by hero on 16-5-21.
 */
public abstract class BaseTest {
    @Before
    public void setup() {
        System.out.println("before");
    }

    @Test
    public abstract void test() throws Exception;

    @After
    public void teardown() {
        System.out.println("after");
    }
}
