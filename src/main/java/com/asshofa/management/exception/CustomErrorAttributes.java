package com.asshofa.management.exception;

import com.asshofa.management.util.interceptor.LoggingHolder;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
    private final LoggingHolder loggingHolder;

    public CustomErrorAttributes(LoggingHolder loggingHolder) {
        this.loggingHolder = loggingHolder;
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.put("result", "Error");
        errorAttributes.put("detail", errorAttributes.get("error"));
        errorAttributes.put("path", errorAttributes.get("path"));
        errorAttributes.put("date", loggingHolder.getDate());
        errorAttributes.put("code", errorAttributes.get("status"));
        errorAttributes.put("version", loggingHolder.getVersion());
        errorAttributes.remove("status");
        errorAttributes.remove("message");
        errorAttributes.remove("locale");
        errorAttributes.remove("error");
        errorAttributes.remove("cause");
        errorAttributes.remove("trace");
        errorAttributes.remove("timestamp");

        return errorAttributes;
    }
}
