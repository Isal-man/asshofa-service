package com.asshofa.management.util.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JwtUtil {
    private JwtUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    private static final Logger logger = LogManager.getLogger(JwtUtil.class);

    public static final String DEFAULT_TOKEN_PREFIX = "Bearer ";

    public static String extractAuthToken(String authHeader, String prefix){
        String token = Optional.ofNullable(authHeader).orElse("");
        if (prefix != null && token.startsWith(prefix)){
            return token.replaceFirst(prefix, "");
        }
        return token;
    }

    public static Map<String, String> getValueFromToken(String token, String[] attrName){
        if (StringUtils.isEmpty(token)) return new HashMap<>();

        JSONParser jsonParser = new JSONParser();
        String[] chunks = token.split("\\.");
        String payload = chunks.length > 1 ? chunks[1]:chunks[0];
        try {
            JSONObject obj = (JSONObject) jsonParser.parse(new String(Base64.getDecoder().decode(payload)));
            Map<String, String> result = new HashMap<>();
            for (String s : attrName) {
                result.put(s, (String) obj.getOrDefault(s, ""));
            }
            return result;
        } catch (ParseException e) {
            logger.error("Failed to extract value from token!", e);
        }
        return new HashMap<>();
    }
}
