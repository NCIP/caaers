package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;

/**
 * Will perform the CURD operations on ConfigProperty domain object. 
 * @author Biju Joseph
 *
 */
@Transactional
public class ConfigPropertyDao extends CaaersDao<ConfigProperty>{

    /**
     *  The domain class this Dao represents in this case ConfigProperty
     * @return
     */
	@Override
	@Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
	public Class<ConfigProperty> domainClass() {
		return ConfigProperty.class;
	}
	
	/**
	 * Lists all ConfigProperty available against the {@link ConfigPropertyType}
	 * @param type   - A ConfigPropertyType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ConfigProperty> getByType(ConfigPropertyType type){
		assert type != null : "Config type should be specified";
		return (List<ConfigProperty>) getHibernateTemplate().find("from ConfigProperty cp where cp.configType = ?", new Object[]{type});
	}
	
	/**
	 * Lists all ConfigProperty available against the {@link ConfigPropertyType}
	 * @param type The type of the ConfigProperty
     * @param code - ConfigProperty code. 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ConfigProperty getByTypeAndCode(ConfigPropertyType type, String code){
		assert type != null && code != null : "Config type and code should be specified";
		List<ConfigProperty> list = (List<ConfigProperty>) getHibernateTemplate().find("from ConfigProperty cp where cp.code = ? and cp.configType = ?",
				new Object[]{code, type});
		if(list == null || list.isEmpty()) return null;
		else return list.get(0);
	}
	
	/**
	 * Lists all ConfigProperty available in the DB.  
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ConfigProperty> getAll(){
		return (List<ConfigProperty>) getHibernateTemplate().find("from ConfigProperty cp");
	}
}
