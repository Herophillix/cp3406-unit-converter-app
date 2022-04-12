package com.example.unit_converter_app.Unit;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Unit {
    private String sign;
    private double multiplier;
    private double adder;

    public Unit(String sign, double multiplier, double adder)
    {
        this.sign = sign;
        this.multiplier = multiplier;
        this.adder = adder;
    }

    public String GetSign()
    {
        return sign;
    }

    public double Convert(double value)
    {
        return value * multiplier + adder;
    }

    public double ConvertToControl(double value)
    {
        return (value - adder) / multiplier;
    }

    public static double RoundTo3sf(double value)
    {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(3, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }
}