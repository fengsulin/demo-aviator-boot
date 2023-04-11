package com.example.aviator.rule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 条件表
 * </p>
 *
 * @author FSL
 * @since 2023-04-07
 */
@Entity
@Table(name = "t_condition_info",indexes = {
        @Index(name = "tci_rule_id_IDX",columnList = "rule_id")
})
@Data
@Accessors(chain = true)
public class ConditionInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) unsigned")
    private Long id;

    /**
     * 所属规则id
     */
    @Column(name = "rule_id",nullable = false,columnDefinition = "bigint(20)")
    @NotNull(message = "ruleId can not be null")
    private Long ruleId;

    /**
     * 条件名称
     */
    @Column(name = "name",nullable = false,columnDefinition = "varchar(50)")
    @NotBlank(message = "条件名称不能为空")
    private String name;

    /**
     * 条件备注
     */
    @Column(name = "remark",columnDefinition = "varchar(100)")
    private String remark;

    /**
     * 参数名
     */
    @Column(name = "variable_name",nullable = false,columnDefinition = "varchar(32)")
    @NotBlank(message = "variableName can not be blank")
    private String variableName;

    /**
     * 参数值
     */
    @Column(name = "variable_value",nullable = false,columnDefinition = "varchar(32)")
    @NotBlank(message = "variableValue can not be blank")
    private String variableValue;

    /**
     * 条件关系运算符
     */
    @Column(name = "relation_type",nullable = false,columnDefinition = "varchar(32)")
    @NotBlank(message = "relationType can not be blank")
    private String relationType;

    /**
     * 条件逻辑运算符
     */
    @Column(name = "logic_type",nullable = false,columnDefinition = "varchar(32)")
    @NotBlank(message = "logicType can not be blank")
    private String logicType;

    /**
     * 条件优先级，值越大优先级越高，条件判断越靠前
     */
    @Column(name = "priority",nullable = false,columnDefinition = "smallint ")
    @NotNull(message = "priority can not be null")
    private Integer priority;

}
