package com.utility;

import com.github.javafaker.Faker;
import com.ui.pojo.AddressPOJO;

import java.util.Locale;

public class FakeAddressUtility {


    public static AddressPOJO getFakeAddress() {
        Faker faker = new Faker(Locale.US);

        return new AddressPOJO(
                faker.company().name(),
                faker.address().streetAddress(),
                faker.address().secondaryAddress(),
                faker.address().city(),
                faker.numerify("#####"),
                faker.numerify("###-###-####"),
                faker.numerify("###-###-####"),
                faker.lorem().sentence(),
                faker.address().streetName(),
                faker.number().numberBetween(1, 51)
        );
    }
}
