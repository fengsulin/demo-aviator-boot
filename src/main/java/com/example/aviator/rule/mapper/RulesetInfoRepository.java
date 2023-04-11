package com.example.aviator.rule.mapper;

import com.example.aviator.rule.entity.RulesetInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: FSL
 * @date: 2023/4/7
 * @description: TODO
 */
public interface RulesetInfoRepository extends JpaRepository<RulesetInfoEntity,Long> {
    RulesetInfoEntity findByCode(String code);
    List<RulesetInfoEntity> findByState(Integer ruleStateType);
}
