package com.shop.service;

import com.shop.pojo.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    Integer registerAdmin(Admin admin);

    List<Admin> selectAdminList();

    Integer countAdmin();

    Admin selectAdminByName(String adminName);
}
