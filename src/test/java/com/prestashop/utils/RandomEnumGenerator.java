package com.prestashop.utils;

import com.prestashop.exceptions.RandomEnumGeneratorException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomEnumGenerator {

    private static final String CLASS_IS_NOT_ENUM_EXCEPTION_MESSAGE = "Provided class is not an enum type.";

    private static final SecureRandom PRNG = new SecureRandom();

    /**
     * Returns a random value from the specified enum type.
     *
     * @param <T> the enum type
     * @param expectedEnum the Class object of the enum type
     * @return a random value of the specified enum
     * @throws RandomEnumGeneratorException if the provided class is not an enum type
     */
    public static <T extends Enum<?>> T randomEnumValue(Class<T> expectedEnum) {
        if (!expectedEnum.isEnum()) {
            throw new RandomEnumGeneratorException(CLASS_IS_NOT_ENUM_EXCEPTION_MESSAGE);
        }
        int index = PRNG.nextInt(expectedEnum.getEnumConstants().length);
        return expectedEnum.getEnumConstants()[index];
    }

}
