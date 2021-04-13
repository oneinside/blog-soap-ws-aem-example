package com.one_inside.blog.core.servlets;

import com.one_inside.blog.soapclient.gen.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(
        resourceTypes="cq/Page",
        methods=HttpConstants.METHOD_GET,
        selectors = "test-soap-client",
        extensions="html")
@ServiceDescription("Simple Demo Servlet")
public class SoapTestServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 3665434509966506719L;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

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

        resp.getWriter().write(message);
    }
}
