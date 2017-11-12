package com.umeshcydv.redmartdemoapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by umeshcydv on 11/11/17.
 */

public class Product {

    @SerializedName("category_tags")
    private List<String> categoryTags;

    private Number id;

    private String title;

    @SerializedName("desc")
    private String description;

    private String sku;

    @SerializedName("img")
    private ImageInfo image;

    private List<ImageInfo> images;

    private PricingInfo pricing;

    @SerializedName("description_fields")
    private Map<String, List<ProductDesc>> productDescMap;

    private int pr;

    public List<String> getCategoryTags() {
        return categoryTags;
    }

    public void setCategoryTags(List<String> categoryTags) {
        this.categoryTags = categoryTags;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public ImageInfo getImage() {
        return image;
    }

    public void setImage(ImageInfo image) {
        this.image = image;
    }

    public List<ImageInfo> getImages() {
        return images;
    }

    public void setImages(List<ImageInfo> images) {
        this.images = images;
    }

    public PricingInfo getPricing() {
        return pricing;
    }

    public void setPricing(PricingInfo pricing) {
        this.pricing = pricing;
    }

    public Map<String, List<ProductDesc>> getProductDescMap() {
        return productDescMap;
    }

    public void setProductDescMap(Map<String, List<ProductDesc>> productDescMap) {
        this.productDescMap = productDescMap;
    }

    public int getPr() {
        return pr;
    }

    public void setPr(int pr) {
        this.pr = pr;
    }

}
