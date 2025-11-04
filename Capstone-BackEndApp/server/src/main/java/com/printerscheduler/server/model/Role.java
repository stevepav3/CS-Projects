package com.printerscheduler.server.model;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRoleAsString() {
        return this.role;
    }
}
