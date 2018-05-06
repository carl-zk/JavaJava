package moc.oreh.common;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpDirEntry;
import sun.net.ftp.FtpProtocolException;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by hero on 26/11/2017
 */
public abstract class FtpUtil {
    // Default port 21
    public static FtpClient newFtpClient(String ip, String loginName, String password) throws IOException, FtpProtocolException {
        //Default port 21
        FtpClient client = FtpClient.create(ip);
        client.login(loginName, password.toCharArray());
        client.setBinaryType();
        return client;
    }

    public static void close(FtpClient client) {
        try {
            if (client != null) {
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, FtpProtocolException {
        FtpClient ftpClient = FtpClient.create("192.168.1.12");
        System.out.println("conn: " + ftpClient.isConnected());
        System.out.println("longin: " + ftpClient.isLoggedIn());
        ftpClient.login("hero", "Qaz123@#".toCharArray());
        System.out.println("conn: " + ftpClient.isConnected());
        System.out.println("longin: " + ftpClient.isLoggedIn());

        ftpClient.setBinaryType();
        String pwd = ftpClient.getWorkingDirectory();
        System.out.println(pwd);
        Iterator<FtpDirEntry> files = ftpClient.listFiles(pwd);
        while (files.hasNext()) {
            FtpDirEntry e = files.next();
            System.out.println(e.getType() + " : " + e.getName());
        }
        ftpClient.close();
    }
}
