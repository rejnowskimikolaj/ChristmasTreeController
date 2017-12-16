package com.example.root.christmastree;

/**
 * Created by root on 12/16/17.
 */

public class SendFrameResponse {
    private String message;

    public SendFrameResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
