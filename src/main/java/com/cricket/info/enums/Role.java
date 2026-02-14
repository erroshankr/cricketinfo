package com.cricket.info.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {
    USER("USER"),
    SUPER_ADMIN("SUPER-ADMIN"),
    PLAYER_ADMIN("PLAYER-ADMIN"),
    MATCH_ADMIN("MATCH-ADMIN"),
    TEAM_ADMIN("TEAM-ADMIN");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }

    /**
     * Returns all role names as a list of strings (for populating form dropdowns/checkboxes).
     */
    public static List<String> getAllRoleNames() {
        return Arrays.stream(values())
                .map(Role::getRoleName)
                .collect(Collectors.toList());
    }
}
