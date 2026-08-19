package com.example.aura_pass2026.ui.fitting;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.databinding.FragmentProductSearchBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.Product;
import com.example.aura_pass2026.ui.fitting.adapter.ProductSearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductSearchFragment extends Fragment {

    private FragmentProductSearchBinding binding;
    private ProductSearchAdapter adapter;
    private final List<Product> searchResults = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProductSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 더미 검색 결과 초기 로드
        searchResults.addAll(getDummySearchResults());

        adapter = new ProductSearchAdapter(searchResults, product -> {
            // 피팅 목록에 추가
            List<Product> fitting = AppSession.getInstance().getFittingProducts();
            if (fitting != null) {
                product.setRecognitionType(Product.RecognitionType.SEARCH);
                fitting.add(product);
            }
            Navigation.findNavController(requireView())
                    .navigate(R.id.action_search_to_product_list);
        });
        binding.rvSearchResults.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvSearchResults.setAdapter(adapter);

        // 검색어 입력
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterResults(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // 피팅 상품 목록으로
        binding.btnGoToFittingList.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_search_to_product_list));
    }

    private void filterResults(String query) {
        List<Product> all = getDummySearchResults();
        searchResults.clear();
        for (Product p : all) {
            if (query.isEmpty() || p.getName().contains(query) || p.getColor().contains(query)) {
                searchResults.add(p);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private List<Product> getDummySearchResults() {
        List<Product> list = new ArrayList<>();
        Product p1 = new Product("S001", "울 오버사이즈 코트", "아우터", "아이보리", "M");
        p1.setBrand("브랜드 A"); p1.setSku("SKU 10-2847-M");
        list.add(p1);
        Product p2 = new Product("S002", "레더 숄더백", "가방", "블랙", "ONE");
        p2.setBrand("브랜드 B"); p2.setSku("SKU 22-1093-OS");
        list.add(p2);
        Product p3 = new Product("S003", "실크 플리츠 스커트", "하의", "네이비", "S");
        p3.setBrand("브랜드 A"); p3.setSku("SKU 10-3361-S");
        list.add(p3);
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
