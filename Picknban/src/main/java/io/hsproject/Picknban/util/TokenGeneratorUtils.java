package io.hsproject.Picknban.util;

import io.hsproject.Picknban.enums.UserType;
import lombok.NonNull;

import java.util.UUID;

public class TokenGeneratorUtils {

    public static String generate(@NonNull String roomId, UserType type) {
        return type.getShortcut() + "_" + roomId + "_" + UUID.randomUUID().toString();
    }

    public static UserType resolveFromToken(@NonNull String token) {
        switch (token.split("_")[0]) {
            case "C": return UserType.CREATOR;
            case "G": return UserType.GUEST;
            default: throw new RuntimeException("todo");
        }
    }
}
