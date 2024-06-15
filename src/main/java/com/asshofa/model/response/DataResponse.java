package com.asshofa.model.response;

import com.asshofa.util.interceptor.LoggingHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class DataResponse<T> {
    String result = "Success";
    String detail;
    String path;
    String date;
    int code = 200;
    String version = "1.0.0";
    T data;

    public DataResponse(String detail, T data, LoggingHolder h) {
        this.detail = detail;
        this.data = data;
        this.path = h.getPath();
        this.date = h.getDate();
    }
}
