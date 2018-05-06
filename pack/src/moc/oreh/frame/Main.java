package moc.oreh.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by hero on 22/11/2017
 */
public class Main extends JFrame {
    public static Menu menu;
    public static TabPane tabPane;

    public Main() {
        menu = new Menu();
        setJMenuBar(menu);
        tabPane = new TabPane();
        add(tabPane);
        setTitle("打包工具 final 版");
        setLayout(null);
        setLocation(300, 150);
        setSize(1294, 800);
        setVisible(true);
        addWindowListener(new MyWindowListener());
        setMaximumSize(new Dimension(1294, 800));
        setMinimumSize(new Dimension(1294, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class MyWindowListener extends WindowAdapter {
        @Override
        public void windowOpened(WindowEvent e) {
            System.out.println("opened");
        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("closing");
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}
