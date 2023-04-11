package com.example.aviator.rule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

/**
 * <p>
 * 规则表
 * </p>
 *
 * @author FSL
 * @since 2023-04-07
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "t_rule_info",indexes = {
        @Index(name = "tri_ruleset_id_IDX",columnList = "ruleset_id")
})
@DynamicInsert
@DynamicUpdate
public class RuleInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) unsigned")
    private Long id;

    /**
     * 所属规则集id
     */
    @Column(name = "ruleset_id",nullable = false,columnDefinition = "bigint(20)")
    @NotNull(message = "rulesetId can not be null")
    private Long rulesetId;

    /**
     * 规则名称
     */
    @Column(name = "name",nullable = false,columnDefinition = "varchar(32)")
    @NotBlank(message = "name can not be blank")
    private String name;

    /**
     * 规则备注
     */
    @Column(name = "remark",columnDefinition = "varchar(100)")
    private String remark;

    /**
     * 规则匹配成功后的动作
     */
    @Column(name = "action",nullable = false,columnDefinition = "varchar(128)")
    @NotBlank(message = "action can not be blank")
    private String action;

    /**
     * 规则是否启用，1-启用，0-禁用
     */
    @Column(name = "state",nullable = false,columnDefinition = "tinyint(4) default 0")
    private Integer state;

}
