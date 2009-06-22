package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;

/**
 * @author Biju Joseph
 *
 */
public class ConfigPropertyDao extends CaaersDao<ConfigProperty>{
	
	@Override
	public Class<ConfigProperty> domainClass() {
		return ConfigProperty.class;
	}
	
	/**
	 * Lists all ConfigProperty available against the {@link ConfigPropertyType}
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ConfigProperty> getByType(ConfigPropertyType type){
		assert type != null : "Config type should be specified";
		return (List<ConfigProperty>) getHibernateTemplate().find("from ConfigProperty cp where cp.configType = ?", new Object[]{type});
	}
	
	/**
	 * Lists all ConfigProperty available against the {@link ConfigPropertyType}
	 * @param type
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
	 * Lists all ConfigProperty 
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ConfigProperty> getAll(){
		return (List<ConfigProperty>) getHibernateTemplate().find("from ConfigProperty cp");
	}
}
