package com.example.aviator.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Date;
import java.util.Map;

/**
 * @author: FSL
 * @date: 2023/4/11
 * @description: 自定义函数，用于判断告警持续时间是否超过xx分钟
 */
public class DurationFunction extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        Date targetDate = (Date) FunctionUtils.getJavaObject(arg1, env);
        System.out.println(targetDate);
        long duration = FunctionUtils.getNumberValue(arg2, env).longValue() * 60 * 60;
        long targetTime = targetDate.getTime();
        long currentTime = new Date().getTime();

        return AviatorBoolean.valueOf((currentTime - targetTime) > duration);
    }

    @Override
    public String getName() {
        return "duration";
    }
}
