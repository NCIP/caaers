package gov.nih.nci.cabig.caaers.api.impl;

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
import gov.nih.nci.cabig.caaers.integration.schema.asael.ASAELType;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Ion C. Olaru
 *         Date: 4/3/12 -10:03 AM
 */
public class ASAELServiceImpl implements ApplicationContextAware {

    private static Log log = LogFactory.getLog(ASAELServiceImpl.class);

    private ApplicationContext applicationContext;
    private AgentDao agentDao;
    private TerminologyRepository terminologyRepository;
    private AgentSpecificTermDao agentSpecificTermDao;
    private StudyAgentDao studyAgentDao;
    private AgentSpecificAdverseEventListService asaelService;
    private StudyDao studyDao;

    // CACHES
    Map<String, CtcCategory> ctcCategories = new HashMap<String, CtcCategory>();

    public CaaersServiceResponse createOrUpdateASAEL(ASAELType asael) {
        CaaersServiceResponse csr = new CaaersServiceResponse();
        csr.setServiceResponse(new ServiceResponse());
        csr.getServiceResponse().setEntityProcessingOutcomes(new EntityProcessingOutcomes());
        csr.getServiceResponse().setStatus(Status.PROCESSED);
        List<EntityProcessingOutcomeType> errors = execute(asael);
        csr.getServiceResponse().getEntityProcessingOutcomes().setEntityProcessingOutcome(errors);
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

    public List<EntityProcessingOutcomeType> execute(ASAELType asael) {

        List<EntityProcessingOutcomeType> errors = new ArrayList<EntityProcessingOutcomeType>();

        if (asael.getAsaelAgent() == null || asael.getAsaelAgent() == null || asael.getAsaelAgent().size() == 0) return errors;

        for (ASAELAgentType asaelAgent : asael.getAsaelAgent()) {
            AgentType agentType = asaelAgent.getAgent();
            if (agentType == null) continue;

            // get the persistent Agent
            Agent a = loadPersistentAgent(agentType.getNscNumber());
            if (a == null) {
                log.warn(String.format("Agent not found: %s", agentType.getNscNumber()));
                errors.add(populateError(Agent.class.getCanonicalName(), agentType.getNscNumber(), "Agent not found."));
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
                    errors.add(populateError(CtcTerm.class.getCanonicalName(), tt.getCtepTerm(), ""));
                } else if (isOnAgent(agentTerms, tt)) {
                    log.debug(String.format("TERM %s is already on the agent", tt.getCtepTerm()));
                    it.remove();
                }
            }

            List<CtcTerm> ctcTerms = loadCtcTerms(asaelAgent.getExpectedAECtcTerm(), errors);
            log.debug("LOADED SIZE: " + ctcTerms.size());
            log.debug("AGENT TERMS SIZE: " + agentTerms.size());

            // Add the new terms to agent
            for (CtcTerm t : ctcTerms) {
                AgentSpecificCtcTerm asaelTerm = new AgentSpecificCtcTerm();
                asaelTerm.setAgent(a);
                asaelTerm.setCtcTerm(t);
                asaelTerm.setExpected(Boolean.TRUE);

                try {
                    agentSpecificTermDao.save(asaelTerm);
                    errors.add(populateError(CtcTerm.class.getCanonicalName(), asaelTerm.getFullName(), ""));
                    log.debug(String.format("NEW TERM ADDED: %s, %d", asaelTerm.getFullName(), asaelTerm.getId()));
                    syncStudies(asaelTerm, AgentSpecificTerm.EXPTECTED_AE_ADDED);
                } catch (Exception e) {
                    errors.add(populateError(CtcTerm.class.getCanonicalName(), asaelTerm.getFullName(), e.getStackTrace().toString()));
                }
            }
        }

        return errors;
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

    private List<CtcTerm> loadCtcTerms(List<ExpectedAECtcTermType> xmlCtcTerms, List<EntityProcessingOutcomeType> errors) {
        List<CtcTerm> ctcTerms = new ArrayList<CtcTerm>();

        for (ExpectedAECtcTermType ctcTermType : xmlCtcTerms) {
            String c = ctcTermType.getCategory();
            Integer v = Integer.parseInt(ctcTermType.getCtcVersion());
            String t = ctcTermType.getCtepTerm();
            CtcTerm term = loadTerm(c, v, t);

            if (term == null) {
                log.warn(String.format("No term found with ctcCategory: %s, ctcVersion: %s, term: %s", c, v, t));
                errors.add(populateError(CtcTerm.class.getCanonicalName(), t, String.format("No term found with ctcCategory: %s, ctcVersion: %s, term: %s", c, v, t)));
                continue;
            }

            ctcTerms.add(term);
        }

        return ctcTerms;
    }

    private Agent loadPersistentAgent(String nscNumber) {
        Agent a = agentDao.getByNscNumber(nscNumber);
        if (a != null) agentDao.initialize(a.getAgentSpecificTerms());
        return a;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
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
