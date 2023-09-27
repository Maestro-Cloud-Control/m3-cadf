/*
 * Copyright 2023 Maestro Cloud Control LLC
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

package io.maestro3.cadf.model;

import io.maestro3.cadf.util.Assert;

/**
 * This enum constant list is FINAL. Do not add anything here. According to CADF, these are the only possible
 * top-level values for the eventType property of an event.
 */
public enum CadfEventType {

    MONITOR("monitor"),
    ACTIVITY("activity"),
    CONTROL("control");

    private final String name;

    CadfEventType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CadfEventType fromName(String name) {
        Assert.hasText(name, "Name cannot be null or empty");
        for (CadfEventType eventType : values()) {
            if (name.equals(eventType.name)) {
                return eventType;
            }
        }
        throw new IllegalArgumentException("Failed to find cadf event type by name: " + name);
    }
}
