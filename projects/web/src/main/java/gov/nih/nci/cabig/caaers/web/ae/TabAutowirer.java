package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: refactor this to be more generally useful, or come up with a different solution.
 *
 * @author Rhett Sutphin
 */
public class TabAutowirer {
    private static final Log log = LogFactory.getLog(TabAutowirer.class);

    private StudyParticipantAssignmentDao assignmentDao;
    private ParticipantDao participantDao;
    private StudyDao studyDao;
    private CtcDao ctcDao;
    private CtcTermDao ctcTermDao;
    private AdverseEventReportDao adverseEventReportDao;
    private ResearchStaffDao researchStaffDao;
    private RuleExecutionService ruleExecutionService;
    private ConfigProperty configurationProperty;

    public ConfigProperty getConfigurationProperty() {
		return configurationProperty;
	}

	public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}

	public void injectDependencies(Flow<AdverseEventInputCommand> flow) {
        BeanWrapper thisWrapped = new BeanWrapperImpl(this);
        for (Tab<AdverseEventInputCommand> tab : flow.getTabs()) {
            injectDependencies(tab, thisWrapped);
        }
    }

    // Quick-and-dirty name-based autowiring for tabs
    // TODO: use RequiredAnnotationBeanFactoryPostProcessor to make sure this is working at runtime
    private void injectDependencies(Tab<AdverseEventInputCommand> tab, BeanWrapper thisWrapped) {
        PropertyDescriptor[] available = thisWrapped.getPropertyDescriptors();
        Map<String, Object> injectables = new HashMap<String, Object>();
        for (PropertyDescriptor descriptor : available) {
            String sourceName = descriptor.getName();
            if (thisWrapped.isReadableProperty(sourceName)) {
                injectables.put(sourceName, thisWrapped.getPropertyValue(sourceName));
            }
        }

        injectDependencies(tab, injectables);
    }

    private void injectDependencies(Tab<AdverseEventInputCommand> tab, Map<String, Object> injectables) {
        BeanWrapper wrappedTab = new BeanWrapperImpl(tab);
        PropertyDescriptor[] target = wrappedTab.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : target) {
            String targetName = descriptor.getName();
            if (wrappedTab.isWritableProperty(targetName)) {
                if (injectables.containsKey(targetName)) {
                    wrappedTab.setPropertyValue(targetName, injectables.get(targetName));
                    if (log.isDebugEnabled()) {
                        log.debug("Injected value for " + targetName
                            + " into " + tab.getClass().getName());
                    }
                } else {
                    if (log.isDebugEnabled()) {
                        log.debug("No property " + targetName + " available to inject into "
                            + tab.getClass().getName());
                    }
                }
            }
        }
    }

    public StudyParticipantAssignmentDao getAssignmentDao() {
        return assignmentDao;
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public CtcDao getCtcDao() {
        return ctcDao;
    }

    public CtcTermDao getCtcTermDao() {
        return ctcTermDao;
    }

    public AdverseEventReportDao getAdverseEventReportDao() {
        return adverseEventReportDao;
    }

    public RuleExecutionService getRuleExecutionService() {
        return ruleExecutionService;
    }

    ////// CONFIGURATION

    public void setAdverseEventReportDao(AdverseEventReportDao adverseEventReportDao) {
        this.adverseEventReportDao = adverseEventReportDao;
    }

    public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }
    
    public ResearchStaffDao getResearchStaffDao() {
		return researchStaffDao;
	}

	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}
	
    public void setRuleExecutionService(RuleExecutionService ruleExecutionService) {
        this.ruleExecutionService = ruleExecutionService;
    }

}
