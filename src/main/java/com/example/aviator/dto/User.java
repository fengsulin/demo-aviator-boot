package com.example.aviator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: FSL
 * @date: 2023/4/6
 * @description: 用于测试测试的实体类
 */
@Data
public class User implements Serializable {
    private String id;
    private String username;
    private Integer age;
    @JsonFormat(locale = "zh",shape = JsonFormat.Shape.STRING,timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
