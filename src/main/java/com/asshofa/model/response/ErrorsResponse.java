package com.asshofa.model.response;

import com.asshofa.util.interceptor.LoggingHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorsResponse {
    String result = "Error";
    String detail;
    String path;
    String date;
    int code = 400;
    String version = "1.0.0";
    List<String> errors;

    public ErrorsResponse(String detail, List<String> errors, LoggingHolder h) {
        this.detail = detail;
        this.errors = errors;
        this.date = h.getDate();
        this.path = h.getPath();
    }
}
