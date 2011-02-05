package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.PersonDao;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.domain.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

 
/**
 * The Class PersonRepository.
 *
 * @author Monish
 */
public class PersonRepository {
	
	/** The logger. */
	private Log logger = LogFactory.getLog(PersonRepository.class);
	
	/** The person dao. */
	private PersonDao personDao;

    /**
     * Will takes care of saving a person.
     *
     * @param person the person
     */
	public void save(Person person){
		personDao.save(person);
        if(person instanceof ResearchStaff){
           List<SiteResearchStaff> inactiveSiteResearchStaffs = ((ResearchStaff)person).getInActiveSiteResearchStaff();
           for(SiteResearchStaff srs : inactiveSiteResearchStaffs){
                personDao.deactivateStudyPersonnel(srs);
           }

        }else if(person instanceof Investigator){
           List<SiteInvestigator> siteInvestigators = ((Investigator)person).getSiteInvestigators();
           for(SiteInvestigator si : siteInvestigators){
               if(si.isInActive()){
                    personDao.deactivateStudyInvestigator(si);
               }
           }
        }
	}
    
	
	
	/**
	 * Gets the by login id.
	 *
	 * @param loginName the login name
	 * @return the by login id
	 */
	public Person getByLoginId(String loginName) {
		return personDao.getByLoginId(loginName);
	}
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public Person getById(int id){
		return personDao.getById(id);
	}
	
	/**
	 * Gets the by email address.
	 *
	 * @param emailAddress the email address
	 * @return the by email address
	 */
	public Person getByEmailAddress(String emailAddress){
		return personDao.getByEmailAddress(emailAddress);
	}

    /**
     * Gets the by person identifier.
     *
     * @param personIdentifier the person identifier
     * @return the by person identifier
     */
    public Person getByPersonIdentifier(String personIdentifier){
        return personDao.getByPersonIdentifier(personIdentifier);
    }
	
	/**
	 * Sets the person dao.
	 *
	 * @param personDao the new person dao
	 */
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

    /**
     * Search local research staff.
     *
     * @param query the query
     * @return the list
     */
    public List<ResearchStaff> searchLocalResearchStaff(AbstractQuery query){
       return (List<ResearchStaff>) personDao.search(query);
    }

    /**
     * Search local investigator.
     *
     * @param query the query
     * @return the list
     */
    public List<Investigator> searchLocalInvestigator(AbstractQuery query){
         return (List<Investigator>) personDao.search(query);
    }

    

}
