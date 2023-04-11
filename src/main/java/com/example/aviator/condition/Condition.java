package com.example.aviator.condition;

import com.example.aviator.constants.ConditionRelationType;

/**
 * @author: FSL
 * @date: 2023/4/7
 * @description: 条件处理接口
 */
public interface Condition {
    default String format(ConditionInstance conditionInstance){
        return conditionInstance.getRelationType().getFormat();
    }

    ConditionRelationType[] relationTypes();
    String build(ConditionInstance conditionInstance);
}
