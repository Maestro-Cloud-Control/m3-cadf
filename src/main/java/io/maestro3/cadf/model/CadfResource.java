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

import io.maestro3.cadf.ICadfResourceType;
import io.maestro3.cadf.util.Assert;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;

// type - cadf:resource (http://schemas.dmtf.org/cloud/audit/1.0/resource)
public class CadfResource {
    private static final String FULL_ROOT_URI = "http://schemas.dmtf.org/cloud/audit/1.0/resource";

    // =============================== all of the following are REQUIRED ======================================

    @NotBlank
    // type - cadf:identifier
    private String id; // "maestro2:uuid"

    /*
     * Within the context of the CADF Event Record, specifically the "typeURI" property of the CADF Resource type,
     * the CADF Resource Taxonomy URI (http://schemas.dmtf.org/cloud/audit/1.0/taxonomy/resource)
     * can be assumed to be the base URI.
     */
    @NotBlank
    // type - cadf:path
    private String typeURI; // "compute/machine", "data"

    // =============================== all of the following are OPTIONAL ======================================

    /*
     * The optional local name for the resource (not necessarily unique).
     */
    // type - string
    private String name;

    /*
     * The optional security credentials associated with the resource’s identity.
     */
    private CadfCredential credential;

    /*
     * An optional array of extended or domain-specific information about the resource or its context.
     */
    private List<CadfAttachment> attachments;

    public String getId() {
        return id;
    }

    public String getTypeURI() {
        return typeURI;
    }

    public String getName() {
        return name;
    }

    public CadfCredential getCredential() {
        return credential;
    }

    public List<CadfAttachment> getAttachments() {
        return attachments;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTypeURI(String typeURI) {
        this.typeURI = typeURI;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredential(CadfCredential credential) {
        this.credential = credential;
    }

    public void setAttachments(List<CadfAttachment> attachments) {
        this.attachments = attachments;
    }
    // ================================= builder ========================================

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private final CadfResource resource;

        public Builder() {
            resource = new CadfResource();
        }

        /**
         * Required.
         */
        public Builder withId(String id) {
            Assert.hasText(id, "resource id can not be null or empty");
            resource.id = id;
            return this;
        }

        /**
         * Required.
         */
        public Builder ofType(ICadfResourceType resourceType) {
            Assert.notNull(resourceType, "resourceType can not be null");
            resource.typeURI = resourceType.getRelativeUri();
            return this;
        }

        public Builder withName(String name) {
            Assert.hasText(name, "name must not be null or empty");
            resource.name = name;
            return this;
        }

        public Builder withAttachments(CadfAttachment... attachments) {
            Assert.notNull(attachments, "attachments must not be null");
            resource.attachments = Arrays.asList(attachments);
            return this;
        }

        public Builder withAttachments(List<CadfAttachment> attachments) {
            Assert.notNull(attachments, "attachments must not be null");
            resource.attachments = attachments;
            return this;
        }

        public CadfResource build() {
            Assert.hasText(resource.id, "id can not be null or empty");
            Assert.notNull(resource.typeURI, "resource type can not be null");
            return resource;
        }

    }

    public static String getFullTypeUri(CadfResource resource) {
        if (resource.getTypeURI().startsWith(FULL_ROOT_URI)) {
            return resource.getTypeURI();
        } else {
            return FULL_ROOT_URI + "/" + resource.getTypeURI();
        }
    }

    @Override
    public String toString() {
        return "CadfResource{" +
            "id='" + id + '\'' +
            ", typeURI='" + typeURI + '\'' +
            ", name='" + name + '\'' +
            ", credential=" + credential +
            ", attachments=" + attachments +
            '}';
    }
}
