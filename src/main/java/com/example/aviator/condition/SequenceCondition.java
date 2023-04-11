package com.example.aviator.condition;

import com.example.aviator.constants.ConditionRelationType;
import org.springframework.util.StringUtils;

/**
 * @author: FSL
 * @date: 2023/4/10
 * @description: TODO
 */
public class SequenceCondition implements Condition{
    @Override
    public ConditionRelationType[] relationTypes() {
        return new ConditionRelationType[]{
                ConditionRelationType.SOME_INCLUDE_IN_LIST,
                ConditionRelationType.ALL_CONTAINS_IN_LIST,
                ConditionRelationType.SOME_INCLUDE_IN_LIST,
                ConditionRelationType.INCLUDE_IN_LIST,
                ConditionRelationType.NOT_INCLUDE_IN_LIST,
                ConditionRelationType.NONE_CONTAINS_IN_LIST
        };
    }

    @Override
    public String build(ConditionInstance conditionInstance) {
        String[] elements = conditionInstance.getVariableValue().split(",");
        for (int i = 0;i < elements.length;i++){
            elements[i] = "'" + elements[i] + "'";
        }
        String expValue = StringUtils.arrayToDelimitedString(elements, ",");
        return String.format(format(conditionInstance),expValue,conditionInstance.getVariableName());
    }
}
