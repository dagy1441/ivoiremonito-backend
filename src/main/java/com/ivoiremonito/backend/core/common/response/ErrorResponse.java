package com.ivoiremonito.backend.core.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int errorCode;
    private  String errorMessage;
    private String devErrorMessage;
    private LocalDateTime timeStamp;
}