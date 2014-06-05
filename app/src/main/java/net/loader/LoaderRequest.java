package net.loader;

import android.content.Context;

/**
 * Created by mohheader on 6/4/14.
 */

public abstract class LoaderRequest {
    Loader loader;
    protected OnLoadComplete mOnLoadComplete;

    protected Context mContext;
    public LoaderRequest(Context context){
        mContext = context;
    }

    public LoaderRequest get(String url, OnLoadComplete onLoadComplete){
        mOnLoadComplete = onLoadComplete;
        loader = LoaderManager.getLoader(this, url);
        loader.registerObserver(this);
        return this;
    }

    public LoaderRequest cancel(){
        loader.unRegisterObserver(this);
        return this;
    }

    public interface OnLoadComplete<T>{
        void loadComplete(T object);
    }

    public abstract void done(byte[] b);
}
