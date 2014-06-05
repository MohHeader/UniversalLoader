package net.loader;

import android.os.AsyncTask;

import net.loader.utils.Converter;
import net.loader.utils.Downloader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohheader on 6/4/14.
 * Loader is the Responsible for Downloading Files, Notifying LoaderRequests when Files are Ready,
 * And if the File is Loaded it is responsible to return the File in byte[] Object.
 */

public class Loader {
    static private String mUrl;             // the absolute path for the File ( i.e. the input for the Loader )
    static private List<LoaderRequest> observers;

    static private Boolean complete = false;// set to True when Download is Complete.
    static private Boolean working = false; // set to True when Download is in Progress.
    static byte[] bytes;                    // the byte[] for the file ( i.e. the Result of the Loader )

    DownloadTask task;                      // AsyncTask

    /**
     * The Main & Only Constructor
     * it will Launch the Download Process, if not Started yet.
     * & Initialize the Observers ArrayList.
     *
     * @param  url   an absolute path String giving the base location of the file
     */
    public Loader(String url){
        mUrl = url;
        observers = new ArrayList<LoaderRequest>();
        if (!working){
            working = true;
            startDownloading();
        }
    }

    /**
     * The Download AsyncTask,
     * in a background thread it loads the file from the internet
     * & Convert it to byte[].
     * <p>
     * Then on the UI Thread it will cache the Result & Notify all LoaderRequests.
     */
    private class DownloadTask extends AsyncTask<String, Void, byte[]> {
        @Override
        protected byte[] doInBackground(String... urls) {
            InputStream is =  Downloader.download(urls[0]);
            return Converter.getBytesFromInputStream(is);
        }
        @Override
        protected void onPostExecute(byte[] result) {
            bytes = result;
            notifyObservers(bytes);
            complete = true;
        }
    }

    private void startDownloading() {
        task = new DownloadTask();
        task.execute(mUrl);
    }

    public String getmUrl() {
        return mUrl;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public boolean isComplete() {
        return complete;
    }

    /**
     * Observer Pattern.
     */
    public void registerObserver(LoaderRequest lr){
        if(observers.indexOf(lr) == -1){ // Check if not added before.
            observers.add(lr);
        }
    }
    public void unRegisterObserver(LoaderRequest ul){
        int i = observers.indexOf(ul);
        if(i != -1)
            observers.remove(ul);
        if(observers.size() == 0){
            task.cancel(true);
            complete = false;
            working = false;
        }
    }
    public void notifyObservers(byte[] is){
        for (LoaderRequest ul : observers){
            ul.done(is);
        }
    }
}
