package moc.oreh.frame;

import moc.oreh.frame.component.ExtractUpdateListTextPanel;
import moc.oreh.frame.component.FtpPanel;

import javax.swing.*;

/**
 * Created by hero on 22/11/2017
 */
public class TabPane extends JTabbedPane {
    public ExtractUpdateListTextPanel extractUpdateListText;
    public JPanel packageToJarWarFile;
    public FtpPanel ftpPanel;

    public TabPane() {
        extractUpdateListText = new ExtractUpdateListTextPanel();
        packageToJarWarFile = new JPanel();
        ftpPanel = new FtpPanel();
        add("更新列表信息", extractUpdateListText);
        add("打包", packageToJarWarFile);
        add("FTP工具", ftpPanel);
        setBounds(0, 0, 1294, 800);
        setVisible(true);
    }

    private JPanel info;

}
