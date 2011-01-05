package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.PersonDao;
import gov.nih.nci.cabig.caaers.domain.Person;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Monish
 *
 */
public class PersonRepository {
	
	private Log logger = LogFactory.getLog(PersonRepository.class);
	private PersonDao personDao;
	private UserRepository userRepository;

	public void save(Person person){
		if(person.getCaaersUser() != null && StringUtils.isNotEmpty(person.getCaaersUser().getLoginName())){
			userRepository.createOrUpdateUser(person.getCaaersUser(),getChangePasswordUrl());
		}
		personDao.save(person);
	}
	
	
	public Person getByLoginId(String loginName) {
		return personDao.getByLoginId(loginName);
	}
	
	public Person getById(int id){
		return personDao.getById(id);
	}
	
	protected String getChangePasswordUrl(){
		String caAERSBaseUrl = Configuration.LAST_LOADED_CONFIGURATION.get(Configuration.CAAERS_BASE_URL);
		if(StringUtils.isEmpty(caAERSBaseUrl)){
			caAERSBaseUrl = "https://localhost:8443/caaers";
			logger.debug("CAAERS_BASE_URL is not configured, hence setting it to be running on localhost");
		}
        StringBuilder changePasswordUrl = new StringBuilder(caAERSBaseUrl);
        changePasswordUrl.append("/public/user/changePassword?");

        return changePasswordUrl.toString();
	}
	
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
