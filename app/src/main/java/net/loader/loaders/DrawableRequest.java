package net.loader.loaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import net.loader.LoaderRequest;

/**
 * Created by mohheader on 6/4/14.
 */
public class DrawableRequest extends LoaderRequest {

    public DrawableRequest(Context context) {
        super(context);
    }

    @Override
    public void done(byte[] b) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        mOnLoadComplete.loadComplete(new BitmapDrawable(mContext.getResources(),bitmap));
    }
}
