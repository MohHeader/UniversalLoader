package net.loader.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mohheader on 6/4/14.
 * Utils Lib. to download files from the internet into InputStream
 */

public class Downloader {

    /**
     * Returns an InputStream object that can then be converted into usable Data Type.
     * The url argument must specify the absolute path for the File on the Internet.
     *
     * @param  url  an absolute path String giving the base location of the file
     * @return      the InputStream from the specified URL
     */
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
