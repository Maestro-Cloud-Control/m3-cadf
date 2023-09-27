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
import io.maestro3.cadf.util.Assert;

import java.util.Objects;

public final class CadfActions {

    private CadfActions() {
        throw new UnsupportedOperationException("Instantiation is forbidden.");
    }

    // ======================= Onboarding ==========================================

    public static CadfOnboardAction onboard() {
        return new CadfOnboardAction("");
    }

    public static final class CadfOnboardAction extends CadfAction {
        CadfOnboardAction(String relativeUri) {
            super("onboard" + relativeUri);
        }

        public ICadfAction azure() {
            return new CadfOnboardAction("/azure");
        }
    }

    // ======================= Security ============================================

    public static CadfSecurityAction security() {
        return new CadfSecurityAction("");
    }

    public static class CadfSecurityAction extends CadfAction {
        CadfSecurityAction(String relativeUri) {
            super("security" + relativeUri);
        }

        public CadfUnauthorizedAction unauthorized() {
            return new CadfUnauthorizedAction("");
        }

        public static final class CadfUnauthorizedAction extends CadfSecurityAction {
            CadfUnauthorizedAction(String relativeUri) {
                super("/unauthorized" + relativeUri);
            }

            public ICadfAction permissionChange() {
                return new CadfUnauthorizedAction("/permissionChange");
            }

            public ICadfAction networkChange() {
                return new CadfUnauthorizedAction("/networkChange");
            }
        }
    }

    // ======================= general resource management =========================

    public static ICadfAction create() {
        return new CadfAction("create");
    }

    public static ICadfAction read() {
        return new CadfAction("read");
    }

    public static ICadfAction renew() {
        return new CadfAction("renew");
    }

    public static ICadfAction enable() {
        return new CadfAction("enable");
    }

    public static ICadfAction send() {
        return new CadfAction("send");
    }

    public static CadfUpdateAction update() {
        return new CadfUpdateAction("");
    }

    public static final class CadfUpdateAction extends CadfAction {
        CadfUpdateAction(String relativeUri) {
            super("update" + relativeUri);
        }

        public ICadfAction size() {
            return new CadfUpdateAction("/size");
        }
    }

    public static CadfTerminatedAction terminated() {
        return new CadfTerminatedAction("");
    }

    public static final class CadfTerminatedAction extends CadfAction {
        CadfTerminatedAction(String relativeUri) {
            super("terminate" + relativeUri);
        }

        public ICadfAction instance() {
            return new CadfAction("/instance");
        }

        public ICadfAction hardware() {
            return new CadfAction("/hardware");
        }
    }

    public static ICadfAction delete() {
        return new CadfAction("delete");
    }

    public static ICadfAction error() {
        return new CadfAction("error");
    }

    public static ICadfAction lose() {
        return new CadfAction("lose");
    }

    public static ICadfAction discover() {
        return new CadfAction("discover");
    }

    public static ICadfAction attach() {
        return new CadfAction("attach");
    }

    public static ICadfAction detach() {
        return new CadfAction("detach");
    }

    public static ICadfAction tag() {
        return new CadfAction("tag");
    }

    public static ICadfAction untag() {
        return new CadfAction("untag");
    }

    public static ICadfAction lock() {
        return new CadfAction("lock");
    }

    public static ICadfAction unlock() {
        return new CadfAction("unlock");
    }

    public static ICadfAction prolongLock() {
        return new CadfAction("prolongLock");
    }

    public static ICadfAction installCW() {
        return new CadfAction("installCW");
    }

    public static ICadfAction uninstallCW() {
        return new CadfAction("uninstallCW");
    }

    public static CadfMoveAction move() {
        return new CadfMoveAction("");
    }

    public static final class CadfMoveAction extends CadfAction {

        CadfMoveAction(String relativeUri) {
            super("move" + relativeUri);
        }

        public ICadfAction to() {
            return new CadfAction(getRelativeUri() + "/to");
        }

        public ICadfAction from() {
            return new CadfAction(getRelativeUri() + "/from");
        }
    }

    // ============================= monitoring ====================================

    public static CadfCaptureAction monitor() {
        return new CadfCaptureAction("");
    }

    public static final class CadfCaptureAction extends CadfAction {

        CadfCaptureAction(String relativeUri) {
            super("capture" + relativeUri);
        }

        public CadfCaptureAction start() {
            return new CadfCaptureAction("/start");
        }

        public CadfCaptureAction stop() {
            return new CadfCaptureAction("/stop");
        }

        public CadfCaptureAction update() {
            return new CadfCaptureAction("/update");
        }

        public CadfCaptureAction instance() {
            return new CadfCaptureAction("/instance");
        }

        public CadfCaptureAction hardware() {
            return new CadfCaptureAction("/hardware");
        }

        public CadfCaptureAction volume() {
            return new CadfCaptureAction("/volume");
        }

        public CadfCaptureAction checkpoint() {
            return new CadfCaptureAction("/checkpoint");
        }

    }

