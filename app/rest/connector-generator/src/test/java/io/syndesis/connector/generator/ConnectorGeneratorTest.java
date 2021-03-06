/*
 * Copyright (C) 2016 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.syndesis.connector.generator;

import io.syndesis.model.connection.ConfigurationProperty;
import io.syndesis.model.connection.Connector;
import io.syndesis.model.connection.ConnectorGroup;
import io.syndesis.model.connection.ConnectorSettings;
import io.syndesis.model.connection.ConnectorTemplate;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnectorGeneratorTest {

    private final ConnectorGenerator generator = new ConnectorGenerator() {

        @Override
        public Connector generate(final ConnectorTemplate connectorTemplate, final ConnectorSettings connectorSettings) {
            return null;
        }

        @Override
        public ConnectorSummary info(final ConnectorTemplate connectorTemplate, final ConnectorSettings connectorSettings) {
            return null;
        }

        @Override
        protected String determineConnectorDescription(final ConnectorTemplate connectorTemplate,
            final ConnectorSettings connectorSettings) {
            return "test-description";
        }

        @Override
        protected String determineConnectorName(final ConnectorTemplate connectorTemplate, final ConnectorSettings connectorSettings) {
            return "test-name";
        }
    };

    private final ConnectorTemplate template = new ConnectorTemplate.Builder()//
        .id("template-id")//
        .connectorGroup(new ConnectorGroup.Builder().id("template-group").build())
        .putProperty("property1", new ConfigurationProperty.Builder().build())
        .putProperty("property2", new ConfigurationProperty.Builder().build())//
        .build();

    @Test
    public void shouldCreateBaseConnectors() {
        final ConnectorSettings settings = new ConnectorSettings.Builder().putConfiguredProperty("property2", "value2").build();

        final Connector connector = generator.baseConnectorFrom(template, settings);

        assertThat(connector).isEqualToIgnoringGivenFields(//
            new Connector.Builder()//
                .name("test-name")//
                .description("test-description")//
                .connectorGroup(template.getConnectorGroup())//
                .properties(template.getConnectorProperties())//
                .putConfiguredProperty("property2", "value2")//
                .build(),
            "id");
    }

    @Test
    public void shouldCreateBaseConnectorsWithGivenNameAndDescription() {
        final ConnectorSettings settings = new ConnectorSettings.Builder().name("given-name").description("given-description")
            .putConfiguredProperty("property2", "value2").build();

        final Connector connector = generator.baseConnectorFrom(template, settings);

        assertThat(connector).isEqualToIgnoringGivenFields(//
            new Connector.Builder()//
                .name("given-name")//
                .description("given-description")//
                .connectorGroup(template.getConnectorGroup())//
                .properties(template.getConnectorProperties())//
                .putConfiguredProperty("property2", "value2").build(),
            "id");
    }
}
