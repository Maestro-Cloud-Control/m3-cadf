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

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

// type - cadf:attachment (http://schemas.dmtf.org/cloud/audit/1.0/attachment)
public class CadfAttachment<T> {

    // type - any URI
    @NotBlank
    private String contentType;

    @NotBlank
    @NotEmpty
    @Valid
    private T content;

    /**
     * An optional name that can be used to provide an identifying name for the content.
     */
    // type - str
    private String name;

    public CadfAttachment() {
    }

    public CadfAttachment(String contentType, String name) {
        this.contentType = contentType;
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public T getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CadfAttachment<CadfResource> ofResource(CadfResource resource) {
        Assert.notNull(resource, "resource can not be null");

        CadfAttachment<CadfResource> attachment = new CadfAttachment<>();
        attachment.contentType = CadfResource.getFullTypeUri(resource);
        attachment.content = resource;
        attachment.name = resource.getName();

        return attachment;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private final CadfAttachment<T> attachment;

        Builder() {
            attachment = new CadfAttachment<>();
        }

        public Builder<T> withContentType(String type) {
            Assert.hasText(type, "Type cannot be null or empty");
            attachment.contentType = type;
            return this;
        }

        public Builder<T> withName(String name) {
            Assert.hasText(name, "Name cannot be null or empty");
            attachment.name = name;
            return this;
        }

        public Builder<T> withContent(T content) {
            Assert.notNull(content, "Content cannot be null");
            attachment.content = content;
            return this;
        }

        public CadfAttachment<T> build() {
            Assert.hasText(attachment.contentType, "Type cannot be null or empty");
            Assert.notNull(attachment.content, "Content cannot be null");
            return attachment;
        }
    }

    @Override
    public String toString() {
        return "CadfAttachment{" +
            "contentType='" + contentType + '\'' +
            ", content=" + content +
            ", name='" + name + '\'' +
            '}';
    }

}
