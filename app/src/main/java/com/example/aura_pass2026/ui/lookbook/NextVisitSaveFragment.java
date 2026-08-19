package com.example.aura_pass2026.ui.lookbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.aura_pass2026.databinding.FragmentNextVisitSaveBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.Look;
import com.example.aura_pass2026.ui.lookbook.adapter.LookAdapter;

import java.util.List;

public class NextVisitSaveFragment extends Fragment {

    private FragmentNextVisitSaveBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNextVisitSaveBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Look> looks = AppSession.getInstance().getSavedLooks();
        if (looks != null) {
            LookAdapter adapter = new LookAdapter(looks, look -> {
                // 룩 상세 표시 (현재 화면에서는 별도 네비게이션 없음)
            });
            binding.rvSavedLooks.setLayoutManager(new LinearLayoutManager(requireContext()));
            binding.rvSavedLooks.setAdapter(adapter);
        }

        // 상담 요청 등록
        binding.btnAddConsultRequest.setOnClickListener(v -> {
            // 상담 요청 처리
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
