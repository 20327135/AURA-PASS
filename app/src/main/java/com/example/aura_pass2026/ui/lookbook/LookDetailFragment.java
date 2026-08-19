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
import com.example.aura_pass2026.databinding.FragmentLookDetailBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.Look;

import java.util.List;

public class LookDetailFragment extends Fragment {

    private FragmentLookDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLookDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String lookId = getArguments() != null
                ? getArguments().getString("lookId", "") : "";

        // 룩 찾기
        List<Look> looks = AppSession.getInstance().getSavedLooks();
        Look look = null;
        if (looks != null) {
            for (Look l : looks) {
                if (l.getId().equals(lookId)) { look = l; break; }
            }
            if (look == null && !looks.isEmpty()) look = looks.get(0);
        }

        if (look != null) {
            binding.tvLookName.setText(look.getName());
            binding.tvLookDate.setText("2025년 6월 분 · 피팅룸 A");
            binding.tvRecommendReason.setText(look.getRecommendReason());
            if (look.getStylistMemo() != null) {
                binding.tvStylistMemo.setText(look.getStylistMemo());
            }

            // 태그 표시
            if (look.getTags() != null && !look.getTags().isEmpty()) {
                binding.tvTags.setText(String.join("  ", look.getTags()));
            }
        }

        binding.btnSaveToNextVisit.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_look_detail_to_next_visit));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
