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
import com.example.aura_pass2026.databinding.FragmentLookCompareBinding;

public class LookCompareFragment extends Fragment {

    private FragmentLookCompareBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLookCompareBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 더미 데이터
        binding.tvLookAName.setText("미니멀 모노톤");
        binding.tvLookBName.setText("클래식 뉴트럴");

        binding.btnSaveLookA.setOnClickListener(v -> {
            // 룩 A 저장
        });
        binding.btnSaveLookB.setOnClickListener(v -> {
            // 룩 B 저장
        });

        // 메모 저장
        binding.btnSaveMemo.setOnClickListener(v -> {
            // 메모 저장 처리
        });

        // 브랜드 앱 홈으로
        binding.tvGotoBrandHome.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_look_compare_to_brand_home));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
