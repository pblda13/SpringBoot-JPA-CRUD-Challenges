package com.project.OrderMaster.enums;

public enum RoleEnum {

    ADMIN ("admin"),
    USER("user");

    private String role;

    RoleEnum(String role){
        this.role = role;
    }
}
