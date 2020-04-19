package com.jcy.demo.domain.datasource_2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Decription:食物表
 **/

@Entity
@Data
@ApiModel(value = "Food", description = "食物")
@Table(name = "food")
public class Food {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键自增列")
    @Column(name = "id", columnDefinition="bigint COMMENT '主键自增列'")
    protected Long id;


    /**
     * 食物名称
     */
    @ApiModelProperty(value = "食物名称")
    @Column(name = "food_name", columnDefinition="bigint COMMENT '食物名称'")
    private String foodName;
}
