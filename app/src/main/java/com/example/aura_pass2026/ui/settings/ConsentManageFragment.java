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
import com.example.aura_pass2026.databinding.FragmentConsentManageBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.ConsentSettings;

public class ConsentManageFragment extends Fragment {

    private FragmentConsentManageBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentConsentManageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ConsentSettings settings = AppSession.getInstance().getConsentSettings();

        // 동의 현황 요약
        binding.tvConsentSummary.setText(
                settings.getTotalCount() + "개 항목 중 "
                + settings.getConsentedCount() + "개 동의 중");
        binding.tvLastChangedDate.setText("마지막 변경일   2025년 6월 10일");

        // 항목별 스위치
        binding.switchNfc.setChecked(settings.isNfcIdentification());
        binding.switchPurchaseHistory.setChecked(settings.isPurchaseHistoryUse());
        binding.switchImageAnalysis.setChecked(settings.isImageAnalysis());
        binding.switchFoodInfo.setChecked(settings.isFoodInfoUse());

        binding.switchNfc.setOnCheckedChangeListener((btn, checked) ->
                settings.setNfcIdentification(checked));
        binding.switchPurchaseHistory.setOnCheckedChangeListener((btn, checked) ->
                settings.setPurchaseHistoryUse(checked));
        binding.switchImageAnalysis.setOnCheckedChangeListener((btn, checked) ->
                settings.setImageAnalysis(checked));
        binding.switchFoodInfo.setOnCheckedChangeListener((btn, checked) ->
                settings.setFoodInfoUse(checked));

        // 전체 동의 철회
        binding.btnWithdrawAll.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_consent_to_withdraw));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
