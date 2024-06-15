package com.asshofa.model.response;

import com.asshofa.util.interceptor.LoggingHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DefaultResponse {
    String result = "Success";
    String detail;
    String path;
    String date;
    int code = 200;
    String version = "1.0.0";

    public DefaultResponse(String detail, LoggingHolder l) {
        this.detail = detail;
        this.path = l.getPath();
        this.date = l.getDate();
    }
}
