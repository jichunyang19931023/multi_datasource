package com.jcy.demo.domain.datasource_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcy.demo.domain.datasource_2.Food;

/**
 * @Author: ChangXuan
 * @Decription:
 * @Date: 17:06 2020/3/18
 **/
public interface FoodRepository extends JpaRepository<Food, Long> {
    
    Food findByFoodName(String name);
}
