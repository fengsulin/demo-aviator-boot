package com.example.aviator.constants;

import lombok.Getter;

/**
 * @author: FSL
 * @date: 2023/4/10
 * @description: TODO
 */
@Getter
public enum RuleStateType {
    ENABLE(1),
    DISABLE(0);
    private final int code;

    RuleStateType(int code) {
        this.code = code;
    }
}
