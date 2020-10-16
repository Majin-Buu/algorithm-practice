package com.company;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author MaJin_Buu
 */
public class Test {
    public static void main(String[] args) {
        BigDecimal numerator = new BigDecimal(8.23);
        BigDecimal denomination = new BigDecimal(16.23);
        BigDecimal divide = numerator.divide(denomination, 4, BigDecimal.ROUND_HALF_UP);
        BigDecimal multiply = divide.multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println(multiply);
    }
}
