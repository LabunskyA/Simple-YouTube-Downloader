import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by LabunskyA.
 * Protected with GNU GPLv2 and your honesty
 */
public class URLGenerator implements Runnable {
    static Boolean ready = false;
    private String url;

    URLGenerator(String url){
        this.url = url;
    }

    @Override
    public void run() {
        String videoID = url.substring(url.indexOf("v=") + 2);
        if (videoID.contains("&"))
            videoID = videoID.substring(videoID.indexOf("&"));
        String htmlCode = getPage("http://johneckman.com/yt/getvideo.php?videoid=" + videoID + "&type=Download");

        Solution.urlToFile = htmlCode.substring(htmlCode.indexOf("video/mp4"));
        Solution.urlToFile = Solution.urlToFile.substring(Solution.urlToFile.indexOf("=\"") + 2);
        Solution.urlToFile = "http://johneckman.com/yt/" + Solution.urlToFile.substring(0, Solution.urlToFile.indexOf("\""));
    }

    public static String getPage(String url) { //get page source from the url
        URL pageURL = null;
        try {
            pageURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuffer = new StringBuilder();

        try {
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
