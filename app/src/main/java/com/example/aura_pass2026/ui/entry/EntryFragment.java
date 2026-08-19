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
import com.example.aura_pass2026.databinding.FragmentEntryBinding;

public class EntryFragment extends Fragment {

    private FragmentEntryBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEntryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // VIP 패스로 입장 버튼 → NFC 인식 화면
        binding.btnVipEntry.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_entry_to_nfc));

        // 일반 고객으로 계속하기 버튼 → 일반 고객 모드
        binding.btnGuestEntry.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_entry_to_guest));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
