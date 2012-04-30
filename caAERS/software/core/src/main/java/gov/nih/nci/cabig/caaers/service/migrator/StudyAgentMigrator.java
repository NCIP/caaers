package gov.nih.nci.cabig.caaers.service.migrator;

import com.aparzev.util.CollectionUtils;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.List;

public class StudyAgentMigrator implements Migrator<gov.nih.nci.cabig.caaers.domain.Study> {
	
	private AgentDao agentDao;
	private InvestigationalNewDrugDao investigationalNewDrugDao;
	private AgentMigrator agentMigrator;
	private OrganizationDao organizationDao;

	/**
	 * Will migrate StudyAgent from source to destination study.
	 * @param source - {@link Study} which is the source
	 * @param destination - {@link Study} which is the destination
	 */
	public void migrate(Study source, Study destination, DomainObjectImportOutcome<Study> outcome) {

        for (StudyAgent studyAgent : source.getStudyAgents()) {

			StudyAgent target = new StudyAgent();
			Agent agent = null;

			if (studyAgent.getAgent().getName() != null) {
				agent = agentDao.getByName(studyAgent.getAgent().getName());
				target.setAgent(agent);
			}

            if (studyAgent.getOtherAgent() != null) {
                target.setOtherAgent(studyAgent.getOtherAgent());
            }

			if (studyAgent.getAgent().getNscNumber() != null && agent == null) {
				agent = agentDao.getByNscNumber(studyAgent.getAgent().getNscNumber());
				target.setAgent(agent);
			}

            if (agent == null) {
                // ADD THE NEW AGENT TO THE DB
                agent = new Agent();
                agent.setName(studyAgent.getAgent().getName());
                agent.setNscNumber(studyAgent.getAgent().getNscNumber());
                agent.setDescription(studyAgent.getAgent().getDescription());
            } else {
                agentMigrator.migrate(studyAgent.getAgent(), agent, null);
            }
            agentDao.save(agent);

			if (studyAgent.getOtherAgent() != null && agent == null) {
				target.setOtherAgent(studyAgent.getOtherAgent());
			}

            if (target.getOtherAgent() == null)
			    outcome.ifNullObject(agent, DomainObjectImportOutcome.Severity.ERROR," Provided Agent is not Valid ");

            target.setIndType(studyAgent.getIndType());
            target.setPartOfLeadIND(studyAgent.getPartOfLeadIND());


/*
            // This has been relaxed as per StudySchema.xsd
			if (target.getIndType() == INDType.DCP_IND || target.getIndType() == INDType.CTEP_IND) {
				outcome.ifNullObject(studyAgent.getPartOfLeadIND(), DomainObjectImportOutcome.Severity.ERROR, " Lead IND required ");
				target.setPartOfLeadIND(studyAgent.getPartOfLeadIND());
			}
*/

            // Building IND by number and holder
            if (target.getIndType() == INDType.OTHER || target.getIndType() == INDType.NA_COMMERCIAL) {

                for (StudyAgentINDAssociation indAssociation : studyAgent.getStudyAgentINDAssociations()) {
                    String indNumber = indAssociation.getInvestigationalNewDrug().getIndNumber().toString();
                    String holderName = indAssociation.getInvestigationalNewDrug().getHolderName();
                    List<InvestigationalNewDrug> inds = investigationalNewDrugDao.findByNumberAndHolderName(indNumber, holderName);

                    InvestigationalNewDrug _ind;

                    if (CollectionUtils.isNotEmpty(inds)) {
                        _ind = inds.get(0);
                    } else {
                        // Create New
                        _ind = new InvestigationalNewDrug();
                        _ind.setIndNumber(Integer.parseInt(indNumber));
                        Organization o = organizationDao.getByName(holderName);
                        OrganizationHeldIND _indHolder = new OrganizationHeldIND();
                        _indHolder.setOrganization(o);
                        _indHolder.setInvestigationalNewDrug(_ind);
                        _ind.setINDHolder(_indHolder);
                    }

                    investigationalNewDrugDao.save(_ind);
                    indAssociation.setInvestigationalNewDrug(_ind);
                    target.addStudyAgentINDAssociation(indAssociation);

                }
            }


			if (target.getIndType() == INDType.DCP_IND) {
				// Ok so we have to provide the id here , well i can't see a different way to do this , defenitly ugly
				// TODO: see how to enhance.
				String[] inds = { "-222" };
				List<InvestigationalNewDrug> investigationalNewDrugs = investigationalNewDrugDao.findByIds(inds);
				StudyAgentINDAssociation indAssociation = new StudyAgentINDAssociation();
				if (investigationalNewDrugs.size() > 0) {
					InvestigationalNewDrug ind = investigationalNewDrugs.get(0);
					indAssociation.setInvestigationalNewDrug(ind);
					target.addStudyAgentINDAssociation(indAssociation);
				} else {
					outcome.ifNullObject(null,DomainObjectImportOutcome.Severity.ERROR, "The investigational new drug for a DCP IND is not Valid ");
				}
			}
			
			if (target.getIndType() == INDType.CTEP_IND) {
				// Ok so we have to provide the id here , well i can't see a different way to do this , defenitly ugly
				// TODO: see how to enhance.
				String[] inds = { "-111" };
				List<InvestigationalNewDrug> investigationalNewDrugs = investigationalNewDrugDao.findByIds(inds);
				StudyAgentINDAssociation indAssociation = new StudyAgentINDAssociation();
				if (investigationalNewDrugs.size() > 0) {
					InvestigationalNewDrug ind = investigationalNewDrugs.get(0);
					indAssociation.setInvestigationalNewDrug(ind);
					target.addStudyAgentINDAssociation(indAssociation);
				} else {
					outcome.ifNullObject(null,DomainObjectImportOutcome.Severity.ERROR, "The investigational new drug for a CTEP IND is not Valid ");
				}
			}

/*
			if (target.getIndType() == INDType.OTHER) {

				for (StudyAgentINDAssociation indAssociation : studyAgent.getStudyAgentINDAssociations()) {
					String indNumber = indAssociation.getInvestigationalNewDrug().getIndNumber().toString();
					String[] inds = { indNumber };
					List<InvestigationalNewDrug> investigationalNewDrugs = investigationalNewDrugDao.findByIds(inds);

					if (investigationalNewDrugs.size() > 0) {
						InvestigationalNewDrug ind = investigationalNewDrugs.get(0);
						indAssociation.setInvestigationalNewDrug(ind);
						target.addStudyAgentINDAssociation(indAssociation);
					} else {
						outcome.ifNullObject(null,DomainObjectImportOutcome.Severity.ERROR, "The selected investigational new drug " + indNumber + " is not Valid ");
					}
				}
			}
*/

			destination.addStudyAgent(target);
			// TODO: ADD error handling with user interaction
		}
	}
	
	///OBJECT PROPERTIES
	public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}
	
    public void setInvestigationalNewDrugDao(final InvestigationalNewDrugDao investigationalNewDrugDao) {
        this.investigationalNewDrugDao = investigationalNewDrugDao;
    }

    public AgentMigrator getAgentMigrator() {
        return agentMigrator;
    }

    public void setAgentMigrator(AgentMigrator agentMigrator) {
        this.agentMigrator = agentMigrator;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }
}
