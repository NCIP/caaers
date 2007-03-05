package gov.nih.nci.security.acegi.csm.authorization;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.dao.AuthorizationDAOImpl;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.provisioning.UserProvisioningManagerImpl;
import gov.nih.nci.security.system.ApplicationSessionFactory;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;

public class UserProvisioningManagerFactory {

    public static UserProvisioningManager newUserProvisioningManager(String contextName, Map props) {
        UserProvisioningManagerImpl mgr = null;

        try {
            mgr = new UserProvisioningManagerImpl(contextName, new HashMap(props));
        } catch (CSConfigurationException ex) {
            throw new RuntimeException("Error instantiating UserProvisioningManager: "
                            + ex.getMessage(), ex);
        }

        return mgr;
    }

}
