package com.shop.service;

import com.shop.dto.BrandDTO;
import com.shop.pojo.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    List<Brand> selectBrandList(BrandDTO brandDTO);

    int insertBrand(Brand brand);

    int deleteBrand(String id);

    List<Brand> selectBrandByName(String brandName);

    int brandAmount(BrandDTO brandDTO);

    int updateBrand(Brand brand);
}
