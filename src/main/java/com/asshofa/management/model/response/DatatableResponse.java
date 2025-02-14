package com.asshofa.management.model.response;

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
}
