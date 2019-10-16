package io.hsproject.Picknban.enums;

public enum BanType {
    BO3("BO3"),
    BO5("BO5");

    private final String name;

    BanType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
