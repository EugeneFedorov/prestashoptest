package com.prestashop.pages;

import com.codeborne.selenide.SelenideElement;
import com.prestashop.model.Customer;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class PersonalInformationPage extends BasePage {

    private final SelenideElement personalInformationTitle =
            $x("//section[@id='checkout-personal-information-step']/h1");
    private final SelenideElement firstNameInput = $x("//input[@id='field-firstname']");
    private final SelenideElement lastNameInput = $x("//input[@id='field-lastname']");
    private final SelenideElement emailInput = $x("//input[@id='field-email']");
    private final SelenideElement customerFormContinueButton =
            $x("//form[@id='customer-form']//button[@name='continue']");
    private final SelenideElement addressInput = $x("//input[@id='field-address1']");
    private final SelenideElement postalInput = $x("//input[@id='field-postcode']");
    private final SelenideElement cityInput = $x("//input[@id='field-city']");
    private final SelenideElement addressesFormContinueButton = $x("//button[@name='confirm-addresses']");
    private final SelenideElement shippingFormContinueButton = $x("//button[@name='confirmDeliveryOption']");
    private final SelenideElement finalAgreeCheckbox = $x("//input[@id='conditions_to_approve[terms-and-conditions]']");
    private final SelenideElement placeOrderButton = $x("//div[@id='payment-confirmation']//button[@type='submit']");

    @Override
    @Step("Waiting for the Personal information page to load")
    public void waitForPageLoad() {
        personalInformationTitle.shouldBe(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
    }

    public OrderConfirmationPage fillCustomerInfo(Customer customer) {
        customer.getSocialTitle().click();
        firstNameInput.setValue(customer.getFirstName());
        lastNameInput.setValue(customer.getLastName());
        emailInput.setValue(customer.getEmail());
        customer.getAgreements().forEach(Customer.Agreement::click);
        customerFormContinueButton.click();
        addressInput.setValue(customer.getAddress());
        postalInput.setValue(customer.getPostalCode().getCode());
        cityInput.setValue(customer.getCity());
        addressesFormContinueButton.click();
        customer.getShippingMethod().click();
        shippingFormContinueButton.click();
        customer.getPayment().click();
        finalAgreeCheckbox.click();
        placeOrderButton.click();
        return new OrderConfirmationPage();
    }

}
