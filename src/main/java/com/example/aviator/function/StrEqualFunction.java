package com.example.aviator.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

/**
 * @author: FSL
 * @date: 2023/4/11
 * @description: 自定义函数，用于判断两个字符串是否相等
 */
public class StrEqualFunction extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        String left = FunctionUtils.getStringValue(arg1, env);
        String right = FunctionUtils.getStringValue(arg2, env);
        return AviatorBoolean.valueOf(left.equals(right));
    }

    @Override
    public String getName() {
        return "equals";
    }
}
