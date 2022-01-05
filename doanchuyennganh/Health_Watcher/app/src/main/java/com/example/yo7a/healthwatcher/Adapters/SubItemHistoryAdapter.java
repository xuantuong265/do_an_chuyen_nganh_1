package com.example.yo7a.healthwatcher.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yo7a.healthwatcher.Models.SubItemHistory;
import com.example.yo7a.healthwatcher.R;
import com.example.yo7a.healthwatcher.utilities.Constants;

import org.w3c.dom.Text;

import java.util.List;

public class SubItemHistoryAdapter extends RecyclerView.Adapter<SubItemHistoryAdapter.SubItemHolder> {

    private List<SubItemHistory> list;

    public SubItemHistoryAdapter(List<SubItemHistory> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SubItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_sub_item_history, parent, false);
        return new SubItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemHolder holder, int position) {
        SubItemHistory subItem = list.get(position);
        holder.tvType.setText(subItem.getType());
        holder.tvNumber.setText(subItem.getNumber());

        if (subItem.getType().equals(Constants.BPM)) {
            holder.icon.setImageResource(R.mipmap.heart_rate);
        }else if (subItem.getType().equals(Constants.MEASURE_OXYGEN)) {
            holder.icon.setImageResource(R.mipmap.oxygen_saturation);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SubItemHolder extends RecyclerView.ViewHolder {

        private TextView tvType;
        private TextView tvNumber;
        private ImageView icon;

        public SubItemHolder(@NonNull View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tv_sub_item_title);
            tvNumber = itemView.findViewById(R.id.number);
            icon = itemView.findViewById(R.id.img_sub_item);
        }
    }
}
