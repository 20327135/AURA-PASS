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
import com.example.aura_pass2026.databinding.FragmentFittingHomeBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.Product;
import com.example.aura_pass2026.ui.fitting.adapter.FittingProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class FittingHomeFragment extends Fragment {

    private FragmentFittingHomeBinding binding;
    private FittingProductAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFittingHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 더미 피팅 상품 로드
        if (AppSession.getInstance().getFittingProducts() == null) {
            AppSession.getInstance().setFittingProducts(Product.createDummyFittingProducts());
        }
        List<Product> products = AppSession.getInstance().getFittingProducts();

        // 인식 상품 수 표시
        binding.tvRecognizedCount.setText(products.size() + "개 상품 인식됨");

        // RecyclerView
        adapter = new FittingProductAdapter(products, productId ->
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_fitting_to_rfid));
        binding.rvRecognizedProducts.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvRecognizedProducts.setAdapter(adapter);

        // RFID 상세 버튼
        binding.btnViewRfid.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_fitting_to_rfid));

        // 피팅 상품 목록 버튼
        binding.btnViewProductList.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_fitting_to_product_list));

        // 피팅 시작하기
        binding.btnStartFitting.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_fitting_to_mirror));

        // 장애 상태 확인
        binding.tvCheckError.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_fitting_to_error));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
