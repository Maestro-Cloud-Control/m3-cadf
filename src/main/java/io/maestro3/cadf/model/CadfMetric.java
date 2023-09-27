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


import javax.validation.constraints.NotBlank;

public class CadfMetric {

    // type - cadf:identifier
    @NotBlank
    private String metricId;

    /*
     * The metrics unit(e.g., "msec.", "Hz", "GB", etc.)
     */
    @NotBlank
    // type - str
    private String unit;

    /*
     * A descriptive name for metric (e.g., “Response Time in Milliseconds", "Storage Capacity in Gigabytes", etc.).
     */
    // type - str
    private String name;

    public CadfMetric() {
        //json
    }

    public String getMetricId() {
        return metricId;
    }

    public void setMetricId(String metricId) {
        this.metricId = metricId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
