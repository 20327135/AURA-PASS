package com.example.aura_pass2026.model;

import java.util.List;

public class MenuItem {
    private String id;
    private String name;
    private String category;        // 웰컴 드링크, 디저트
    private String description;
    private String recommendReason;
    private String imageUrl;
    private List<String> allergyInfo;   // 견과류 포함, 글루텐 없음 등
    private List<String> tags;          // 카페인 없음, 따뜻한 음료 등
    private boolean isCaffeineFree;
    private boolean isVeganFriendly;
    private String tempOption;          // 핫·아이스
    private String availableTime;       // 입장 후 15분 이내
    private FeedbackType feedback;

    public enum FeedbackType {
        NONE, LIKE, DISLIKE
    }

    public MenuItem() {}

    public MenuItem(String id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.feedback = FeedbackType.NONE;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRecommendReason() { return recommendReason; }
    public void setRecommendReason(String recommendReason) { this.recommendReason = recommendReason; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<String> getAllergyInfo() { return allergyInfo; }
    public void setAllergyInfo(List<String> allergyInfo) { this.allergyInfo = allergyInfo; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public boolean isCaffeineFree() { return isCaffeineFree; }
    public void setCaffeineFree(boolean caffeineFree) { isCaffeineFree = caffeineFree; }

    public boolean isVeganFriendly() { return isVeganFriendly; }
    public void setVeganFriendly(boolean veganFriendly) { isVeganFriendly = veganFriendly; }

    public String getTempOption() { return tempOption; }
    public void setTempOption(String tempOption) { this.tempOption = tempOption; }

    public String getAvailableTime() { return availableTime; }
    public void setAvailableTime(String availableTime) { this.availableTime = availableTime; }

    public FeedbackType getFeedback() { return feedback; }
    public void setFeedback(FeedbackType feedback) { this.feedback = feedback; }

    // 더미 데이터
    public static List<MenuItem> createDummyMenuItems() {
        List<MenuItem> list = new java.util.ArrayList<>();

        MenuItem m1 = new MenuItem("M001", "유자 얼 그레이 스파클링", "웰컴 드링크");
        m1.setDescription("퍼스널 컬러 봄·여름, 청량한 향 선호·무알코올 요청 반영");
        m1.setRecommendReason("상큼하고 감귤 계열과 플로럴 노트를 선호하시는 취향에 맞춰 선정되었습니다.");
        m1.setAllergyInfo(List.of());
        m1.setTags(List.of("카페인 없음", "무알코올"));
        m1.setCaffeineFree(true);
        m1.setTempOption("아이스");
        list.add(m1);

        MenuItem m2 = new MenuItem("M002", "말차 가나슈 타르트", "디저트");
        m2.setDescription("단맛 절제·일식 감성·글루텐 주의 버전 제공");
        m2.setRecommendReason("쓴맛과 단맛의 균형을 선호하시는 프로파일과 이전 방문 피드백 반영");
        m2.setAllergyInfo(List.of("글루텐 포함"));
        m2.setTags(List.of("스타일 선호 일치"));
        m2.setAvailableTime("입장 후 15분 이내");
        list.add(m2);

        MenuItem m3 = new MenuItem("M003", "유즈 웰컴 티 블렌드", "웰컴 드링크");
        m3.setDescription("잔잔한 플로럴 향을 선호하신다고 알려주셨고, 오늘 방문 목적인 휴식인 점을 반영해 선택한 메뉴입니다.");
        m3.setAllergyInfo(List.of("견과류 포함", "글루텐 없음"));
        m3.setTags(List.of("카페인 없음", "따뜻한 음료", "계절 한정"));
        m3.setCaffeineFree(true);
        m3.setVeganFriendly(true);
        m3.setTempOption("핫·아이스");
        m3.setAvailableTime("입장 후 15분 이내");
        list.add(m3);

        return list;
    }
}
