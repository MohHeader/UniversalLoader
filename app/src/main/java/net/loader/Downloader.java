package net.loader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mohheader on 6/4/14.
 */
public class Downloader {
    public static InputStream download(String url) {
        InputStream is = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();
            is = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }
}
