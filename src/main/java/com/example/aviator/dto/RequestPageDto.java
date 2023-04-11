package com.example.aviator.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: FSL
 * @date: 2023/4/10
 * @description: TODO
 */
@Data
public class RequestPageDto implements Serializable {
    private int page;
    private int size;
}
