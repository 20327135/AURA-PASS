package com.example.aura_pass2026.ui.lookbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.model.Look;

import java.util.List;

public class LookAdapter extends RecyclerView.Adapter<LookAdapter.ViewHolder> {

    public interface OnClickListener {
        void onClick(Look look);
    }

    private final List<Look> looks;
    private final OnClickListener clickListener;

    public LookAdapter(List<Look> looks, OnClickListener clickListener) {
        this.looks = looks;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_look, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Look look = looks.get(position);
        holder.tvName.setText(look.getName());
        holder.tvDescription.setText(look.getDescription());
        holder.tvReason.setText("추천 이유: " + (look.getRecommendReason() != null ? look.getRecommendReason() : ""));
        holder.btnDetail.setOnClickListener(v -> clickListener.onClick(look));
    }

    @Override
    public int getItemCount() { return looks.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDescription, tvReason;
        Button btnDetail;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_look_name);
            tvDescription = itemView.findViewById(R.id.tv_look_description);
            tvReason = itemView.findViewById(R.id.tv_look_reason);
            btnDetail = itemView.findViewById(R.id.btn_look_detail);
        }
    }
}
