package com.bcajagn.brainbox.pdfviewer;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bcajagn.brainbox.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.link.DefaultLinkHandler;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PdfviewerActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {
    ProgressDialog pDialog;
    PDFView pdfView;
    Integer pageNumber = 0;


    @Override
    protected void onResume() {
        super.onResume();


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String currentDateStr = df.format(c);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String saveddate = preferences.getString("saveddate", "");


        if (!saveddate.equals(currentDateStr)) {//if new date clear the cache
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(PdfviewerActivity.this).edit();
            editor.putString("saveddate", currentDateStr).apply();
            File cacheDir = getCacheDir();
            File appDir = new File(cacheDir.getParent());
            deleteRecursive(appDir);
            }
    }

    public void deleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }
        fileOrDirectory.delete();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_pdfviewer2);
        pDialog = new ProgressDialog(this);

        pdfView = (PDFView) findViewById(R.id.pdfView);
        String filename = getIntent().getStringExtra("filename");//getting filename shared from previous activity

        String path = getCacheDir() + "/" + filename; // getting the stored file from app cache directory by name
        pdfView.fromFile(new File(path)).defaultPage(0).enableSwipe(true).onPageChange(this).scrollHandle(null).enableAntialiasing(true).spacing(65).pageFitPolicy(FitPolicy.WIDTH).fitEachPage(false).pageFling(false).pageSnap(false).onLoad(this).enableAnnotationRendering(true).enableDoubletap(true).load();




    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", "pdfFileName", page + 1, pageCount));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

}