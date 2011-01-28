package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Person;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class PersonDao extends CaaersDao<Person> implements MutableDomainObjectDao<Person>{
	
	private ResearchStaffDao researchStaffDao;
	private InvestigatorDao investigatorDao;
	
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<Person> domainClass() {
        return Person.class;
    }

    /**
     * Save or update the person in the db.
     * 
     * @param The
     *                person.
     */
    @Transactional(readOnly = false)
    public void save(Person person) {
    	if(person instanceof ResearchStaff){
    		researchStaffDao.save((ResearchStaff)person);
    	}
    	if(person instanceof Investigator){
    		investigatorDao.save((Investigator)person);
    	}
    }

    /**
     * Get the person who has specified email address.
     * 
     * @param emailAddress
     *                The email address of the person.
     * @return The person.
     */
    @SuppressWarnings("unchecked")
	public Person getByEmailAddress(String emailAddress) {
    	
    	Person person = null;
    	
    	List<ResearchStaff> rsList = getHibernateTemplate().find("from ResearchStaff where emailAddress= ?", emailAddress);
    	if(rsList != null && rsList.size() > 0){
    		person = rsList.get(0);
    		return person;
    	}
    	
        List<Investigator> invList = getHibernateTemplate().find("from Investigator where emailAddress= ?", emailAddress);
        if(invList != null && invList.size() > 0){
        	person = invList.get(0);
        	return person;
        }
        
        return person;
    }


    /**
     * Get the person who has specified person identifier
     *
     * @param personIdentifier
     *                The person identifier of the person.
     * @return The person.
     */
    @SuppressWarnings("unchecked")
	public Person getByPersonIdentifier(String personIdentifier) {

    	Person person = null;

    	List<ResearchStaff> rsList = getHibernateTemplate().find("from ResearchStaff where nciIdentifier= ?", personIdentifier);
    	if(rsList != null && rsList.size() > 0){
    		person = rsList.get(0);
    		return person;
    	}

        List<Investigator> invList = getHibernateTemplate().find("from Investigator where nciIdentifier= ?", personIdentifier);
        if(invList != null && invList.size() > 0){
        	person = invList.get(0);
        	return person;
        }

        return person;
    }

    /**
     * Get the person who has specified email address.
     * 
     * @param loginName
     *                The loginId of the person.
     * @return The person.
     */
    @SuppressWarnings("unchecked")
	public Person getByLoginId(String loginName) {
    	
    	Person person = null;
    	
    	List<ResearchStaff> rsList = getHibernateTemplate().find("from ResearchStaff rs where rs.caaersUser.loginName= ?", loginName);
    	if(rsList != null && rsList.size() > 0){
    		person = rsList.get(0);
    		return person;
    	}
        List<Investigator> invList = getHibernateTemplate().find("from Investigator inv where inv.caaersUser.loginName= ?", loginName);
        if(invList != null && invList.size() > 0){
        	person = invList.get(0);
        	return person;
        }
        return person;
    }
    
    public Person getById(int id){
    	Person person = null;
    	person = researchStaffDao.getById(id);
    	if(person != null){
    		return person;
    	}
    	person = investigatorDao.getById(id);
    	if(person != null){
    		return person;
    	}
    	return person;
    }

	public Person getByGridId(String arg0) {
		return null;
	}

	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}

	public void setInvestigatorDao(InvestigatorDao investigatorDao) {
		this.investigatorDao = investigatorDao;
	}
}