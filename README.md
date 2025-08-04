# Presta Shop Testing Framework

This project is an automated online Presta shop testing framework built using Selenide, Maven and Allure.

## Technologies

- **Selenide** - a framework for automating web application testing
- **Maven** - a build and dependency management system
- **Allure** - a framework for generating test reports
- **JUnit 5** - a framework for unit testing
- **Docker** - a containerization platform
- **Selenoid** - a powerful implementation of Selenium Hub using Docker containers

## Project Structure

```
src/
├── test/
│   └── java/
│       └── com/
│           └── prestashop/
│               ├── config/
│               │   └── TestConfig.java          # Test configuration
│               ├── pages/
│               │   ├── BasePage.java            # Base class for pages
│               │   ├── HomePage.java            # Home page
│               │   ├── ShoppingCartPage.java    # Shopping Cart Page
│               │   ├── ProductPage.java         # Product page
│               │   └── TopMenu.java             # Navigation menu
│               ├── tests/
│               │   ├── BaseTest.java           # Basic test with settings for other tests
│               │   └── HomePageTest.java       # Home Page Tests
│               └── utils/
│                   ├── ConfigManager.java      # Helper class for framework settings
│                   └── ScreenshotUtils.java    # Helper class for screenshots
```

### Allure annotations
- `@Epic` - epic (main functionality)
- `@Feature` - feature
- `@Story` - user story
- `@Description` - test description
- `@Severity` - criticality
- `@Step` - step in test