package com.ranajeetbarik2205.icds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ranajeetbarik2205.icds.R;
import com.ranajeetbarik2205.icds.databinding.BnfListItemBinding;
import com.ranajeetbarik2205.icds.interfaces.RecyclerViewClick;
import com.ranajeetbarik2205.icds.models.BNF;

import java.util.List;

public class BnfListAdapter extends RecyclerView.Adapter<BnfListAdapter.MyViewHolder> {
    private final Context context;
    private List<BNF> items;
    RecyclerViewClick recyclerViewClick;

    public BnfListAdapter(List<BNF> items, Context context,RecyclerViewClick recyclerViewClick) {
        this.items = items;
        this.context = context;
        this.recyclerViewClick = recyclerViewClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                           int viewType) {
        BnfListItemBinding bnfListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.bnf_list_item, parent, false);

        return new MyViewHolder(bnfListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        BNF item = items.get(position);
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
            holder.bnfListItemBinding.statusIcon.setImageResource(R.drawable.ic_thumb_up_black_24dp);
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
        BnfListItemBinding bnfListItemBinding;

        public MyViewHolder(BnfListItemBinding bnfListItemBinding) {
            super(bnfListItemBinding.getRoot());
            this.bnfListItemBinding = bnfListItemBinding;
        }

        public void set(BNF item) {
            //UI setting code
            bnfListItemBinding.centreTxt.setText(item.getCentre());
            bnfListItemBinding.monthTxt.setText(item.getReporting_month());
            bnfListItemBinding.bnfTxt.setText(item.getNumber_total_beneficiary());
        }
    }
}