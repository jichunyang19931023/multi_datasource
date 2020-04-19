package com.jcy.demo.domain.datasource_3;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @Decription: 地址表
 **/
@Entity
@Data
@ApiModel(value = "Address", description = "地址表")
@Table(name = "address")
public class Address {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键自增列")
    @Column(name = "id", columnDefinition="bigint COMMENT '主键自增列'")
    private Long id;

    @Lob
    @ApiModelProperty(value = "address")
    @Column(name = "address", columnDefinition="longtext COMMENT 'address'")
    private String address;
}
