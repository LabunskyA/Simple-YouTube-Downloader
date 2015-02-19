import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by LabunskyA.
 * Protected with GNU GPLv2 and your honesty
 */
class URLGenerator implements Runnable {
    static String urlToFile;
    private final String url;

    URLGenerator(String url){
        this.url = url;
    }

    @Override
    public synchronized void run() {
        String videoID = url.substring(url.indexOf("v=") + 2);
        if (videoID.contains("&"))
            videoID = videoID.substring(videoID.indexOf("&"));
        String htmlCode = getPage("http://johneckman.com/yt/getvideo.php?videoid=" + videoID + "&type=Download");

        urlToFile = htmlCode.substring(htmlCode.indexOf("video/mp4"));
        urlToFile = urlToFile.substring(urlToFile.indexOf("=\"") + 2);
        urlToFile = "http://johneckman.com/yt/" + urlToFile.substring(0, urlToFile.indexOf("\""));
    }

    static String getPage(String url) { //get page source from the url
        URL pageURL = null;
        try {
            pageURL = new URL(url);
        } catch (MalformedURLException ignored) {}

        StringBuilder stringBuffer = new StringBuilder();

        try {
            assert pageURL != null;
            InputStream inputStream = pageURL.openConnection().getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int numCharsRead;
            char[] charArray = new char[1024];

            while ((numCharsRead = inputStreamReader.read(charArray)) > 0) {
                stringBuffer.append(charArray, 0, numCharsRead);
            }
            inputStream.close();
            inputStreamReader.close();
        } catch (IOException e) { return ""; }

        return stringBuffer.toString();
    }
}
