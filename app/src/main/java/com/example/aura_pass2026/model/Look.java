package com.example.aura_pass2026.model;

import java.util.List;

public class Look {
    private String id;
    private String name;            // 미니멀 오피스 룩 등
    private String description;
    private List<Product> items;
    private String recommendReason;
    private String stylistMemo;
    private String savedDate;
    private String visitLocation;   // 피팅룸 A 등
    private String imageUrl;
    private List<String> tags;      // 워크, 오버사이즈, 미니... 등

    public Look() {}

    public Look(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Product> getItems() { return items; }
    public void setItems(List<Product> items) { this.items = items; }

    public String getRecommendReason() { return recommendReason; }
    public void setRecommendReason(String recommendReason) { this.recommendReason = recommendReason; }

    public String getStylistMemo() { return stylistMemo; }
    public void setStylistMemo(String stylistMemo) { this.stylistMemo = stylistMemo; }

    public String getSavedDate() { return savedDate; }
    public void setSavedDate(String savedDate) { this.savedDate = savedDate; }

    public String getVisitLocation() { return visitLocation; }
    public void setVisitLocation(String visitLocation) { this.visitLocation = visitLocation; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    // 더미 데이터
    public static List<Look> createDummyLooks() {
        List<Look> list = new java.util.ArrayList<>();

        Look l1 = new Look("L001", "미니멀 오피스 룩");
        l1.setDescription("재킷 + 슬랙스 + 토트백 조합");
        l1.setRecommendReason("퍼스널 컬러 모델, 보컬 선호 반영");
        l1.setSavedDate("2025.06.01");
        l1.setTags(List.of("워크", "오버사이즈", "미니"));
        list.add(l1);

        Look l2 = new Look("L002", "위크엔드 캐주얼 룩");
        l2.setDescription("니트 + 와이드 팬츠 + 숄더키 조합");
        l2.setRecommendReason("퍼스널 심쿵맛 선호, 주말 외출 맥락 반영");
        l2.setSavedDate("2025.05.28");
        list.add(l2);

        Look l3 = new Look("L003", "이브닝 세미포멀 룩");
        l3.setDescription("드레스 + 클러치 + 힐 조합");
        l3.setRecommendReason("저녁 디너 방문 맥락, 엘레강스 스타일 선호 반영");
        l3.setSavedDate("2025.05.20");
        list.add(l3);

        return list;
    }
}
