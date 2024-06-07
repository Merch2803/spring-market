package com.merch.spring.market.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ProductDTO implements Comparable<ProductDTO> {

    @EqualsAndHashCode.Include
    private Long id;

    private String title;
    private int price;

    @EqualsAndHashCode.Exclude
    private int count;

    @Override
    public int compareTo(ProductDTO o) {
        return (int) (this.id - o.getId());
    }
}
