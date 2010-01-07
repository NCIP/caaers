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
 */
public class ImportCommand {

    private MultipartFile dataFile;

    private String schemaValidationResult;

    private String type;

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
    
    private List<DomainObjectImportOutcome<Agent>> updateableAgents = new ArrayList<DomainObjectImportOutcome<Agent>>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
	
	 public List<DomainObjectImportOutcome<Organization>> getImportableOrganizations() {
		 return importableOrganizations;
	 }

	 public void setImportableOrganizations(
			List<DomainObjectImportOutcome<Organization>> importableOrganizations) {
		this.importableOrganizations = importableOrganizations;
	}

	public List<DomainObjectImportOutcome<Organization>> getUpdateableOrganizations() {
		return updateableOrganizations;
	}

	public void setUpdateableOrganizations(
			List<DomainObjectImportOutcome<Organization>> updateableOrganizations) {
		this.updateableOrganizations = updateableOrganizations;
	}

	public void addUpdateableOrganization(
			DomainObjectImportOutcome<Organization> domainObjectImportOutcome) {
		getUpdateableOrganizations().add(domainObjectImportOutcome);
	}

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
	
	
	public void addUpdateableAgent(
			DomainObjectImportOutcome<Agent> domainObjectImportOutcome) {
		getUpdateableAgents().add(domainObjectImportOutcome);
	}

	public void addImportableAgent(
			DomainObjectImportOutcome<Agent> domainObjectImportOutcome) {
		getImportableAgents().add(domainObjectImportOutcome);
	}
	
}
