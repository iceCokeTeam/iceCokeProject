package com.shop.service.impl;

import com.shop.dto.ProductDTO;
import com.shop.mapper.ProductMapper;
import com.shop.pojo.Product;
import com.shop.service.ProductService;
import com.shop.utils.ConstantUtil;
import com.shop.utils.RegexUtil;
import com.shop.utils.TimeUtil;
import com.shop.vo.ProductVO;
import org.apache.tomcat.util.bcel.Const;
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
    public int insertProduct(ProductDTO productDTO) {
        if (productDTO.getProductName() == null || !RegexUtil.isDigital(productDTO.getPrice()))
            return ConstantUtil.FAILED;
        Map<String, Object> map = new HashMap<>();
        map.put("productName", productDTO.getProductName());
        if (productMapper.selectProductList(map).size() != 0)
            return ConstantUtil.FAILED;
        Product product = transformProduct(productDTO);
        product.setCreateTime(new Date());
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
    public int updateProduct(ProductDTO productDTO) {
        if (productDTO.getId() == null)
            return ConstantUtil.FAILED;
        Product product = transformProduct(productDTO);
        return productMapper.updateProduct(product);
    }

    @Override
    public int productAmount(ProductDTO productDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("productName", productDTO.getProductName());
        map.put("status", productDTO.getStatus());
        map.put("brandId", productDTO.getBrandId());
        map.put("categoryId", productDTO.getCategoryId());
        map.put("subtitle", productDTO.getSubtitle());
        return productMapper.productAmount(map);
    }

    @Override
    public ProductVO selectProductById(String id) {
        if(!RegexUtil.isDigital(id))
            return null;
        Product product = productMapper.selectProductById(Integer.valueOf(id));
        if (product == null){
            return null;
        }
        ProductVO productVO = transformProductVO(product);
        return productVO;
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
        productVO.setCreateTime(TimeUtil.dateFormat(product.getCreateTime()));
        productVO.setUpdateTime(TimeUtil.dateFormat(product.getUpdateTime()));
        return productVO;
    }

    public Product transformProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setProductName(productDTO.getProductName());
        product.setBrandId(Integer.valueOf(productDTO.getBrandId()));
        product.setCategoryId(Integer.valueOf(productDTO.getCategoryId()));
        product.setProductImg(productDTO.getProductImg());
        product.setSubtitle(productDTO.getSubtitle());
        product.setIntroduce(productDTO.getIntroduce());
        product.setPrice(Double.valueOf(productDTO.getPrice()));
        product.setStock(Integer.valueOf(productDTO.getStock()));
        product.setMeasure(productDTO.getMeasure());
        product.setStatus(Integer.valueOf(productDTO.getStatus()));
        product.setUpdateTime(new Date());
        return product;
    }
}
