package com.shop.mapper;

import com.shop.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    List<Product> selectProductList(Map<String, Object> map);

    int insertProduct(Product product);

    int deleteProduct(Integer id);

    int updateProduct(Product product);

    Product selectProductById(Integer id);

    int productAmount(Map<String, Object> map);

}
