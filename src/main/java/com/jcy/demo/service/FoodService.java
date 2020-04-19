package com.jcy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcy.demo.annotation.DataSource;
import com.jcy.demo.constant.ContextConst;
import com.jcy.demo.domain.datasource_2.Food;
import com.jcy.demo.domain.datasource_2.repository.FoodRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;
    
    @DataSource(ContextConst.DataSourceType.DATASOURCE_2)
	@Transactional(rollbackFor = Exception.class)
    public Food getFoodByName(String name){
    	log.info("访问第二个数据源，获得爱吃的零食信息", name);
        return foodRepository.findByFoodName(name);
    }
}
