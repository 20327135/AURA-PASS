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

public class FittingProductListAdapter extends RecyclerView.Adapter<FittingProductListAdapter.ViewHolder> {

    public interface OnRemoveListener {
        void onRemove(int position);
    }

    private final List<Product> products;
    private final OnRemoveListener removeListener;

    public FittingProductListAdapter(List<Product> products, OnRemoveListener removeListener) {
        this.products = products;
        this.removeListener = removeListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fitting_product_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = products.get(position);
        holder.tvName.setText(p.getName());
        holder.tvDetail.setText("카테고리 · " + p.getColor() + " · " + p.getSize());
        holder.tvTag.setText(p.getRecognitionLabel());
        holder.btnRemove.setOnClickListener(v -> removeListener.onRemove(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() { return products.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDetail, tvTag;
        Button btnRemove;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_product_name);
            tvDetail = itemView.findViewById(R.id.tv_product_detail);
            tvTag = itemView.findViewById(R.id.tv_product_tag);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }
    }
}
