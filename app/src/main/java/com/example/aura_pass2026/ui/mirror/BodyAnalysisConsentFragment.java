package com.example.aura_pass2026.ui.mirror;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aura_pass2026.databinding.FragmentBodyAnalysisConsentBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.ConsentSettings;

public class BodyAnalysisConsentFragment extends Fragment {

    private FragmentBodyAnalysisConsentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBodyAnalysisConsentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ConsentSettings settings = AppSession.getInstance().getConsentSettings();
        binding.switchBodyInfo.setChecked(settings.isImageAnalysis());
        binding.switchImageAnalysis.setChecked(settings.isImageAnalysis());
        binding.switchStyleData.setChecked(settings.isPurchaseHistoryUse());

        binding.btnSave.setOnClickListener(v -> {
            settings.setImageAnalysis(binding.switchBodyInfo.isChecked());
            settings.setImageAnalysis(binding.switchImageAnalysis.isChecked());
            settings.setPurchaseHistoryUse(binding.switchStyleData.isChecked());
            requireActivity().onBackPressed();
        });

        binding.btnCancel.setOnClickListener(v -> requireActivity().onBackPressed());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
