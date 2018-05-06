package moc.oreh.entity;

import java.io.*;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

/**
 * Created by hero on 26/11/2017
 */
public class FtpServerConfig implements Serializable {
    private static final long serialVersionUID = -2362253259776594442L;
    public String ip;
    public String loginName;
    public String password;
    public String title;

    public static final transient String FILE_PATH = System.getProperty("user.dir") + "/resource/conf/servers/";
    public static final transient HashMap<String, FtpServerConfig> cache = new HashMap<>();
    public static transient FtpServerConfig current = null;

    static {
        FtpServerConfig[] all = listAll();
        if (all != null) {
            for (FtpServerConfig a : all) {
                cache.put(a.title, a);
            }
        }
    }

    public FtpServerConfig() {
    }

    public FtpServerConfig(String ip, String loginName, String password, String title) {
        this.ip = ip;
        this.loginName = loginName;
        this.password = password;
        this.title = title;
    }

    public void init(Vector<String> v) {
        if (v.get(0) == null || "".equals(v.get(0)))
            title = v.get(1);
        else
            title = v.get(0);
        ip = v.get(1);
        loginName = v.get(2);
        password = v.get(3);
    }

    public void removeFile() {
        try {
            File f = new File(FILE_PATH + title);
            f.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(FILE_PATH + title));
            out.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                out.close();
            } catch (Exception e) {
            }
        }
    }

    public static FtpServerConfig readFromFile(File file) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(file));
            FtpServerConfig config = (FtpServerConfig) in.readObject();
            return config;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }
    }

    public static FtpServerConfig[] listAll() {
        try {
            File file = new File(FILE_PATH);
            File[] cfgs = file.listFiles();
            if (cfgs == null || cfgs.length == 0) return null;
            FtpServerConfig[] all = new FtpServerConfig[cfgs.length];
            for (int i = 0; i < all.length; i++) {
                all[i] = readFromFile(cfgs[i]);
            }
            return all;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "ip='" + ip + '\'' +
                ", loginName='" + loginName + '\'';
    }

    public String[] toArray() {
        return new String[]{title, ip, loginName, password};
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Set<String> s = cache.keySet();
        for (String i : s) {
            System.out.println(i);
        }
    }
}
