package com.one_inside.blog.soapserver;

import org.springframework.stereotype.Component;

@Component
public class TemperatureConversionService {

    public float celsiusToFahrenheit(float celsius) {
        return celsius * 1.8f + 32.00f;
    }

    public float fahrenheitToCelsius(float fahrenheit) {
        return (fahrenheit - 32.0f) / 1.8f;
    }

}
