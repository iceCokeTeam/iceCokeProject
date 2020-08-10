package com.shop.service;

import com.shop.dto.ProductDTO;
import com.shop.pojo.Product;
import com.shop.vo.ProductVO;

import java.util.List;

public interface ProductService {
    List<ProductVO> selectProductList(ProductDTO productDTO) throws Exception;

    int insertProduct(Product product);

    int deleteProduct(String id);

    int updateProduct(Product product);
}
