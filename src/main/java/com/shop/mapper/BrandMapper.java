package com.shop.mapper;

import com.shop.pojo.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {

    List<Brand> selectBrandList(String name);

    int insertBrand(Brand brand);

    int deleteBrand(int id);
}