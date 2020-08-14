package com.shop.mapper;

import com.shop.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMapper {

    Integer registerAdmin(Admin admin);

    List<Admin> selectAdminList(Map<String, Object> map);

    int adminAmount();

    Admin selectAdminByName(String adminName);

    int updateAdmin(Admin admin);

    int deleteAdmin(Integer id);

    Admin selectAdminById(Integer id);
}
