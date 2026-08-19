package com.example.aura_pass2026.ui.entry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.databinding.FragmentGuestModeBinding;
import com.example.aura_pass2026.model.AppSession;

public class GuestModeFragment extends Fragment {

    private FragmentGuestModeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentGuestModeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppSession.getInstance().setVipMode(false);
        AppSession.getInstance().setCurrentCustomer(null);

        // 라운지 추천 보기
        binding.btnViewLounge.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_guest_to_lounge));

        // 피팅 시작하기
        binding.tvStartFitting.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_guest_to_fitting));

        // 프로필 다시 로드 (NFC 재시도)
        binding.btnReloadProfile.setOnClickListener(v ->
                requireActivity().onBackPressed());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
