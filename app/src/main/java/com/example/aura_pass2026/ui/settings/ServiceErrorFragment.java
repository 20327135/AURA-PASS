package com.example.aura_pass2026.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.databinding.FragmentServiceErrorBinding;

public class ServiceErrorFragment extends Fragment {

    private FragmentServiceErrorBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentServiceErrorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 장애 상태 칩 (더미 — 실제로는 서버에서 받아옴)
        binding.tvRfidError.setText("RFID 인식 불가");
        binding.tvAiError.setText("AI 추천 중단");
        binding.tvNetworkError.setText("네트워크 불안정");

        // 수동 응대 전환
        binding.btnManualMode.setOnClickListener(v -> requireActivity().onBackPressed());

        // 대체 추천 보기
        binding.btnAltRecommend.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_error_to_alt_recommend));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
