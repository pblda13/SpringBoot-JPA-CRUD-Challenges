package com.project.OrderMaster.dto;

import com.project.OrderMaster.enums.RoleEnum;

;

public record RegisterDTO(String username, String password, RoleEnum role) {
}
