package com.shop.service;

import com.shop.pojo.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> selectBrandList(String name);

    int insertBrand(Brand brand);

    int deleteBrand(String id);

    List<Brand> selectBrandByName(String brandName);

    int brandAmount();

    int updateBrand(Brand brand);
}
