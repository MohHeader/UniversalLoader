package net.loader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohheader on 6/4/14.
 * LoaderManager Manages Loaders, & Determine if they need to be initialized or
 * successfully completed so just retrieve the byte[] from the Cache.
 */
public class LoaderManager {
    static List<Loader> loaders;    // Hold Reference to all Loaders

    /**
     *
     * @param ul    LoaderRequest, so if Loader will not be initialized,
     *              cached byte[] will be just used.
     * @param url   an absolute path String giving the base location of the file
     * @return Loader that match the given url
     */
    public static Loader getLoader(LoaderRequest ul, String url) {
        if (loaders == null){
            loaders = new ArrayList<Loader>();
        }
        Loader loader = get(url);
        if( loader == null)
            loaders.add(new Loader(url));
        else if (loader.isComplete()){
            ul.done(loader.getBytes());
        }
        return get(url);
    }

    /**
     *
     * @param url    an absolute path String giving the base location of the file
     * @return Loader that match the given url
     */
    private static Loader get(String url){
        for(Loader loader : loaders){
            if(loader.getmUrl().equals(url))
                return loader;
        }
        return null;
    }
}
