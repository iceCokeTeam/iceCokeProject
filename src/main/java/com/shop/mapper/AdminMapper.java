package com.shop.mapper;

import com.shop.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {

    Integer registerAdmin(Admin admin);

    List<Admin> selectAdminList();

    Integer countAdmin();

    Admin selectAdminByName(String adminName);

    int updateAdmin(Admin admin);

    int deleteAdmin(Integer id);
}
