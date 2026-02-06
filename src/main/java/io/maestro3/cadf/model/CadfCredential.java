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

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

/**
 * Valid Credential typed data SHALL contain at least one valid identify token.
 */
// type - cadf:credential (http://schemas.dmtf.org/cloud/audit/1.0/credential)
public class CadfCredential<T> {

    // not required according to spec - but looks like it should be required, because token may be of any type - so how to know which one?
    // type - any URI
    private String type;

    /*
     * The “token” property SHALL contain the primary identity token, credential, or assertion value that was used to
     * represent the INITIATOR’s access credentials at the time an authorized access (i.e., ACTION) to the TARGET
     * resource(s) was observed (by the OBSERVER resource).
     */
    @NotBlank
    @NotEmpty
    @Valid
    private T token;

    /*
     * The trusted authority (a service) that understands and can verify the credential.
     */
    // type - any URI
    private String authority;

    public CadfCredential() {
        //json
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getToken() {
        return token;
    }

    public void setToken(T token) {
        this.token = token;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
