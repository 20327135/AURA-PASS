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
import com.example.aura_pass2026.databinding.FragmentSettingsBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.ConsentSettings;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ConsentSettings settings = AppSession.getInstance().getConsentSettings();

        // 동의 스위치 바인딩
        binding.switchNfc.setChecked(settings.isNfcIdentification());
        binding.switchPurchaseHistory.setChecked(settings.isPurchaseHistoryUse());
        binding.switchImageAnalysis.setChecked(settings.isImageAnalysis());
        binding.switchFoodInfo.setChecked(settings.isFoodInfoUse());

        // 스위치 변경 시 세션에 반영
        binding.switchNfc.setOnCheckedChangeListener((btn, checked) ->
                settings.setNfcIdentification(checked));
        binding.switchPurchaseHistory.setOnCheckedChangeListener((btn, checked) ->
                settings.setPurchaseHistoryUse(checked));
        binding.switchImageAnalysis.setOnCheckedChangeListener((btn, checked) ->
                settings.setImageAnalysis(checked));
        binding.switchFoodInfo.setOnCheckedChangeListener((btn, checked) ->
                settings.setFoodInfoUse(checked));

        // 목적별 동의 관리 버튼
        binding.btnCheckStatus.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_settings_to_consent));

        // 장애 상태 확인 버튼
        binding.tvCheckError.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_settings_to_error));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
