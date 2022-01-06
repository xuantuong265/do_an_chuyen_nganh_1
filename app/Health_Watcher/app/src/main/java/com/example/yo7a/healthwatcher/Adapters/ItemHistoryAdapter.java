package com.example.yo7a.healthwatcher.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yo7a.healthwatcher.Models.ItemHistory;
import com.example.yo7a.healthwatcher.R;

import java.util.List;

public class ItemHistoryAdapter extends RecyclerView.Adapter<ItemHistoryAdapter.ItemHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<ItemHistory> itemList;

    public ItemHistoryAdapter(List<ItemHistory> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemHistoryAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_history, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHistoryAdapter.ItemHolder holder, int position) {

        ItemHistory item = itemList.get(position);
        holder.tvTime.setText(item.getTime());

        // Create layout manager with initial prefetch item count
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rcSubItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(item.getList().size());

        // Create sub item view adapter
        SubItemHistoryAdapter subItemAdapter = new SubItemHistoryAdapter(item.getList());

        holder.rcSubItem.setLayoutManager(layoutManager);
        holder.rcSubItem.setAdapter(subItemAdapter);
        holder.rcSubItem.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        System.out.println("sfsahfjshf");
        System.out.println(itemList.size());
        return itemList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        private TextView tvTime;
        private RecyclerView rcSubItem;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            tvTime = itemView.findViewById(R.id.tv_item_title);
            rcSubItem = itemView.findViewById(R.id.rv_sub_item);
        }
    }
}
