package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.StudyAgentDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.TerminologyRepository;
import gov.nih.nci.cabig.caaers.integration.schema.asael.ASAELAgentType;
import gov.nih.nci.cabig.caaers.integration.schema.asael.Asael;
import gov.nih.nci.cabig.caaers.integration.schema.asael.ExpectedAECtcTermType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ActiveInactiveStatusType;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.service.AgentSpecificAdverseEventListService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

/**
 * Responsibilities :
 *   1. Should retain existing terms.
 *   2. All inactive terms has to be removed
 *   3. All active terms (new ones) must be added.
 *   4. Ignore and continue if there is any error.
 *
 * @author Ion C. Olaru
 *         Date: 4/3/12 -10:03 AM
 * @author Biju Joseph (Refactored)
 */
public class ASAELServiceImpl {

    private static Log log = LogFactory.getLog(ASAELServiceImpl.class);

    private AgentDao agentDao;
    private TerminologyRepository terminologyRepository;
    private StudyAgentDao studyAgentDao;
    private AgentSpecificAdverseEventListService asaelService;
    private StudyDao studyDao;


    public CaaersServiceResponse createOrUpdateASAEL(Asael asael) {
        CaaersServiceResponse csr = Helper.createResponse();
        for (ASAELAgentType asaelAgentType : asael.getAsaelAgent()) {
            try{
                createOrUpdate(csr, asaelAgentType);
            }catch (Exception e){
                log.error("Asael loading error", e);
                Helper.populateError(csr, "WS_GEN_000" , "Unable to process ASAEL (" + asaelAgentType.getAgent().getNscNumber() + ") " +  e.getMessage());
            }
        }
        return csr;
    }
    
    
    public void createOrUpdate(CaaersServiceResponse csr,ASAELAgentType asaelAgentType) throws  Exception{
        String nscNumber = asaelAgentType.getAgent().getNscNumber();
        Agent agent = getAgentDao().getByNscNumber(nscNumber);
        if(agent == null) {
            log.warn(String.format("Agent not found: %s", nscNumber));
            ProcessingOutcome outcome = Helper.createOutcome(AgentSpecificTerm.class, nscNumber, true, "Unable to find Agent by NSC :" + nscNumber) ;
            Helper.populateProcessingOutcome(csr, outcome);
            return;
        }
        
        List<AgentSpecificTerm> processedTermsList =  new ArrayList<AgentSpecificTerm>();
        for(ExpectedAECtcTermType termType : asaelAgentType.getExpectedAECtcTerm()) {
            AgentSpecificTerm agentSpecificTerm = addOrRemoveExpectedTerm(csr, agent, termType);  
            if(agentSpecificTerm != null){
                processedTermsList.add(agentSpecificTerm);
            }
        }

        agentDao.save(agent);
        syncStudies(agent , processedTermsList);
        
    }

