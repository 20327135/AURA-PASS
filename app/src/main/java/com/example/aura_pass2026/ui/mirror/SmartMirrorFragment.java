package com.example.aura_pass2026.ui.mirror;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.databinding.FragmentSmartMirrorBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.Product;

import java.util.List;

public class SmartMirrorFragment extends Fragment {

    private FragmentSmartMirrorBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSmartMirrorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Product> products = AppSession.getInstance().getFittingProducts();
        if (products != null && !products.isEmpty()) {
            binding.tvFittingStatus.setText(products.size() + "개 피팅 상품");
        }

        // 체형·분석 동의
        binding.btnBodyConsent.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_mirror_to_consent));

        // AR 가상 착용
        binding.btnArTryOn.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_mirror_to_ar));

        // 개인화 추천
        binding.btnStyleRecommend.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_mirror_to_style_recommend));

        // 룩 비교
        binding.btnLookCompare.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_mirror_to_look_compare));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
