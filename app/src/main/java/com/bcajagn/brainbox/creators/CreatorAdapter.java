package com.bcajagn.brainbox.creators;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.URLUtil;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.bcajagn.brainbox.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CreatorAdapter extends BaseAdapter {
    AppCompatActivity c;
    ArrayList<creatormodal> itemmodal;
    LayoutInflater inflater;

    public CreatorAdapter(AppCompatActivity c, ArrayList<creatormodal> itemmodal) {
        this.c = c;
        this.itemmodal = itemmodal;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return itemmodal.size();
    }

    @Override
    public Object getItem(int position) {
        return itemmodal.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itemmodal.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.creatoritem, parent, false);
        }
        ImageView profile = (ImageView) convertView.findViewById(R.id.profile);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        ImageView github = (ImageView) convertView.findViewById(R.id.github);
        ImageView linkedin = (ImageView) convertView.findViewById(R.id.linkedin);
        profile.setImageResource(itemmodal.get(position).getProfile());

        name.setText(itemmodal.get(position).getName());
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] link = itemmodal.get(position).getGithub().split(":-");
                String url = link[1].trim();
                if (URLUtil.isValidUrl(url)) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    c.startActivity(i);

                } else {
                    Toast.makeText(c, "Url not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] link = itemmodal.get(position).getLinkedin().split(":-");
                String url = link[1].trim();
                if (URLUtil.isValidUrl(url)) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    c.startActivity(i);

                } else {
                    Toast.makeText(c, "Url not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;

    }


}
