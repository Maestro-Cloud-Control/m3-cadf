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

/**
 * Tree of constants.
 * This tree is based on builder pattern, so it may look complicated here, but is very convenient to use:
 * <p/>
 * String relativeUri = CadfResourceTree.compute().machine().getRelativeUri();<br/>
 * String relativeUri = CadfResourceTree.compute().machine().vm().getRelativeUri();<br/>
 * ICadfResourceType resourceType = CadfResourceTree.compute().machine().vm();
 * <p/>
 */
public final class CadfResourceTypes {

    private CadfResourceTypes() {
    }

    // ====================================== "unknown" ======================================

    public static ICadfResourceType unknown() {
        return new CadfResourceType("unknown");
    }

    // ====================================== "system" ======================================

    public static ICadfResourceType system() {
        return new CadfResourceType("system");
    }

    // ====================================== "private agent" ======================================

    public static ICadfResourceType privateAgent() {
        return new CadfResourceType("private_agent");
    }

    // ====================================== "compute" ======================================

    public static CadfResourceCompute compute() {
        return new CadfResourceCompute("compute");
    }

    public static final class CadfResourceCompute extends CadfResourceType {
        private CadfResourceCompute(String parentUri) {
            super(parentUri);
        }

        // +++++++++++ "compute/machine" +++++++++++++
        public CadfResourceComputeMachine machine() {
            return new CadfResourceComputeMachine(relativeUri + "/machine");
        }

        public static final class CadfResourceComputeMachine extends CadfResourceType {
            private CadfResourceComputeMachine(String parentUri) {
                super(parentUri);
            }

            // +++++++++++ "compute/machine/vm" +++++++++++++
            public ICadfResourceType vm() {
                return new CadfResourceType(relativeUri + "/vm");
            }

            // +++++++++++ "compute/machine/hw" +++++++++++++
            public ICadfResourceType hw() {
                return new CadfResourceType(relativeUri + "/hw");
            }
        }
    }

    // ====================================== "storage" ======================================

    public static CadfResourceStorage storage() {
        return new CadfResourceStorage("storage");
    }

    public static final class CadfResourceStorage extends CadfResourceType {
        private CadfResourceStorage(String parentUri) {
            super(parentUri);
        }

        // +++++++++++ "storage/checkpoint" +++++++++++++
        public ICadfResourceType checkpoint() {
            return new CadfResourceType(relativeUri + "/checkpoint");
        }

        // +++++++++++ "storage/volume" +++++++++++++
        public ICadfResourceType volume() {
            return new CadfResourceType(relativeUri + "/volume");
        }

        public ICadfResourceType database() {
            return new CadfResourceType(relativeUri + "/database");
        }
    }

    // ====================================== "data" ======================================

    public static CadfResourceData data() {
        return new CadfResourceData("data");
    }

    public static class CadfResourceData extends CadfResourceType {
        private CadfResourceData(String parentUri) {
            super(parentUri);
        }

        // +++++++++++ "data/zone" +++++++++++++
        public final ICadfResourceType zone() {
            return new CadfResourceType(relativeUri + "/zone");
        }

        // +++++++++++ "data/image" +++++++++++++
        public final ICadfResourceType image() {
            return new CadfResourceType(relativeUri + "/image");
        }

        public final CadfTemplateData template() {
            return new CadfTemplateData(relativeUri + "/template");
        }

        public static class CadfTemplateData extends CadfResourceData {

            private CadfTemplateData(String parentUri) {
                super(parentUri);
            }

            public final CadfTemplateData stack() {
                return new CadfTemplateData(relativeUri + "/stack");
            }
        }

        // +++++++++++ "data/security" +++++++++++++
        public final CadfResourceDataSecurity security() {
            return new CadfResourceDataSecurity(relativeUri + "/security");
        }

        public static final class CadfResourceDataSecurity extends CadfResourceType {
            private CadfResourceDataSecurity(String parentUri) {
                super(parentUri);
            }

            // +++++++++++ "data/security/account" +++++++++++++
            public final CadfResourceDataSecurityAccount account() {
                return new CadfResourceDataSecurityAccount(relativeUri + "/account");
            }

            public static final class CadfResourceDataSecurityAccount extends CadfResourceType {
                private CadfResourceDataSecurityAccount(String parentUri) {
                    super(parentUri);
                }

                // +++++++++++ "data/security/account/user" +++++++++++++
                public ICadfResourceType user() {
                    return new CadfResourceType(relativeUri + "/user");
                }

                // +++++++++++ "data/security/account/admin" +++++++++++++
                public ICadfResourceType admin() {
                    return new CadfResourceType(relativeUri + "/admin");
                }

                // +++++++++++ "data/security/account/access" +++++++++++++
                public ICadfResourceType access() {
                    return new CadfResourceType(relativeUri + "/access");
                }
            }

