package net.loader.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mohheader on 6/5/14.
 */
public class Converter {
    public static byte[] getBytesFromInputStream(InputStream is)
    {
        try
        {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[0xFFFF];

            for (int len; (len = is.read(buffer)) != -1;)
                os.write(buffer, 0, len);

            os.flush();

            return os.toByteArray();
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
