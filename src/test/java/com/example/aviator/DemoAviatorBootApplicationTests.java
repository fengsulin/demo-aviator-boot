package com.example.aviator;

import com.example.aviator.dto.User;
import com.example.aviator.rule.AviatorEngine;
import com.example.aviator.rule.mapper.ConditionInfoRepository;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.EvalMode;
import com.googlecode.aviator.Expression;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
class DemoAviatorBootApplicationTests {

	@Resource
	private AviatorEngine aviatorEngine;
	@Resource
	private ConditionInfoRepository conditionInfoRepository;

	@Test
	void contextLoads() {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setAge(18);
		user.setUsername("张三");
		String exp = "age<20 && username == '张三'";
//		Expression compile = AviatorEvaluator.compile(exp, true);
//		Boolean execute1 = (Boolean) compile.execute(params);
//		Boolean execute = (Boolean) AviatorEvaluator.getCachedExpression(exp).execute(params);
	}

	@Test
	void jpaTest(){
		boolean b = conditionInfoRepository.existsById(1L);
		System.out.println(b);
	}
}
