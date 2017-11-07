package com.example.lavanya.basics.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.example.lavanya.basics.MainActivity;
import com.example.lavanya.basics.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by lavanya on 08/11/17.
 */

public class DownloadService extends IntentService {

    public static final String NOTIFICATION = "download.android.service.receiver";

    public DownloadService(){
        super("Download Service");
    }
    @Override
    protected void onHandleIntent(Intent intent) {

 File output = new File(Environment.getExternalStorageDirectory(),
                "index.html");
        if (output.exists()) output.delete();

        FileOutputStream fos=null;
        InputStream stream=null;
        String result="-1";
        try{
            URL url=new URL("http://www.vogella.com/index.html");
            stream=url.openConnection().getInputStream();
            InputStreamReader reader=new InputStreamReader(stream);
            fos = new FileOutputStream(output.getPath());
            int k=-1;
            while((k=reader.read())!=-1) fos.write(k);

           result="0";

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        publishResults(output.getAbsolutePath(), result);

    }
    private void publishResults(String path, String result){
        Intent intent=new Intent(NOTIFICATION);
        intent.putExtra("OUTPUT_PATH",path);
        intent.putExtra("RESULT",result);
        sendBroadcast(intent);

    }
}
