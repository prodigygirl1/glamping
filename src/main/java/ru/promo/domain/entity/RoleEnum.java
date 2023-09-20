package ru.promo.domain.entity;

public enum RoleEnum {
    ADMIN,
    USER;

    private static final String PREFIX = "ROLE_";

    public static String getRoleName(RoleEnum roleEnum) {
        return PREFIX + roleEnum.name();
    }
}
