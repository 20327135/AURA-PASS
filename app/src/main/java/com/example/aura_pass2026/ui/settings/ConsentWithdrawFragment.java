package com.example.aura_pass2026.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aura_pass2026.databinding.FragmentConsentWithdrawBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.ConsentSettings;

public class ConsentWithdrawFragment extends Fragment {

    private FragmentConsentWithdrawBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentConsentWithdrawBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 철회할 항목 표시 (현재 동의된 항목)
        ConsentSettings settings = AppSession.getInstance().getConsentSettings();
        StringBuilder items = new StringBuilder();
        if (settings.isNfcIdentification()) items.append("NFC 식별\n매장 방문 시 VIP 패스 자동 인식\n\n");
        if (settings.isPurchaseHistoryUse()) items.append("구매 이력 활용\n추천 및 맞춤 응대에 참고\n");
        binding.tvWithdrawItems.setText(items.toString().trim());

        // 철회하기
        binding.btnWithdraw.setOnClickListener(v -> {
            settings.setNfcIdentification(false);
            settings.setPurchaseHistoryUse(false);
            settings.setImageAnalysis(false);
            settings.setFoodInfoUse(false);
            requireActivity().onBackPressed();
        });

        // 취소
        binding.btnCancel.setOnClickListener(v -> requireActivity().onBackPressed());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
