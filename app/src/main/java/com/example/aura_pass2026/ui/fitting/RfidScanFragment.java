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
import com.example.aura_pass2026.databinding.FragmentRfidScanBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.Product;
import com.example.aura_pass2026.ui.fitting.adapter.FittingProductAdapter;

import java.util.List;

public class RfidScanFragment extends Fragment {

    private FragmentRfidScanBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRfidScanBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Product> products = AppSession.getInstance().getFittingProducts();
        if (products != null) {
            binding.tvRfidActiveCount.setText("RFID 인식 활성   " + products.size() + "개 인식됨");

            FittingProductAdapter adapter = new FittingProductAdapter(products, id -> {});
            binding.rvRfidProducts.setLayoutManager(new LinearLayoutManager(requireContext()));
            binding.rvRfidProducts.setAdapter(adapter);
        }

        binding.btnAddManually.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_rfid_to_manual_search));

        binding.btnViewFittingList.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_rfid_to_product_list));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
