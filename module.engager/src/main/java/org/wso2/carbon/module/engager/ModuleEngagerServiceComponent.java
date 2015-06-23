package org.wso2.carbon.module.engager;

import org.apache.axis2.AxisFault;
import org.apache.axis2.description.AxisModule;
import org.apache.axis2.description.AxisService;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.utils.ConfigurationContextService;

/**
 * @scr.component name="org.wso2.carbon.module.engager.component" immediate="true"
 * @scr.reference name="config.context.service"
 * interface="org.wso2.carbon.utils.ConfigurationContextService"
 * cardinality="1..1" policy="dynamic"
 * bind="setConfigurationContextService"
 * unbind="unsetConfigurationContextService"
 */
public class ModuleEngagerServiceComponent {

    protected void activate(ComponentContext ctxt) {

        AxisService axisService = null;
        try {
            axisService = ServiceReferenceHolder.getServerConfigContext().getAxisConfiguration().getService("AuthenticationAdmin");
        } catch (AxisFault axisFault) {
            throw new RuntimeException(axisFault);
        }

        // engage the yahooCookieModule
        AxisModule module = ServiceReferenceHolder.getServerConfigContext().getAxisConfiguration().getModule("yahooCookieModule");
        if (module != null) {
            try {
                axisService.engageModule(module);
            } catch (AxisFault axisFault) {
                throw new RuntimeException(axisFault);
            }
        } else {
            System.out.println("Module yahooCookieModule not found, unable to engage to AuthenticationAdmin service");
        }

        System.out.println("Module engager component activated");
    }

    protected void deactivate(ComponentContext ctxt) {
        System.out.println("Module engager component de-activated");
    }

    protected void setConfigurationContextService(ConfigurationContextService contextService) {
        ServiceReferenceHolder.setClientConfigContext(contextService.getClientConfigContext());
        ServiceReferenceHolder.setServerConfigContext(contextService.getServerConfigContext());

    }

    protected void unsetConfigurationContextService(ConfigurationContextService contextService) {
        ServiceReferenceHolder.setClientConfigContext(null);
        ServiceReferenceHolder.setServerConfigContext(null);
    }
}
