package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Krikor Krumlian
 * @author Sameer Sawant
 * @author Monish Dombla
 */
public class ImportCommand {

    private MultipartFile dataFile;
    private String schemaValidationResult;
    private String type;
    private MultipartFile socFile;
    private MultipartFile hlgtFile;
    private MultipartFile hltFile;
    private MultipartFile lltFile;
    private MultipartFile ptFile;
    private MultipartFile socHlgtFile;
    private MultipartFile hlgtHltFile;
    private MultipartFile hltPtFile;
    private String meddraVersionName;
    private List<DomainObjectImportOutcome<Study>> nonImportableStudies = new ArrayList<DomainObjectImportOutcome<Study>>();
    private List<DomainObjectImportOutcome<Study>> importableStudies = new ArrayList<DomainObjectImportOutcome<Study>>();
    private List<DomainObjectImportOutcome<Participant>> nonImportableParticipants = new ArrayList<DomainObjectImportOutcome<Participant>>();
    private List<DomainObjectImportOutcome<Participant>> importableParticipants = new ArrayList<DomainObjectImportOutcome<Participant>>();
    private List<DomainObjectImportOutcome<RoutineAdverseEventReport>> nonImportableRoutineAdverseEventReports = new ArrayList<DomainObjectImportOutcome<RoutineAdverseEventReport>>();
    private List<DomainObjectImportOutcome<RoutineAdverseEventReport>> importableRoutineAdverseEventReports = new ArrayList<DomainObjectImportOutcome<RoutineAdverseEventReport>>();
    private List<DomainObjectImportOutcome<Investigator>> importableInvestigators = new ArrayList<DomainObjectImportOutcome<Investigator>>();
    private List<DomainObjectImportOutcome<Investigator>> nonImportableInvestigators = new ArrayList<DomainObjectImportOutcome<Investigator>>();
    private List<DomainObjectImportOutcome<ResearchStaff>> importableResearchStaff = new ArrayList<DomainObjectImportOutcome<ResearchStaff>>();
    private List<DomainObjectImportOutcome<ResearchStaff>> nonImportableResearchStaff = new ArrayList<DomainObjectImportOutcome<ResearchStaff>>();
    private List<DomainObjectImportOutcome<Organization>> importableOrganizations = new ArrayList<DomainObjectImportOutcome<Organization>>();
    private List<DomainObjectImportOutcome<Organization>> updateableOrganizations = new ArrayList<DomainObjectImportOutcome<Organization>>();
    private List<DomainObjectImportOutcome<Agent>> importableAgents = new ArrayList<DomainObjectImportOutcome<Agent>>();
    private List<DomainObjectImportOutcome<Agent>> nonImportableAgents = new ArrayList<DomainObjectImportOutcome<Agent>>();
    private List<DomainObjectImportOutcome<Organization>> nonImportableOrganizations = new ArrayList<DomainObjectImportOutcome<Organization>>();
    private List<DomainObjectImportOutcome<Agent>> updateableAgents = new ArrayList<DomainObjectImportOutcome<Agent>>();
    private String socDataFileName;
    private String hlgtDataFileName;
    private String socHlgtDataFileName;
    private String hltDataFileName;
    private String hlgtHltDataFileName;
    private String ptDataFileName;
    private String hltPtDataFileName;
    private String lltDataFileName;
    
	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MultipartFile getSocFile() {
		return socFile;
	}

	public void setSocFile(MultipartFile socFile) {
		this.socFile = socFile;
	}

	public MultipartFile getHlgtFile() {
		return hlgtFile;
	}

	public void setHlgtFile(MultipartFile hlgtFile) {
		this.hlgtFile = hlgtFile;
	}

	public MultipartFile getHltFile() {
		return hltFile;
	}

	public void setHltFile(MultipartFile hltFile) {
		this.hltFile = hltFile;
	}

	public MultipartFile getLltFile() {
		return lltFile;
	}

	public void setLltFile(MultipartFile lltFile) {
		this.lltFile = lltFile;
	}

	public MultipartFile getPtFile() {
		return ptFile;
	}

	public void setPtFile(MultipartFile ptFile) {
		this.ptFile = ptFile;
	}

	public MultipartFile getSocHlgtFile() {
		return socHlgtFile;
	}

	public void setSocHlgtFile(MultipartFile socHlgtFile) {
		this.socHlgtFile = socHlgtFile;
	}

	public MultipartFile getHlgtHltFile() {
		return hlgtHltFile;
	}

