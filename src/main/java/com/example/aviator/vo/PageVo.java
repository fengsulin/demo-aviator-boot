package com.example.aviator.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author: FSL
 * @date: 2023/2/21
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageVo<T> implements Serializable {
    private static final long serialVersionUID = 2L;
    /**总数*/
    private long total;
    /**每页个数*/
    private long pageSize;
    /**总页数*/
    private long pages;
    /**当前页码*/
    private long currentPage;
    /**数据记录*/
    private List<T> items;
}
