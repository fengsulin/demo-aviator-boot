package com.example.aviator.condition;

import com.example.aviator.constants.ConditionRelationType;
import org.springframework.stereotype.Component;

/**
 * @author: FSL
 * @date: 2023/4/10
 * @description: 正则表达式处理类
 */
@Component
public class RegularCondition implements Condition{
    @Override
    public ConditionRelationType[] relationTypes() {
        return new ConditionRelationType[]{ConditionRelationType.REGULAR};
    }

    @Override
    public String build(ConditionInstance conditionInstance) {
        return String.format(format(conditionInstance),conditionInstance.getVariableName(),conditionInstance.getVariableValue());
    }
}
