package com.example.aura_pass2026.ui.lookbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.aura_pass2026.databinding.FragmentWishlistBinding;
import com.example.aura_pass2026.model.Product;
import com.example.aura_pass2026.ui.lookbook.adapter.WishlistAdapter;

import java.util.ArrayList;
import java.util.List;

public class WishlistFragment extends Fragment {

    private FragmentWishlistBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentWishlistBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Product> wishlist = getDummyWishlist();

        WishlistAdapter adapter = new WishlistAdapter(wishlist);
        binding.rvWishlist.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvWishlist.setAdapter(adapter);
    }

    private List<Product> getDummyWishlist() {
        List<Product> list = new ArrayList<>();

        Product p1 = new Product("W001", "크루즈 컬렉션 클래식 코트", "아우터", "블랙", "36");
        p1.setPrice(2450000);
        list.add(p1);

        Product p2 = new Product("W002", "실크 블렌드 이브닝 가운", "드레스", "네이비", "34");
        p2.setPrice(3200000);
        list.add(p2);

        Product p3 = new Product("W003", "제뉴인 레더 헤리티지 백", "가방", "버건디", "-");
        p3.setPrice(2890000);
        list.add(p3);

        Product p4 = new Product("W004", "클래식 펄 이어링 셋", "액세서리", "화이트 골드", "-");
        p4.setPrice(1150000);
        list.add(p4);

        Product p5 = new Product("W005", "레이어드 캐시미어 스웨터", "상의", "크림", "38");
        p5.setPrice(890000);
        list.add(p5);

        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
