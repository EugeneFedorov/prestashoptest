# Presta Shop Testing Framework

This project is an automated online Presta shop testing framework built using Selenide, Maven and Allure.

## Technologies

- **Selenide** - a framework for automating web application testing
- **Maven** - a build and dependency management system
- **Allure** - a framework for generating test reports
- **JUnit 5** - a framework for unit testing
- **Java Faker** - a library for generating test data
- **Lombok** - a library that helps to reduce boilerplate code in Java

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── prestashop/
│               ├── exceptions/
│               │   └── RandomEnumGeneratorException.java
│               ├── model/
│               │   └── Customer.java
│               ├── pages/
│               │   ├── ArtPage.java
│               │   ├── BasePage.java
│               │   ├── ClothesPage.java
│               │   ├── ConfirmationForm.java
│               │   ├── HomePage.java
│               │   ├── MenClothesPage.java
│               │   ├── OrderConfirmationPage.java
│               │   ├── PersonalInformationPage.java
│               │   ├── ProductCartPage.java
│               │   ├── ShoppingCartPage.java
│               │   └── WomenClothesPage.java
│               └── utils/
│                   ├── ConfigManager.java
│                   ├── RandomEnumGenerator.java
│                   ├── ScreenshotUtils.java
│                   └── TestDataGenerator.java
├── test/
│   └── java/
│       └── com/
│           └── prestashop/
│               ├── config/
│               │   └── TestConfig.java
│               ├── suites/
│               │   └── AllTestsSuites.java
│               ├── tests/
│               │   ├── HomePageTests.java
│               │   └── OrderFromCartTests.java
│               └── BaseTest.java
```

### Allure annotations
- `@Feature` - feature
- `@Story` - user story
- `@Description` - test description
- `@Severity` - criticality
- `@Step` - step in test

### Code Style Check
The project has an automatic code style check set up using Checkstyle. 
The check is run automatically with each push via **GitHub Actions**. 
To run it locally, use the command:
```bash
mvn checkstyle:check
```