package net.loader;

import android.os.AsyncTask;
import android.util.Log;

import net.loader.utils.Converter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohheader on 6/4/14.
 */
public class Loader {
    static private String mUrl;
    static private List<LoaderRequest> observers;

    static private Boolean complete = false;
    static private Boolean working = false;
    static byte[] bytes;

    public Loader(String url){
        mUrl = url;
        observers = new ArrayList<LoaderRequest>();
        if (!working){
            working = true;
            startDownloading();
        }
    }

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
    DownloadTask task;
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
    
    public void registerObserver(LoaderRequest ul){
        if(observers.indexOf(ul) == -1){
            observers.add(ul);
        }
    }
    public void unRegisterObserver(LoaderRequest ul){
        int i = observers.indexOf(ul);
        if(i >= 0)
            observers.remove(ul);
        if(observers.size() == 0){
            task.cancel(true);
            task = null;
            // TODO, Remove self from Loaders
        }
    }
    public void notifyObservers(byte[] is){
        for (LoaderRequest ul : observers){
            ul.done(is);
        }
    }
}
