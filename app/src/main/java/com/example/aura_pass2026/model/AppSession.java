package com.example.aura_pass2026.model;

import java.util.List;

/**
 * 앱 세션 전체에서 공유하는 상태 (싱글톤)
 * Navigation 간 데이터 전달을 위한 세션 저장소
 */
public class AppSession {
    private static AppSession instance;

    private Customer currentCustomer;
    private boolean isVipMode;
    private List<Product> fittingProducts;
    private ConsentSettings consentSettings;
    private List<Look> savedLooks;
    private String currentStaffName;
    private String currentStaffRole;

    private AppSession() {
        consentSettings = new ConsentSettings();
    }

    public static AppSession getInstance() {
        if (instance == null) {
            instance = new AppSession();
        }
        return instance;
    }

    public void reset() {
        currentCustomer = null;
        isVipMode = false;
        fittingProducts = null;
        savedLooks = null;
    }

    // Getters & Setters
    public Customer getCurrentCustomer() { return currentCustomer; }
    public void setCurrentCustomer(Customer currentCustomer) { this.currentCustomer = currentCustomer; }

    public boolean isVipMode() { return isVipMode; }
    public void setVipMode(boolean vipMode) { isVipMode = vipMode; }

    public List<Product> getFittingProducts() { return fittingProducts; }
    public void setFittingProducts(List<Product> fittingProducts) { this.fittingProducts = fittingProducts; }

    public ConsentSettings getConsentSettings() { return consentSettings; }
    public void setConsentSettings(ConsentSettings consentSettings) { this.consentSettings = consentSettings; }

    public List<Look> getSavedLooks() { return savedLooks; }
    public void setSavedLooks(List<Look> savedLooks) { this.savedLooks = savedLooks; }

    public String getCurrentStaffName() { return currentStaffName; }
    public void setCurrentStaffName(String currentStaffName) { this.currentStaffName = currentStaffName; }

    public String getCurrentStaffRole() { return currentStaffRole; }
    public void setCurrentStaffRole(String currentStaffRole) { this.currentStaffRole = currentStaffRole; }
}
