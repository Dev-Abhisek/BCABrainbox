package com.bcajagn.brainbox.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bcajagn.brainbox.R;
import com.bcajagn.brainbox.creators.CreatorActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<DataModel> parentlist;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView infobtn = findViewById(R.id.infobtn);
        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreatorActivity.class);
                startActivity(i);
            }
        });
        recyclerView = findViewById(R.id.main_recyclervie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        parentlist = new ArrayList<>();

        //adding child items

        List<String> chaildlist1 = new ArrayList<>();
        chaildlist1.add("IT Tools & Applications");
        chaildlist1.add("Principles of Mathematics");
        chaildlist1.add("Functional English");
        chaildlist1.add("C Language");


        List<String> chaildlist2 = new ArrayList<>();
        chaildlist2.add("Discrete Mathematics");
        chaildlist2.add("Accounts & Financial Management");
        chaildlist2.add("Digital Circuit & Logic Design");
        chaildlist2.add("C++");

        List<String> chaildlist3 = new ArrayList<>();
        chaildlist3.add("Operating System");
        chaildlist3.add("Computer Oriented Mathematics");
        chaildlist3.add("Data Structure");
        chaildlist3.add("Computer Org. & Architecture");

        List<String> chaildlist4 = new ArrayList<>();
        chaildlist4.add("Intro to Database Management System");
        chaildlist4.add("Operation Research");
        chaildlist4.add("Computer Graphics");
        chaildlist4.add("Software Engineering");

        List<String> chaildlist5 = new ArrayList<>();
        chaildlist5.add("Internet and JAVA");
        chaildlist5.add("ORACLE and PL/SQL");
        chaildlist5.add("Computer Networks");
        chaildlist5.add("Software Project Management");

        List<String> chaildlist6 = new ArrayList<>();
        chaildlist6.add("Advance Networks & Network Security");
        chaildlist6.add("Web Development Tools & Techniques");

//adding parent items
        parentlist.add(new DataModel(chaildlist1, "SEMESTER I"));
        parentlist.add(new DataModel(chaildlist2, "SEMESTER II"));
        parentlist.add(new DataModel(chaildlist3, "SEMESTER III"));
        parentlist.add(new DataModel(chaildlist4, "SEMESTER IV"));
        parentlist.add(new DataModel(chaildlist5, "SEMESTER V"));
        parentlist.add(new DataModel(chaildlist6, "SEMESTER VI"));


        adapter = new ItemAdapter(MainActivity.this, parentlist);
        recyclerView.setAdapter(adapter);
    }
}