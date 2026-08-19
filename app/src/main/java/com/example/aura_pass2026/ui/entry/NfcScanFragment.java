package com.example.aura_pass2026.ui.entry;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.aura_pass2026.R;
import com.example.aura_pass2026.databinding.FragmentNfcScanBinding;
import com.example.aura_pass2026.model.AppSession;
import com.example.aura_pass2026.model.Customer;

public class NfcScanFragment extends Fragment {

    private FragmentNfcScanBinding binding;
    private NfcAdapter nfcAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNfcScanBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nfcAdapter = NfcAdapter.getDefaultAdapter(requireContext());

        // 테스트용 버튼: 패스 인식됨 (실제 NFC 없이 플로우 테스트)
        binding.btnTestNfcRecognized.setOnClickListener(v -> onNfcRecognized("NFC_TAG_001"));

        // 수동 입장 버튼
        binding.btnManualEntry.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_nfc_to_manual_entry));
    }

    private void onNfcRecognized(String tagId) {
        // 더미 VIP 고객 로드
        Customer customer = Customer.createDummyVip();
        customer.setNfcTagId(tagId);
        customer.setNfcVerified(true);
        AppSession.getInstance().setCurrentCustomer(customer);
        AppSession.getInstance().setVipMode(true);

        // 고객 프로필 화면으로 이동
        Bundle args = new Bundle();
        args.putString("customerId", customer.getId());
        Navigation.findNavController(requireView())
                .navigate(R.id.action_nfc_to_profile, args);
    }

    @Override
    public void onResume() {
        super.onResume();
        enableNfcForegroundDispatch();
    }

    @Override
    public void onPause() {
        super.onPause();
        disableNfcForegroundDispatch();
    }

    private void enableNfcForegroundDispatch() {
        if (nfcAdapter == null || !nfcAdapter.isEnabled()) return;
        Intent intent = new Intent(requireActivity(), requireActivity().getClass())
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                requireActivity(), 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
        nfcAdapter.enableForegroundDispatch(requireActivity(), pendingIntent, null, null);
    }

    private void disableNfcForegroundDispatch() {
        if (nfcAdapter == null) return;
        nfcAdapter.disableForegroundDispatch(requireActivity());
    }

    /** Activity에서 호출 — NFC 태그 감지 시 */
    public void onNfcTagDiscovered(Tag tag) {
        if (tag != null) {
            onNfcRecognized(tag.getId() != null ? bytesToHex(tag.getId()) : "UNKNOWN");
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02X", b));
        return sb.toString();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
