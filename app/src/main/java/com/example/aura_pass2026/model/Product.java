package com.example.aura_pass2026.model;

public class Product {
    private String id;
    private String name;
    private String category;    // 아우터, 상의, 하의, 가방, 슈즈, 액세서리
    private String color;
    private String size;
    private String brand;
    private String sku;
    private long price;
    private String imageUrl;
    private String material;
    private String origin;
    private String season;
    private RecognitionType recognitionType;

    public enum RecognitionType {
        RFID,       // RFID 자동 인식
        MANUAL,     // 수동 추가
        SEARCH      // 검색으로 추가
    }

    public Product() {}

    public Product(String id, String name, String category, String color, String size) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.color = color;
        this.size = size;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public long getPrice() { return price; }
    public void setPrice(long price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }

    public RecognitionType getRecognitionType() { return recognitionType; }
    public void setRecognitionType(RecognitionType recognitionType) { this.recognitionType = recognitionType; }

    public String getRecognitionLabel() {
        if (recognitionType == RecognitionType.RFID) return "RFID 인식";
        if (recognitionType == RecognitionType.MANUAL) return "수동 추가";
        return "검색 추가";
    }

    // 더미 데이터
    public static java.util.List<Product> createDummyFittingProducts() {
        java.util.List<Product> list = new java.util.ArrayList<>();

        Product p1 = new Product("P001", "실크 드레이프 블라우스", "상의", "아이보리", "S");
        p1.setBrand("브랜드A"); p1.setSku("10-2234-S");
        p1.setRecognitionType(RecognitionType.RFID);
        list.add(p1);

        Product p2 = new Product("P002", "레더 숄더백", "가방", "블랙", "ONE");
        p2.setBrand("브랜드B"); p2.setSku("22-1093-OS");
        p2.setRecognitionType(RecognitionType.RFID);
        list.add(p2);

        Product p3 = new Product("P003", "울 와이드 팬츠", "하의", "네이비", "M");
        p3.setBrand("브랜드A"); p3.setSku("10-3312-M");
        p3.setRecognitionType(RecognitionType.RFID);
        list.add(p3);

        Product p4 = new Product("P004", "골드 체인 네클리스", "액세서리", "골드", "ONE");
        p4.setBrand("브랜드C"); p4.setSku("33-0021-G");
        p4.setRecognitionType(RecognitionType.MANUAL);
        list.add(p4);

        return list;
    }
}
