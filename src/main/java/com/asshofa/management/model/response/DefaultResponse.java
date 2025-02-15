package com.asshofa.management.model.response;

import com.asshofa.management.util.interceptor.LoggingHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DefaultResponse {
    String result;
    String detail;
    String path;
    String date;
    int code ;
    String version ;

    public DefaultResponse(String result, String detail, int code, LoggingHolder h){
        this.result = result;
        this.detail = detail;
        this.code = code;
        this.path = h.getPath();
        this.date = h.getDate();
        this.version = h.getVersion();
    }


}
