package com.asshofa.model.response;

import com.asshofa.util.interceptor.LoggingHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DatatableResponse<T> {
    String result = "Success";
    String detail;
    String path;
    String date;
    int code = 200;
    String version = "1.0.0";
    PageDataResponse<T> data;

    public DatatableResponse(String detail, PageDataResponse<T> data, LoggingHolder h) {
        this.detail = detail;
        this.path = h.getPath();
        this.date = h.getDate();
        this.data = data;
    }
}
