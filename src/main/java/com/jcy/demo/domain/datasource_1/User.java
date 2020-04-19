package com.jcy.demo.domain.datasource_1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @description 用户信息
 */

@Entity
@Data
@ApiModel(value = "User", description = "用户")
@Table(name = "user_info")
@org.hibernate.annotations.Table(appliesTo = "user_info", comment = "用户表")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "主键")
	@Column(name = "id", unique = true, nullable = false, columnDefinition = "bigint COMMENT '主键自增列'")
	private String id;

	@NotBlank(message = "用户账号不能为空")
	@ApiModelProperty(value = "用户账号")
	@Column(name = "account", columnDefinition = "varchar(255) COMMENT '用户账号'")
	private String account;

	@ApiModelProperty(value = "用户名称")
	@Column(name = "username", columnDefinition = "varchar(255) COMMENT '用户名称'")
	private String username;

}
