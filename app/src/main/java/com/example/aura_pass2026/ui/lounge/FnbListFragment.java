package com.example.aura_pass2026.ui.lounge;

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
import com.example.aura_pass2026.databinding.FragmentFnbListBinding;
import com.example.aura_pass2026.model.MenuItem;
import com.example.aura_pass2026.ui.lounge.adapter.MenuItemAdapter;

import java.util.List;

public class FnbListFragment extends Fragment {

    private FragmentFnbListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFnbListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<MenuItem> items = MenuItem.createDummyMenuItems();

        MenuItemAdapter adapter = new MenuItemAdapter(items, menuItem -> {
            Bundle args = new Bundle();
            args.putString("menuItemId", menuItem.getId());
            Navigation.findNavController(requireView())
                    .navigate(R.id.action_fnb_list_to_menu_detail, args);
        });
        binding.rvMenuItems.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvMenuItems.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
