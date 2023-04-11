package com.example.aviator.condition;

import com.example.aviator.constants.ConditionLogicType;
import com.example.aviator.constants.ConditionRelationType;
import lombok.*;

/**
 * @author: FSL
 * @date: 2023/4/7
 * @description: TODO
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConditionInstance {
    private String variableName;
    private String variableValue;
    private ConditionRelationType relationType;
    private int priority;
    private ConditionLogicType logicType;
}