    // ============================= commands ====================================
    public static CadfCommandAction command() {
        return new CadfCommandAction("");
    }

    public static final class CadfCommandAction extends CadfAction {
        CadfCommandAction(String relativeUri) {
            super("command" + relativeUri);
        }

        public CadfCommandAction start() {
            return new CadfCommandAction("/start");
        }

        public CadfCommandAction create() {
            return new CadfCommandAction("/create");
        }

        public CadfCommandAction stop() {
            return new CadfCommandAction("/stop");
        }

        public CadfCommandAction reboot() {
            return new CadfCommandAction("/reboot");
        }

        public CadfCommandAction delete() {
            return new CadfCommandAction("/delete");
        }

    }

    // ===================== workload and data management ==========================

    public static ICadfAction start() {
        return new CadfAction("start");
    }

    public static ICadfAction stop() {
        return new CadfAction("stop");
    }

    public static ICadfAction plan() {
        return new CadfAction("plan");
    }

    public static ICadfAction suspend() {
        return new CadfAction("suspend");
    }

    public static ICadfAction paused() {
        return new CadfAction("paused");
    }

    public static ICadfAction rescue() {
        return new CadfAction("rescue");
    }

    public static ICadfAction configure() {
        return new CadfAction("configure");
    }

    public static ICadfAction allow() {
        return new CadfAction("allow");
    }

    public static ICadfAction deny() {
        return new CadfAction("deny");
    }

    public static ICadfAction restore() {
        return new CadfAction("restore");
    }

    public static ICadfAction deploy() {
        return new CadfAction("deploy");
    }

    public static ICadfAction undeploy() {
        return new CadfAction("undeploy");
    }

    public static ICadfAction disable() {
        return new CadfAction("disable");
    }

    public static ICadfAction cadfNotify() {
        return new CadfAction("notify");
    }

    public static ICadfAction scan() {
        return new CadfAction("scan");
    }

//    backup
//    capture
//    configure
//    deploy
//    disable
//    enable
//    restore
//    start
//    stop
//    undeploy


    // =============================================================================

    private static class CadfAction implements ICadfAction {

        private final String relativeUri;

        CadfAction(String relativeUri) {
            this.relativeUri = relativeUri;
        }

        @Override
        public String getRelativeUri() {
            return relativeUri;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CadfAction that = (CadfAction) o;
            return Objects.equals(relativeUri, that.relativeUri);
        }

        @Override
        public int hashCode() {
            return Objects.hash(relativeUri);
        }
    }

    public static ICadfAction byRelativeURI(String uri) {
        Assert.notNull(uri, "uri");
        switch (uri) {
            case "configure":
                return configure();
            case "suspend":
                return suspend();
            case "stop":
                return stop();
            case "plan":
                return plan();
            case "start":
                return start();
            case "monitor":
                return monitor();
            case "monitor/start":
                return monitor().start();
            case "monitor/stop":
                return monitor().stop();
            case "monitor/update":
                return monitor().update();
            case "monitor/instance":
                return monitor().instance();
            case "monitor/hardware":
                return monitor().hardware();
            case "monitor/volume":
                return monitor().volume();
            case "monitor/checkpoint":
                return monitor().checkpoint();
            case "command":
                return command();
            case "command/create":
                return command().create();
            case "command/start":
                return command().start();
            case "command/stop":
                return command().stop();
            case "command/reboot":
                return command().reboot();
            case "command/delete":
                return command().delete();
            case "move":
                return move();
            case "move/to":
                return move().to();
            case "move/from":
                return move().from();
            case "detach":
                return detach();
            case "attach":
                return attach();
            case "discover":
                return discover();
            case "lose":
                return lose();
            case "delete":
                return delete();
            case "update":
                return update();
            case "update/size":
                return update().size();
            case "read":
                return read();
            case "create":
                return create();
            case "renew":
                return renew();
            case "enable":
                return enable();
            case "send":
                return send();
            case "terminated":
                return terminated();
            case "terminated/instance":
                return terminated().instance();
            case "terminated/hardware":
                return terminated().hardware();
            case "tag":
                return tag();
            case "untag":
                return untag();
            case "allow":
                return allow();
            case "deny":
                return deny();
            case "restore":
                return restore();
            case "deploy":
                return deploy();
            case "undeploy":
                return undeploy();
            case "disable":
                return disable();
            case "notify":
                return cadfNotify();
            case "lock":
                return lock();
            case "unlock":
                return unlock();
            case "prolongLock":
                return prolongLock();
            case "installCW":
                return installCW();
            case "scan":
                return scan();
            case "uninstallCW":
                return uninstallCW();
            case "onboard/azure":
                return onboard().azure();
            case "security/unauthorized/permissionChange":
                return security().unauthorized().permissionChange();
            case "security/unauthorized/networkChange":
                return security().unauthorized().networkChange();
            default:
                throw new UnsupportedOperationException("Action with uri: '" + uri + "' is unsupported.");
        }
    }

}