    /**
     * Will do the following
     *   a. New active terms will be added
     *   b. Existing terms will be updated
     *   c. Inactive terms will be removed.
     *
     * @param csr
     * @param agent
     * @param termType
     */
    private AgentSpecificTerm addOrRemoveExpectedTerm(CaaersServiceResponse csr, Agent agent,  ExpectedAECtcTermType termType) throws Exception{
       AgentSpecificTerm existingAgentTerm = agent.findTerm(termType.getCtepTerm(), termType.getCategory(), termType.getCtcVersion(), termType.getOtherToxicity(), null);
       boolean isInactive = termType.getStatus() == ActiveInactiveStatusType.INACTIVE;
        if(existingAgentTerm == null){
           if(isInactive) return null;
           //create new
           CtcTerm ctcTerm = loadTerm(termType.getCategory(),Integer.parseInt(termType.getCtcVersion()), termType.getCtepTerm());
           if(ctcTerm == null){
               ProcessingOutcome outcome = Helper.createOutcome(AgentSpecificTerm.class, termType.getCtepTerm(),
                       true, "Term not found. So, unable to add to agent (" + agent.getNscNumber() + ") expected term : " + termType.getCtepTerm() );
               Helper.populateProcessingOutcome(csr, outcome); 
               return null;
           }
            
            AgentSpecificCtcTerm agentSpecificCtcTerm = new AgentSpecificCtcTerm();
            agentSpecificCtcTerm.setTerm(ctcTerm);
            agentSpecificCtcTerm.setLastSynchedDate(new Date());
            agentSpecificCtcTerm.setExpected(true);
            agentSpecificCtcTerm.setOtherToxicity(termType.getOtherToxicity());
            agent.addAgentSpecificTerm(agentSpecificCtcTerm);
            
            ProcessingOutcome outcome = Helper.createOutcome(AgentSpecificTerm.class, termType.getCtepTerm(),
                    false, "Added to add to agent (" + agent.getNscNumber() + ") expected term : " + termType.getCtepTerm() );
            Helper.populateProcessingOutcome(csr, outcome);
            agentSpecificCtcTerm.setOperationPerformed(AgentSpecificTerm.EXPTECTED_AE_ADDED);
            return agentSpecificCtcTerm;
            
       }else{
            
            if(isInactive){
                agent.removeAgentSpecificTerm(existingAgentTerm);
                ProcessingOutcome outcome = Helper.createOutcome(AgentSpecificTerm.class, termType.getCtepTerm(),
                        false, "Removed from agent (" + agent.getNscNumber() + ") expected term : " + termType.getCtepTerm() );
                Helper.populateProcessingOutcome(csr, outcome);
                existingAgentTerm.setOperationPerformed(AgentSpecificTerm.EXPTECTED_AE_DELETED);
                return existingAgentTerm;
            }
            existingAgentTerm.setLastSynchedDate(new Date());
            existingAgentTerm.setOtherToxicity(termType.getOtherToxicity());
            existingAgentTerm.setExpected(true);
            ProcessingOutcome outcome = Helper.createOutcome(AgentSpecificTerm.class, termType.getCtepTerm(),
                    false, "Updated on Agent (" + agent.getNscNumber() + ") expected term : " + termType.getCtepTerm() );
            Helper.populateProcessingOutcome(csr, outcome);
            existingAgentTerm.setOperationPerformed(AgentSpecificTerm.EXPTECTED_AE_UPDATED);
            return  existingAgentTerm;
       }

    }

    private void syncStudies(Agent agent, List<AgentSpecificTerm> terms){
        if(terms.isEmpty()) return;

        List<StudyAgent> studyAgents = getStudyAgentDao().getByAgentID(agent.getId());
        Map<Integer, Study> studyMap = new HashMap<Integer, Study>();
        for(StudyAgent sa : studyAgents){
            studyMap.put(sa.getStudy().getId(), sa.getStudy()); //for saving the study
            for(AgentSpecificTerm t : terms){
                asaelService.synchronizeStudyWithAgentTerm(sa, t, t.getOperationPerformed());
            }
        }

        //save the studies
        for(Study s : studyMap.values()){
            studyDao.updateStudyForServiceUseOnly(s);
        }
    }

    private CtcTerm loadTerm(String ctcae_category, Integer ctcae_version, String ae_term) {
        List<CtcTerm> list = terminologyRepository.getCtcTerm(ctcae_category, ctcae_version, ae_term);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    public AgentDao getAgentDao() {
        return agentDao;
    }

    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public TerminologyRepository getTerminologyRepository() {
        return terminologyRepository;
    }

    public void setTerminologyRepository(TerminologyRepository terminologyRepository) {
        this.terminologyRepository = terminologyRepository;
    }


    public StudyAgentDao getStudyAgentDao() {
        return studyAgentDao;
    }

    public void setStudyAgentDao(StudyAgentDao studyAgentDao) {
        this.studyAgentDao = studyAgentDao;
    }

    public AgentSpecificAdverseEventListService getAsaelService() {
        return asaelService;
    }

    public void setAsaelService(AgentSpecificAdverseEventListService asaelService) {
        this.asaelService = asaelService;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }
}
