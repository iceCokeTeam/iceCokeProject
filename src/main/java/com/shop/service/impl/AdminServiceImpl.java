package com.shop.service.impl;

import com.shop.mapper.AdminMapper;
import com.shop.pojo.Admin;
import com.shop.service.AdminService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    // 管理员添加
    @Override
    public Integer registerAdmin(Admin admin) {
        Md5Hash hash = new Md5Hash(admin.getPassword(), "abc", 1024);
        admin.setPassword(hash.toHex());
        admin.setCreateTime(new Date());
        return adminMapper.registerAdmin(admin);
    }

    // 获取管理员列表
    @Override
    public List<Admin> selectAdminList() {
        return adminMapper.selectAdminList();
    }

    // 获取管理员数
    @Override
    public Integer countAdmin() {
        return adminMapper.countAdmin();
    }

    @Override
    public Admin selectAdminByName(String adminName) {
        return adminMapper.selectAdminByName(adminName);
    }
}
