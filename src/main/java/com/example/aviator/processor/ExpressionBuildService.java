package com.example.aviator.processor;

import com.example.aviator.condition.Condition;
import com.example.aviator.condition.ConditionInstance;
import com.example.aviator.constants.ConditionLogicType;
import com.example.aviator.constants.ConditionRelationType;
import com.example.aviator.rule.entity.ConditionInfoEntity;
import com.example.aviator.rule.entity.RuleInfoEntity;
import com.example.aviator.rule.mapper.ConditionInfoRepository;
import com.example.aviator.rule.mapper.RuleInfoRepository;
import com.example.aviator.vo.ServiceException;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author: FSL
 * @date: 2023/4/10
 * @description: Expression表达式字符串构建
 */
@Component
@Slf4j
public class ExpressionBuildService {

    @Autowired
    private List<Condition> conditionList;

    @Resource
    private RuleInfoRepository ruleInfoRepository;
    @Resource
    private ConditionInfoRepository conditionInfoRepository;

    /**
     * @description: get condition bean by relationType
     * @author: FSL
     * @date: 2023/4/10
     * @param relationType
     * @return: com.example.aviator.condition.Condition
     */
    public Condition selectCondition(@NotNull ConditionRelationType relationType){
        return conditionList.stream()
                .filter(condition -> Arrays.asList(condition.relationTypes()).contains(relationType))
                .findFirst()
                .orElseThrow(() -> new ServiceException("No bean of type condition found for" + relationType));
    }

    /**
     * @description: build ruleset expression by rulesetId
     * @author: FSL
     * @date: 2023/4/10
     * @param rulesetId
     * @return: java.lang.String
     */
    public String buildRulesetExpression(@NotNull Long rulesetId){
        List<RuleInfoEntity> ruleInfoEntityList = ruleInfoRepository.findByRulesetId(rulesetId);
        if (ruleInfoEntityList == null || ruleInfoEntityList.isEmpty()){
            log.error("No rules under for the ruleset:{}",rulesetId);
            return null;
        }
        StringBuilder rulesetExp = new StringBuilder("let rMap = seq.map();\n");
        for (int i = 0;i < ruleInfoEntityList.size();i++){
            RuleInfoEntity ruleInfoEntity = ruleInfoEntityList.get(i);
            List<ConditionInfoEntity> conditionInfoEntityList = conditionInfoRepository.findByRuleId(ruleInfoEntity.getId());
            if (conditionInfoEntityList == null || conditionInfoEntityList.isEmpty()){
                log.error("No conditions under for the rule:{}",ruleInfoEntity.getId());
                return null;
            }
            String ruleExp = buildRuleExpression(conditionInfoEntityList);
            rulesetExp.append(i == 0 ? "if(" : "elsif(").append(ruleExp).append("){\n");
            Arrays.stream(ruleInfoEntity.getAction().split(","))
                    .map(v -> v.split(":"))
                    .forEach(a -> rulesetExp.append("seq.put(rMap,'").append(a[0]).append("', ").append(a[1]).append(");\n"));
            rulesetExp.append("}\n");
        }
        rulesetExp.append("return rMap;");
        return rulesetExp.toString();
    }

    /**
     * @description: build rule expression by ConditionInfoEntity list
     * @author: FSL
     * @date: 2023/4/10
     * @param conditionInfoEntityList
     * @return: java.lang.String
     */
    private String buildRuleExpression(List<ConditionInfoEntity> conditionInfoEntityList) {
        // set List initialCapacity base on business
        List<ConditionInstance> conditionInstances = new ArrayList<>(10);
        for (ConditionInfoEntity conditionInfo : conditionInfoEntityList){
            ConditionInstance conditionInstance = ConditionInstance.builder()
                    .variableName(conditionInfo.getVariableName())
                    .variableValue(conditionInfo.getVariableValue())
                    .priority(conditionInfo.getPriority())
                    .logicType(ConditionLogicType.getConditionLogicType(conditionInfo.getLogicType()))
                    .relationType(ConditionRelationType.getConditionRelationType(conditionInfo.getRelationType()))
                    .build();
            conditionInstances.add(conditionInstance);
        }
        log.debug("###### build condition expression start ######");
        log.debug("### sort by priority in desc order ###");
        conditionInstances.sort(Comparator.comparingInt(ConditionInstance::getPriority).reversed());
        StringBuilder finalExp = new StringBuilder();
        for (int i = 0,conditionInstancesSize = conditionInstances.size();i < conditionInstancesSize;i ++){
            ConditionInstance conditionInstance = conditionInstances.get(i);
            Condition condition = selectCondition(conditionInstance.getRelationType());
            finalExp.append(condition.build(conditionInstance));
            if (i < conditionInstancesSize -1){
                if (i > 0){
                    finalExp.insert(0,"(");
                    finalExp.append(")");
                }
                finalExp.append(" ").append(conditionInstance.getLogicType().getValue()).append(" ");
            }
        }
        log.debug("###### build condition expression successful:{} #####",finalExp);
        return finalExp.toString();
    }
}
