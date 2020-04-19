package com.jcy.demo.domain.vo;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author tianwy
 * @description 错误码说明
 * @date 2020/3/4 14:16
 */
@Slf4j
public class CodeMsgDesc {

    public String getClassFieldValue(String field) {
        Field tempField;
        String value = "";
        Class className = this.getClass();
        try {
            tempField = className.getDeclaredField(field);
            value = String.valueOf(tempField.get(this));
            return value;
        } catch (NoSuchFieldException e1) {
            log.error("获取映射消息时发生异常", e1);
            return "1";
        } catch (SecurityException e2) {
            log.error("获取映射消息时发生异常", e2);
            return "2";
        } catch (IllegalAccessException e3) {
            log.error("获取映射消息时发生异常", e3);
            return "3";
        }
    }

    // 公共信息
    public String Code_100 = "参数错误，请联系管理员";
    public String Code_200 = "成功";
    public String Code_500 = "系统错误，请联系技术人员";

    public String Code_401 = "用户名或密码错误";

}
