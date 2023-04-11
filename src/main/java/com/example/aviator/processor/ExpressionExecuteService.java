package com.example.aviator.processor;

import com.example.aviator.constants.RuleStateType;
import com.example.aviator.rule.AviatorEngine;
import com.example.aviator.rule.entity.RulesetInfoEntity;
import com.example.aviator.rule.service.RuleManageService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: FSL
 * @date: 2023/4/10
 * @description: aviator引擎执行入口
 */
@Component
@Slf4j
public class ExpressionExecuteService {

    @Resource
    private AviatorEngine aviatorEngine;
    @Resource
    private RuleManageService ruleManageService;

    /**
     * @description: 传入一个实体，查询数据库中启用的规则集，然后通过id获取本地缓存的Expression，执行判断
     * @author: FSL
     * @date: 2023/4/11
     * @param obj
     * @return: java.lang.Object，可通过返回的数据或标识执行对应的业务逻辑
     */
    public Object execute(Object obj){
        Set<Map.Entry<String, String>> entries = AviatorEngine.expressionMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            Map<String,String> objMap = (Map<String, String>) aviatorEngine.doMatch(key,entry.getValue(), obj);
            if (objMap != null && !objMap.isEmpty()) return objMap;
        }
        return null;
    }

    @PostConstruct
    public void init(){
        log.info("### 初始化加载所有启用的规则到缓存 ###");
        List<RulesetInfoEntity> entityList = ruleManageService.findRulesetByState(RuleStateType.ENABLE.getCode());
        entityList.stream().forEach(rulesetInfoEntity -> ruleManageService.refreshRuleset(rulesetInfoEntity.getId()));
        log.info("### {}：条规则集初始化加载完成 ###",entityList.size());
    }

}
