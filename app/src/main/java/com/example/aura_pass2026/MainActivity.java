package com.example.aura_pass2026;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.aura_pass2026.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    // BottomNav를 숨길 Fragment ID 목록 (서브 화면들)
    private final Set<Integer> HIDDEN_BOTTOM_NAV_DESTINATIONS = new HashSet<>(Arrays.asList(
            R.id.nfcScanFragment,
            R.id.customerProfileFragment,
            R.id.guestModeFragment,
            R.id.rfidScanFragment,
            R.id.fittingProductListFragment,
            R.id.productSearchFragment,
            R.id.smartMirrorFragment,
            R.id.bodyAnalysisConsentFragment,
            R.id.arTryOnFragment,
            R.id.styleRecommendFragment,
            R.id.recommendProductDetailFragment,
            R.id.lookCompareFragment,
            R.id.fnbListFragment,
            R.id.menuDetailFragment,
            R.id.myLookbookFragment,
            R.id.lookDetailFragment,
            R.id.nextVisitSaveFragment,
            R.id.wishlistFragment,
            R.id.consentManageFragment,
            R.id.consentWithdrawFragment,
            R.id.serviceErrorFragment,
            R.id.altRecommendFragment
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // NavHostFragment에서 NavController 가져오기
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        // BottomNavigationView와 NavController 연결
        BottomNavigationView bottomNav = binding.bottomNavigation;
        NavigationUI.setupWithNavController(bottomNav, navController);

        // 서브 화면에서 BottomNav 숨기기
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (HIDDEN_BOTTOM_NAV_DESTINATIONS.contains(destination.getId())) {
                bottomNav.setVisibility(View.GONE);
            } else {
                bottomNav.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
