package moc.courage;

/**
 * Created by hero on 17-7-8.
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class MinorGC {
    private static final int MAX = 1024 * 1024;

    @SuppressWarnings("unaccessed, unused, unchecked")
    public static void allocation() {
        byte[] a, b, c, d;
        a = new byte[2 * MAX];
        b = new byte[2 * MAX];
        c = new byte[4 * MAX];
        d = new byte[5 * MAX];
    }

    public static void main(String[] args) {
        MinorGC.allocation();
    }
}
