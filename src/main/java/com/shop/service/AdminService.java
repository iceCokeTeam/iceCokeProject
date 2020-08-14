package com.shop.service;

import com.shop.dto.AdminDTO;
import com.shop.pojo.Admin;
import com.shop.vo.AdminVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    Integer registerAdmin(Admin admin);

    List<AdminVO> selectAdminList(AdminDTO adminDTO);

    AdminVO selectAdminByName(String adminName);

    int updateAdmin(AdminDTO adminDTO);

    int deleteAdmin(String id);

    int updateLoginTime(Integer id);

    Admin selectAdminName(String adminName);

    int adminAmount();

    Admin selectAdminById(Integer id);
}
