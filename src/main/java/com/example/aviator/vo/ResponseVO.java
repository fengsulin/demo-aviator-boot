package com.example.aviator.vo;

import com.example.aviator.vo.enums.ResultCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResponseVO<T> implements Serializable {
    private static final long serialVersionUID = 2384975113L;

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应提示信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    /**
     * 无参构造方法
     */
    public ResponseVO(){}

    public ResponseVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public ResponseVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ResponseVO<T> success(T data){
        return new ResponseVO<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),data);
    }
    public static <T> ResponseVO<T> success(ResultCode resultCode, String message){
        return new ResponseVO<>(ResultCode.SUCCESS.getCode(),message);
    }
    public static <T> ResponseVO<T> success(){
        return new ResponseVO<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    public static <T> ResponseVO<T> error(){
        return new ResponseVO<>(ResultCode.SYSTEM_ERROR.getCode(), ResultCode.SYSTEM_ERROR.getMessage());
    }

    public static <T> ResponseVO<T> error(String message){
        return new ResponseVO<>(ResultCode.SYSTEM_ERROR.getCode(),message);
    }

    public static <T> ResponseVO<T> error(ResultCode resultCode){
        return new ResponseVO<>(resultCode.getCode(), resultCode.getMessage());
    }

}

