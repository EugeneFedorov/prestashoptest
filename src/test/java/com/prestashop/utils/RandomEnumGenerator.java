package com.prestashop.utils;

import com.prestashop.exceptions.RandomEnumGeneratorException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

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

    /**
     * Returns a random value from the specified enum class, excluding the specified values.
     *
     * This method allows you to obtain a random value from an enum while excluding certain values that
     * you do not want to include in the random selection. This can be useful in situations where some
     * enum values are not applicable or should not be selected under certain conditions.
     *
     * @param <T> the type of the enum
     * @param expectedEnum the class of the enum from which to select a random value
     * @param exclude the enum values that should be excluded from the random selection
     * @return a random value from the specified enum class, excluding the specified values
     * @throws RandomEnumGeneratorException if all enum values are excluded, making it impossible to return a value
     */

    @SafeVarargs
    public static <T extends Enum<?>> T randomEnumValue(Class<T> expectedEnum, T... exclude) {
        if (!expectedEnum.isEnum()) {
            throw new RandomEnumGeneratorException(CLASS_IS_NOT_ENUM_EXCEPTION_MESSAGE);
        }
        List<T> enumValues = Arrays.stream(expectedEnum.getEnumConstants())
                .filter(e -> Arrays.stream(exclude).noneMatch(ex -> ex.equals(e)))
                .toList();
        if (enumValues.isEmpty()) {
            throw new RandomEnumGeneratorException("All enum values are excluded. No value to select.");
        }
        return enumValues.get(PRNG.nextInt(enumValues.size()));
    }

}