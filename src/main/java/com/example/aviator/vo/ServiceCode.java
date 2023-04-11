package com.example.aviator.vo;

/**
 * @author: FSL
 * @date: 2023/2/24
 * @description: 响应码接口
 */
public interface ServiceCode {
    /**获取响应码*/
    int getCode();
    /**获取响应提示信息*/
    String getMessage();
}
