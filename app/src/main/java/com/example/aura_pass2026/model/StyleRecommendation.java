package com.example.aura_pass2026.model;

import java.util.List;

public class StyleRecommendation {
    private String id;
    private String lookName;            // 오늘의 추천 룩 이름
    private String lookDescription;    // 룩 설명
    private List<Product> recommendedItems;
    private List<Product> accessoryRecommendations;
    private String recommendBasis;     // 퍼스널 컬러 기반, 스타일 선호 기반 등
    private String imageUrl;
    private FeedbackType feedback;
    private String userMemo;

    public enum FeedbackType {
        NONE, LIKE, SO_SO, DISLIKE
    }

    public StyleRecommendation() {
        this.feedback = FeedbackType.NONE;
    }

    public StyleRecommendation(String id, String lookName) {
        this.id = id;
        this.lookName = lookName;
        this.feedback = FeedbackType.NONE;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getLookName() { return lookName; }
    public void setLookName(String lookName) { this.lookName = lookName; }

    public String getLookDescription() { return lookDescription; }
    public void setLookDescription(String lookDescription) { this.lookDescription = lookDescription; }

    public List<Product> getRecommendedItems() { return recommendedItems; }
    public void setRecommendedItems(List<Product> recommendedItems) { this.recommendedItems = recommendedItems; }

    public List<Product> getAccessoryRecommendations() { return accessoryRecommendations; }
    public void setAccessoryRecommendations(List<Product> accessoryRecommendations) { this.accessoryRecommendations = accessoryRecommendations; }

    public String getRecommendBasis() { return recommendBasis; }
    public void setRecommendBasis(String recommendBasis) { this.recommendBasis = recommendBasis; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public FeedbackType getFeedback() { return feedback; }
    public void setFeedback(FeedbackType feedback) { this.feedback = feedback; }

    public String getUserMemo() { return userMemo; }
    public void setUserMemo(String userMemo) { this.userMemo = userMemo; }
}
