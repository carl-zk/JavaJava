import java.util.regex.Pattern;

/**
 * @author carl
 */
public class Test {

    @org.junit.Test
    public void t() {
        String[] list = {"/consumer-service/", "/consumer-service/api/u", "/consumer-service/rpc/v"};

        Pattern pattern = Pattern.compile("/consumer-service/.*[^rpc]/?.*");

        for (String s : list) {
            System.out.println(pattern.matcher(s).find());
        }
    }
}
