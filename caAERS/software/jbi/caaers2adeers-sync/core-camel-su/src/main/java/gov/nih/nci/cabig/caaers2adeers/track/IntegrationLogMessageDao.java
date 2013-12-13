/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers.track;

import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Will record the status of each step in the database
 */
public class IntegrationLogMessageDao{
	
    protected static final Log log = LogFactory.getLog(IntegrationLogMessageDao.class);
	
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(IntegrationLogMessage integrationLogMessage) throws Exception {
		hibernateTemplate.save(integrationLogMessage);
	}
	
	public IntegrationLogMessage findByComboId(final String comboMessageId, final Stage stage) {
		

		String query = "from IntegrationLogMessage where stage=:stage and comboMessageId =:comboMessageId";

		List<Object> result = hibernateTemplate.findByNamedParam(query,
                new String[]{"stage", "comboMessageId"} , new Object[]{stage, comboMessageId});

        if(result == null || result.isEmpty()) return null;

        if(result.get(0) == null) return null;
        return (IntegrationLogMessage) result.get(0);
        
	}
	
}
