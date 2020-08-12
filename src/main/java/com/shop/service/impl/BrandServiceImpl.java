package com.shop.service.impl;

import com.shop.dto.BrandDTO;
import com.shop.mapper.BrandMapper;
import com.shop.pojo.Brand;
import com.shop.service.BrandService;
import com.shop.utils.ConstantUtil;
import com.shop.utils.RegexUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Resource
    private BrandMapper brandMapper;

    private final int SUCCESS = 1;

    private final int FAILED = 0;

    @Override
    public List<Brand> selectBrandList(BrandDTO brandDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("brandName", brandDTO.getBrandName());
        map.put("pageIndex", brandDTO.getPageIndex());
        map.put("pageSize", brandDTO.getPageSize());
        return brandMapper.selectBrandList(map);
    }

    @Override
    public int insertBrand(Brand brand) {
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

    @Override
    public int brandAmount() {
        return brandMapper.brandAmount();
    }

    @Override
    public int updateBrand(Brand brand) {
        if (brand.getId() == null)
            return ConstantUtil.FAILED;
        return brandMapper.updateBrand(brand);
    }
}
