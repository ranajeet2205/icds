package com.ranajeetbarik2205.icds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ranajeetbarik2205.icds.R;
import com.ranajeetbarik2205.icds.databinding.ThrItemIstBinding;
import com.ranajeetbarik2205.icds.interfaces.RecyclerViewClick;
import com.ranajeetbarik2205.icds.models.THR;

import java.util.List;

public class ThrListAdapter extends RecyclerView.Adapter<ThrListAdapter.MyViewHolder> {
    private final Context context;
    private List<THR> items;
    private RecyclerViewClick recyclerViewClick;

    public ThrListAdapter(List<THR> items, Context context,RecyclerViewClick recyclerViewClick) {
        this.items = items;
        this.context = context;
        this.recyclerViewClick = recyclerViewClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                           int viewType) {
        ThrItemIstBinding thrItemIstBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.thr_item_ist, parent, false);

        return new MyViewHolder(thrItemIstBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        THR item = items.get(position);
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

        if (item.getStatus()==1){
            holder.thrItemIstBinding.statusIcon.setImageResource(R.drawable.ic_thumb_up_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ThrItemIstBinding thrItemIstBinding;

        public MyViewHolder(ThrItemIstBinding thrItemIstBinding) {
            super(thrItemIstBinding.getRoot());
            this.thrItemIstBinding = thrItemIstBinding;
        }

        public void set(THR item) {
            //UI setting code
            thrItemIstBinding.centreTxt.setText(item.getCentre());
            thrItemIstBinding.monthTxt.setText(item.getReporting_month());
            thrItemIstBinding.bnfTxt.setText(item.getNumber_total_beneficiary());
            thrItemIstBinding.packetsTxt.setText(item.getNumber_total_packets());
        }
    }
}