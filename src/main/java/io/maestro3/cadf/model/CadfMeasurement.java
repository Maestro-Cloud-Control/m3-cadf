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

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public class CadfMeasurement<T> {

    /*
     * The quantitative or qualitative result of a measurement from applying the associated metric.
     * The measure value could be boolean, integer, double, a scalar value (e.g., from an enumeration),
     * or a more complex value.
     */
    @NotBlank
    @NotEmpty
    @Valid
    private T result;

    // =============================== one of these is required ======================================

    // type - cadf:identifier
    private String metricId;

    @Valid
    private CadfMetric metric;

    // =============================== one of these is optional ======================================

    // type - cadf:identifier
    private String calculatedById;

    /*
     * An optional description of the resource that calculated the measurement
     * (if it is not the same resource described by the INITIATOR already provided in the same CADF Event Record).
     */
    @Valid
    private CadfResource calculatedBy;

    public CadfMeasurement() {
        //json
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMetricId() {
        return metricId;
    }

    public void setMetricId(String metricId) {
        this.metricId = metricId;
    }

    public CadfMetric getMetric() {
        return metric;
    }

    public void setMetric(CadfMetric metric) {
        this.metric = metric;
    }

    public String getCalculatedById() {
        return calculatedById;
    }

    public void setCalculatedById(String calculatedById) {
        this.calculatedById = calculatedById;
    }

    public CadfResource getCalculatedBy() {
        return calculatedBy;
    }

    public void setCalculatedBy(CadfResource calculatedBy) {
        this.calculatedBy = calculatedBy;
    }

// ================================= builder ========================================

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static final class Builder<T> {
        private final CadfMeasurement<T> measurement;

        public Builder() {
            this.measurement = new CadfMeasurement<>();
        }

        public Builder<T> withResult(T result) {
            Assert.notNull(result, "measurement result can not be null");
            measurement.result = result;
            return this;
        }

        public Builder<T> withMetricId(String metricId) {
            Assert.hasText(metricId, "metricId can not be null or empty");
            Assert.isNull(measurement.metric, "you can specify either metricId or metric, not both");
            measurement.metricId = metricId;
            return this;
        }

        public Builder<T> withMetric(CadfMetric metric) {
            Assert.notNull(metric, "metric can not be null or empty");
            Assert.isNull(measurement.metricId, "you can specify either metricId or metric, not both");
            measurement.metric = metric;
            return this;
        }

        public CadfMeasurement<T> build() {
            Assert.notNull(measurement.result, "measurement result can not be null");
            Assert.isTrue((measurement.metric == null && measurement.metricId != null)
                    || (measurement.metric != null && measurement.metricId == null),
                "you can specify either metricId or metric, not both");

            return measurement;
        }

    }

}
