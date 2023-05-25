package com.bcajagn.brainbox.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcajagn.brainbox.R;
import com.bcajagn.brainbox.syllabus.SyllabusActivity;

import java.util.List;

public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.NestedViewHolder> {

    Activity activity;
    private List<String> mList;
    int semester;

    public NestedAdapter(Activity activity, List<String> mList, int semester) {
        this.activity = activity;
        this.mList = mList;
        this.semester = semester;

    }

    @NonNull
    @Override
    public NestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nested_item, parent, false);
        return new NestedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NestedViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mTv.setText(mList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, SyllabusActivity.class);
                i.putExtra("semester", semester + "");
                i.putExtra("subject", (position+1)+"");
                i.putExtra("bookname", mList.get(position));
                activity.startActivity(i);
            }
        });
        if (semester == 1) {
            if (position == 0) {
                holder.book_cover.setImageResource(R.drawable.it_toolx);
            } else if (position == 1) {
                holder.book_cover.setImageResource(R.drawable.principle_of_mathx );
            } else if (position == 2) {
                holder.book_cover.setImageResource(R.drawable.functional_englishz);
            } else if (position == 3) {
                holder.book_cover.setImageResource(R.drawable.c_languagez);
            }
        } else if (semester == 2) {
            if (position == 0) {
                holder.book_cover.setImageResource(R.drawable.discrete_mathematicsz);
            } else if (position == 1) {
                holder.book_cover.setImageResource(R.drawable.accounting_and_financialx);
            } else if (position == 2) {
                holder.book_cover.setImageResource(R.drawable.digital_circuitx);
            } else if (position == 3) {
                holder.book_cover.setImageResource(R.drawable.c_plusz);
            }
        } else if (semester == 3) {
            if (position == 0) {
                holder.book_cover.setImageResource(R.drawable.operating_systemz);
            } else if (position == 1) {
                holder.book_cover.setImageResource(R.drawable.computer_oriented_mathx);
            } else if (position == 2) {
                holder.book_cover.setImageResource(R.drawable.data_structurez);
            } else if (position == 3) {
                holder.book_cover.setImageResource(R.drawable.computer_organizationx);
            }
        } else if (semester == 4) {
            if (position == 0) {
                holder.book_cover.setImageResource(R.drawable.databasex);
            } else if (position == 1) {
                holder.book_cover.setImageResource(R.drawable.operation_researchz);
            } else if (position == 2) {
                holder.book_cover.setImageResource(R.drawable.computer_graphicsz);
            } else if (position == 3) {
                holder.book_cover.setImageResource(R.drawable.software_engineeringz);
            }
        } else if (semester == 5) {
            if (position == 0) {
                holder.book_cover.setImageResource(R.drawable.javaz);
            } else if (position == 1) {
                holder.book_cover.setImageResource(R.drawable.oracle_pl_sqlz);
            } else if (position == 2) {
                holder.book_cover.setImageResource(R.drawable.computer_networksz);
            } else if (position == 3) {
                holder.book_cover.setImageResource(R.drawable.software_project_managementx);
            }
        } else if (semester == 6) {
            if (position == 0) {
                holder.book_cover.setImageResource(R.drawable.network_and_securityx);
            } else if (position == 1) {
                holder.book_cover.setImageResource(R.drawable.web_development_toolsx);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class NestedViewHolder extends RecyclerView.ViewHolder {
        private TextView mTv;
        private ImageView book_cover;

        public NestedViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.book_title);
            book_cover = itemView.findViewById(R.id.book_cover);

        }
    }
}
