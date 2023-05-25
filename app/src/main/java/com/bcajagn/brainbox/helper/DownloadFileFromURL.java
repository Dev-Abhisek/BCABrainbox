package com.bcajagn.brainbox.helper;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.bcajagn.brainbox.pdfviewer.PdfviewerActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFileFromURL extends AsyncTask<String, String, String> {
    Context context;
    String filename;
    ProgressDialog pd;

    public DownloadFileFromURL(Context context, String filename, ProgressDialog pd) {
        this.context = context;
        this.filename = filename;
        this.pd = pd;
    }

    /**
     * Before starting background thread Show Progress Bar Dialog
     **/
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//            showDialog(progress_bar_type);

    }

    /**
     * Downloading file in background thread
     **/
    @Override
    protected String doInBackground(String... f_url) {
        int count;
        try {
            URL url = new URL(f_url[0]);
            URLConnection conection = url.openConnection();
            conection.connect();

            // this will be useful so that you can show a tipical 0-100%
            // progress bar
            int lenghtOfFile = conection.getContentLength();

            // download the file
            InputStream input = new BufferedInputStream(url.openStream(),
                    8192);

            // Output stream
            OutputStream output = new FileOutputStream(context.getCacheDir() + File.separator
                    + filename);

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }

    /**
     * Updating progress bar
     **/
    protected void onProgressUpdate(String... progress) {
           //setting progress percentage
          // pDialog.setProgress(Integer.parseInt(progress[0]));
    }

    /**
     * After completing background task Dismiss the progress dialog
     **/
    @Override
    protected void onPostExecute(String file_url) {
        String path = context.getCacheDir()
                + "/" + filename;
        File file = new File(path);
        if (file.exists()) {
            Intent intent = new Intent(context, PdfviewerActivity.class);
            intent.putExtra("filename", filename);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
//            Uri uri = FileProvider.getUriForFile(context,
//                    "com.bcajagn.brainbox.android.fileprovider",
//                    file);
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(uri);
//            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
//            try {
//                context.startActivity(intent);
//            } catch (ActivityNotFoundException e) {
//                Log.e("error", "error" + e);
//            }
        } else {
            Toast.makeText(context, "'" + filename + "' does not exist in firebase", Toast.LENGTH_SHORT).show();
        }
        // dismiss the dialog after the file was downloaded
//            dismissDialog(progress_bar_type);
        pd.dismiss();
    }

}


