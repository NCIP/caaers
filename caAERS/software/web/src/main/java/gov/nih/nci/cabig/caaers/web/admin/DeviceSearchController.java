/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.web.search.SearchController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
* @author Ion C. Olaru
* 
* */

public class DeviceSearchController extends SearchController {

    private static final Log log = LogFactory.getLog(DeviceSearchController.class);

    public DeviceSearchController() {
        setFormView("admin/deviceSearchForm");
        setSuccessView("admin/deviceSearchForm");
        setCommandClass(AgentCommand.class);
    }
}
