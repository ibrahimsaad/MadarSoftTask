package com.example.ibrahimsaad.madarsofttask;

import java.math.BigDecimal;

public class Util {

    public static double round(double d, int decimalPlace)
    {
        return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
