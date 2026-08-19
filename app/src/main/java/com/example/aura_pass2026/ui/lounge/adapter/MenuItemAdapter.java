package com.example.aura_pass2026.ui.lounge.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.model.MenuItem;

import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {

    public interface OnClickListener {
        void onClick(MenuItem menuItem);
    }

    private final List<MenuItem> items;
    private final OnClickListener clickListener;

    public MenuItemAdapter(List<MenuItem> items, OnClickListener clickListener) {
        this.items = items;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem item = items.get(position);
        holder.tvName.setText(item.getName());
        holder.tvCategory.setText(item.getCategory());
        holder.tvDescription.setText(item.getDescription());
        holder.itemView.setOnClickListener(v -> clickListener.onClick(item));
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCategory, tvDescription;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_menu_name);
            tvCategory = itemView.findViewById(R.id.tv_menu_category);
            tvDescription = itemView.findViewById(R.id.tv_menu_description);
        }
    }
}
