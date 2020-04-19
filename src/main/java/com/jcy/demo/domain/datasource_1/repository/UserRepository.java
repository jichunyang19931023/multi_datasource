package com.jcy.demo.domain.datasource_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcy.demo.domain.datasource_1.User;


/**
 * @author jichunyang
 * @description 账号对象
 * @date 2020/3/10 9:56
 */
public interface UserRepository extends JpaRepository<User, Long>{

    /**
     * 获取相应账号的用户
     * @param account
     * @return
     */
    User findTopByAccount(String account);
    
}
