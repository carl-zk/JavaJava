package moc.oreh.frame.component;

import moc.oreh.common.FtpUtil;
import moc.oreh.entity.FtpServerConfig;
import moc.oreh.frame.Main;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpDirEntry;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by hero on 25/11/2017
 */
public class FtpPanel extends JPanel {
    public JLabel local;
    public LocalFileViewPane localPane;
    public JComboBox localPath;
    public JLabel remote;
    public JComboBox remotePath;
    public RemoteFileViewPane remotePane;

    public FtpPanel() {
        // local
        local = new JLabel("本地");
        local.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        local.setBounds(10, 0, 100, 35);
        add(local);
        localPane = new LocalFileViewPane(10, 65, 400, 400);
        add(localPane, BorderLayout.WEST);
        File[] roots = localPane.fileSystemView.getRoots();
        String[] list = new String[roots.length + 1];
        list[0] = localPane.fileSystemView.getHomeDirectory().getPath();
        for (int i = 1; i < list.length; i++) {
            list[i] = roots[i - 1].getPath();
        }
        localPath = new JComboBox(list);
        localPath.setEditable(true);
        localPath.setBounds(10, 40, 400, 20);
        localPath.addActionListener(new ChangeLocalPathAction());
        add(localPath);
        // remote
        remote = new JLabel("远程");
        remote.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        remote.setBounds(657, 0, 400, 35);
        add(remote);
        remotePath = new JComboBox();
        remotePath.setEditable(true);
        remotePath.setBounds(657, 40, 400, 20);
        remotePath.addActionListener(new ChangeRemotePathAction());
        add(remotePath);
        remotePane = new RemoteFileViewPane(657, 65, 400, 400);
        add(remotePane, BorderLayout.WEST);
        setLayout(null);
    }

    private class LocalFileViewPane extends JScrollPane {
        public FileSystemView fileSystemView;
        public JList list;

        public LocalFileViewPane(int x, int y, int width, int height) {
            fileSystemView = FileSystemView.getFileSystemView();
            setViewportView(fileSystemView.getHomeDirectory());
            setBounds(x, y, width, height);
        }

        public void setViewportView(File directory) {
            File[] tmp = fileSystemView.getFiles(directory, false);
            list = new JList(tmp);
            list.setCellRenderer(new FileRenderer(true));
            list.setVisibleRowCount(-1);
            list.addMouseListener(new MouseDoubleClickAction(this));
            super.setViewportView(list);
        }

        private class MouseDoubleClickAction extends MouseAdapter {
            private LocalFileViewPane localFileViewPane;

            public MouseDoubleClickAction(LocalFileViewPane localFileViewPane) {
                this.localFileViewPane = localFileViewPane;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    File file = (File) localFileViewPane.list.getSelectedValue();
                    if (file != null && file.isDirectory()) {
                        localPath.getModel().setSelectedItem(file.getPath());
                    }
                }
            }
        }

        class FileRenderer extends DefaultListCellRenderer {
            private boolean pad;
            private Border padBorder = new EmptyBorder(3, 3, 3, 3);

            FileRenderer(boolean pad) {
                this.pad = pad;
            }

            @Override
            public Component getListCellRendererComponent(
                    JList list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {

                Component c = super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                JLabel l = (JLabel) c;
                File f = (File) value;
                l.setText(f.getName());
                l.setIcon(FileSystemView.getFileSystemView().getSystemIcon(f));
                if (pad) {
                    l.setBorder(padBorder);
                }

                return l;
            }
        }
    }

    public class RemoteFileViewPane extends JScrollPane {
        public FileSystemView fileSystemView;
        public JList list;
        public JPopupMenu popupMenu;
        public JMenuItem transfer;

        public RemoteFileViewPane(int x, int y, int width, int height) {
            fileSystemView = FileSystemView.getFileSystemView();
            popupMenu = new JPopupMenu();
            transfer = new JMenuItem("transfer");
            transfer.addMouseListener(new MouseAction());
            popupMenu.add(transfer);
            add(popupMenu);
            setBounds(x, y, width, height);
        }

        public void setViewportView(String directory) {
            FtpClient ftpClient = null;
            try {
                ftpClient = FtpUtil.newFtpClient(FtpServerConfig.current.ip,
                        FtpServerConfig.current.loginName, FtpServerConfig.current.password);
                Iterator<FtpDirEntry> files = ftpClient.listFiles(directory);
                Vector<FtpDirEntry> data = new Vector<>();
                while (files.hasNext()) {
                    data.add(files.next());
                }
                list = new JList(data);
                list.setCellRenderer(new FileRenderer());
                list.setVisibleRowCount(-1);
                list.addMouseListener(new MouseAction());
                super.setViewportView(list);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                FtpUtil.close(ftpClient);
            }
        }

        class FileRenderer extends DefaultListCellRenderer {
            public final Icon DIR = FileSystemView.getFileSystemView().getSystemIcon(new File(FtpServerConfig.FILE_PATH));
            public final Icon FILE = FileSystemView.getFileSystemView().getSystemIcon(new File("icon"));
            private Border padBorder = new EmptyBorder(3, 3, 3, 3);
            private DateFormat df = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");

            @Override
            public Component getListCellRendererComponent(
                    JList list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {

                Component c = super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                JLabel l = (JLabel) c;
                FtpDirEntry f = (FtpDirEntry) value;
                l.setText(f.getName() + "\t\t" + f.getSize() + "\t\t" + df.format(f.getLastModified()));
                l.setBorder(padBorder);
                l.setIcon(f.getType() == FtpDirEntry.Type.DIR ? DIR : FILE);
                return l;
            }
        }

        class MouseAction extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == list) {
                    if (e.getClickCount() == 2 && !e.isConsumed()) {
                        FtpDirEntry entry = (FtpDirEntry) list.getSelectedValue();
                        if (entry.getType() == FtpDirEntry.Type.DIR) {
                            String path = Main.tabPane.ftpPanel.remotePath.getSelectedItem() + "/" + entry.getName();
                            Main.tabPane.ftpPanel.remotePath.setSelectedItem(path);
                        }
                    } else if (e.isMetaDown()) {
                        popupMenu.show(remotePane, e.getX(), e.getY());
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getSource() == transfer) {
                    FtpDirEntry entry = (FtpDirEntry) list.getSelectedValue();
                    if (entry.getType() == FtpDirEntry.Type.FILE) {
                        FtpClient client = null;
                        BufferedOutputStream out = null;
                        try {
                            client = FtpUtil.newFtpClient(FtpServerConfig.current.ip,
                                    FtpServerConfig.current.loginName,
                                    FtpServerConfig.current.password);
                            String from = remotePath.getSelectedItem() + "/" + entry.getName();
                            String to = localPath.getSelectedItem() + File.separator + entry.getName();
                            out = new BufferedOutputStream(new FileOutputStream(to));
                            client.getFile(from, out);
                            out.flush();
                            localPath.setSelectedItem(localPath.getSelectedItem());
                        } catch (Exception ex) {
                            FtpUtil.close(client);
                            try {
                                out.close();
                            } catch (IOException e1) {
                            }
                        }
                    }
                }
            }
        }
    }

    private class ChangeLocalPathAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JComboBox) {
                JComboBox box = (JComboBox) e.getSource();
                String path = (String) box.getSelectedItem();
                localPane.setViewportView(new File(path));
            }
        }
    }

    private class ChangeRemotePathAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox) e.getSource();
            String dir = (String) box.getSelectedItem();
            remotePane.setViewportView(dir);
        }
    }
}