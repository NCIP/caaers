package gov.nih.nci.cabig.caaers.dao.workflow;

import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * This class implements the Data access related operations for the TaskConfig domain object.
 * 
 * @author Ramakrishna Gundala
 */
@Transactional(readOnly=true)
public class TaskConfigDao extends GridIdentifiableDao<TaskConfig>
implements MutableDomainObjectDao<TaskConfig>{	
	/**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
	 @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<TaskConfig> domainClass() {
        return TaskConfig.class;
    }
    
    /**
     * Get the list of TaskConfigs based on the taskName.
     */
    @SuppressWarnings("unchecked")
	public  TaskConfig getByTaskName(String taskName) {
        List<TaskConfig> results = getHibernateTemplate().find("from TaskConfig where taskName= ?", taskName);
        return (results == null || results.size() < 1) ? null : results.get(0);
    }
    
    /**
     * Get the list of all TaskConfig configs.
     *
     * @return return the list of TaskConfig configs.
     */
    @SuppressWarnings("unchecked")
    public List<TaskConfig> getAllTaskConfigs() {
        return getHibernateTemplate().find("from TaskConfig");
    }

}