package com.example.victor_pc.shimmereffect;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.victor_pc.shimmereffect.databinding.ItemBinding;
import com.example.victor_pc.shimmereffect.model.Item;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    private List<Item> itemList;
    private OnItemClickListener listener;
    private MainActivity.OnClick listener2;
    private Context context;

    public MyAdapter(Context context, List<Item> itemList, OnItemClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    public MyAdapter(Context context, List<Item> itemList, MainActivity.OnClick listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener2 = listener;
    }

    public void filterList(List<Item> filteredItem) {
        this.itemList = filteredItem;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ItemBinding itemBinding;

        public MyViewHolder(ItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

//        public void bind(final Item item, final OnItemClickListener listener) {
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                    listener.onItemClick(item);
//                }
//            });
//        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item, viewGroup, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final Item item = itemList.get(i);
        myViewHolder.itemBinding.setItem(item);
//        myViewHolder.bind(item, listener);

        myViewHolder.itemBinding.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((MainActivity)context).bebas();
                listener2.Click(item, i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
