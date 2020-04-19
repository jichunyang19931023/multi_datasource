package com.jcy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcy.demo.domain.datasource_1.User;
import com.jcy.demo.domain.datasource_1.repository.UserRepository;

import cn.hutool.core.util.StrUtil;

/**
 * @Decription: 账号服务
 **/
@Service
public class UserService {
	
    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public User findByAccount(String account){
    	if(StrUtil.isBlank(account)){
    		return null;
    	}
    	User u = userRepository.findTopByAccount(account);
    	return u;
    }
    
}
