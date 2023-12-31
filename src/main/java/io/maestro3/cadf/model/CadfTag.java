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
 * Can look like this:  //GRC20.gov/cloud/auditplan?value=audit10
 */
public class CadfTag {

    private String name;

    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public final String get() {
        if (value != null && !value.isEmpty()) {
            return name + "?value=" + value;
        } else {
            return name;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final CadfTag tag = new CadfTag();

        public Builder withName(String name) {
            tag.name = name;
            return this;
        }

        public Builder withValue(String value) {
            tag.value = value;
            return this;
        }

        public CadfTag build() {
            Assert.notNull(tag.name, "Name cannot be null");
            Assert.notNull(tag.value, "Value cannot be null");
            return tag;
        }
    }

    @Override
    public String toString() {
        return "CadfTag{" +
            "name='" + name + '\'' +
            ", value='" + value + '\'' +
            '}';
    }
}
