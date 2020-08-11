package com.shop.mapper;

import com.shop.pojo.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    List<Address> selectAddressList(Integer userId);

    Address selectAddressById(Integer id);

    int insertAddress(Address address);

    int updateAddress(Address address);

    int deleteAddress(Integer id);

}
