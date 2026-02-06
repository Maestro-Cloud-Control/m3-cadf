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

import io.maestro3.cadf.ICadfAction;
import io.maestro3.cadf.ICadfOutcome;
import io.maestro3.cadf.util.Assert;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class CadfAuditEvent {

    // =============================== all of the following are required ======================================

    @NotBlank
    // type - cadf:path
    private String typeURI = "http://schemas.dmtf.org/cloud/audit/1.0/event";

    @NotBlank
    // type - cadf:identifier
    protected String id; // "maestro2:uuid"

    @NotBlank
    protected String eventType; // monitor or activity or control

    @NotBlank
    // type - cadf:timestamp
    protected String eventTime; // "yyyy-mm-ddThh:mm:ss(.s+)+00:00"

    /*
     * Within the context of the CADF Event Record, specifically when used as value for the "action" property
     * of the CADF Event data type, the CADF Action Taxonomy URI (http://schemas.dmtf.org/cloud/audit/1.0/taxonomy/action/)
     * can be assumed to be the base URI.
     */
    @NotBlank
    // type - cadf:path
    protected String action; // "read", "read/list", "allow"...

    /*
     *  Within the context of the CADF Event Record, specifically when used as a value for the "outcome" property of the
     *  CADF Event data type, the CADF Outcome Taxonomy URI (http://schemas.dmtf.org/cloud/audit/1.0/taxonomy/outcome/)
     *  can be assumed to be the base URI.
     */
    @NotBlank
    // type - cadf:path
    protected String outcome; // success or failure or unknown or pending. No siblings, children permitted

    // =============================== one of these is required ======================================

    // type - cadf:identifier
//    private String initiatorId; // must be defined on upper level

    @Valid
    protected CadfResource initiator;

    // =============================== one of these is required ======================================

    // type - cadf:identifier
//    private String targetId; // must be defined on upper level

    @Valid
    protected CadfResource target;

    // =============================== one of these is required ======================================

    // type - cadf:identifier
//    private String observerId; // must be defined on upper level

    @Valid
    protected CadfResource observer;

    // =============================== all of the following are optional ======================================

    @Valid
    List<CadfMeasurement> measurements; // required if eventType=monitor

    /*
     * This optional property represents a descriptive name for the this.
     * This property SHALL NOT be used in place of the required CADF Event property “id”.
     */
    // type - str
    protected String name;

    /*
     * This optional property describes domain-relative severity assigned to the event by the OBSERVER.
     * This property's value is non-normative, but is the recommended place where such information should be placed.
     */
    // type - str
    protected String severity;

    protected List<CadfTag> tags;

    /*
     * An optional array of extended or domain-specific information about the event or its context.
     */
    protected List<CadfAttachment> attachments;
    protected List<CadfAttachment> secureAttachments;

    protected CadfAuditEvent() {
    }

    public CadfAuditEvent(String id) {
        this.id = id;
    }

    public String getTypeURI() {
        return typeURI;
    }

    public String getId() {
        return id;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getAction() {
        return action;
    }

    public String getOutcome() {
        return outcome;
    }

    public CadfResource getInitiator() {
        return initiator;
    }

    public CadfResource getTarget() {
        return target;
    }

    public CadfResource getObserver() {
        return observer;
    }

    public List<CadfMeasurement> getMeasurements() {
        return measurements;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeverity() {
        return severity;
    }

    public List<CadfTag> getTags() {
        return tags;
    }

    public List<CadfAttachment> getAttachments() {
        return attachments;
    }

    public List<CadfAttachment> getSecureAttachments() {
        return secureAttachments;
    }

    // ================================= builder ========================================

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String eventType;
        private String eventTime;
        private String action;
        private String outcome;
        private CadfResource initiator;
        private CadfResource target;
        private CadfResource observer;
        private List<CadfMeasurement> measurements;
        private List<CadfAttachment> attachments;
        private List<CadfAttachment> secureAttachments;
        private List<CadfTag> tags;
        private String name;
        private String severity;

        Builder() {
        }

        /**
         * Required.
         */
        public Builder withId(String id) {
            Assert.hasText(id, "id can not be null or empty");
            this.id = id;
            return this;
        }

        /**
         * Required.
         */
        public Builder withEventType(CadfEventType eventType) {
            Assert.notNull(eventType, "eventType can not be null");
            this.eventType = eventType.getName();
            return this;
        }

        /**
         * Required.<br/>
         * You do not have to do anything to the date before passing it here. It will be serialized with its time zone
         * and deserialized the same way.
         *
         * @param date
         */
        public Builder withEventTime(String date) {
            Assert.notNull(date, "event date must not be null");

           /* SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.US);

            String formattedDate = dateFormat.format(date);
            if (formattedDate.endsWith("Z")) { // have to change zulu time to +00:00
                formattedDate = formattedDate.substring(0, formattedDate.length() - 1) + "+00:00";
            }
            // 2001-07-04T12:08:56.235-07:00
            // 2001-07-04T12:08:56.235+03:00
            // 2001-07-04T12:08:56.235+00:00  <--- here it is, no zulu time, no Z.*/
            this.eventTime = date;
            return this;
        }

        /**
         * Required.
         */
        public Builder withAction(ICadfAction action) {
            Assert.notNull(action, "action must not be null");
            this.action = action.getRelativeUri();
            return this;
        }

        /**
         * Required.
         */
        public Builder withOutcome(ICadfOutcome outcome) {
            Assert.notNull(outcome, "outcome must not be null");
            this.outcome = outcome.getRelativeUri();
            return this;
        }

        public Builder withInitiator(CadfResource initiator) {
            Assert.notNull(initiator, "initiator must not be null");
            this.initiator = initiator;
            return this;
        }

        public Builder withTarget(CadfResource target) {
            Assert.notNull(target, "target must not be null");
            this.target = target;
            return this;
        }

        public Builder withObserver(CadfResource observer) {
            Assert.notNull(observer, "observer must not be null");
            this.observer = observer;
            return this;
        }

        public Builder withMeasurements(List<CadfMeasurement> measurements) {
            Assert.notNull(measurements, "measurements should not be null");
            this.measurements = measurements;
            return this;
        }

        public Builder withAttachments(List<CadfAttachment> attachments) {
            Assert.notNull(attachments, "attachment list can not be null");
            this.attachments = attachments;
            return this;
        }

        public Builder withSecureAttachments(List<CadfAttachment> secureAttachments) {
            Assert.notNull(secureAttachments, "secureAttachments list can not be null");
            this.secureAttachments = secureAttachments;
            return this;
        }

        public Builder withTags(List<CadfTag> tags) {
            Assert.notNull(tags, "tags list can not be null");
            this.tags = tags;
            return this;
        }

        /**
         * Optional field
         */
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSeverity(String severity) {
            this.severity = severity;
            return this;
        }

        public CadfAuditEvent build() {
            Assert.hasText(this.id, "you must set not empty event id");
            Assert.hasText(this.eventType, "you must set event type");
            Assert.hasText(this.eventTime, "you must set event time");
            Assert.hasText(this.action, "you must provide not empty event action");
            Assert.hasText(this.outcome, "you must provide not empty event outcome");
            Assert.notNull(this.initiator, "you must provide event initiator");
            Assert.notNull(this.target, "you must provide event target");
            Assert.notNull(this.observer, "you must provide event observer");
            CadfAuditEvent cadfAuditEvent = new CadfAuditEvent();
            cadfAuditEvent.id = this.id;
            cadfAuditEvent.eventType = this.eventType;
            cadfAuditEvent.eventTime = this.eventTime;
            cadfAuditEvent.action = this.action;
            cadfAuditEvent.outcome = this.outcome;
            cadfAuditEvent.initiator = this.initiator;
            cadfAuditEvent.target = this.target;
            cadfAuditEvent.observer = this.observer;
            cadfAuditEvent.name = this.name;
            cadfAuditEvent.severity = this.severity;
            cadfAuditEvent.attachments = this.attachments;
            cadfAuditEvent.secureAttachments = this.secureAttachments;
            cadfAuditEvent.measurements = this.measurements;
            cadfAuditEvent.tags = this.tags;
            return cadfAuditEvent;
        }

    }

    @Override
    public String toString() {
        return "CadfAuditEvent{" +
            "typeURI='" + typeURI + '\'' +
            ", id='" + id + '\'' +
            ", eventType='" + eventType + '\'' +
            ", eventTime='" + eventTime + '\'' +
            ", action='" + action + '\'' +
            ", outcome='" + outcome + '\'' +
            ", initiator=" + initiator +
            ", target=" + target +
            ", observer=" + observer +
            ", measurements=" + measurements +
            ", name='" + name + '\'' +
            ", severity='" + severity + '\'' +
            ", tags=" + tags +
            ", attachments=" + attachments +
            '}';
    }
}
