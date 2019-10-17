package io.hsproject.Picknban.enums;

public enum Class {
    DRUID("Druid"),
    HUNTER("Hunter"),
    WARRIOR("Warrior"),
    ROGUE("Rogue"),
    MAGE("Mage"),
    PALADIN("Paladin"),
    PRIEST("Priest"),
    SHAMAN("Shaman"),
    WARLOCK("Warlock");

    private final String name;

    Class(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
