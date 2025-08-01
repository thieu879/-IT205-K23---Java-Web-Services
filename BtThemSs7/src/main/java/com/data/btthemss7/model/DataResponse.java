package com.data.btthemss7.model;

public class DataResponse<T> {
    private String message;
    private int status;
    private T data;

    public DataResponse() {
        this.message = "Success";
        this.status = 200;
    }

    public DataResponse(T data) {
        this.message = "Success";
        this.status = 200;
        this.data = data;
    }

    public DataResponse(String message, int status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
