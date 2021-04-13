# README #

## Spring SOAP Server ##
Starts the Spring Server
```
cd spring-soap-server
mvn clean compile spring-boot:run
```

## CLI SOAP Client ##
Builds and executes the CLI SOAP client. 

You should see the message `SUCCESS: 32.5 °C => 90.5 °F => 32.5 °C 🎉`

```
cd cli-soap-client
mvn clean compile exec:java
```

## AEM SOAP Client ##
Assumes running AEM on default port 4502.
Opening `http://localhost:4502/content/we-retail/language-masters/en.test-soap-client.html` should result in message:
`SUCCESS: 32.5 °C => 90.5 °F => 32.5 °C 🎉`

```
cd aem-soap-client
mvn clean install -P autoInstallPackage
```