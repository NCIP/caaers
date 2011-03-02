package gov.nih.nci.cabig.caaers.dao.security;

import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.security.RolePrivilege;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the RolePrivilege object.
 * @author Monish Dombla
 *
 */

@Transactional(readOnly = true)
public class RolePrivilegeDao extends GridIdentifiableDao<RolePrivilege> implements MutableDomainObjectDao<RolePrivilege>{

	@Override
	public Class<RolePrivilege> domainClass() {
		return RolePrivilege.class;
	}
	
	/**
	 * This method returns a list of roles for a given objectId and privilege
	 * @param objectId
	 * @param privilege
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getRoles(String objectId,String privilege){
		if(StringUtils.isEmpty(objectId) || StringUtils.isEmpty(privilege)){
			return null;
		}

		StringBuilder queryBuf = new StringBuilder(" select distinct rp.roleName from RolePrivilege rp ");
        queryBuf.append(" where rp.objectId = ? and rp.privilege = ? ");
		List<String> roles = getHibernateTemplate().find(queryBuf.toString(), new Object[]{objectId,privilege});
		return roles;
	}
}