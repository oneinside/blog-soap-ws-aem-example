package com.one_inside.blog.soapclient;

import com.one_inside.blog.soapclient.gen.*;

public class ClientTest {
    public static void main(String[] args) {

        final float initialCelsius = 32.5f;
        TemperatureConversionPortService service = new TemperatureConversionPortService();
        TemperatureConversionPort temperatureConversionPort = service.getTemperatureConversionPortSoap11();

        ConvertCelsiusToFahrenheitRequest convertCelsiusToFahrenheitRequest = new ConvertCelsiusToFahrenheitRequest();
        convertCelsiusToFahrenheitRequest.setCelsius(initialCelsius);
        ConvertCelsiusToFahrenheitResponse convertCelsiusToFahrenheitResponse = temperatureConversionPort.convertCelsiusToFahrenheit(convertCelsiusToFahrenheitRequest);

        ConvertFahrenheitToCelsiusRequest convertFahrenheitToCelsiusRequest = new ConvertFahrenheitToCelsiusRequest();
        convertFahrenheitToCelsiusRequest.setFahrenheit(convertCelsiusToFahrenheitResponse.getFahrenheit());
        ConvertFahrenheitToCelsiusResponse convertFahrenheitToCelsiusResponse = temperatureConversionPort.convertFahrenheitToCelsius(convertFahrenheitToCelsiusRequest);

        String message = String.format("SUCCESS: %s °C => %s °F => %s °C \uD83C\uDF89",
                initialCelsius,
                convertCelsiusToFahrenheitResponse.getFahrenheit(),
                convertFahrenheitToCelsiusResponse.getCelsius());
        System.out.println(message);
    }
}
