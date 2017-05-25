package com.qy.sp.manage.dto;

public class TUserRole extends TUserRoleKey {
    private String tUUserId;

    public String gettUUserId() {
        return tUUserId;
    }

    public void settUUserId(String tUUserId) {
        this.tUUserId = tUUserId == null ? null : tUUserId.trim();
    }
}