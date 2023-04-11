package com.example.aviator.rule.mapper;

import com.example.aviator.rule.entity.ConditionInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: FSL
 * @date: 2023/4/7
 * @description: TODO
 */
public interface ConditionInfoRepository extends JpaRepository<ConditionInfoEntity,Long> {
    List<ConditionInfoEntity> findByRuleId(Long ruleId);
    void deleteByRuleId(Long ruleId);
    Page<ConditionInfoEntity> findByRuleId(Long ruleId, Pageable pageable);
}
