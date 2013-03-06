package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.PersonDao;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.domain.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

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
    
	
	
	public Person getByLoginId(String loginName) {
		return personDao.getByLoginId(loginName);
	}
	
	public Person getById(int id){
		return personDao.getById(id);
	}
	
	public Person getByEmailAddress(String emailAddress){
		return personDao.getByEmailAddress(emailAddress);
	}

    public Person getByPersonIdentifier(String personIdentifier){
        return personDao.getByPersonIdentifier(personIdentifier);
    }
	
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

    public List<ResearchStaff> searchLocalResearchStaff(AbstractQuery query){
       return (List<ResearchStaff>) personDao.search(query);
    }

    public List<Investigator> searchLocalInvestigator(AbstractQuery query){
         return (List<Investigator>) personDao.search(query);
    }

    

}
