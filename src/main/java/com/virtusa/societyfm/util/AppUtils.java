package com.virtusa.societyfm.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AppUtils {

    public int getFourDigitNumber() {
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }
}