	public void setHlgtHltFile(MultipartFile hlgtHltFile) {
		this.hlgtHltFile = hlgtHltFile;
	}

	public MultipartFile getHltPtFile() {
		return hltPtFile;
	}

	public void setHltPtFile(MultipartFile hltPtFile) {
		this.hltPtFile = hltPtFile;
	}

	public MultipartFile getDataFile() {
        return dataFile;
    }

    public void setDataFile(MultipartFile dataFile) {
        this.dataFile = dataFile;
    }

    public List<DomainObjectImportOutcome<Study>> getNonImportableStudies() {
        return nonImportableStudies;
    }

    public void setNonImportableStudies(List<DomainObjectImportOutcome<Study>> nonImportableStudies) {
        this.nonImportableStudies = nonImportableStudies;
    }

    public void addNonImportableStudy(DomainObjectImportOutcome<Study> domainObjectImportOutcome) {
        getNonImportableStudies().add(domainObjectImportOutcome);
    }

    public List<DomainObjectImportOutcome<Study>> getImportableStudies() {
        return importableStudies;
    }

    public void setImportableStudies(List<DomainObjectImportOutcome<Study>> importableStudies) {
        this.importableStudies = importableStudies;
    }

    public void addImportableStudy(DomainObjectImportOutcome<Study> domainObjectImportOutcome) {
        getImportableStudies().add(domainObjectImportOutcome);
    }

    public List<DomainObjectImportOutcome<Participant>> getImportableParticipants() {
        return importableParticipants;
    }

    public void setImportableParticipants(
                    List<DomainObjectImportOutcome<Participant>> importableParticipants) {
        this.importableParticipants = importableParticipants;
    }

    public void addImportableParticipant(
                    DomainObjectImportOutcome<Participant> domainObjectImportOutcome) {
        getImportableParticipants().add(domainObjectImportOutcome);
    }

    public List<DomainObjectImportOutcome<Participant>> getNonImportableParticipants() {
        return nonImportableParticipants;
    }

    public void setNonImportableParticipants(
                    List<DomainObjectImportOutcome<Participant>> nonImportableParticipants) {
        this.nonImportableParticipants = nonImportableParticipants;
    }

    public void addNonImportableParticipant(
                    DomainObjectImportOutcome<Participant> domainObjectImportOutcome) {
        getNonImportableParticipants().add(domainObjectImportOutcome);
    }

    public List<DomainObjectImportOutcome<RoutineAdverseEventReport>> getImportableRoutineAdverseEventReports() {
        return importableRoutineAdverseEventReports;
    }

    public void setImportableRoutineAdverseEventReports(
                    List<DomainObjectImportOutcome<RoutineAdverseEventReport>> importableRoutineAes) {
        this.importableRoutineAdverseEventReports = importableRoutineAes;
    }

    public void addImportableRoutineAdverseEventReport(
                    DomainObjectImportOutcome<RoutineAdverseEventReport> domainObjectImportOutcome) {
        getImportableRoutineAdverseEventReports().add(domainObjectImportOutcome);
    }

    public List<DomainObjectImportOutcome<RoutineAdverseEventReport>> getNonImportableRoutineAdverseEventReports() {
        return nonImportableRoutineAdverseEventReports;
    }

    public void setNonImportableRoutineAdverseEventReports(
                    List<DomainObjectImportOutcome<RoutineAdverseEventReport>> nonImportableRoutineAes) {
        this.nonImportableRoutineAdverseEventReports = nonImportableRoutineAes;
    }

    public void addNonImportableRoutineAdverseEventReport(
                    DomainObjectImportOutcome<RoutineAdverseEventReport> domainObjectImportOutcome) {
        getNonImportableRoutineAdverseEventReports().add(domainObjectImportOutcome);
    }

    public String getSchemaValidationResult() {
        return schemaValidationResult;
    }

    public void setSchemaValidationResult(String schemaValidationResult) {
        this.schemaValidationResult = schemaValidationResult;
    }

	public List<DomainObjectImportOutcome<Investigator>> getImportableInvestigators() {
		return importableInvestigators;
	}

	public void setImportableInvestigators(List<DomainObjectImportOutcome<Investigator>> importableInvestigators) {
		this.importableInvestigators = importableInvestigators;
	}

	public List<DomainObjectImportOutcome<Investigator>> getNonImportableInvestigators() {
		return nonImportableInvestigators;
	}

