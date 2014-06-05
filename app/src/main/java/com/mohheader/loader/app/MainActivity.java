package com.mohheader.loader.app;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import net.loader.LoaderRequest;
import net.loader.loaders.DrawableRequest;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        LoaderRequest ui = new DrawableRequest(this)
            .get("http://s3.amazonaws.com/totem_production/profiles/2129/hosted/duriana-full-blue.png",
                    new LoaderRequest.OnLoadComplete<Drawable>() {
                        @Override
                        public void loadComplete(Drawable drawable) {
                            ((ImageView) findViewById(R.id.imageView)).setImageDrawable(drawable);
                        }
                    }
            );
    }
}
