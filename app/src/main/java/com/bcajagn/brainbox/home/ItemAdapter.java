package com.bcajagn.brainbox.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bcajagn.brainbox.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<DataModel> mList;
    Activity activity;
    private List<String> list = new ArrayList<>();
    private int selectedposition = -1;

    public ItemAdapter(Activity activity, List<DataModel> mList) {
        this.activity = activity;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {

        DataModel model = mList.get(position);
        holder.mTextView.setText(model.getItemText());

        if (selectedposition == position) {
            //expanding the clicked item
            model.setExpanded(true);
            list = model.getchaildlist();
            holder.expandableLayout.setVisibility(View.VISIBLE);
            holder.mArrowImage.setImageResource(R.drawable.arrow_up);
        } else {
            //collapsing all items
            model.setExpanded(false);
            holder.expandableLayout.setVisibility(View.GONE);
            holder.mArrowImage.setImageResource(R.drawable.arrow_down);
        }
        NestedAdapter adapter = new NestedAdapter(activity, list, position + 1);
        holder.nestedRecyclerView.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(), 2));
        holder.nestedRecyclerView.setHasFixedSize(true);
        holder.nestedRecyclerView.setAdapter(adapter);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.isExpanded()) {
                    selectedposition = -1;
                    //                  selectedposition value = -1 means no selection because position starts form 0;
                } else {
                    selectedposition = position;
                    //                 to expand the clicked item (notifyDataSetChanged is mandatory);
                }
                notifyDataSetChanged();// refresh the adapter only
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private RelativeLayout expandableLayout;
        private TextView mTextView;
        private ImageView mArrowImage;
        private RecyclerView nestedRecyclerView;

        ImageView cover;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            mTextView = itemView.findViewById(R.id.itemTv);
            mArrowImage = itemView.findViewById(R.id.arro_imageview);
            nestedRecyclerView = itemView.findViewById(R.id.child_rv);
            cover = itemView.findViewById(R.id.book_cover);
        }
    }
}
