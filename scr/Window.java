import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by LabunskyA.
 * Protected with GNU GPLv2 and your honesty
 */
class Window extends JFrame {
    private final JTextField url = new JTextField();

    Window(){
        super("YouTube Multi-Threaded Downloader");
        setResizable(false);
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JTextField urlPointer = new JTextField("Url: ");
        JButton getButton = new JButton();
        getButton.setText("get It");

        urlPointer.setBorder(BorderFactory.createEmptyBorder());
        urlPointer.setDisabledTextColor(Color.BLACK);
        urlPointer.setEnabled(false);

        url.setDisabledTextColor(Color.BLACK);
        url.setPreferredSize(new Dimension(250, 20));

        getButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                URLGenerator urlGen = new URLGenerator(url.getText());
                VideoDownloader videoDownloader = new VideoDownloader(url.getText());

                Thread urlGenThread = new Thread(urlGen);
                Thread videoDownloadThread = new Thread(videoDownloader);

                urlGenThread.start();
                videoDownloadThread.start();
            }
        });

        add(urlPointer);
        add(url);
        add(getButton);
    }
}
