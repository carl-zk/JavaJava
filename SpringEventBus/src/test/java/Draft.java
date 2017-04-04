import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hero on 17-4-2.
 */
public class Draft {

    private void t() {
        System.out.println("hello");
    }

    @Test
    public void tes() throws InvocationTargetException, IllegalAccessException {
        ManualTest manualTest = new ManualTest();
        Class clazz = ManualTest.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("fun")) {
                method.invoke(manualTest);
            }
        }
    }
}
