package com.prestashop.model;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.prestashop.utils.RandomEnumGenerator;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.andreinc.mockneat.MockNeat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Builder
@Getter
public class Customer {

    private SocialTitle socialTitle;
    private String firstName;
    private String lastName;
    private String email;
    private List<Agreement> agreements;
    private String address;
    private PostalCode postalCode;
    private String city;
    private ShippingMethod shippingMethod;
    private Payment payment;

    public static Customer getCustomer() {
        return Customer.builder()
                .socialTitle(RandomEnumGenerator.randomEnumValue(SocialTitle.class))
                .firstName(MockNeat.threadLocal().names().get())
                .lastName(MockNeat.threadLocal().names().get())
                .email(MockNeat.threadLocal().emails().get())
                .agreements(Agreement.generateAgreements())
                .address(MockNeat.threadLocal().addresses().get())
                .postalCode(PostalCode.generatePostalCode())
                .city(MockNeat.threadLocal().cities().capitals().get())
                .shippingMethod(RandomEnumGenerator.randomEnumValue(ShippingMethod.class))
                .payment(RandomEnumGenerator.randomEnumValue(Payment.class))
                .build();
    }

    @AllArgsConstructor
    @Getter
    public static class PostalCode {

        @Size(min = 5, max = 5, message = "The post code length must be 5 characters.")
        private String code;

        private static PostalCode generatePostalCode() {
            return new PostalCode(String.valueOf(IntStream
                    .rangeClosed(10_000, 99_999)
                    .findAny()
                    .orElse(10_000)));
        }

    }

    @RequiredArgsConstructor
    @Getter
    public enum SocialTitle {
        MR(Selenide.$x("//input[@id='field-id_gender-1']")),
        MRS(Selenide.$x("//input[@id='field-id_gender-2']"));

        private final SelenideElement element;

        public void click() {
            element.click();
        }
    }

    @RequiredArgsConstructor
    @Getter
    public enum Agreement {
        FIRST(1),
        SECOND(2),
        THIRD(3),
        FOURTH(4);

        private static final String CHECKBOX_XPATH_TEMPLATE = "(//span[@class='custom-checkbox']/parent::div)[%d]";
        private final int index;
        private SelenideElement element;

        public SelenideElement getElement() {
            if (element == null) {
                element = Selenide.$x(String.format(CHECKBOX_XPATH_TEMPLATE, index));
            }
            return element;
        }

        private static List<Agreement> generateAgreements() {
            List<Agreement> agreements = new ArrayList<>();

            // Mandatory agreements (SECOND and FOURTH)
            agreements.add(Agreement.SECOND);
            agreements.add(Agreement.FOURTH);

            // Random agreements (FIRST and/or THIRD)
            Random random = new Random();
            if (random.nextBoolean()) {
                agreements.add(Agreement.FIRST);
            }
            if (random.nextBoolean()) {
                agreements.add(Agreement.THIRD);
            }
            return agreements;
        }

        public void click() {
            getElement().click();
        }
    }

    @RequiredArgsConstructor
    @Getter
    public enum ShippingMethod {
        CLICK_AND_COLLECT(Selenide.$x("//input[@id='delivery_option_1']/parent::span/parent::div")),
        MY_CARRIER(Selenide.$x("//input[@id='delivery_option_2']/parent::span/parent::div"));

        private final SelenideElement element;

        public void click() {
            element.click();
        }
    }

    @RequiredArgsConstructor
    @Getter
    public enum Payment {
        PAY_BY_BANK_WIRE(Selenide.$x("//input[@id='payment-option-1']")),
        PAY_BY_CASH_ON_DELIVERY(Selenide.$x("//input[@id='payment-option-2']")),
        PAY_BY_CHECK(Selenide.$x("//input[@id='payment-option-3']"));

        private final SelenideElement element;

        public void click() {
            element.click();
        }
    }

}
