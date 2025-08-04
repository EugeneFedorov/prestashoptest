# Presta Shop Testing Framework

This project is an automated online Presta shop testing framework built using Selenide, Maven and Allure.

## Technologies

- **Selenide** - a framework for automating web application testing
- **Maven** - a build and dependency management system
- **Allure** - a framework for generating test reports
- **JUnit 5** - a framework for unit testing
- **Java Faker** - a library for generating test data
- **Selenoid** - a powerful implementation of Selenium Hub using Docker containers

## Project Structure

```
src/
├── test/
│   └── java/
│       └── com/
│           └── prestashop/
│               ├── base/
│               │   ├── BasePage.java           # Base class for pages
│               │   ├── BaseTest.java           # Basic test with settings for other tests
│               ├── config/
│               │   └── TestConfig.java         # Test configuration
│               ├── model/
│               │   └── Customer.java           # Model describing the customer
│               ├── pages/
│               │   ├── ArtPage.java            
│               │   ├── ClothesPage.java        
│               │   ├── ConfirmationForm.java            
│               │   ├── HomePage.java           
│               │   ├── MenClothesPage.java
│               │   ├── OrderConfirmationPage.java
│               │   ├── PersonalInformationPage.java
│               │   ├── ProductCartPage.java        
│               │   ├── ShoppingCartPage.java   
│               │   └── WomenClothesPage.java            
│               ├── suites/
│               │   └── AllTestsDuites.java     # Class to run all tests
│               ├── tests/
│               │   ├── OrderFromCartTests.java # Testing orders from the basket 
│               │   └── HomePageTests.java      # Home Page Tests
│               └── utils/
│                   ├── ConfigManager.java          # Helper class for framework settings
│                   ├── RandomEnumGenerator.java    # Helper class for getting random value from enum
│                   ├── ScreenshotUtils.java        # Helper class for screenshots
│                   └── TestDataGenerator.java      # Helper class for generating various random values 
```

### Allure annotations
- `@Feature` - feature
- `@Story` - user story
- `@Description` - test description
- `@Severity` - criticality
- `@Step` - step in test