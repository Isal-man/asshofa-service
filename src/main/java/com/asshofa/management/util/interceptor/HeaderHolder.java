package com.asshofa.management.util.interceptor;

import lombok.Data;

@Data
public class HeaderHolder {
    private String nip;
    private String name;
    private String givenName;
    private String username;
    private String remoteAddress;
    private String kodeKantor;
    private String[] roles;
    private String unit;
}
