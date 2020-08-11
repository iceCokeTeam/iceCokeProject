package com.shop.service.impl;

import com.shop.mapper.BrandMapper;
import com.shop.pojo.Brand;
import com.shop.service.BrandService;
import com.shop.utils.RegexUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Resource
    private BrandMapper brandMapper;

    private final int SUCCESS = 1;

    private final int FAILED = 0;

    @Override
    public List<Brand> selectBrandList(String name) {
        return brandMapper.selectBrandList(name);
    }

    @Override
    public int insertBrand(Brand brand) {
        if (brand == null || brand.getLogo() == null || brand.getBrandName() == null || brand.getIntroduce() == null || brand.getBrandName() == "" || brand.getLogo() == "")
            return FAILED;
        if (brandMapper.selectBrandByName(brand.getBrandName()).size() != 0)
            return FAILED;
        return brandMapper.insertBrand(brand);
    }

    @Override
    public int deleteBrand(String id) {
        if (RegexUtil.isDigital(id)) {
            return brandMapper.deleteBrand(Integer.valueOf(id));
        }
        return FAILED;
    }

    @Override
    public List<Brand> selectBrandByName(String brandName) {
        return brandMapper.selectBrandByName(brandName);
    }
}
