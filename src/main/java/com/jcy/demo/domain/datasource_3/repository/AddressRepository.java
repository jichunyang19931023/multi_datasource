package com.jcy.demo.domain.datasource_3.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jcy.demo.domain.datasource_3.Address;

/**
 * @Author: ChangXuan
 * @Decription:
 * @Date: 15:12 2020/3/18
 **/
public interface AddressRepository extends JpaRepository<Address, Long>{
	
	Address findByIdAndAddressIsNotNull(Long id);
}