package com.bikertribe.harley_events.model;

public enum Role {
    RIDER("ROLE_RIDER"),
    ADMIN("ROLE_ADMIN");

    private final String authority;

    Role(String authority) {

        this.authority = authority;
    }

    public String getAuthority() {

        return  authority;
    }
}
