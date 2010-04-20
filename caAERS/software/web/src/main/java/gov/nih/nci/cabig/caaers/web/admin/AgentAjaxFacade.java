package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseCategoryDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.SiteResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySiteAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.repository.*;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySiteAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;
import gov.nih.nci.cabig.caaers.web.dwr.IndexChange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import gov.nih.nci.cabig.caaers.web.study.CreateStudyController;
import gov.nih.nci.cabig.caaers.web.study.EditStudyController;
import gov.nih.nci.cabig.caaers.web.study.StudyCommand;
import gov.nih.nci.cabig.caaers.web.study.StudyController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 * @author Ion C. Olaru
 */
public class AgentAjaxFacade extends AbstractAjaxFacade {

    private static final Log log = LogFactory.getLog(AgentAjaxFacade.class);
    private static Class<?>[] CONTROLLERS = {AgentEditController.class};
    private AgentRepository agentRepository;
    private CtcTermDao ctcTermDao;
    private LowLevelTermDao lowLevelTermDao;

    //

    public AjaxOutput addAgentSpecificTerms(Integer agentID, String type, int[] agentIDs) {
        AjaxOutput out = new AjaxOutput();
        AgentCommand c = (AgentCommand)extractCommand();
        Agent a = agentRepository.getAgentByID(agentID);
        int firstIndex = c.getAgentSpecificTerms().size();

        if (type.equals("ctep")) {
            for (int i : agentIDs) {
                    AgentSpecificCtcTerm at = new AgentSpecificCtcTerm();
                    at.setTerm(ctcTermDao.getById(i));
                    c.getAgentSpecificTerms().add(at);
            }
        } else {
            for (int i : agentIDs) {
                    AgentSpecificMeddraLowLevelTerm at = new AgentSpecificMeddraLowLevelTerm();
                    at.setTerm(lowLevelTermDao.getById(i));
                    c.getAgentSpecificTerms().add(at);
            }
        }

        Map<String, String> params = new LinkedHashMap<String, String>();
        int lastIndex = c.getAgentSpecificTerms().size() - 1;

        params.put("firstIndex", Integer.toString(firstIndex));
        params.put("lastIndex", Integer.toString(lastIndex));
        params.put("isADD", Boolean.toString(true));

        out.setHtmlContent(renderAjaxView("agentSpecificTermSection", params));
        return out;
    }

    //


    public Class<?>[] controllers() {
        return CONTROLLERS;
    }

    public AgentRepository getAgentRepository() {
        return agentRepository;
    }

    public void setAgentRepository(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public CtcTermDao getCtcTermDao() {
        return ctcTermDao;
    }

    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }

    public LowLevelTermDao getLowLevelTermDao() {
        return lowLevelTermDao;
    }

    public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
        this.lowLevelTermDao = lowLevelTermDao;
    }
}
