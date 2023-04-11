package com.example.aviator.rule.mapper;

import com.example.aviator.rule.entity.RuleInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: FSL
 * @date: 2023/4/7
 * @description: TODO
 */
public interface RuleInfoRepository extends JpaRepository<RuleInfoEntity,Long> {
    List<RuleInfoEntity> findByRulesetId(Long rulesetId);
    Page<RuleInfoEntity> findByRulesetId(Long rulesetId, Pageable pageable);

}
