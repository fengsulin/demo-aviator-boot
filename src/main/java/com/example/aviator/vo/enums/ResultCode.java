package com.example.aviator.vo.enums;


import com.example.aviator.vo.ServiceCode;

public enum ResultCode implements ServiceCode {
    SUCCESS(10001,"请求成功"),
    SYSTEM_ERROR(99999,"系统异常，请联系管理员"),
    VALID_ERROR(10002,"参数校验异常"),
    CUSTOM_ERROR(10003,""),
    JSON_PARSE_ERROR(10004,"JSON转换异常"),
    XML_PARSE_ERROR(10005,"XML转换异常"),
    REMOTE_FALLBACK(10007,"网络异常，请重试"),
    CONCURRENT_BUILD_ERROR(10006,"不允许并发构建");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @description: 根据编码查询枚举
     * @author: FSL
     * @date: 2023/2/24
     * @param code
     * @return: com.aspire.devops.common.vo.enums.ResultEnum
     */
    public static ResultCode getByCode(int code){
        for (ResultCode value : ResultCode.values()){
            if (code == value.getCode()){
                return value;
            }
        }
        return null;
    }
}
