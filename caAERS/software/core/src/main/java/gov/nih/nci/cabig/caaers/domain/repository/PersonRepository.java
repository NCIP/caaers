package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.PersonDao;
import gov.nih.nci.cabig.caaers.domain.Person;

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

    /**
     * Will takes care of saving a person. 
     * @param person
     */
	public void save(Person person){
		personDao.save(person);
	}
	
	
	public Person getByLoginId(String loginName) {
		return personDao.getByLoginId(loginName);
	}
	
	public Person getById(int id){
		return personDao.getById(id);
	}
	
	public Person getByEmailAddress(String emailAddress){
		return personDao.getByEmailAddress(emailAddress);
	}
	
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

}