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
import com.example.aura_pass2026.databinding.FragmentStyleRecommendBinding;

public class StyleRecommendFragment extends Fragment {

    private FragmentStyleRecommendBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentStyleRecommendBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 더미 추천 데이터
        binding.tvLookName.setText("오늘의 추천 룩 — 미니멀 모노톤");
        binding.tvLookDescription.setText("현재 피팅 중인 코트에 슬린 트라우저와 앵클 부츠를 매한 구성입니다");
        binding.tvRecommendBasis.setText("퍼스널 컬러 쿨 톤 기반의 선호 스타일 미니멀, 체형 분석 반영");

        // 저장
        binding.btnSaveLook.setOnClickListener(v -> {
            // 룩 저장 처리
        });

        // AR로 착용해보기
        binding.btnArTryOn.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_style_to_ar));

        // 액세서리 상세 보기
        binding.tvAccessory1Detail.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("productId", "ACC001");
            Navigation.findNavController(v).navigate(R.id.action_style_to_product_detail, args);
        });

        // 이 룩 저장하기
        binding.btnSaveThisLook.setOnClickListener(v -> {
            // 저장 처리
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
