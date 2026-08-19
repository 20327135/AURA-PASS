package com.example.aura_pass2026.model;

public class ConsentSettings {
    private boolean nfcIdentification;     // NFC 식별 동의
    private boolean purchaseHistoryUse;    // 구매 이력 활용 동의
    private boolean imageAnalysis;         // 이미지 분석 동의
    private boolean foodInfoUse;           // 식이 정보 활용 동의
    private String lastChangedDate;
    private int totalConsentCount;

    public ConsentSettings() {
        // 기본값: 전체 동의
        this.nfcIdentification = true;
        this.purchaseHistoryUse = true;
        this.imageAnalysis = true;
        this.foodInfoUse = true;
    }

    public int getConsentedCount() {
        int count = 0;
        if (nfcIdentification) count++;
        if (purchaseHistoryUse) count++;
        if (imageAnalysis) count++;
        if (foodInfoUse) count++;
        return count;
    }

    public int getTotalCount() { return 4; }

    // Getters & Setters
    public boolean isNfcIdentification() { return nfcIdentification; }
    public void setNfcIdentification(boolean nfcIdentification) { this.nfcIdentification = nfcIdentification; }

    public boolean isPurchaseHistoryUse() { return purchaseHistoryUse; }
    public void setPurchaseHistoryUse(boolean purchaseHistoryUse) { this.purchaseHistoryUse = purchaseHistoryUse; }

    public boolean isImageAnalysis() { return imageAnalysis; }
    public void setImageAnalysis(boolean imageAnalysis) { this.imageAnalysis = imageAnalysis; }

    public boolean isFoodInfoUse() { return foodInfoUse; }
    public void setFoodInfoUse(boolean foodInfoUse) { this.foodInfoUse = foodInfoUse; }

    public String getLastChangedDate() { return lastChangedDate; }
    public void setLastChangedDate(String lastChangedDate) { this.lastChangedDate = lastChangedDate; }

    public void setTotalConsentCount(int totalConsentCount) { this.totalConsentCount = totalConsentCount; }
}
