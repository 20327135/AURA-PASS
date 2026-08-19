package com.example.aura_pass2026.ui.fitting.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.model.Product;

import java.util.List;

public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.ViewHolder> {

    public interface OnAddListener {
        void onAdd(Product product);
    }

    private final List<Product> products;
    private final OnAddListener addListener;

    public ProductSearchAdapter(List<Product> products, OnAddListener addListener) {
        this.products = products;
        this.addListener = addListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_search, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = products.get(position);
        holder.tvName.setText(p.getName());
        holder.tvDetail.setText(p.getBrand() + " · " + p.getColor() + " · " + p.getSize());
        holder.tvSku.setText(p.getSku() != null ? p.getSku() : "");
        holder.btnAdd.setOnClickListener(v -> addListener.onAdd(p));
    }

    @Override
    public int getItemCount() { return products.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDetail, tvSku;
        Button btnAdd;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_product_name);
            tvDetail = itemView.findViewById(R.id.tv_product_detail);
            tvSku = itemView.findViewById(R.id.tv_product_sku);
            btnAdd = itemView.findViewById(R.id.btn_add);
        }
    }
}
