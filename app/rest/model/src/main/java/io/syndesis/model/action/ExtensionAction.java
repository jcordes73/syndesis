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
package io.syndesis.model.action;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.syndesis.model.WithId;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ExtensionAction.Builder.class)
@SuppressWarnings("immutables")
public interface ExtensionAction extends Action<ExtensionDescriptor>, WithId<ExtensionAction> {

    @Override
    @Value.Default
    default String getActionType() {
        return Action.TYPE_EXTENSION;
    }

    @Override
    default ExtensionAction withId(String id) {
        return new Builder().createFrom(this).id(id).build();
    }

    class Builder extends ImmutableExtensionAction.Builder {
    }


    enum Kind {
        STEP,
        BEAN,
        ENDPOINT
    }
}
