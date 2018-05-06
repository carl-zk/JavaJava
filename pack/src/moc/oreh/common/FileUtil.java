package moc.oreh.common;

import java.io.*;

/**
 * Created by hero on 25/11/2017
 */
public abstract class FileUtil {
    public static final String NEW_LINE = File.separatorChar == '/' ? "\n" : "\r\n";

    public static String read(File file) {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
                sb.append(NEW_LINE);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != in)
                    in.close();
            } catch (IOException e) {
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }
}
