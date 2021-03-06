package io.syndesis.connector.jms.springboot;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Generated("org.apache.camel.maven.connector.SpringBootAutoConfigurationMojo")
@ConfigurationProperties(prefix = "activemq-request")
public class ActiveMQRequestConnectorConfiguration
        extends
            ActiveMQRequestConnectorConfigurationCommon {

    /**
     * Define additional configuration definitions
     */
    private Map<String, ActiveMQRequestConnectorConfigurationCommon> configurations = new HashMap<>();

    public Map<String, ActiveMQRequestConnectorConfigurationCommon> getConfigurations() {
        return configurations;
    }
}