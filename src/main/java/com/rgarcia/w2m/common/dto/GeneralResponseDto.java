package com.rgarcia.w2m.common.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class GeneralResponseDto<T> implements Serializable {

    boolean success = true;
    T data;
    long timestamp;

    public GeneralResponseDto() {
        timestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public GeneralResponseDto(T data) {
        this.data = data;
        timestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public GeneralResponseDto(T data, boolean success) {
        this.data = data;
        this.success = success;
        timestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }
}