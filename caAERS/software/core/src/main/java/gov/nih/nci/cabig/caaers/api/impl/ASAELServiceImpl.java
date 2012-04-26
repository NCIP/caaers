package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.AgentSpecificTermDao;
import gov.nih.nci.cabig.caaers.dao.StudyAgentDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.repository.TerminologyRepository;
import gov.nih.nci.cabig.caaers.integration.schema.asael.ASAELAgentType;
import gov.nih.nci.cabig.caaers.integration.schema.asael.Asael;
import gov.nih.nci.cabig.caaers.integration.schema.asael.ExpectedAECtcTermType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ActiveInactiveStatusType;
import gov.nih.nci.cabig.caaers.integration.schema.common.AgentType;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomeType;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomes;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.Status;
import gov.nih.nci.cabig.caaers.service.AgentSpecificAdverseEventListService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;

/**
 * @author Ion C. Olaru
 *         Date: 4/3/12 -10:03 AM
 */
public class ASAELServiceImpl {

    private static Log log = LogFactory.getLog(ASAELServiceImpl.class);

    private AgentDao agentDao;
    private TerminologyRepository terminologyRepository;
    private AgentSpecificTermDao agentSpecificTermDao;
    private StudyAgentDao studyAgentDao;
    private AgentSpecificAdverseEventListService asaelService;
    private StudyDao studyDao;


    public CaaersServiceResponse createOrUpdateASAEL(Asael asael) {
        CaaersServiceResponse csr = Helper.createResponse();
        execute(csr, asael);
        return csr;
    }


    private EntityProcessingOutcomeType populateError(String cn, String bi, String message) {
        EntityProcessingOutcomeType e = new EntityProcessingOutcomeType();
        e.setBusinessIdentifier(bi);
        e.setKlassName(cn);
        e.setMessage(new ArrayList<String>(1));
        e.getMessage().add(message);
        return e;
    }

    public void execute(CaaersServiceResponse csr, Asael asael) {
        if (CollectionUtils.isEmpty(asael.getAsaelAgent())) return;

        for (ASAELAgentType asaelAgent : asael.getAsaelAgent()) {
            AgentType agentType = asaelAgent.getAgent();
            if (agentType == null) continue;

            // get the persistent Agent
            Agent a = loadPersistentAgent(agentType.getNscNumber());
            if (a == null) {
                log.warn(String.format("Agent not found: %s", agentType.getNscNumber()));
                ProcessingOutcome outcome = Helper.createOutcome(AgentSpecificTerm.class, agentType.getNscNumber(), true, "Unable to find Agent by NSC :" + agentType.getNscNumber()) ;
                Helper.populateProcessingOutcome(csr, outcome);
                continue;
            }

            // Existing agent's expected terms collection
            List<AgentSpecificTerm> agentTerms = a.getAgentSpecificTerms();
            log.debug("AGENT TERMS SIZE: " + agentTerms.size());

            // Build the collection of Terms from XML data types

            // process the Inactive ones and remove from the list
            log.debug("PASSED SIZE: " + asaelAgent.getExpectedAECtcTerm().size());
            Iterator<ExpectedAECtcTermType> it = asaelAgent.getExpectedAECtcTerm().iterator();
            while (it.hasNext()) {
                ExpectedAECtcTermType tt = it.next();
                if (tt.getStatus().equals(ActiveInactiveStatusType.INACTIVE)) {
                    it.remove();
                    removeAgentSpecificTerm(agentTerms, tt);
                    ProcessingOutcome outcome = Helper.createOutcome(AgentSpecificTerm.class, tt.getCtepTerm(), false, "Removed from agent (" + agentType.getNscNumber() + ") expected term : " + tt.getCtepTerm() );
                    Helper.populateProcessingOutcome(csr, outcome);
                } else if (isOnAgent(agentTerms, tt)) {
                    log.debug(String.format("TERM %s is already on the agent", tt.getCtepTerm()));
                    it.remove();
                    ProcessingOutcome outcome = Helper.createOutcome(AgentSpecificTerm.class, tt.getCtepTerm(), false, "Agent (" + agentType.getNscNumber() + ") already have expected term : " + tt.getCtepTerm() );
                    Helper.populateProcessingOutcome(csr, outcome);
                }
            }

            List<AgentSpecificCtcTerm> asaelCtcTerms = loadCtcTerms(asaelAgent.getExpectedAECtcTerm(), csr);
            log.debug("LOADED SIZE: " + asaelCtcTerms.size());
            log.debug("AGENT TERMS SIZE: " + agentTerms.size());

            // Add the new terms to agent
            for (AgentSpecificCtcTerm _asaelTerm : asaelCtcTerms) {
                _asaelTerm.setAgent(a);
                _asaelTerm.setExpected(Boolean.TRUE);

                try {
                    agentSpecificTermDao.save(_asaelTerm);
                    ProcessingOutcome outcome = Helper.createOutcome(AgentSpecificTerm.class, _asaelTerm.getFullName(), false, "To the agent (" + agentType.getNscNumber() + ") the expected term : " + _asaelTerm.getFullName() + " got added");
                    Helper.populateProcessingOutcome(csr, outcome);
                    log.debug(String.format("NEW TERM ADDED: %s, %d", _asaelTerm.getFullName(), _asaelTerm.getId()));
                    syncStudies(_asaelTerm, AgentSpecificTerm.EXPTECTED_AE_ADDED);
                } catch (Exception e) {
                    log.error("Exception occured while adding asael ", e);
                    ProcessingOutcome outcome = Helper.createOutcome(AgentSpecificTerm.class, _asaelTerm.getFullName(), true, "Unable to add to the agent (" + agentType.getNscNumber() + ") the expected term : " + _asaelTerm.getTerm());
                    Helper.populateProcessingOutcome(csr, outcome);
                }
            }
        }


    }

