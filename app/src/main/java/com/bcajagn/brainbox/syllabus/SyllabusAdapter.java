package com.bcajagn.brainbox.syllabus;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bcajagn.brainbox.R;
import com.bcajagn.brainbox.creators.creatormodal;

import java.util.ArrayList;


public class SyllabusAdapter extends BaseAdapter {
    AppCompatActivity c;
    String[] itemmodal;
    LayoutInflater inflater;

    public SyllabusAdapter(AppCompatActivity c, String[] itemmodal) {
        this.c = c;
        this.itemmodal = itemmodal;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return itemmodal.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_listview, parent, false);
        }
        TextView label = (TextView) convertView.findViewById(R.id.label);
        label.setText(itemmodal[position]);

        return convertView;

    }


}
