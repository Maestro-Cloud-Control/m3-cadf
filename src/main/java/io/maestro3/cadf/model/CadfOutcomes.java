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

import io.maestro3.cadf.ICadfOutcome;

public final class CadfOutcomes {

    // this is the final list of top level outcomes! do not add any! you may only add children

    public static ICadfOutcome success() {
        return new CadfOutcome("success");
    }

    public static ICadfOutcome failure() {
        return new CadfOutcome("failure");
    }

    public static ICadfOutcome unknown() {
        return new CadfOutcome("unknown");
    }

    public static ICadfOutcome pending() {
        return new CadfOutcome("pending");
    }

    // =======================================================================

    private static class CadfOutcome implements ICadfOutcome {
        final String relativeUri;

        private CadfOutcome(String relativeUri) {
            this.relativeUri = relativeUri;
        }

        @Override
        public String getRelativeUri() {
            return relativeUri;
        }
    }

    public static ICadfOutcome fromRelativeUri(String uri) {
        switch (uri) {
            case "success":
                return success();
            case "failure":
                return failure();
            case "pending":
                return pending();
            default:
                return unknown();
        }
    }

}
