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
public enum ConditionRelationType {
    EQUAL("EQUAL","%s == str(%s)"),
    NOT_EQUAL("NOT_EQUAL","%s != %s"),
    LESS_EQUAL("LESS_EQUAL","%s <= %s"),
    GREATER_EQUAL("GREATER_EQUAL","%s >= %s"),
    LESS("LESS","%s < %s"),
    GREATER("GREATER","%s > %s"),
    INCLUDE_IN_LIST("INCLUDE_IN_LIST","include(seq.set(%s),str(%s))"),
    // 只要有一个满足条件就为true
    SOME_INCLUDE_IN_LIST("SOME_INCLUDE_IN_LIST","seq.some(seq.set(%s),lambda(x) -> string.indexOf(str(%s),x) > -1 end)"),
    NOT_INCLUDE_IN_LIST("NOT_INCLUDE_IN_LIST","!include(seq.set(%s),str(%s))"),
    // lambda表达式中所有条件都为false才为true
    NONE_CONTAINS_IN_LIST("NONE_CONTAINS_IN_LIST","seq.not_any(seq.set(%),lambda(x) -> string.indexOf(str(%s),x) > -1 end)"),
    // lambda表达式中所有条件都为true才为true
    ALL_CONTAINS_IN_LIST("ALL_CONTAINS_IN_LIST","seq.every(seq.set(%s),lambda(x) -> string.indexOf(str(%s),x) > -1 end)"),
    // 正则匹配
    REGULAR("REGULAR","str(%s) =~ %s"),
    // 自定义java静态方法
    STR_EQUAL("STR_EQUAL","equals(%s,%s)"),
    // 持续时间超过xx分钟
    DURATION_GREATER("DURATION_GREATER","duration(%s,%s)");
    public static ConditionRelationType getConditionRelationType(String name){
        return Arrays.stream(ConditionRelationType.values())
                .filter(t -> t.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
    public static Map<String,String> conditionRelationTypeMap(){
        return Arrays.stream(ConditionRelationType.values())
                .collect(Collectors.toMap(ConditionRelationType::name,ConditionRelationType::getDesc));
    }

    private final String desc;
    private final String format;

    @Override
    public String toString() {
        return desc;
    }
}
