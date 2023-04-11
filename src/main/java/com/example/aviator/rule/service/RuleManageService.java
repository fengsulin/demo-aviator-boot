package com.example.aviator.rule.service;

import com.example.aviator.constants.RuleStateType;
import com.example.aviator.constants.RulesetModeType;
import com.example.aviator.processor.ExpressionBuildService;
import com.example.aviator.rule.AviatorEngine;
import com.example.aviator.rule.entity.ConditionInfoEntity;
import com.example.aviator.rule.entity.RuleInfoEntity;
import com.example.aviator.rule.entity.RulesetInfoEntity;
import com.example.aviator.rule.mapper.ConditionInfoRepository;
import com.example.aviator.rule.mapper.RuleInfoRepository;
import com.example.aviator.rule.mapper.RulesetInfoRepository;
import com.example.aviator.vo.ServiceException;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author: FSL
 * @date: 2023/4/10
 * @description: TODO
 */
@Service
@Slf4j
@Validated
public class RuleManageService {
    @Resource
    private RulesetInfoRepository rulesetInfoRepository;
    @Resource
    private RuleInfoRepository ruleInfoRepository;
    @Resource
    private ConditionInfoRepository conditionInfoRepository;
    @Resource
    private ExpressionBuildService expressionBuildService;
    @Resource
    private AviatorEngine aviatorEngine;

    public List<RulesetInfoEntity> findRulesetByState(Integer state){
        return rulesetInfoRepository.findByState(state);
    }
    public Page<RulesetInfoEntity> findRulesetInfoPage(Integer pageNumber,Integer pageSize){
        Pageable pageable = PageRequest.of(pageSize,pageNumber, Sort.Direction.DESC,"id");
        return rulesetInfoRepository.findAll(pageable);
    }

    public Page<RuleInfoEntity> findRuleInfoPage(Long rulesetId,Integer pageNumber,Integer pageSize){
        Pageable pageable = PageRequest.of(pageSize,pageNumber, Sort.Direction.DESC,"id");
        return ruleInfoRepository.findByRulesetId(rulesetId,pageable);
    }

    public Page<ConditionInfoEntity> findConditionInfoInfoPage(Long ruleId,Integer pageNumber,Integer pageSize){
        Pageable pageable = PageRequest.of(pageSize,pageNumber, Sort.Direction.DESC,"id");
        return conditionInfoRepository.findByRuleId(ruleId,pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    public RulesetInfoEntity saveRuleset( RulesetInfoEntity rulesetInfoEntity){
        RulesetInfoEntity byCode = rulesetInfoRepository.findByCode(rulesetInfoEntity.getCode());
        if (null != byCode && !byCode.getId().equals(rulesetInfoEntity.getId())) throw new ServiceException("ruleset code already exists");
        RulesetInfoEntity entity = rulesetInfoRepository.saveAndFlush(rulesetInfoEntity);
        refreshRuleset(rulesetInfoEntity.getId());
        return entity;
    }

    @Transactional(rollbackFor = Exception.class)
    public RuleInfoEntity saveRule( RuleInfoEntity ruleInfoEntity){
        RuleInfoEntity infoEntity = ruleInfoRepository.saveAndFlush(ruleInfoEntity);
        refreshRuleset(infoEntity.getRulesetId());
        return infoEntity;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<ConditionInfoEntity> saveConditionList(List<ConditionInfoEntity> conditionInfos){
        boolean samePriority = conditionInfos.stream()
                .map(ConditionInfoEntity::getPriority)
                .distinct()
                .count() < conditionInfos.size();
        if (samePriority) throw new ServiceException("rule下的condition的priority不能设置为相同");
        Long ruleId = conditionInfos.get(0).getRuleId();
//        conditionInfoRepository.deleteById(ruleId);
        List<ConditionInfoEntity> conditionInfoEntities = conditionInfoRepository.saveAllAndFlush(conditionInfos);
        Long rulesetId = ruleInfoRepository.findById(ruleId).get().getRulesetId();
        refreshRuleset(rulesetId);
        return conditionInfoEntities;
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeRuleset(@NotNull(message = "rulesetId不能为空") Long rulesetId){
        List<RuleInfoEntity> entities = ruleInfoRepository.findByRulesetId(rulesetId);
        if (entities != null && !entities.isEmpty()) throw new ServiceException("请先删掉ruleset下的rule");
        rulesetInfoRepository.deleteById(rulesetId);
        log.info("rulesetId:{}，删除成功",rulesetId);
        refreshRuleset(rulesetId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeForceRuleset(@NotNull(message = "rulesetId不能为空") Long rulesetId){
        List<RuleInfoEntity> byRulesetId = ruleInfoRepository.findByRulesetId(rulesetId);
        if (byRulesetId != null && !byRulesetId.isEmpty()){
            byRulesetId.stream().forEach(b -> removeRule(b.getId()));
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void removeRule(@NotNull(message = "ruleId不能为空") Long ruleId){
        conditionInfoRepository.deleteByRuleId(ruleId);
        ruleInfoRepository.deleteById(ruleId);
        refreshRuleset(ruleInfoRepository.findById(ruleId).get().getRulesetId());
        log.info("ruleId:{}，删除成功",ruleId);
    }
    @Transactional(rollbackFor = Exception.class)
    public void refreshRuleset(@NotNull(message = "rulesetId不能为空") Long rulesetId){
        RulesetInfoEntity rulesetInfoEntity = rulesetInfoRepository.findById(rulesetId)
                .orElseThrow(() -> new ServiceException("invalid param rulesetId:" + rulesetId));
        String rulesetExpression = expressionBuildService.buildRulesetExpression(rulesetId);
        if ((RuleStateType.DISABLE.getCode()) == (rulesetInfoEntity.getState())) return;
        if (StringUtils.hasText(rulesetExpression)){
            rulesetInfoEntity.setMode(RulesetModeType.BUILT.getCode());
            rulesetInfoEntity.setExpression(rulesetExpression);
            rulesetInfoRepository.saveAndFlush(rulesetInfoEntity);
            aviatorEngine.doCompile(String.valueOf(rulesetId),rulesetExpression);
            log.debug("["+rulesetId + "]:refresh completed");
            return;
        }
        rulesetInfoEntity.setMode(RulesetModeType.BUILDING.getCode());
        rulesetInfoEntity.setExpression(null);
        rulesetInfoRepository.saveAndFlush(rulesetInfoEntity);
        // 同步本地缓存的Expression
        aviatorEngine.delCacheByKey(String.valueOf(rulesetId));
    }
}
