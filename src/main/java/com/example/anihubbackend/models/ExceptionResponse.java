package com.example.anihubbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private Date timeStamp;
    private Class<? extends Exception> type;
    private String url;
    private String details;
}
