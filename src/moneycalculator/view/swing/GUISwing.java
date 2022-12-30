package moneycalculator.view.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUISwing extends JFrame {
    
    public GUISwing(JPanel jpanel, String string) {
        super(string);
        getContentPane().add(jpanel);
        addWindowListener(new WindowCloseManager());
        pack();
        setVisible(true);
    }

    private static class WindowCloseManager extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent windowEvent) {
            System.exit(0);
        }
    }
}