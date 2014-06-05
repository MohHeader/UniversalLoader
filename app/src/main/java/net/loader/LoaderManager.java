package net.loader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohheader on 6/4/14.
 */
public class LoaderManager {
    static List<Loader> loaders;

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

    private static Loader get(String url){
        for(Loader loader : loaders){
            if(loader.getmUrl().equals(url))
                return loader;
        }
        return null;
    }
}
