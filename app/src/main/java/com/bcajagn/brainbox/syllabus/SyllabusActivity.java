package com.bcajagn.brainbox.syllabus;

import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bcajagn.brainbox.helper.BaseActivity;
import com.bcajagn.brainbox.helper.DownloadFileFromURL;
import com.bcajagn.brainbox.R;
import com.bcajagn.brainbox.pdfviewer.PdfviewerActivity;

import java.io.File;

public class SyllabusActivity extends BaseActivity {
    String[] unitArray = {"Syllabus".toUpperCase(), "Unit: 1".toUpperCase(), "Unit: 2".toUpperCase(), "Unit: 3".toUpperCase(),
            "Unit: 4".toUpperCase(), "Unit: 5".toUpperCase(), "Previous year papers".toUpperCase()};
    String subject;
    String semester;
    ListView listView;
    private String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        semester = getIntent().getStringExtra("semester");
        subject = getIntent().getStringExtra("subject");
        String bookname = getIntent().getStringExtra("bookname");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(bookname);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.listview);
        SyllabusAdapter adapter = new SyllabusAdapter(SyllabusActivity.this, unitArray);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    filename = "syllabus_" + semester + "_" + subject + ".pdf";
//                    file name of syllabus
                } else if (i == unitArray.length - 1) {
                    filename = "papers_" + semester + "_" + subject + ".pdf";
                    //                    file name of previous paper
                } else {
                    filename = semester + "_" + subject + "_" + (i) + ".pdf";
                    //                    file name of all units
                }
                String fileurl = BaseUrl + filename + ext;
                String path = getCacheDir() + "/" + filename;
//                storing the file in app cache directory
                File file = new File(path);
                if (file.exists()) {
                    Intent intent = new Intent(SyllabusActivity.this, PdfviewerActivity.class);
                    intent.putExtra("filename",filename);//sharing the filename to PdfviewerActivity2
                    startActivity(intent);
                } else {
                    ProgressDialog pd = new ProgressDialog(SyllabusActivity.this);
                    pd.setMessage("Loading " + unitArray[i] + " of Semester: " + semester);
                    pd.show();
                    new DownloadFileFromURL(getApplicationContext(), filename, pd).execute(fileurl);
                }


            }
        });
    }


}