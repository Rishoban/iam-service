package com.iam.service.mapper;

public class ResponseMessage {
    private String message;
    private Integer code;
    private Long recordId;

    public ResponseMessage(String message, Integer code, Long recordId) {
        this.message = message;
        this.code = code;
        this.recordId = recordId;
    }
}
