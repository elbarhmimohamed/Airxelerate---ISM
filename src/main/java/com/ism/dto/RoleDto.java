package com.ism.dto;

import jakarta.persistence.Column;

public class RoleDto {

    private Long id;
    private String name;

    public RoleDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
