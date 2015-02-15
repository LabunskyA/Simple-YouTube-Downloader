import javax.swing.*;

/**
 * Created by LabunskyA.
 * Protected with GNU GPLv2 and your honesty
 */
class Solution {
    static final Window mainWindow = new Window();

    public static void main (String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        mainWindow.setVisible(true);
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null);
    }
}