            // +++++++++++ "data/security/iam" +++++++++++++
            public CadfResourceDataSecurityIam iam() {
                return new CadfResourceDataSecurityIam(relativeUri + "/iam");
            }

            public static final class CadfResourceDataSecurityIam extends CadfResourceType {
                private CadfResourceDataSecurityIam(String parentUri) {
                    super(parentUri);
                }

                // +++++++++++ "data/security/iam/user" +++++++++++++
                public ICadfResourceType user() {
                    return new CadfResourceType(relativeUri + "/user");
                }

                // +++++++++++ "data/security/iam/group" +++++++++++++
                public ICadfResourceType group() {
                    return new CadfResourceType(relativeUri + "/group");
                }

                // +++++++++++ "data/security/iam/role" +++++++++++++
                public ICadfResourceType role() {
                    return new CadfResourceType(relativeUri + "/role");
                }

                // +++++++++++ "data/security/iam/policy" +++++++++++++
                public ICadfResourceType policy() {
                    return new CadfResourceType(relativeUri + "/policy");
                }
            }

            // +++++++++++ "data/security/network" +++++++++++++
            public CadfResourceDataSecurityNetwork network() {
                return new CadfResourceDataSecurityNetwork(relativeUri + "/network");
            }

            public static final class CadfResourceDataSecurityNetwork extends CadfResourceType {
                private CadfResourceDataSecurityNetwork(String parentUri) {
                    super(parentUri);
                }

                // +++++++++++ "data/security/network/securityGroup" +++++++++++++
                public ICadfResourceType securityGroup() {
                    return new CadfResourceType(relativeUri + "/securityGroup");
                }

                // +++++++++++ "data/security/network/prefixList" +++++++++++++
                public ICadfResourceType prefixList() {
                    return new CadfResourceType(relativeUri + "/prefixList");
                }
            }
        }
    }

    // ====================================== "service" ======================================

    public static CadfResourceService service() {
        return new CadfResourceService("service");
    }

    public static final class CadfResourceService extends CadfResourceType {
        private CadfResourceService(String parentUri) {
            super(parentUri);
        }

        // +++++++++++ "service/platform" +++++++++++++
        public final CadfResourceServicePlatform platform() {
            return new CadfResourceServicePlatform(relativeUri + "/platform");
        }

        public static final class CadfResourceServicePlatform extends CadfResourceType {
            public CadfResourceServicePlatform(String parentUri) {
                super(parentUri);
            }

        }

        public CadfResourceServiceOss oss() {
            return new CadfResourceServiceOss(relativeUri + "/oss");
        }

        public static final class CadfResourceServiceOss extends CadfResourceType {

            private CadfResourceServiceOss(String relativeUri) {
                super(relativeUri);
            }

            public ICadfResourceType stack() {
                return new CadfResourceType(relativeUri + "/stack");
            }
        }

        // +++++++++++ "service/composition" +++++++++++++
        public final CadfResourceServiceComposition composition() {
            return new CadfResourceServiceComposition(relativeUri + "/composition");
        }

        public static final class CadfResourceServiceComposition extends CadfResourceType {
            private CadfResourceServiceComposition(String relativeUri) {
                super(relativeUri);
            }

            // +++++++++++ "service/composition/orchestration" +++++++++++++
            public final CadfResourceServiceCompositionOrchestration orchestration() {
                return new CadfResourceServiceCompositionOrchestration(relativeUri + "/orchestration");
            }

            public static final class CadfResourceServiceCompositionOrchestration extends CadfResourceType {
                private CadfResourceServiceCompositionOrchestration(String relativeUri) {
                    super(relativeUri);
                }

                // +++++++++++ "service/composition/orchestration/schedule" +++++++++++++
                public ICadfResourceType schedule() {
                    return new CadfResourceType(relativeUri + "/schedule");
                }
            }
        }

        // +++++++++++ "service/bss (Business Support Services)" +++++++++++++
        public final CadfResourceServiceBss bss() {
            /*
             * The logical classification grouping for services that are identified to support business activities.
             */
            return new CadfResourceServiceBss(relativeUri + "/bss");
        }

        public static final class CadfResourceServiceBss extends CadfResourceType {
            private CadfResourceServiceBss(String relativeUri) {
                super(relativeUri);
            }

            // +++++++++++ "service/bss/location" +++++++++++++
            public final ICadfResourceType location() {
                /*
                 * Business services to manage the location, physical or virtual, of cloud-based resources as well as clients (e.g., mobile devices)
                 */
                return new CadfResourceType(relativeUri + "/location");
            }

            public final CadfServiceBssScope scope() {
                return new CadfServiceBssScope(relativeUri + "/scope");
            }

            public static final class CadfServiceBssScope extends CadfResourceType {
                private CadfServiceBssScope(String relativeUri) {
                    super(relativeUri);
                }

                public final ICadfResourceType project() {
                    return new CadfResourceType(relativeUri + "/project");
                }

            }

        }
    }

}


