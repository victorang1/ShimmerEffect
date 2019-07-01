package com.example.victor_pc.shimmereffect;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.victor_pc.shimmereffect.databinding.ItemBinding;
import com.example.victor_pc.shimmereffect.databinding.ShowMoreBinding;
import com.example.victor_pc.shimmereffect.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_SHOW_MORE = 1;

    private Context context;
    private List<Item> listItem;
    private MainActivity.OnClick listener;

    public ItemAdapter(Context context, List<Item> listItem, MainActivity.OnClick listener) {
        this.context = context;
        this.listItem = listItem;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        if(i == VIEW_TYPE_ITEM) {
            ItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item, viewGroup, false);
            return new ItemViewHolder(itemBinding);
        }
        else {
            ShowMoreBinding showMoreBinding = DataBindingUtil.inflate(layoutInflater, R.layout.show_more, viewGroup, false);
            return new ShowMoreViewHolder(showMoreBinding);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return listItem.size() == position ? VIEW_TYPE_SHOW_MORE : VIEW_TYPE_ITEM;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        ItemBinding itemBinding;

        public ItemViewHolder(@NonNull ItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }

    private class ShowMoreViewHolder extends RecyclerView.ViewHolder {

        ShowMoreBinding showMoreBinding;

        public ShowMoreViewHolder(@NonNull ShowMoreBinding showMoreBinding) {
            super(showMoreBinding.getRoot());
            this.showMoreBinding = showMoreBinding;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if(viewHolder instanceof ItemViewHolder) {
            bindItemRows((ItemViewHolder) viewHolder, i);
        }
        else {
            showShowMoreButton((ShowMoreViewHolder) viewHolder, i);
        }
    }

    @Override
    public int getItemCount() {
        return listItem == null ? 0 : listItem.size() + 1;
    }

    private void bindItemRows(ItemViewHolder viewHolder, final int position) {

        final Item item = listItem.get(position);
        viewHolder.itemBinding.setItem(item);
        viewHolder.itemBinding.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.Click(item, position);
            }
        });
    }

    private void showShowMoreButton(ShowMoreViewHolder viewHolder, int position) {
//        viewHolder.showMoreBinding.tvShowMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.ClickTextView();
//            }
//        });
    }
}
