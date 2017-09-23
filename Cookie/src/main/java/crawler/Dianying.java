package crawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hero on 17-8-13.
 */
public class Dianying {

    private static String crawlWeb(String url, String charset, String regex) throws IOException {
        StringBuilder sb = new StringBuilder();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        Pattern pattern = Pattern.compile(regex);
        CloseableHttpResponse response = null;
        BufferedReader reader = null;
        Matcher matcher;
        try {
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.93 Safari/537.36");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("Referer", "http://www.a.com");
            // httpGet.setHeader("Content-type", "text/xml; charset=UTF-8");
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            reader = new BufferedReader(new InputStreamReader(entity.getContent(), charset), 4096);
            String line;
            while ((line = reader.readLine()) != null) {
                //System.out.println("line---" + line);
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    System.out.println(matcher.group());
                    sb.append("<h3>");
                    sb.append(matcher.group());
                    sb.append("</h3>");
                }
            }
            EntityUtils.consume(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (response != null)
                response.close();
            if (reader != null)
                reader.close();
        }
        return sb.toString();
    }

    private static void sendEmail(String subject, String content) {
        String to = "zxfspace@163.com";
        String from = "zxfspace@163.com";
        Properties properties = System.getProperties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", "smtp.163.com");
        properties.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content, "text/html;charset=UTF-8");
            Transport transport = session.getTransport();
            transport.connect("zxfspace@163.com", "G5j37I66zc00--");
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            String res = crawlWeb("http://www.dy2018.com/i/98194.html", "gb2312", "ftp://m:m@tv.dl1234.com:2199/权力的游戏第七季[0-9]{2}.{0,2}\\.mp4");
            System.out.println(res);
            sendEmail("权利的游戏7更新提示", res);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
