package com.FileSearch.bean;

import lombok.Data;

@Data
public class Result<T> {

    private T data;
    private boolean success;
    private String msg;
    private String errorMsg;
    private String identifier;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static Result<Void> fail(String errorMsg) {
        Result<Void> result = new Result<Void>();
        result.setSuccess(false);
        result.setMsg(errorMsg);
        return result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}
