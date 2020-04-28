package com.cl.common;

/**
 *
 * @param <T>
 */
public class ResultSupport<T> implements Result<T> {
    private static final long serialVersionUID = 1L;
    /**
     * 执行是否成功
     */
    private boolean success = false;
    /**
     * 执行成功后的数据
     */
    private T dataObject;
    /**
     * 执行代码
     * 例如
     * 200:成功
     * 500:失败
     *
     */
    private String code;
    /**
     * 执行失败后的错误信息
     */
    private String message;

    public ResultSupport() {
    }

    public ResultSupport(boolean success) {
        this.success = success;
    }

    public ResultSupport(boolean success, T dataObject) {
        this.success = success;
        this.dataObject = dataObject;
    }

    public ResultSupport(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public ResultSupport(boolean success, T dataObject, String code, String message) {
        this.success = success;
        this.dataObject = dataObject;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getDataObject() {
        return this.dataObject;
    }

    public void setDataObject(T dataObject) {
        this.dataObject = dataObject;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

