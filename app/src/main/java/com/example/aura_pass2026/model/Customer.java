package com.example.aura_pass2026.model;

import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String grade;           // VIP, 일반
    private int visitYears;
    private String nfcTagId;
    private List<String> stylePreferences;   // 미니멀, 모노톤, 오버사이즈 등
    private List<String> brandPreferences;
    private String personalColor;   // 쿨·여름 등
    private PurchaseHistory purchaseHistory;
    private boolean isNfcVerified;

    public Customer() {}

    public Customer(String id, String name, String grade, int visitYears) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.visitYears = visitYears;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public int getVisitYears() { return visitYears; }
    public void setVisitYears(int visitYears) { this.visitYears = visitYears; }

    public String getNfcTagId() { return nfcTagId; }
    public void setNfcTagId(String nfcTagId) { this.nfcTagId = nfcTagId; }

    public List<String> getStylePreferences() { return stylePreferences; }
    public void setStylePreferences(List<String> stylePreferences) { this.stylePreferences = stylePreferences; }

    public List<String> getBrandPreferences() { return brandPreferences; }
    public void setBrandPreferences(List<String> brandPreferences) { this.brandPreferences = brandPreferences; }

    public String getPersonalColor() { return personalColor; }
    public void setPersonalColor(String personalColor) { this.personalColor = personalColor; }

    public PurchaseHistory getPurchaseHistory() { return purchaseHistory; }
    public void setPurchaseHistory(PurchaseHistory purchaseHistory) { this.purchaseHistory = purchaseHistory; }

    public boolean isNfcVerified() { return isNfcVerified; }
    public void setNfcVerified(boolean nfcVerified) { isNfcVerified = nfcVerified; }

    public boolean isVip() {
        return "VIP".equals(grade);
    }

    // 더미 데이터 생성 (테스트용)
    public static Customer createDummyVip() {
        Customer c = new Customer("KI001", "김지윤", "VIP", 5);
        c.setNfcTagId("NFC_TAG_001");
        c.setNfcVerified(true);
        c.setPersonalColor("쿨·여름");
        c.setStylePreferences(List.of("미니멀", "모노톤", "오버사이즈"));
        c.setBrandPreferences(List.of("브랜드A", "브랜드B", "브랜드C"));
        PurchaseHistory history = new PurchaseHistory();
        history.setLastPurchaseDate("2025년 5월 3일");
        history.setTotalCount(18);
        history.setMainCategory("아우터, 백, 슈즈");
        c.setPurchaseHistory(history);
        return c;
    }
}
