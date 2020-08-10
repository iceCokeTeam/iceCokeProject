package com.shop.mapper;

import com.shop.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    List<Product> selectProductList(Map<String, Object> map);
}
