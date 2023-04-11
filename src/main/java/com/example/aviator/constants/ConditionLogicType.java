package com.example.aviator.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: FSL
 * @date: 2023/4/7
 * @description: TODO
 */
@Getter
@AllArgsConstructor
public enum ConditionLogicType {
    AND("AND","&&"),
    OR("OR","||");
    private final String desc;
    private final String value;

    public static ConditionLogicType getConditionLogicType(String name){
        return Arrays.stream(ConditionLogicType.values())
                .filter(t -> t.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static Map<String,String> conditionLogicTypeMap(){
        return Arrays.stream(ConditionLogicType.values())
                .collect(Collectors.toMap(ConditionLogicType::name,ConditionLogicType::getDesc));
    }


    @Override
    public String toString() {
        return desc;
    }
}
