package com.ranajeetbarik2205.icds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ranajeetbarik2205.icds.R;
import com.ranajeetbarik2205.icds.databinding.ImmunListItemBinding;
import com.ranajeetbarik2205.icds.interfaces.RecyclerViewClick;
import com.ranajeetbarik2205.icds.models.Immunization;

import java.util.List;

public class ImmunListAdapter extends RecyclerView.Adapter<ImmunListAdapter.MyViewHolder> {
    private final Context context;
    private List<Immunization> items;
    private RecyclerViewClick recyclerViewClick;

    public ImmunListAdapter(List<Immunization> items, Context context,RecyclerViewClick recyclerViewClick) {
        this.items = items;
        this.context = context;
        this.recyclerViewClick = recyclerViewClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                           int viewType) {
        ImmunListItemBinding immunListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.immun_list_item, parent, false);

        return new MyViewHolder(immunListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Immunization item = items.get(position);
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
            holder.immunListItemBinding.statusIcon.setImageResource(R.drawable.ic_thumb_up_black_24dp);
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
        private ImmunListItemBinding immunListItemBinding;

        public MyViewHolder(ImmunListItemBinding immunListItemBinding) {
            super(immunListItemBinding.getRoot());
            this.immunListItemBinding = immunListItemBinding;
        }

        public void set(Immunization item) {
            //UI setting code
            immunListItemBinding.centreTxt.setText(item.getCentre());
            immunListItemBinding.monthTxt.setText(item.getReporting_month());
            immunListItemBinding.dueTxt.setText(item.getNumber_total_due());
            immunListItemBinding.receivedTxt.setText(item.getTotal_number_received());
        }
    }
}