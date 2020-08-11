package com.shop.service.impl;

import com.shop.dto.AdminDTO;
import com.shop.mapper.AdminMapper;
import com.shop.pojo.Admin;
import com.shop.service.AdminService;
import com.shop.utils.ConstantUtil;
import com.shop.utils.RegexUtil;
import com.shop.vo.AdminVO;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    // 管理员添加
    @Override
    public Integer registerAdmin(Admin admin) {
        if(admin.getAdminName() == null && admin.getPassword() == null && admin.getEmail() == null && admin.getNickName() == null && admin.getAdminImg() == null)
            return ConstantUtil.FAILED;
        if (adminMapper.selectAdminByName(admin.getAdminName()) != null)
            return ConstantUtil.FAILED;
        Md5Hash hash = new Md5Hash(admin.getPassword(), "abc", 1024);
        admin.setPassword(hash.toHex());
        admin.setCreateTime(new Date());
        return adminMapper.registerAdmin(admin);
    }

    // 获取管理员列表
    @Override
    public List<AdminVO> selectAdminList(AdminDTO adminDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", adminDTO.getId());
        map.put("adminName", adminDTO.getAdminName());
        map.put("nickName", adminDTO.getNickName());
        map.put("email", adminDTO.getEmail());
        map.put("status", adminDTO.getStatus());
        map.put("pageIndex", adminDTO.getPageIndex());
        map.put("pageSize", adminDTO.getPageSize());
        List<Admin> admins = adminMapper.selectAdminList(map);
        List<AdminVO> adminVOs = new ArrayList<>();
        for (Admin admin : admins) {
            adminVOs.add(transformAdminVO(admin));
        }
        return adminVOs;
    }

    @Override
    public AdminVO selectAdminByName(String adminName) {
        AdminVO adminVO = transformAdminVO(adminMapper.selectAdminByName(adminName));
        return adminVO;
    }

    @Override
    public int updateAdmin(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setNickName(adminDTO.getNickName());
        admin.setEmail(adminDTO.getEmail());
        admin.setStatus(adminDTO.getStatus());
        admin.setAdminImg(adminDTO.getAdminImg());
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public int deleteAdmin(String id) {
        if (RegexUtil.isDigital(id))
            return adminMapper.deleteAdmin(Integer.valueOf(id));
        return ConstantUtil.FAILED;
    }

    @Override
    public int updateLoginTime(Integer id) {
        Admin admin = new Admin();
        admin.setLoginTime(new Date());
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public Admin selectAdminName(String adminName) {
        return adminMapper.selectAdminByName(adminName);
    }

    public AdminVO transformAdminVO(Admin admin) {
        AdminVO adminVO = new AdminVO();
        adminVO.setAdminImg(admin.getAdminImg());
        adminVO.setAdminName(admin.getAdminName());
        adminVO.setEmail(admin.getEmail());
        adminVO.setNickName(admin.getNickName());
        adminVO.setStatus(admin.getStatus());
        adminVO.setId(admin.getId());
        return adminVO;
    }
}
