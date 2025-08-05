package com.prestashop.utils;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import java.util.Locale;

public class TestDataGenerator {

    private static final Faker faker = new Faker(new Locale("en"));

    @Step("Generate random email")
    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    @Step("Generate random first name")
    public static String generateFirstName() {
        return faker.name().firstName();
    }

    @Step("Generate random last name")
    public static String generateLastName() {
        return faker.name().lastName();
    }

    @Step("Generate random address")
    public static String generateAddress() {
        return faker.address().fullAddress();
    }

    @Step("Generate random city")
    public static String generateCity() {
        return faker.address().city();
    }

} 