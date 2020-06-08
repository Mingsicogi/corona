package com.mins.corona.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfiguration implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {


    @Override
    public void customize(TomcatServletWebServerFactory factory) {
//        factory.addConnectorCustomizers(connector -> {
//            connector.setAttribute("relaxedQueryChars", "`^{}[]");
//        });

        CustomErrorValve customErrorValve = new CustomErrorValve();
        factory.addContextValves(customErrorValve);
        factory.addEngineValves(customErrorValve);
    }
}