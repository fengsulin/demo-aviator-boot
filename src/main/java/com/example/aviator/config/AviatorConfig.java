package com.example.aviator.config;

import com.example.aviator.function.DurationFunction;
import com.example.aviator.function.StrEqualFunction;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Options;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: FSL
 * @date: 2023/4/6
 * @description: TODO
 */
@Configuration
public class AviatorConfig {

    @Bean
    public AviatorEvaluatorInstance aviatorEvaluatorInstance(){
        // 运行模式设置
        AviatorEvaluatorInstance aviatorEvaluatorInstance = AviatorEvaluator.newInstance(); // 使用默认的运行时性能优先
        aviatorEvaluatorInstance.setCachedExpressionByDefault(true); // 开启默认缓存
        aviatorEvaluatorInstance.setOption(Options.TRACE_EVAL,true); // 本地调试使用，生产环境需要注释掉
        // 注册自定义函数
        aviatorEvaluatorInstance.addFunction(new StrEqualFunction());
        aviatorEvaluatorInstance.addFunction(new DurationFunction());
        return aviatorEvaluatorInstance;
    }
}
