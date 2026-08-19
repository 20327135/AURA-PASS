package com.example.aura_pass2026.ui.lookbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.databinding.FragmentBrandAppHomeBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.Look;

import java.util.List;

public class BrandAppHomeFragment extends Fragment {

    private FragmentBrandAppHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBrandAppHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 저장된 룩 미리보기
        if (AppSession.getInstance().getSavedLooks() == null) {
            AppSession.getInstance().setSavedLooks(Look.createDummyLooks());
        }
        List<Look> looks = AppSession.getInstance().getSavedLooks();
        if (looks != null && looks.size() >= 2) {
            binding.tvSavedLook1Name.setText(looks.get(0).getName());
            binding.tvSavedLook2Name.setText(looks.get(1).getName());
        }

        // 내 룩북 전체 보기
        binding.btnViewAllLooks.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_brand_home_to_my_lookbook));

        // 라운지로
        binding.tvGotoLounge.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_brand_home_to_lounge));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