	public void setNonImportableInvestigators(List<DomainObjectImportOutcome<Investigator>> nonImportableInvestigators) {
		this.nonImportableInvestigators = nonImportableInvestigators;
	}

	public List<DomainObjectImportOutcome<ResearchStaff>> getImportableResearchStaff() {
		return importableResearchStaff;
	}

	public void setImportableResearchStaff(
			List<DomainObjectImportOutcome<ResearchStaff>> importableResearchStaff) {
		this.importableResearchStaff = importableResearchStaff;
	}

	public List<DomainObjectImportOutcome<ResearchStaff>> getNonImportableResearchStaff() {
		return nonImportableResearchStaff;
	}

	public void setNonImportableResearchStaff(
			List<DomainObjectImportOutcome<ResearchStaff>> nonImportableResearchStaff) {
		this.nonImportableResearchStaff = nonImportableResearchStaff;
	}

	public void addNonImportableResearchStaff(
            DomainObjectImportOutcome<ResearchStaff> domainObjectImportOutcome) {
				getNonImportableResearchStaff().add(domainObjectImportOutcome);
	}		
	
	public void addImportableResearchStaff(
            DomainObjectImportOutcome<ResearchStaff> domainObjectImportOutcome) {
				getImportableResearchStaff().add(domainObjectImportOutcome);
	}
	
	public void addNonImportableInvestigator(
            DomainObjectImportOutcome<Investigator> domainObjectImportOutcome) {
				getNonImportableInvestigators().add(domainObjectImportOutcome);
	}		
	
	public void addImportableInvestigator(
            DomainObjectImportOutcome<Investigator> domainObjectImportOutcome) {
				getImportableInvestigators().add(domainObjectImportOutcome);
	}
	
	public void initializeImportCommand(){
		nonImportableStudies = new ArrayList<DomainObjectImportOutcome<Study>>();
	    importableStudies = new ArrayList<DomainObjectImportOutcome<Study>>();
	    nonImportableParticipants = new ArrayList<DomainObjectImportOutcome<Participant>>();
	    importableParticipants = new ArrayList<DomainObjectImportOutcome<Participant>>();
	    nonImportableRoutineAdverseEventReports = new ArrayList<DomainObjectImportOutcome<RoutineAdverseEventReport>>();
	    importableRoutineAdverseEventReports = new ArrayList<DomainObjectImportOutcome<RoutineAdverseEventReport>>();
	    importableInvestigators = new ArrayList<DomainObjectImportOutcome<Investigator>>();
	    nonImportableInvestigators = new ArrayList<DomainObjectImportOutcome<Investigator>>();
	    importableResearchStaff = new ArrayList<DomainObjectImportOutcome<ResearchStaff>>();
	    nonImportableResearchStaff = new ArrayList<DomainObjectImportOutcome<ResearchStaff>>();
	    importableOrganizations = new ArrayList<DomainObjectImportOutcome<Organization>>();
	    updateableOrganizations = new ArrayList<DomainObjectImportOutcome<Organization>>();
	    importableAgents = new ArrayList<DomainObjectImportOutcome<Agent>>();
	    nonImportableAgents = new ArrayList<DomainObjectImportOutcome<Agent>>();
	    nonImportableOrganizations = new ArrayList<DomainObjectImportOutcome<Organization>>();
	    updateableAgents = new ArrayList<DomainObjectImportOutcome<Agent>>();
	    setSchemaValidationResult(null);
	}

	/**
	 * 
	 * @return
	 */
	 public List<DomainObjectImportOutcome<Organization>> getImportableOrganizations() {
		 return importableOrganizations;
	 }

	 /**
	  * 
	  * @param importableOrganizations
	  */
	 public void setImportableOrganizations(
			List<DomainObjectImportOutcome<Organization>> importableOrganizations) {
		this.importableOrganizations = importableOrganizations;
	}

	
	/**
	 *  
	 * @return
	 */
	public List<DomainObjectImportOutcome<Organization>> getUpdateableOrganizations() {
		return updateableOrganizations;
	}
	
	/**
	 * 
	 * @param updateableOrganizations
	 */
	public void setUpdateableOrganizations(
			List<DomainObjectImportOutcome<Organization>> updateableOrganizations) {
		this.updateableOrganizations = updateableOrganizations;
	}

	/**
	 * 
	 * @param domainObjectImportOutcome
	 */
	public void addUpdateableOrganization(
			DomainObjectImportOutcome<Organization> domainObjectImportOutcome) {
		getUpdateableOrganizations().add(domainObjectImportOutcome);
	}

