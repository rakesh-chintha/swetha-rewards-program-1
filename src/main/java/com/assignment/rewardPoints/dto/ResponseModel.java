package com.assignment.rewardPoints.dto;

import lombok.Data;

@Data
public class ResponseModel {

    private String message;
    private int status;

    public ResponseModel(String message, int status) {
        super();
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResponseModel [message=" + message + ", status=" + status + "]";
    }
}


