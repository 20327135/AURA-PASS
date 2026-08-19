package com.example.aura_pass2026.ui.mirror;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aura_pass2026.databinding.FragmentRecommendProductDetailBinding;

public class RecommendProductDetailFragment extends Fragment {

    private FragmentRecommendProductDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRecommendProductDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 더미 상품 상세 데이터
        binding.tvProductName.setText("울 블렌드 오버핏 재킷");
        binding.tvProductCategory.setText("아우터 · 재킷 · 브라운 / 사이즈 M");
        binding.tvRecommendReason.setText(
                "선하하신 오버핏 실루엣과 어스 톤 계열 컬러를 반영했습니다.\n" +
                "현재 피팅 중인 와이드 팬츠와 자연스럽게 코디됩니다.\n" +
                "퍼스널 컬러(웜 오텀) 기반으로 피부 톤을 밝게 연출합니다.");
        binding.tvMaterial.setText("울 60%, 폴리에스터 40%");
        binding.tvOrigin.setText("이탈리아");
        binding.tvSeason.setText("2025 F/W");

        // 피드백 버튼
        binding.btnLike.setOnClickListener(v -> binding.btnLike.setSelected(true));
        binding.btnDislike.setOnClickListener(v -> binding.btnDislike.setSelected(true));

        // 의견 저장
        binding.btnSaveMemo.setOnClickListener(v -> requireActivity().onBackPressed());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
