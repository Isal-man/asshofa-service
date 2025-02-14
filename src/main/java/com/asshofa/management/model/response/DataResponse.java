package com.asshofa.management.model.response;

import com.asshofa.management.util.interceptor.LoggingHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DataResponse<T> {
    String result ;
    String detail ;
    String path ;
    String date ;
    String version ;
    T data;

    public DataResponse(String result, String detail, T data, LoggingHolder h) {
        this.detail = detail;
        this.path = h.getPath();
        this.date = h.getDate();
        this.version = h.getVersion();
        this.data = data;
        this.result = result;
    }
}
