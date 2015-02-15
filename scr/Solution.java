import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Calendar;

/**
 * Created by LabunskyA.
 * Protected with GNU GPLv2 and your honesty
 */
public class Solution {
    static String urlToFile;
    static Window mainWindow = new Window();

    public static void main (String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        mainWindow.setVisible(true);
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null);
    }
}

