package com.asshofa.management.util;

import com.asshofa.management.exception.custom.ForbiddenException;
import com.asshofa.management.util.interceptor.HeaderHolder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CheckRole {

    private final HeaderHolder headerHolder;

    public CheckRole(HeaderHolder headerHolder) {
        this.headerHolder = headerHolder;
    }

    public void checkRoleBrowseWaliSantri() {
        List<String> roles = Arrays.asList(
                Constant.ADMIN,
                Constant.PENGAJAR,
                Constant.WALI_SANTRI
        );

        aksesRole(roles);
    }

    public void checkRoleAdminAndPengajar() {
        List<String> roles = Arrays.asList(
                Constant.ADMIN,
                Constant.PENGAJAR
        );

        aksesRole(roles);
    }

    public void checkRoles() {
        List<String> roles = Arrays.asList(
                Constant.ADMIN,
                Constant.PENGAJAR,
                Constant.WALI_SANTRI,
                Constant.SANTRI,
                Constant.USER
        );

        aksesRole(roles);
    }

    public void checkRoleCRD() {
        List<String> roles = Collections.singletonList(
                Constant.ADMIN
        );

        aksesRole(roles);
    }

    public void checkRoleDetailJadwalPengajaran() {
        List<String> roles = Arrays.asList(
                Constant.ADMIN,
                Constant.PENGAJAR,
                Constant.SANTRI
        );

        aksesRole(roles);
    }

    public void aksesRole(List<String> roles) {
        List<String> roleList = Arrays.asList(headerHolder.getRoles());

        boolean hasAccess = roleList.stream().anyMatch(roles::contains);
        if (!hasAccess) throw new ForbiddenException();
    }
}
