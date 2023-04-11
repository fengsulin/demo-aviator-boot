package com.example.aviator.vo;


import com.example.aviator.vo.enums.ResultCode;

/**
 * 自定义业务异常
 */
public final class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1235892964386362L;

    private final ServiceCode serviceCode;

    public ServiceException(){
        super(ResultCode.SYSTEM_ERROR.getMessage());
        this.serviceCode = ResultCode.SYSTEM_ERROR;
    }
    public ServiceException(ServiceCode serviceCode) {
        super(serviceCode.getMessage());
        this.serviceCode = serviceCode;
    }

    public ServiceException(final String detailMessage){
        super(detailMessage);
        this.serviceCode = ResultCode.SYSTEM_ERROR;
    }

    public ServiceException(final Throwable cause){
        super(cause);
        this.serviceCode = ResultCode.SYSTEM_ERROR;
    }

    public ServiceException(final ServiceCode serviceCode,final String detailMessage){
        super(detailMessage);
        this.serviceCode = serviceCode;
    }

    public ServiceException(final ServiceCode serviceCode,final Throwable cause){
        super(serviceCode.getMessage(),cause);
        this.serviceCode = serviceCode;
    }

    public ServiceException(final String detailMessage,final Throwable cause){
        super(detailMessage,cause);
        this.serviceCode = ResultCode.SYSTEM_ERROR;
    }

    public ServiceException(final ServiceCode serviceCode,final String detailMessage,final Throwable cause){
        super(detailMessage,cause);
        this.serviceCode = serviceCode;
    }

    public  ServiceCode getServiceCode(){
        return serviceCode;
    }
}
