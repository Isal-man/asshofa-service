package com.asshofa.management.model.response;

import com.asshofa.management.util.interceptor.LoggingHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DatatableResponse<T> {
    String result ;
    String detail ;
    String path ;
    String date ;
    int code ;
    String version ;
    PageDataResponse<T> data;

    public DatatableResponse(String result, String detail, PageDataResponse<T> data, LoggingHolder h){
        this.detail = detail;
        this.path = h.getPath();
        this.date = h.getDate();
        this.version = h.getVersion();
        this.data = data;
        this.result = result;
    }


}
