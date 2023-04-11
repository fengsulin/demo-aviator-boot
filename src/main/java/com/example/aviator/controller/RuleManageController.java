package com.example.aviator.controller;

import com.example.aviator.constants.ConditionLogicType;
import com.example.aviator.constants.ConditionRelationType;
import com.example.aviator.dto.ConditionInfoDTO;
import com.example.aviator.dto.RequestPageDto;
import com.example.aviator.dto.RuleInfoDTO;
import com.example.aviator.rule.entity.ConditionInfoEntity;
import com.example.aviator.rule.entity.RuleInfoEntity;
import com.example.aviator.rule.entity.RulesetInfoEntity;
import com.example.aviator.rule.service.RuleManageService;
import com.example.aviator.vo.ResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rule/manage")
@Tag(name = "规则管理接口")
@Lazy
public class RuleManageController {

    @Resource
    private RuleManageService ruleManageService;

    @GetMapping("/logicTypeMap")
    @Operation(summary = "获取loginTypeMap接口")
    public ResponseVO<Map<String, String>> logicTypeMap() {
        return ResponseVO.success(ConditionLogicType.conditionLogicTypeMap());
    }

    @GetMapping("/relationTypeMap")
    @Operation(summary = "获取relationTypeMap接口")
    public ResponseVO<Map<String, String>> relationTypeMap() {
        return ResponseVO.success(ConditionRelationType.conditionRelationTypeMap());
    }

    @PostMapping("/findRulesetInfoPage")
    @Operation(summary = "分页查询规则集接口")
    public ResponseVO<Page<RulesetInfoEntity>> findRulesetInfoPage(@RequestBody RequestPageDto dto) {
        return ResponseVO.success(ruleManageService.findRulesetInfoPage(dto.getPage(), dto.getSize()));
    }

    @PostMapping("/findRuleInfoPage")
    @Operation(summary = "分页查询规则接口")
    public ResponseVO<Page<RuleInfoEntity>> findRuleInfoPage(@RequestBody RuleInfoDTO dto) {
        return ResponseVO.success(ruleManageService.findRuleInfoPage(dto.getRulesetId(), dto.getPage(), dto.getSize()));
    }

    @PostMapping("/findConditionInfoPage")
    @Operation(summary = "分页查询条件接口")
    public ResponseVO<Page<ConditionInfoEntity>> findConditionInfoPage(@RequestBody ConditionInfoDTO dto) {
        return ResponseVO.success(ruleManageService.findConditionInfoInfoPage(dto.getRuleId(), dto.getPage(), dto.getSize()));
    }

    @PostMapping("/saveRulesetInfo")
    @Operation(summary = "添加或更新规则集接口")
    public ResponseVO<RulesetInfoEntity> saveRulesetInfo(@RequestBody RulesetInfoEntity rulesetInfoEntity) {
        return ResponseVO.success(ruleManageService.saveRuleset(rulesetInfoEntity));
    }

    @PostMapping("/deleteRulesetInfo")
    @Operation(summary = "删除规则集接口")
    public ResponseVO<String> deleteRulesetInfo( Long id) {
        ruleManageService.removeRuleset(id);
        return ResponseVO.success();
    }

    @PostMapping("/saveRuleInfo")
    @Operation(summary = "添加规则接口")
    public ResponseVO<RuleInfoEntity> saveRuleInfo(@RequestBody RuleInfoEntity ruleInfoEntity) {
        return ResponseVO.success(ruleManageService.saveRule(ruleInfoEntity));
    }

    @PostMapping("/deleteRuleInfo")
    @Operation(summary = "删除规则接口")
    public ResponseVO<String> deleteRuleInfo(Long id) {
        ruleManageService.removeRule(id);
        return ResponseVO.success();
    }

    @PostMapping("/saveConditionInfoList")
    @Operation(summary = "批量添加或更新条件接口")
    public ResponseVO<List<ConditionInfoEntity>> saveConditionInfoList(@RequestBody List<ConditionInfoEntity> conditionInfoEntityList) {
        return ResponseVO.success(ruleManageService.saveConditionList(conditionInfoEntityList));
    }

    @PostMapping("/refreshRuleset")
    @Operation(summary = "刷新规则集接口")
    public ResponseVO<String> refreshRuleset(@RequestBody Map<String, Object> requestBody) {
        ruleManageService.refreshRuleset(((Integer) requestBody.get("rulesetId")).longValue());
        return ResponseVO.success();
    }
}