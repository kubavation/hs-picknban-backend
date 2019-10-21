package io.hsproject.Picknban.enums;

public enum UserType {
    CREATOR("C"),
    GUEST("G");

    private final String shortcut;

    UserType(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getShortcut() {
        return shortcut;
    }
}
