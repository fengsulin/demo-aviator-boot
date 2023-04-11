package com.example.aviator.condition;

import com.example.aviator.constants.ConditionRelationType;
import org.springframework.stereotype.Component;

/**
 * @author: FSL
 * @date: 2023/4/11
 * @description: TODO
 */
@Component
public class CustomCondition implements Condition{


    @Override
    public ConditionRelationType[] relationTypes() {
        return new ConditionRelationType[]{
                ConditionRelationType.STR_EQUAL
        };
    }

    @Override
    public String build(ConditionInstance conditionInstance) {
        String strValue = "'" + conditionInstance.getVariableValue() +"'";
        return String.format(format(conditionInstance),conditionInstance.getVariableName(),strValue);
    }
}
