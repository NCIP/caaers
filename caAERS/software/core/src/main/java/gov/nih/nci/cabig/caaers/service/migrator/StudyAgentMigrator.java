package gov.nih.nci.cabig.caaers.service.migrator;

import com.aparzev.util.CollectionUtils;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class StudyAgentMigrator implements Migrator<gov.nih.nci.cabig.caaers.domain.Study> {

    protected final Log log = LogFactory.getLog(StudyAgentMigrator.class);

    private AgentDao agentDao;
	private InvestigationalNewDrugDao investigationalNewDrugDao;
	private AgentMigrator agentMigrator;
	private OrganizationDao organizationDao;

    private Agent createAgent(Agent a){
        Agent newAgent = new Agent();
        agentMigrator.migrate(a, newAgent, null);
        agentDao.save(newAgent);
        return newAgent;
    }

    private InvestigationalNewDrug findOrCreateIND(String holderName, String number){
        //check - with assumption that holderName is NCI code
        List<InvestigationalNewDrug> inds = investigationalNewDrugDao.findOrganizationHeldIND(number, holderName);
        if(CollectionUtils.isEmpty(inds)){
            //check with assumption that holder name is Organization Name.
            inds = investigationalNewDrugDao.findByNumberAndHolderName(number, holderName);
        }


        if(CollectionUtils.isNotEmpty(inds)) return inds.get(0);
        
        //need to create IND
        InvestigationalNewDrug ind = new InvestigationalNewDrug();
        ind.setIndNumber(Integer.parseInt(number));
        
        OrganizationHeldIND holder = new OrganizationHeldIND();
        Organization org = organizationDao.getByNCIcode(holderName);
        if(org == null) org = organizationDao.getByName(holderName);
        if(org == null) return null; //still null then cannot create IND?
        
        holder.setOrganization(org);
        holder.setInvestigationalNewDrug(ind);
        ind.setINDHolder(holder);
        investigationalNewDrugDao.save(ind);
        return  ind;

    } 
        
    
    private void migrate(StudyAgent src, StudyAgent dest, DomainObjectImportOutcome<Study> outcome){
        //check if agent exist ?
        if(src.getAgent() == null && src.getOtherAgent() == null){
            outcome.addErrorMessage("Agent or Other Agent must be available.", DomainObjectImportOutcome.Severity.ERROR);
            return;
        }

        if(src.getAgent() != null){
            String nsc = src.getAgent().getNscNumber();
            Agent agent = null;
            if(StringUtils.isNotEmpty(nsc)){
                agent = agentDao.getByNscNumber(nsc);
            }else{
                agent = agentDao.getByName(src.getAgent().getName());
            }

            if(agent == null){
                agent = createAgent(src.getAgent());
            }
            dest.setAgent(agent);
        }else{
            dest.setOtherAgent(src.getOtherAgent());
        }

        dest.setIndType(src.getIndType());
        dest.setPartOfLeadIND(src.getPartOfLeadIND());

        if(src.getStudyAgentINDAssociations() != null){
            if(dest.getIndType() == null){
                outcome.addErrorMessage("IND type must be provided for investigationa/commercial agent",DomainObjectImportOutcome.Severity.ERROR);
                return;
            }

            if(dest.getIndType() == INDType.OTHER || dest.getIndType() == INDType.DCP_IND || dest.getIndType() == INDType.CTEP_IND){
                for(StudyAgentINDAssociation indAssociation : src.getStudyAgentINDAssociations()){
                    InvestigationalNewDrug ind = findOrCreateIND(indAssociation.getInvestigationalNewDrug().getHolderName(), indAssociation.getInvestigationalNewDrug().getStrINDNo()) ;
                    if(ind == null){
                        outcome.addErrorMessage("IND must be provided for Investigational agent", DomainObjectImportOutcome.Severity.WARNING);
                        continue;
                    }
                    StudyAgentINDAssociation newIndAssociation = new StudyAgentINDAssociation();
                    newIndAssociation.setInvestigationalNewDrug(ind);
                    dest.addStudyAgentINDAssociation(newIndAssociation);
                }

            }

            if(CollectionUtils.isEmpty(dest.getStudyAgentINDAssociations())){
                if(dest.getIndType() == INDType.CTEP_IND) {
                    StudyAgentINDAssociation ctepIndAssociation = new StudyAgentINDAssociation();
                    ctepIndAssociation.setInvestigationalNewDrug(investigationalNewDrugDao.fetchCtepInd());
                    dest.addStudyAgentINDAssociation(ctepIndAssociation);
                }else if(dest.getIndType() == INDType.DCP_IND) {
                    StudyAgentINDAssociation dcpIndAssociation = new StudyAgentINDAssociation();
                    dcpIndAssociation.setInvestigationalNewDrug(investigationalNewDrugDao.fetchCtepInd());
                    dest.addStudyAgentINDAssociation(dcpIndAssociation);
                }
            }

        }

    }
    
	/**
	 * Will migrate StudyAgent from source to destination study.
	 * @param source - {@link Study} which is the source
	 * @param destination - {@link Study} which is the destination
	 */
	public void migrate(Study source, Study destination, DomainObjectImportOutcome<Study> outcome) {
        for (StudyAgent studyAgent : source.getStudyAgents()) {
            StudyAgent newStudyAgent = new StudyAgent();
            migrate(studyAgent, newStudyAgent, outcome);
            destination.addStudyAgent(newStudyAgent);
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
