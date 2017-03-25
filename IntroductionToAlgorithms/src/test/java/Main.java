import java.util.Scanner;

/**
 * Created by hero on 17-3-22.
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            System.out.println(input.nextInt() + input.nextInt());
        }
    }
}
