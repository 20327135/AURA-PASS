package com.example.aura_pass2026.ui.lounge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aura_pass2026.databinding.FragmentMenuDetailBinding;
import com.example.aura_pass2026.model.MenuItem;

import java.util.List;

public class MenuDetailFragment extends Fragment {

    private FragmentMenuDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMenuDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String menuItemId = getArguments() != null
                ? getArguments().getString("menuItemId", "") : "";

        // ID로 더미 데이터에서 찾기
        List<MenuItem> items = MenuItem.createDummyMenuItems();
        MenuItem found = null;
        for (MenuItem m : items) {
            if (m.getId().equals(menuItemId)) { found = m; break; }
        }
        if (found == null && !items.isEmpty()) found = items.get(2); // 기본값

        final MenuItem finalItem = found;
        if (finalItem != null) {
            binding.tvMenuName.setText(finalItem.getName());
            binding.tvMenuCategory.setText(finalItem.getCategory());
            binding.tvRecommendReason.setText(finalItem.getRecommendReason() != null
                    ? finalItem.getRecommendReason() : finalItem.getDescription());

            // 알레르기 정보
            if (finalItem.getAllergyInfo() != null && !finalItem.getAllergyInfo().isEmpty()) {
                binding.tvAllergyInfo.setText(String.join(", ", finalItem.getAllergyInfo()));
            }

            // 태그
            if (finalItem.getTags() != null && !finalItem.getTags().isEmpty()) {
                binding.tvTags.setText(String.join(" · ", finalItem.getTags()));
            }

            // 온도 옵션
            if (finalItem.getTempOption() != null) {
                binding.tvTempOption.setText("온도 옵션: " + finalItem.getTempOption());
            }

            // 피드백
            binding.btnLike.setOnClickListener(v -> finalItem.setFeedback(MenuItem.FeedbackType.LIKE));
            binding.btnDislike.setOnClickListener(v -> finalItem.setFeedback(MenuItem.FeedbackType.DISLIKE));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
