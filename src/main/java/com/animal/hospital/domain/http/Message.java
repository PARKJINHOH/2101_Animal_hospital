package com.animal.hospital.domain.http;

import lombok.Data;

@Data
public class Message {

    private HttpStatusEnum status;
    private String message;
    private Object data;

    public Message() {
        this.status = HttpStatusEnum.BAD_REQUEST;
        this.message = null;
        this.data = null;
    }

}
