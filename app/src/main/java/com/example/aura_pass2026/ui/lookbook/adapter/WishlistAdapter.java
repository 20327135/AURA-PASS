package com.example.aura_pass2026.ui.lookbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.model.Product;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private final List<Product> products;

    public WishlistAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wishlist, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = products.get(position);
        holder.tvName.setText(p.getName());
        holder.tvDetail.setText(p.getColor() + " · " + p.getSize());
        if (p.getPrice() > 0) {
            NumberFormat nf = NumberFormat.getNumberInstance(Locale.KOREA);
            holder.tvPrice.setText("₩" + nf.format(p.getPrice()));
        }
    }

    @Override
    public int getItemCount() { return products.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDetail, tvPrice;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_product_name);
            tvDetail = itemView.findViewById(R.id.tv_product_detail);
            tvPrice = itemView.findViewById(R.id.tv_product_price);
        }
    }
}
