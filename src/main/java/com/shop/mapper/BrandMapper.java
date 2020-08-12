package com.shop.mapper;

import com.shop.pojo.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BrandMapper {

    List<Brand> selectBrandList(Map<String, Object> map);

    int insertBrand(Brand brand);

    int deleteBrand(int id);

    Brand selectBrandById(Integer id);

    List<Brand> selectBrandByName(String brandName);

    int updateBrand(Brand brand);

    int brandAmount();
}
