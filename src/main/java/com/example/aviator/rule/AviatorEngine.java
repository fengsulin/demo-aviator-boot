package com.example.aviator.rule;

import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: FSL
 * @date: 2023/4/6
 * @description: 表达式引擎
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AviatorEngine {

    public final AviatorEvaluatorInstance instance;
    public static final ConcurrentHashMap<String,String> expressionMap = new ConcurrentHashMap<>();

    public Object doMatch(String key,String exp,@Nonnull Object obj){
        if (!StringUtils.hasText(exp)) throw new RuntimeException("规则表达式不能为空");
        if (instance.getCachedExpressionByKey(key) == null){
            doCompile(key,exp);
        }
        return doExecute(key,bean2Map(obj));
    }
    public ConcurrentHashMap<String,Object> bean2Map( Object obj){
        Field[] fields = obj.getClass().getDeclaredFields();
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>(15);
        for (Field field : fields){
            field.setAccessible(true);
            try {
                if (null == field.getName() || field.get(obj) == null){
                    continue;
                }
                map.put(field.getName(),field.get(obj));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("bean转换为ConcurrentHashMap异常");
            }
        }
        if (map.containsKey("serialVersionUID")) map.remove("serialVersionUID");
        return map;
    }
    public void doCompile(String key,String exp){
        instance.compile(key,exp,true);
        expressionMap.put(key,exp);
    }

    public Object doExecute(String expKey,Map<String,Object> params){
        Expression expression = instance.getCachedExpressionByKey(expKey);
        return doExecute(expression,params);
    }
    public Object doExecute(Expression exp, Map<String,Object> params){
        return exp.execute(params);
    }


    public void delCacheByKey(String key){
        if (!StringUtils.hasText(key)){
            log.error("exp为空");
            return;
        }
        instance.invalidateCacheByKey(key);
        expressionMap.remove(key);
    }

    public void clearCache(){
        instance.clearExpressionCache();
        expressionMap.clear();
    }

}
