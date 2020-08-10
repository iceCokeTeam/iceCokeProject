package com.shop.mapper;

import com.shop.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    Product selectProduct(Product product);
}
