package com.umeshcydv.redmartdemoapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by umeshcydv on 11/11/17.
 */

public class ListPageInfo {

    @SerializedName("products")
    private List<Product> products;

    private int total;

    private int page;

    @SerializedName("page_size")
    private int pageSize;

    @SerializedName("on_sale_count")
    private int onSaleCount;


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOnSaleCount() {
        return onSaleCount;
    }

    public void setOnSaleCount(int onSaleCount) {
        this.onSaleCount = onSaleCount;
    }
}
