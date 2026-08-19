package com.example.aura_pass2026.ui.entry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.databinding.FragmentCustomerProfileBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.Customer;

public class CustomerProfileFragment extends Fragment {

    private FragmentCustomerProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCustomerProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Customer customer = AppSession.getInstance().getCurrentCustomer();
        if (customer != null) {
            bindCustomerData(customer);
        }

        // 스마트 피팅 홈으로 이동
        binding.btnGoToFitting.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_profile_to_fitting));
    }

    private void bindCustomerData(Customer customer) {
        // 고객 이름 및 등급
        binding.tvCustomerName.setText(customer.getName() + " 고객");
        binding.tvCustomerGrade.setText(customer.getGrade() + " 멤버 · " + customer.getVisitYears() + "년차");
        binding.tvNfcStatus.setText("NFC 인식 완료 · 방문 세션 활성");

        // 이니셜
        if (customer.getName() != null && !customer.getName().isEmpty()) {
            binding.tvCustomerInitial.setText(
                    String.valueOf(customer.getName().charAt(0)));
        }

        // 퍼스널 컬러
        if (customer.getPersonalColor() != null) {
            binding.tvPersonalColor.setText("퍼스널 컬러\n" + customer.getPersonalColor());
        }

        // 구매 이력
        if (customer.getPurchaseHistory() != null) {
            binding.tvLastPurchase.setText(customer.getPurchaseHistory().getLastPurchaseDate());
            binding.tvPurchaseCount.setText(customer.getPurchaseHistory().getTotalCount() + "회");
            binding.tvMainCategory.setText(customer.getPurchaseHistory().getMainCategory());
        }

        // 스타일 선호 (최대 3개 chip)
        if (customer.getStylePreferences() != null) {
            StringBuilder styles = new StringBuilder();
            for (int i = 0; i < Math.min(3, customer.getStylePreferences().size()); i++) {
                if (i > 0) styles.append("  ");
                styles.append(customer.getStylePreferences().get(i));
            }
            binding.tvStylePreferences.setText(styles.toString());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