	/**
	 * 
	 * @param domainObjectImportOutcome
	 */
	public void addImportableOrganization(
			DomainObjectImportOutcome<Organization> domainObjectImportOutcome) {
		getImportableOrganizations().add(domainObjectImportOutcome);
	}

	/**
	 * @return the importableAgents
	 */
	public List<DomainObjectImportOutcome<Agent>> getImportableAgents() {
		return importableAgents;
	}

	/**
	 * @param importableAgents the importableAgents to set
	 */
	public void setImportableAgents(
			List<DomainObjectImportOutcome<Agent>> importableAgents) {
		this.importableAgents = importableAgents;
	}

	/**
	 * @return the updateableAgents
	 */
	public List<DomainObjectImportOutcome<Agent>> getUpdateableAgents() {
		return updateableAgents;
	}

	/**
	 * @param updateableAgents the updateableAgents to set
	 */
	public void setUpdateableAgents(
			List<DomainObjectImportOutcome<Agent>> updateableAgents) {
		this.updateableAgents = updateableAgents;
	}
	
	/**
	 * 
	 * @param domainObjectImportOutcome
	 */
	public void addUpdateableAgent(
			DomainObjectImportOutcome<Agent> domainObjectImportOutcome) {
		getUpdateableAgents().add(domainObjectImportOutcome);
	}
	
	/**
	 * 
	 * @param domainObjectImportOutcome
	 */
	public void addImportableAgent(
			DomainObjectImportOutcome<Agent> domainObjectImportOutcome) {
		getImportableAgents().add(domainObjectImportOutcome);
		
	}
		
	public String getMeddraVersionName(){
		return meddraVersionName;
	}
	
	public List<DomainObjectImportOutcome<Organization>> getNonImportableOrganizations() {
		return nonImportableOrganizations;
	}

	public void setNonImportableOrganizations(
			List<DomainObjectImportOutcome<Organization>> nonImportableOrganizations) {
		this.nonImportableOrganizations = nonImportableOrganizations;
	}

	public List<DomainObjectImportOutcome<Agent>> getNonImportableAgents() {
		return nonImportableAgents;
	}

	public void setNonImportableAgents(
			List<DomainObjectImportOutcome<Agent>> nonImportableAgents) {
		this.nonImportableAgents = nonImportableAgents;
	}
	
	public void addNonImportableOrganization(
			DomainObjectImportOutcome<Organization> domainObjectImportOutcome) {
		getNonImportableOrganizations().add(domainObjectImportOutcome);
	}
	
	public void addNonImportableAgent(
			DomainObjectImportOutcome<Agent> domainObjectImportOutcome) {
		getNonImportableAgents().add(domainObjectImportOutcome);
	}
	
	public void setMeddraVersionName(String meddraVersionName){
		this.meddraVersionName = meddraVersionName;
	}

	public String getSocDataFileName() {
		return socDataFileName;
	}

	public void setSocDataFileName(String socDataFileName) {
		this.socDataFileName = socDataFileName;
	}

	public String getHlgtDataFileName() {
		return hlgtDataFileName;
	}

	public void setHlgtDataFileName(String hlgtDataFileName) {
		this.hlgtDataFileName = hlgtDataFileName;
	}

	public String getSocHlgtDataFileName() {
		return socHlgtDataFileName;
	}

	public void setSocHlgtDataFileName(String socHlgtDataFileName) {
		this.socHlgtDataFileName = socHlgtDataFileName;
	}

	public String getHltDataFileName() {
		return hltDataFileName;
	}

	public void setHltDataFileName(String hltDataFileName) {
		this.hltDataFileName = hltDataFileName;
	}

	public String getHlgtHltDataFileName() {
		return hlgtHltDataFileName;
	}

	public void setHlgtHltDataFileName(String hlgtHltDataFileName) {
		this.hlgtHltDataFileName = hlgtHltDataFileName;
	}

	public String getPtDataFileName() {
		return ptDataFileName;
	}

	public void setPtDataFileName(String ptDataFileName) {
		this.ptDataFileName = ptDataFileName;
	}

	public String getHltPtDataFileName() {
		return hltPtDataFileName;
	}

	public void setHltPtDataFileName(String hltPtDataFileName) {
		this.hltPtDataFileName = hltPtDataFileName;
	}

	public String getLltDataFileName() {
		return lltDataFileName;
	}

	public void setLltDataFileName(String lltDataFileName) {
		this.lltDataFileName = lltDataFileName;
	}
	
}
