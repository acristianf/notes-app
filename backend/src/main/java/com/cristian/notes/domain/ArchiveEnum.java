package com.cristian.notes.domain;

public enum ArchiveEnum {
    ARCHIVED("ARCHIVED"), ACTIVE("ACTIVE");

    ArchiveEnum(String state) {
    }

    public static ArchiveEnum getType(String state) {
        if (state.equals("ARCHIVED")) return ARCHIVED;
        if (state.equals("ACTIVE")) return ACTIVE;
        throw new EnumConstantNotPresentException(ArchiveEnum.class, state);
    }
}
