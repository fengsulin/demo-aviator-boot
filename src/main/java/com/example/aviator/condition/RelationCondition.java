package com.example.aviator.condition;

import com.example.aviator.constants.ConditionRelationType;
import org.springframework.stereotype.Component;

/**
 * @author: FSL
 * @date: 2023/4/10
 * @description: 常规表达式匹配
 */
@Component
public class RelationCondition implements Condition{
    @Override
    public ConditionRelationType[] relationTypes() {
        return new ConditionRelationType[]{
                ConditionRelationType.EQUAL,
                ConditionRelationType.GREATER,
                ConditionRelationType.LESS,
                ConditionRelationType.LESS_EQUAL,
                ConditionRelationType.GREATER_EQUAL,
                ConditionRelationType.DURATION_GREATER
        };
    }

    @Override
    public String build(ConditionInstance conditionInstance) {
        return String.format(format(conditionInstance),conditionInstance.getVariableName(),conditionInstance.getVariableValue());
    }
}
