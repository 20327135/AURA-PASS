package com.example.aura_pass2026.model;

public class PurchaseHistory {
    private String lastPurchaseDate;
    private int totalCount;
    private String mainCategory;
    private String avgAmount;   // 권한 제한 시 "*** 만원"

    public PurchaseHistory() {}

    public String getLastPurchaseDate() { return lastPurchaseDate; }
    public void setLastPurchaseDate(String lastPurchaseDate) { this.lastPurchaseDate = lastPurchaseDate; }

    public int getTotalCount() { return totalCount; }
    public void setTotalCount(int totalCount) { this.totalCount = totalCount; }

    public String getMainCategory() { return mainCategory; }
    public void setMainCategory(String mainCategory) { this.mainCategory = mainCategory; }

    public String getAvgAmount() { return avgAmount; }
    public void setAvgAmount(String avgAmount) { this.avgAmount = avgAmount; }
}
