package com.jcy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcy.demo.domain.datasource_1.User;
import com.jcy.demo.domain.datasource_2.Food;
import com.jcy.demo.domain.datasource_3.Address;
import com.jcy.demo.domain.vo.Ret;
import com.jcy.demo.service.AddressService;
import com.jcy.demo.service.FoodService;
import com.jcy.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "多数据源demo")
@RestController
@Slf4j
public class DemoController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private AddressService addressService;
	
	@Autowired
    private FoodService foodService;

	@ApiOperation("多数据源切换")
	@RequestMapping(value = "/changeDatasource")
    public Ret index() {
        Ret result = Ret.create();
        // 访问默认数据源
        User user = userService.findByAccount("喵");
        // 访问第二个数据源
        Food food = foodService.getFoodByName("鸡腿");
        // 访问第三个数据源
        Address address = addressService.saveAddress(1L);
        log.info("用户为:{},爱吃的零食为:{},更改后的地址为:{}", user.getUsername(), food.getFoodName(), address.getAddress());
        result.setCodeAndMsg(200);
        return result;
    }
}
