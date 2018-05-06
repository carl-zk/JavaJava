package moc.oreh.frame.component;

import javax.swing.*;

/**
 * Created by hero on 22/11/2017
 */
public class ExtractUpdateListTextPanel extends JPanel {
    public ScrollText source;

    public ExtractUpdateListTextPanel() {
        source = new ScrollText(10, 40, 600, 650);
        add(source);
        setLayout(null);
    }

    public class ScrollText extends JScrollPane {
        public JTextArea textArea;

        public ScrollText(int x, int y, int width, int hight) {
            textArea = new JTextArea();
            setViewportView(textArea);
            setBounds(x, y, width, hight);
            setVisible(true);
        }
    }
}
