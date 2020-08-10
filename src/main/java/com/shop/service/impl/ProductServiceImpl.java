package com.shop.service.impl;

import com.shop.dto.ProductDTO;
import com.shop.mapper.ProductMapper;
import com.shop.pojo.Product;
import com.shop.service.ProductService;
import com.shop.utils.RegexUtil;
import com.shop.vo.ProductVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public List<ProductVO> selectProductList(ProductDTO productDTO) throws Exception {
        Map<String, Object> map = setSelect(productDTO);
        List<Product> products = productMapper.selectProductList(map);
        List<ProductVO> productVOs = new ArrayList<>();
        for (Product product : products) {
            productVOs.add(transformProductVO(product));
        }
        return productVOs;
    }

    @Override
    public int insertProduct(Product product) {
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        if (product.getIntroduce() == null || product.getMeasure() == null || product.getProductImg() == null || product.getStatus() == null || product.getProductName() == null || product.getSubtitle() == null || product.getCategoryId() == null || product.getBrandId() == null || product.getPrice() == null || product.getStock() == null)
            return 0;
        Map<String, Object> map = new HashMap<>();
        map.put("productName", product.getProductName());
        if (productMapper.selectProductList(map).size() != 0)
            return 0;
        return productMapper.insertProduct(product);
    }

    @Override
    public int deleteProduct(String id) {
        if (RegexUtil.isDigital(id)) {
            return productMapper.deleteProduct(Integer.valueOf(id));
        }
        return 0;
    }

    @Override
    public int updateProduct(Product product) {
        if (product.getId() == null)
            return 0;
        return productMapper.updateProduct(product);
    }

    public Map<String, Object> setSelect(ProductDTO productDTO) throws Exception {
        Class cls = productDTO.getClass();
        Field[] fields = cls.getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        for (Field field : fields) {
            String str = field.getName();
            Method m = productDTO.getClass().getMethod("get" + str.substring(0, 1).toUpperCase() + str.substring(1));
            Object value = m.invoke(productDTO);
            if (str.equals("introduce") || str.equals("updateTime") || str.equals("category") || str.equals("brand") || value == null)
                continue;
            map.put(str, value);
        }
        if (map.get("pageIndex") == null)
            map.put("pageIndex", 1);
        if (map.get("pageSize") == null)
            map.put("pageSize", 5);
        if (map.get("priceSort") == null)
            map.put("priceSort", "desc");
        if (map.get("stockSort") == null)
            map.put("stockSort", "desc");
        if (map.get("createTimeSort") == null)
            map.put("createTimeSort", "desc");
        return map;
    }

    public ProductVO transformProductVO(Product product) {
        ProductVO productVO = new ProductVO();
        productVO.setCategory(product.getCategory());
        productVO.setBrand(product.getBrand());
        productVO.setId(product.getId());
        productVO.setProductName(product.getProductName());
        productVO.setProductImg(product.getProductImg());
        productVO.setSubtitle(product.getSubtitle());
        productVO.setIntroduce(product.getIntroduce());
        productVO.setPrice(product.getPrice());
        productVO.setStock(product.getStock());
        productVO.setMeasure(product.getMeasure());
        productVO.setStatus(product.getStatus());
        productVO.setCreateTime(product.getCreateTime());
        productVO.setUpdateTime(product.getUpdateTime());
        return productVO;
    }
}
