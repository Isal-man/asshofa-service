package com.asshofa.management.model.response;

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
}