    private boolean isSameTerm(String t1Term, String t1Version, String t2Term, String t2Version) {
        return (t1Term.equals(t2Term) && t1Version.equals(t2Version));
    }

    private boolean isOnAgent(List<AgentSpecificTerm> ts, ExpectedAECtcTermType tt) {
        Iterator<AgentSpecificTerm> it = ts.iterator();
        while (it.hasNext()) {
            AgentSpecificCtcTerm at = (AgentSpecificCtcTerm)it.next();
            if (isSameTerm(at.getTerm().getTerm(), String.valueOf(at.getTerm().getCategory().getCtc().getId()), tt.getCtepTerm(), tt.getCtcVersion())) {
                return true;
            }
        }
        return false;
    }

    public void removeAgentSpecificTerm(List<AgentSpecificTerm> ts, ExpectedAECtcTermType tt) {
        Iterator<AgentSpecificTerm> it = ts.iterator();
        while (it.hasNext()) {
            AgentSpecificCtcTerm at = (AgentSpecificCtcTerm)it.next();
            if (isSameTerm(at.getTerm().getTerm(), String.valueOf(at.getTerm().getCategory().getCtc().getId()), tt.getCtepTerm(), tt.getCtcVersion())) {
                it.remove();
                agentSpecificTermDao.delete(at);
                syncStudies(at, AgentSpecificTerm.EXPTECTED_AE_DELETED);
            }
        }
    }

    private void syncStudies(AgentSpecificTerm t, String action) {
        List<StudyAgent> l = getStudyAgentDao().getByAgentID(t.getAgent().getId());
        log.debug(String.format("FOUND %s studies.", l.size()));
        for (StudyAgent s : l) {
            asaelService.synchronizeStudyWithAgentTerm(s, t, action);
            studyDao.merge(s.getStudy());
            studyDao.updateStudyForServiceUseOnly(s.getStudy());
        }
    }

    private CtcTerm loadTerm(String ctcae_category, Integer ctcae_version, String ae_term) {
        List<CtcTerm> list = terminologyRepository.getCtcTerm(ctcae_category, ctcae_version, ae_term);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * Loads the list of CtcTerms, each element is wrapped in a AgentSpecificCtcTerm
     * to be able to store the AgentSpecificCtcTerm related properties
     * Populated properties are
     * gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm#term and
     * gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm#otherToxicity
     * @param xmlCtcTerms XML AgentSpecificCtcTerm
     * @param csr Place to store errors and messages
     * @return
     */
    private List<AgentSpecificCtcTerm> loadCtcTerms(List<ExpectedAECtcTermType> xmlCtcTerms, CaaersServiceResponse csr) {
        List<AgentSpecificCtcTerm> ctcTerms = new ArrayList<AgentSpecificCtcTerm>();

        for (ExpectedAECtcTermType ctcTermType : xmlCtcTerms) {
            String _category = ctcTermType.getCategory();
            Integer _version = Integer.parseInt(ctcTermType.getCtcVersion());
            String _term = ctcTermType.getCtepTerm();
            CtcTerm _ctcTerm = loadTerm(_category, _version, _term);

            if (_term == null) {
                log.warn(String.format("No term found with ctcCategory: %s, ctcVersion: %s, term: %s", _category, _version, _term));
                ProcessingOutcome outcome = Helper.createOutcome(CtcTerm.class, _term, true, String.format("No term found with ctcCategory: %s, ctcVersion: %s, term: %s", _category, _version, _term)) ;
                Helper.populateProcessingOutcome(csr, outcome);
                continue;
            }

            AgentSpecificCtcTerm _asaelTerm = new AgentSpecificCtcTerm();
            _asaelTerm.setTerm(_ctcTerm);
            _asaelTerm.setOtherToxicity(ctcTermType.getOtherToxicity());

            ctcTerms.add(_asaelTerm);
        }

        return ctcTerms;
    }

    private Agent loadPersistentAgent(String nscNumber) {
        Agent a = agentDao.getByNscNumber(nscNumber);
        if (a != null) agentDao.initialize(a.getAgentSpecificTerms());
        return a;
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

    public AgentSpecificTermDao getAgentSpecificTermDao() {
        return agentSpecificTermDao;
    }

    public void setAgentSpecificTermDao(AgentSpecificTermDao agentSpecificTermDao) {
        this.agentSpecificTermDao = agentSpecificTermDao;
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
