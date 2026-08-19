package com.example.aura_pass2026.ui.fitting;

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
import com.example.aura_pass2026.databinding.FragmentFittingProductListBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.Product;
import com.example.aura_pass2026.ui.fitting.adapter.FittingProductListAdapter;

import java.util.List;

public class FittingProductListFragment extends Fragment {

    private FragmentFittingProductListBinding binding;
    private FittingProductListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFittingProductListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Product> products = AppSession.getInstance().getFittingProducts();
        if (products != null) {
            binding.tvRfidCount.setText("RFID 자동 인식 중   " + products.size() + "개 인식됨");
            adapter = new FittingProductListAdapter(products, position -> {
                products.remove(position);
                adapter.notifyItemRemoved(position);
                binding.tvRfidCount.setText("RFID 자동 인식 중   " + products.size() + "개 인식됨");
            });
            binding.rvFittingProducts.setLayoutManager(new LinearLayoutManager(requireContext()));
            binding.rvFittingProducts.setAdapter(adapter);
        }

        binding.tvAddBySearch.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_product_list_to_search));

        binding.btnGoToMirror.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_product_list_to_mirror));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
