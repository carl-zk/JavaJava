package common;

/**
 * Created by carl on 5/20/17.
 */
public abstract class StringUtil {
    public static String assertNotBlank(String value) {
        if (value == null || value.trim().equals(""))
            throw new IllegalArgumentException();
        return value;
    }
}
