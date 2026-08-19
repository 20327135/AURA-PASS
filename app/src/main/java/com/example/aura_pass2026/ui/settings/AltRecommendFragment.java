package com.example.aura_pass2026.ui.settings;

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
import com.example.aura_pass2026.databinding.FragmentAltRecommendBinding;
import com.example.aura_pass2026.model.Product;
import com.example.aura_pass2026.ui.fitting.adapter.FittingProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class AltRecommendFragment extends Fragment {

    private FragmentAltRecommendBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAltRecommendBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 인기 상품 TOP5 목록
        List<Product> popularProducts = getDummyPopularProducts();
        FittingProductAdapter adapter = new FittingProductAdapter(popularProducts, id -> {});
        binding.rvPopularProducts.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvPopularProducts.setAdapter(adapter);

        // 장애 안내 화면으로 돌아가기
        binding.btnBackToError.setOnClickListener(v -> requireActivity().onBackPressed());
    }

    private List<Product> getDummyPopularProducts() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("T001", "캐시미어 코트", "아우터", "카멜", "M"));
        list.add(new Product("T002", "레더 숄더백", "가방", "블랙", "ONE"));
        list.add(new Product("T003", "실크 블라우스", "상의", "화이트", "S"));
        list.add(new Product("T004", "골드 이어링", "액세서리", "골드", "ONE"));
        list.add(new Product("T005", "첼시 부츠", "슈즈", "블랙", "250"));
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
