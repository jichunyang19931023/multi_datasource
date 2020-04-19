package com.jcy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcy.demo.annotation.DataSource;
import com.jcy.demo.constant.ContextConst;
import com.jcy.demo.domain.datasource_3.Address;
import com.jcy.demo.domain.datasource_3.repository.AddressRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @Decription: 地址服务
 **/

@Service
@Slf4j
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    
    @DataSource(ContextConst.DataSourceType.DATASOURCE_3)
    @Transactional(rollbackFor = Exception.class)
    public Address saveAddress(Long id){
    	log.info("访问第三个数据源，获得地址信息后更新");
    	Address address = addressRepository.findByIdAndAddressIsNotNull(id);
    	address.setAddress("山东省烟台市");
        return addressRepository.save(address);
    }
    
}
