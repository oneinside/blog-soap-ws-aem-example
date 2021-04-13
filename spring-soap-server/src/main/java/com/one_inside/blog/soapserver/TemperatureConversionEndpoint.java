package com.one_inside.blog.soapserver;

import com.one_inside.blog.soapserver.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static com.one_inside.blog.soapserver.WebServiceConfig.TARGET_NAMESPACE;

@Endpoint
public class TemperatureConversionEndpoint {

    private final TemperatureConversionService temperatureConversionService;

    private final ObjectFactory objectFactory = new ObjectFactory();

    @Autowired
    public TemperatureConversionEndpoint(TemperatureConversionService temperatureConversionService) {
        this.temperatureConversionService = temperatureConversionService;
    }

    @PayloadRoot(namespace = TARGET_NAMESPACE, localPart = "convertCelsiusToFahrenheitRequest")
    @ResponsePayload
    public ConvertCelsiusToFahrenheitResponse convertCelsiusToFahrenheit(@RequestPayload ConvertCelsiusToFahrenheitRequest request) {
        ConvertCelsiusToFahrenheitResponse response = objectFactory.createConvertCelsiusToFahrenheitResponse();
        response.setFahrenheit(temperatureConversionService.celsiusToFahrenheit(request.getCelsius()));
        return response;
    }

    @PayloadRoot(namespace = TARGET_NAMESPACE, localPart = "convertFahrenheitToCelsiusRequest")
    @ResponsePayload
    public ConvertFahrenheitToCelsiusResponse convertFahrenheitToCelsius(@RequestPayload ConvertFahrenheitToCelsiusRequest request) {
        ConvertFahrenheitToCelsiusResponse response = objectFactory.createConvertFahrenheitToCelsiusResponse();
        response.setCelsius(temperatureConversionService.fahrenheitToCelsius(request.getFahrenheit()));
        return response;
    }
}
