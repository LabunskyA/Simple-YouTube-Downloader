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
class VideoDownloader implements Runnable{
    private final String url;
    private final String videoTitle;

    VideoDownloader(String url){
        this.url = url;
        String videoPageHTMLCode = URLGenerator.getPage(url);
        videoTitle = videoPageHTMLCode.substring(videoPageHTMLCode.indexOf("title>") + 6, videoPageHTMLCode.indexOf("</ti"));
    }

    @Override
    public void run() {
        try {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("MP4 Video Format", "mp4", "MP4");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File(videoTitle + ".mp4"));
            fileChooser.setFileFilter(filter);

            if (fileChooser.showSaveDialog(Solution.mainWindow) == JFileChooser.APPROVE_OPTION) {
                URL downloadURL = new URL(URLGenerator.urlToFile);
                ReadableByteChannel byteChannel = Channels.newChannel(downloadURL.openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(fileChooser.getSelectedFile());

                JFrame downloading = new JFrame("Downloading");

                showDownloadingDialog(downloading);

                fileOutputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
                fileOutputStream.close();

                closeDownloadingDialog(downloading);
            }
        } catch (MalformedURLException ignored) {} catch (FileNotFoundException ignored) {} catch (IOException ignored) {}
    }

    private void showDownloadingDialog(JFrame downloading){
        JTextField pleaseWait = new JTextField("Please, wait");
        Font font = new Font("Open Sans", Font.BOLD, 14);

        pleaseWait.setEnabled(false);
        pleaseWait.setFont(font);
        pleaseWait.setHorizontalAlignment(JTextField.CENTER);
        pleaseWait.setDisabledTextColor(Color.BLACK);

        downloading.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        downloading.setResizable(false);
        downloading.getContentPane().setBackground(Color.WHITE);
        downloading.setPreferredSize(Solution.mainWindow.getSize());
        downloading.add(pleaseWait);
        downloading.pack();
        downloading.setLocation(Solution.mainWindow.getLocation());

        Solution.mainWindow.setVisible(false);
        downloading.setVisible(true);
    }

    private void closeDownloadingDialog(JFrame downloading){
        Solution.mainWindow.setLocation(downloading.getLocation());

        downloading.dispose();
        Solution.mainWindow.setVisible(true);
    }
}
