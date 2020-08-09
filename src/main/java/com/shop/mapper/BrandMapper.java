package com.shop.mapper;

import com.shop.pojo.Brand;

import java.util.List;

public interface BrandMapper {

    List<Brand> selectBrandList(String name);

    int insertBrand(Brand brand);

    int deleteBrand(int id);
}
