package com.example.aviator.constants;

import lombok.Getter;

/**
 * @author: FSL
 * @date: 2023/4/10
 * @description: TODO
 */
@Getter
public enum RulesetModeType {
    BUILT(1),
    BUILDING(0);
    private final int code;

    RulesetModeType(int code) {
        this.code = code;
    }
}
