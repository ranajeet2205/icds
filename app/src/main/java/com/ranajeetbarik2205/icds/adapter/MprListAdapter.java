package com.ranajeetbarik2205.icds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ranajeetbarik2205.icds.R;
import com.ranajeetbarik2205.icds.databinding.MprListItemBinding;
import com.ranajeetbarik2205.icds.interfaces.RecyclerViewClick;
import com.ranajeetbarik2205.icds.models.MPR;

import java.util.List;

public class MprListAdapter extends RecyclerView.Adapter<MprListAdapter.MyViewHolder> {
    private final Context context;
    private List<MPR> items;
    private RecyclerViewClick recyclerViewClick;

    public MprListAdapter(List<MPR> items, Context context,RecyclerViewClick recyclerViewClick) {
        this.items = items;
        this.context = context;
        this.recyclerViewClick = recyclerViewClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                           int viewType) {
        MprListItemBinding mprListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.mpr_list_item,parent,false);

        return new MyViewHolder(mprListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        MPR item = items.get(position);
        holder.set(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewClick.onClick(holder.itemView,position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                recyclerViewClick.onLongClick(holder.itemView,position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private MprListItemBinding mprListItemBinding;

        public MyViewHolder(MprListItemBinding mprListItemBinding) {
            super(mprListItemBinding.getRoot());
           this.mprListItemBinding = mprListItemBinding;
        }

        public void set(MPR item) {
            //UI setting code
            mprListItemBinding.centreTxt.setText(item.getCentre());
            mprListItemBinding.monthTxt.setText(item.getReporting_month());
            mprListItemBinding.awwTxt.setText(item.getAww_name());
            mprListItemBinding.awhTxt.setText(item.getAwh_name());
        }
    }
}