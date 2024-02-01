package com.batch.dataprocessor.dataproccessor.model;

import java.util.Objects;

public class ProductDataDTO {

    private Long productId;

    private String title;

    private String description;

    private Double price;

    private Double discount;

    private Double discountedPrice;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {

            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }
        ProductDataDTO that = (ProductDataDTO) o;
        return Objects.equals(productId, that.productId)
                && Objects.equals(title, that.title)
                && Objects.equals(description, that.description)
                && Objects.equals(price, that.price)
                && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, title, description, price, discount);
    }

    @Override
    public String toString() {
        return "ProductDataDTO{" +
                "productId=" + productId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", discountedPrice=" + discountedPrice +
                '}';
    }
}
