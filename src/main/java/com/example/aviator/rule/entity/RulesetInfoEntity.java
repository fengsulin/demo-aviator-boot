package com.example.aviator.rule.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 规则集
 * </p>
 *
 * @author FSL
 * @since 2023-04-07
 */
@Entity
@Table(name = "t_ruleset_info",indexes = {
        @Index(name = "tri_code_IDX",columnList = "code")
})
@Data
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
public class RulesetInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) unsigned")
    private Long id;

    /**
     * 规则集编码（不同规则集，有不同的编码）
     */
    @Column(name = "code",nullable = false,columnDefinition = "varchar(32)")
    @NotBlank(message = "规则集编码不能为空")
    private String code;

    /**
     * 规则集名称
     */
    @Column(name = "name",nullable = false,columnDefinition = "varchar(50)")
    @NotBlank(message = "规则集名称不能为空")
    private String name;

    /**
     * 规则集备注
     */
    @Column(name = "remark",columnDefinition = "varchar(100)")
    private String remark;

    /**
     * 规则集表达式
     */
    @Column(name = "expression",columnDefinition = "varchar(2048)")
    private String expression;

    /**
     * 规则集状态,1-启用，0-禁用
     */
    @Column(name = "state",columnDefinition = "tinyint(4) not null default 1")
    private Integer state;

    /**
     * 规则集准备模式，1-已就绪，0-准备中
     */
    @Column(name = "mode",columnDefinition = "tinyint(4) not null default 0")
    private Integer mode;
    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false,columnDefinition = "datetime")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime createTime = LocalDateTime.now();

    /**
     * 更新时间
     */
    @Column(name = "update_time",columnDefinition = "datetime")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    private LocalDateTime updateTime = LocalDateTime.now();

}
