package com.prestashop.utils;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import java.util.Locale;

public class TestDataGenerator {

    private static final Faker FAKER = new Faker(new Locale("en"));

    @Step("Generate random email")
    public static String generateEmail() {
        return FAKER.internet().emailAddress();
    }

    @Step("Generate random first name")
    public static String generateFirstName() {
        return FAKER.name().firstName();
    }

    @Step("Generate random last name")
    public static String generateLastName() {
        return FAKER.name().lastName();
    }

    @Step("Generate random address")
    public static String generateAddress() {
        return FAKER.address().fullAddress();
    }

    @Step("Generate random city")
    public static String generateCity() {
        return FAKER.address().city();
    }

} 