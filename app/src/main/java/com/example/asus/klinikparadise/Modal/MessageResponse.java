package com.example.asus.klinikparadise.Modal;

/**
 * Created by Asus on 04/06/2018.
 */

public class MessageResponse {
    private boolean status;
    private String message;

    public MessageResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
