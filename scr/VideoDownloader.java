import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by LabunskyA.
 * Protected with GNU GPLv2 and your honesty
 */
public class VideoDownloader implements Runnable{
    private String url;
    JFrame downloading = new JFrame("Downloading");

    VideoDownloader(String url){
        this.url = url;

        JTextField pleaseWait = new JTextField("Please, wait");
        Font font = new Font("Open Sans", Font.BOLD, 14);

        pleaseWait.setEnabled(false);
        pleaseWait.setFont(font);
        pleaseWait.setHorizontalAlignment(JTextField.CENTER);
        pleaseWait.setDisabledTextColor(Color.BLACK);

        downloading.getContentPane().setBackground(Color.WHITE);
        downloading.setPreferredSize(Solution.mainWindow.getSize());
        downloading.add(pleaseWait);
        downloading.pack();
        downloading.setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        try {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("MP4 Video Format", "mp4", "MP4");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File(url.substring(url.indexOf("=") + 1) + ".mp4"));
            fileChooser.setFileFilter(filter);

            if (fileChooser.showSaveDialog(Solution.mainWindow) == JFileChooser.APPROVE_OPTION) {
                URL downloadURL = new URL(Solution.urlToFile);
                ReadableByteChannel byteChannel = Channels.newChannel(downloadURL.openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(fileChooser.getSelectedFile());

                showDownloadingDialog();

                fileOutputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
                fileOutputStream.close();

                closeDownloadingDialog();
            }
        } catch (MalformedURLException ignored) {} catch (FileNotFoundException ignored) {} catch (IOException ignored) {}
    }

    private void showDownloadingDialog(){
        downloading.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        Solution.mainWindow.setVisible(false);
        downloading.setVisible(true);
    }

    private void closeDownloadingDialog(){
        downloading.setCursor(Cursor.getDefaultCursor());

        downloading.setVisible(false);
        Solution.mainWindow.setVisible(true);
    }
}
