package moc.oreh.frame;

import moc.oreh.common.FtpUtil;
import moc.oreh.common.FileUtil;
import moc.oreh.entity.FtpServerConfig;
import sun.net.ftp.FtpClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.File;
import java.util.Collection;
import java.util.Vector;

/**
 * Created by hero on 22/11/2017
 */
public class Menu extends JMenuBar {
    private JMenu file;
    private JMenu config;
    private JMenu tools;
    private JMenu about;
    private Open open;
    private Site site;

    public Menu() {
        file = new JMenu("文件");
        config = new JMenu("配置");
        tools = new JMenu("工具");
        about = new JMenu("关于");

        open = new Open("打开");
        file.add(open);
        site = new Site("站点");
        config.add(site);

        add(file);
        add(config);
        add(tools);
        add(about);
    }

    private class Open extends JMenuItem implements ActionListener {
        public Open(String name) {
            super(name);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int i = fileChooser.showOpenDialog(null);
            if (JFileChooser.APPROVE_OPTION == i) {
                File f = fileChooser.getSelectedFile();
                Main.tabPane.extractUpdateListText.source.textArea.setText(FileUtil.read(f));
            }
        }
    }

    private class Site extends JMenuItem implements ActionListener {
        public Site(String name) {
            super(name);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog dialog = new JDialog();
            dialog.setTitle("站点管理");
            dialog.setBounds(800, 300, 400, 500);
            dialog.setVisible(true);
            SiteTable siteTable = new SiteTable(0, 0, 400, 400);
            dialog.add(siteTable);
        }

        private class SiteTable extends JScrollPane {
            public JTable table;
            public DefaultTableModel model;
            public JPopupMenu popupMenu;
            public JMenuItem connect;
            public JMenuItem delete;

            public SiteTable(int x, int y, int width, int height) {
                String column[] = {"TITLE", "IP", "LOGINNAME", "PASSWORD"};
                model = new DefaultTableModel();
                model.setColumnIdentifiers(column);
                Collection<FtpServerConfig> data = FtpServerConfig.cache.values();
                for (FtpServerConfig d : data) {
                    model.addRow(d.toArray());
                }
                model.addRow(new String[]{"", "", "", ""});
                table = new JTable(model);
                table.addKeyListener(new EnterKeyAction());
                table.addMouseListener(new MetaClick());
                setViewportView(table);
                popupMenu = new JPopupMenu();
                connect = new JMenuItem("connect");
                connect.addMouseListener(new ConnectAction());
                popupMenu.add(connect);
                delete = new JMenuItem("delete");
                delete.addMouseListener(new DeleteAction());
                popupMenu.add(delete);
                setBounds(x, y, width, height);
            }

            class EnterKeyAction extends KeyAdapter {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        int i = table.getSelectedRow();
                        FtpServerConfig config = new FtpServerConfig();
                        config.init((Vector<String>) model.getDataVector().get(i));
                        model.addRow(new String[]{"", "", "", ""});
                        config.writeToFile();
                        FtpServerConfig.cache.put(config.title, config);
                    }
                }
            }

            class MetaClick extends MouseAdapter {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.isMetaDown()) {
                        popupMenu.show(table, e.getX(), e.getY());
                    }
                }
            }

            class ConnectAction extends MouseAdapter {
                @Override
                public void mousePressed(MouseEvent e) {
                    int i = table.getSelectedRow();
                    String title = ((Vector<String>) model.getDataVector().get(i)).get(0);
                    FtpServerConfig.current = FtpServerConfig.cache.get(title);
                    Main.tabPane.ftpPanel.remote.setText("远程 : " + FtpServerConfig.current.toString());
                    FtpClient client = null;
                    try {
                        client = FtpUtil.newFtpClient(
                                FtpServerConfig.current.ip,
                                FtpServerConfig.current.loginName,
                                FtpServerConfig.current.password);
                        client.setConnectTimeout(Integer.MAX_VALUE);
                        Main.tabPane.ftpPanel.remotePath.addItem(client.getWorkingDirectory());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        FtpUtil.close(client);
                    }
                }
            }

            class DeleteAction extends MouseAdapter {
                @Override
                public void mousePressed(MouseEvent e) {
                    int[] r = table.getSelectedRows();
                    for (int i = r.length - 1; i > -1; i--) {
                        String title = ((Vector<String>) model.getDataVector().get(r[i])).get(0);
                        FtpServerConfig config = FtpServerConfig.cache.get(title);
                        config.removeFile();
                        FtpServerConfig.cache.remove(title);
                        model.removeRow(r[i]);
                    }
                }
            }
        }
    }
}
