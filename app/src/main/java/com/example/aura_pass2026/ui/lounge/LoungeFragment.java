package com.example.aura_pass2026.ui.lounge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.databinding.FragmentLoungeBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.Customer;
import com.example.aura_pass2026.model.MenuItem;

import java.util.List;

public class LoungeFragment extends Fragment {

    private FragmentLoungeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoungeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<MenuItem> menuItems = MenuItem.createDummyMenuItems();

        // 웰컴 드링크 첫 항목
        if (!menuItems.isEmpty()) {
            MenuItem welcome = menuItems.get(0);
            binding.tvWelcomeDrinkName.setText(welcome.getName());
            binding.tvWelcomeDrinkReason.setText(welcome.getDescription());
            binding.btnWelcomeLike.setOnClickListener(v -> welcome.setFeedback(MenuItem.FeedbackType.LIKE));
            binding.btnWelcomeDislike.setOnClickListener(v -> welcome.setFeedback(MenuItem.FeedbackType.DISLIKE));
        }

        // 디저트 첫 항목
        if (menuItems.size() > 1) {
            MenuItem dessert = menuItems.get(1);
            binding.tvDessertName.setText(dessert.getName());
            binding.tvDessertReason.setText(dessert.getDescription());
            binding.btnDessertLike.setOnClickListener(v -> dessert.setFeedback(MenuItem.FeedbackType.LIKE));
            binding.btnDessertDislike.setOnClickListener(v -> dessert.setFeedback(MenuItem.FeedbackType.DISLIKE));
        }

        // 전체 F&B 추천 보기
        binding.btnViewAllFnb.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_lounge_to_fnb_list));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
