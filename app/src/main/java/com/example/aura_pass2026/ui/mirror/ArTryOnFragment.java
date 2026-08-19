package com.example.aura_pass2026.ui.mirror;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.databinding.FragmentArTryOnBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.Product;

import java.util.List;

public class ArTryOnFragment extends Fragment {

    private FragmentArTryOnBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentArTryOnBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 현재 착용 아이템 목록 표시
        List<Product> products = AppSession.getInstance().getFittingProducts();
        if (products != null && !products.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Product p : products) {
                sb.append(p.getCategory()).append(" · ").append(p.getName())
                  .append(" - ").append(p.getColor()).append(" · ").append(p.getSize()).append("\n");
            }
            binding.tvCurrentItems.setText(sb.toString().trim());
        }

        // 룩 저장 버튼
        binding.btnSaveLook.setOnClickListener(v -> {
            // 룩 저장 처리 (추후 구현)
        });

        // 룩 비교 버튼
        binding.btnCompareLook.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_ar_to_look_compare));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
