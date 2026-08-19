package com.example.aura_pass2026.ui.lookbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.databinding.FragmentMyLookbookBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.Look;
import com.example.aura_pass2026.ui.lookbook.adapter.LookAdapter;

import java.util.List;

public class MyLookbookFragment extends Fragment {

    private FragmentMyLookbookBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMyLookbookBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Look> looks = AppSession.getInstance().getSavedLooks();
        if (looks == null) {
            looks = Look.createDummyLooks();
            AppSession.getInstance().setSavedLooks(looks);
        }

        binding.tvLookDate.setText("2025년 6월 14일 방문 기록");
        binding.tvLookCount.setText("저장된 룩 " + looks.size() + "개");

        LookAdapter adapter = new LookAdapter(looks, look -> {
            Bundle args = new Bundle();
            args.putString("lookId", look.getId());
            Navigation.findNavController(requireView())
                    .navigate(R.id.action_my_lookbook_to_look_detail, args);
        });
        binding.rvLooks.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvLooks.setAdapter(adapter);

        // 관심 상품
        binding.tvWishlist.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_my_lookbook_to_wishlist));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
