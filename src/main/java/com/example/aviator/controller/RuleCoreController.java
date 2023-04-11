package com.example.aviator.controller;

import com.example.aviator.dto.User;
import com.example.aviator.processor.ExpressionExecuteService;
import com.example.aviator.rule.service.RuleManageService;
import com.example.aviator.vo.ResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: FSL
 * @date: 2023/4/11
 * @description: TODO
 */
@RestController
@Tag(name = "规则引擎核心接口")
@RequestMapping("/rule/core")
public class RuleCoreController {

    @Resource
    private RuleManageService ruleManageService;
    @Resource
    private ExpressionExecuteService expressionExecuteService;

    @PostMapping("/refresh")
    @Operation(summary = "规则集刷新接口")
    public ResponseVO refresh(Long id){
        ruleManageService.refreshRuleset(id);
        return ResponseVO.success();
    }

    @PostMapping("/execute")
    @Operation(summary = "规则集工作接口")
    public ResponseVO execute(@RequestBody User user){
        return ResponseVO.success(expressionExecuteService.execute(user));
    }
}
