package com.example.aura_pass2026.ui.fitting.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.model.Product;

import java.util.List;

public class FittingProductAdapter extends RecyclerView.Adapter<FittingProductAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String productId);
    }

    private final List<Product> products;
    private final OnItemClickListener listener;

    public FittingProductAdapter(List<Product> products, OnItemClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fitting_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = products.get(position);
        holder.tvName.setText(p.getName());
        holder.tvDetail.setText(p.getCategory() + " · " + p.getSize() + " 사이즈");
        holder.tvTag.setText(p.getRecognitionLabel());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(p.getId()));
    }

    @Override
    public int getItemCount() { return products.size(); }

    public void removeItem(int position) {
        products.remove(position);
        notifyItemRemoved(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDetail, tvTag;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_product_name);
            tvDetail = itemView.findViewById(R.id.tv_product_detail);
            tvTag = itemView.findViewById(R.id.tv_product_tag);
        }
    }
}
