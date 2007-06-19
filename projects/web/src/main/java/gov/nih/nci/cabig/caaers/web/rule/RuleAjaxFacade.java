package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.brxml.Action;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Condition;
import gov.nih.nci.cabig.caaers.rules.brxml.FieldConstraint;
import gov.nih.nci.cabig.caaers.rules.brxml.LiteralRestriction;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentService;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.rule.author.CreateRuleCommand;
import gov.nih.nci.cabig.caaers.web.rule.author.CreateRuleController;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 * All the DWR methods specific to rules will be here
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class RuleAjaxFacade 
{
	private StudyDao studyDao;
	
	private CtcTermDao ctcTermDao;
	
	private RuleAuthoringService ruleAuthoringService;
	
	private RuleExecutionService ruleExecutionService;
	
	private RuleDeploymentService ruleDeploymentService;
	
    private ConfigProperty configurationProperty;

    public ConfigProperty getConfigurationProperty() 
    {
        return configurationProperty;
    }

    public void setConfigurationProperty(ConfigProperty configProperty) 
    {
        this.configurationProperty = configProperty;
    }

	/*
	 * This method retrieves studies based on the Sponsor Name and Partial Study name
	 */
    public List<Study> matchStudies(String text, String sponsorName) 
	{
        List<Study> studies = studyDao.getBySubnames(extractSubnames(text));
        
        if (sponsorName == null)
        {
        	return null;
        }
        
        for (Iterator<Study> it = studies.iterator(); it.hasNext();) 
        {
            Study study = it.next();
            
            if (!sponsorName.equals(study.getPrimarySponsorCode()))
            {
            	it.remove();
            }	
        }
        
        // cut down objects for serialization
        List<Study> reducedStudies = new ArrayList<Study>(studies.size());
        for (Study study : studies) {
            reducedStudies.add(
                buildReduced(study, Arrays.asList("id", "shortTitle"))
            );
        }
        return reducedStudies;
    }
    
    public List<CtcTerm> fetchTerms() throws Exception {
        List<CtcTerm> terms = ctcTermDao.getBySubname(extractSubnames("%"), null, null);
        // cut down objects for serialization
        for (CtcTerm term : terms) {
            term.getCategory().setTerms(null);
            term.getCategory().getCtc().setCategories(null);
        }
        return terms;
    }
    
    /**
     * This will access the spring managed object from the session (RuleSet) and will
     * update the object with Condition.
     * 
     * Then will forward to a jsp to get the html for that condition and will return that.
     * 
     * */
    public String addRule(String name) {
    	CreateRuleCommand createRuleCommand = getAuthorRuleCommand();
    	RuleSet ruleSet = (RuleSet)createRuleCommand.getRuleSet();
    	Rule newRule = new Rule();
    	MetaData metaData = new MetaData();
    	metaData.setName(name);
    	newRule.setMetaData(metaData);

    	Condition condition = newCondition();
		newRule.setCondition(condition);
		
		Action action = new Action();
		newRule.setAction(action);
    	
    	ruleSet.getRule().add(newRule);
    	
    	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
    	request.setAttribute("ruleCount", ruleSet.getRule().size()-1);
    	request.setAttribute(AbstractFormController.DEFAULT_COMMAND_NAME, createRuleCommand);
    	
    	return getOutputFromJsp("/pages/rule/addRule");
    }
    
    public String addCondition(int ruleCount) {
    	CreateRuleCommand createRuleCommand = getAuthorRuleCommand();
    	RuleSet ruleSet = (RuleSet)createRuleCommand.getRuleSet();
    	Rule rule = ruleSet.getRule().get(ruleCount);
    	Column column = newColumn();
    	rule.getCondition().getColumn().add(column);

    	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
    	request.setAttribute("ruleCount", ruleCount);
    	request.setAttribute("columnCount", rule.getCondition().getColumn().size()-1);
    	return getOutputFromJsp("/pages/rule/addColumn");
    }
    
    private Condition newCondition() {
    	Condition condition = new Condition();
		Column column = newColumn();
		condition.getColumn().add(column);
		return condition;
    }
    
    private Column newColumn() {
    	Column column = new Column();
    	FieldConstraint fieldConstraint = newFieldConstraint();
		column.getFieldConstraint().add(fieldConstraint);
		return column;
    }
    
    private FieldConstraint newFieldConstraint() {
		FieldConstraint fieldConstraint = new FieldConstraint();
		LiteralRestriction literalRestriction = new LiteralRestriction();
		fieldConstraint.getLiteralRestriction().add(literalRestriction);
		return fieldConstraint;
    }

    public Boolean removeCondition(int ruleCount, int columnCount) {
    	CreateRuleCommand createRuleCommand = getAuthorRuleCommand();
    	RuleSet ruleSet = (RuleSet)createRuleCommand.getRuleSet();
    	Rule rule = ruleSet.getRule().get(ruleCount);
    	return rule.getCondition().getColumn().remove(columnCount) != null;
    }
    
    private CreateRuleCommand getAuthorRuleCommand() {
    	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
    	String commandName = CreateRuleController.class.getName()+".FORM.command";
    	CreateRuleCommand createRuleCommand = (CreateRuleCommand)request.getSession().getAttribute(commandName); 
    	request.setAttribute(AbstractFormController.DEFAULT_COMMAND_NAME, createRuleCommand);
    	return createRuleCommand;
    }

    private String getOutputFromJsp(String jspResource) {
    	String html = "Error in rendering...";
    	try {
			html = WebContextFactory.get().forwardToString(jspResource);
		} catch (ServletException e) {
			throw new CaaersSystemException(e.getMessage(), e);
		} catch (IOException e) {
			throw new CaaersSystemException(e.getMessage(), e);
		}
    	return html;
    }
    
    public List<Grade> fetchGrades() {
    	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
    	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
    	return Arrays.asList(Grade.values());
    }
    
    public void deployRuleSet(String ruleSetName) throws RemoteException
    {
    	String bindUri = ruleSetName;
    	
    	try 
    	{
    		getRuleDeploymentService().deregisterRuleSet(bindUri);
    	} 
    	catch (Exception e) 
    	{
    		//A hack... for the first time this exception will be there...ignore...
    	}
    	
    	getRuleDeploymentService().registerRuleSet(bindUri, ruleSetName);
    }
    
    public void fireRules(String bindUri, String mode) throws RemoteException {
    	StudySDO study = new StudySDO();
    	study.setShortTitle("AML/MDS 9911");
    	List<AdverseEventSDO> list = new ArrayList();
    	if("1".equals(mode)) {
    		list.add(getSuccessful());
    	} else if ("2".equals(mode)) {
    		list.add(getNonSuccessful());
       	} else if ("3".equals(mode)) {
    		list.add(getSuccessfulAgain());
    	}

    	getRuleExecutionService().fireRules(bindUri, study, list);
    }
    
    public void fireAERules() throws RemoteException {
    	String bindUri = "CAAERS_AE_RULES";
    	ExpeditedAdverseEventReport adverseEventReport = null;
		StudySDO studySDO = null;
		ArrayList<AdverseEventSDO> list = new ArrayList<AdverseEventSDO>();

        // XXX: there's no way this code could work -- adverseEventReport is never initialized.
        AdverseEvent adverseEvent = adverseEventReport.getAdverseEvents().get(0);
        // TODO: This code is *exactly* the same as in CreateAdverseEventCommand.
        // Don't do that -- put it in a shared utility library
        if(adverseEvent != null) { //Little over defensive
			studySDO = new StudySDO();
			Study study = adverseEventReport.getAssignment().getStudySite().getStudy();
			studySDO.setShortTitle(study.getShortTitle());
			
			AdverseEventSDO adverseEventSDO = new AdverseEventSDO();
			
			// ATTRIBUTION
			//adverseEventSDO.setAttribution(adverseEventReport.get); // Where to get this from -- ask Rhett

			//PHASE -- // Where to get this from -- ask Rhett
			String phase = adverseEventReport.getAssignment().getStudySite().getStudy().getPhaseCode();
			adverseEventSDO.setPhase(phase);
			
			//EXPECTED
			boolean expected = adverseEvent.getExpected();
			adverseEventSDO.setExpected((String.valueOf(expected)));
			
			//GRADE
			int grade = adverseEvent.getGrade().getCode();
			//adverseEventSDO.setGrade(String.valueOf(grade));
			adverseEventSDO.setGrade(new Integer(grade));
					
			//CATEGORY
			CtcCategory category = adverseEvent.getCtcTerm().getCategory();
			adverseEventSDO.setCategory(category.getName());
			
			//CTC TERM
			CtcTerm ctcTerm = adverseEvent.getCtcTerm();
			adverseEventSDO.setTerm(ctcTerm.getFullName());
			
			//HOSPITALIZATION
			int hospitalization = adverseEvent.getHospitalization().getCode();
			Boolean isHospitalization = (hospitalization == Hospitalization.NONE.getCode()) ? Boolean.FALSE : Boolean.TRUE ;
			
			adverseEventSDO.setHospitalization(isHospitalization.toString());
		}
		getRuleExecutionService().fireRules(bindUri, studySDO, list);
    }

    private AdverseEventSDO getSuccessful() {
    	AdverseEventSDO adverseEventSDO = new AdverseEventSDO();
    	//adverseEventSDO.setGrade("3");
    	adverseEventSDO.setGrade(new Integer(3));
    	adverseEventSDO.setHospitalization("No");
    	adverseEventSDO.setAttribution("3");
    	return adverseEventSDO;
    }

    private AdverseEventSDO getSuccessfulAgain() {
    	AdverseEventSDO adverseEventSDO = new AdverseEventSDO();
    	//adverseEventSDO.setGrade("1");
    	adverseEventSDO.setGrade(new Integer(1));
    	return adverseEventSDO;
    }
    
    private AdverseEventSDO getNonSuccessful() {
    	AdverseEventSDO adverseEventSDO = new AdverseEventSDO();
    	//adverseEventSDO.setGrade("0");
    	adverseEventSDO.setGrade(new Integer(0));
    	return adverseEventSDO;
    }
    
    // TODO: move this somewhere shared.  Or, better, obviate it.
    @SuppressWarnings("unchecked")
    private <T> T buildReduced(T src, List<String> properties) {
        T dst = null;
        try {
            // it doesn't seem like this cast should be necessary
            dst = (T) src.getClass().newInstance();
        } catch (InstantiationException e) {
            throw new CaaersSystemException("Failed to instantiate " + src.getClass().getName(), e);
        } catch (IllegalAccessException e) {
            throw new CaaersSystemException("Failed to instantiate " + src.getClass().getName(), e);
        }

        BeanWrapper source = new BeanWrapperImpl(src);
        BeanWrapper destination = new BeanWrapperImpl(dst);
        for (String property : properties) {
            destination.setPropertyValue(
                property,
                source.getPropertyValue(property)
            );
        }
        return dst;
    }
    
    private String[] extractSubnames(String text) {
        return text.split("\\s+");
    }

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public CtcTermDao getCtcTermDao() {
		return ctcTermDao;
	}

	public void setCtcTermDao(CtcTermDao ctcTermDao) {
		this.ctcTermDao = ctcTermDao;
	}
	
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<Action>();
		Action action = new Action();
		action.setActionId("1");
		action.setName("24-Hour Notification to TRI");
		actions.add(action);
		
		action.setActionId("2");
		action.setName("24 Hour Report Submitted");
		actions.add(action);
		
		action.setActionId("3");
		action.setName("Pending 24-Hour 3 day Notification");
		actions.add(action);
		
		action.setActionId("4");
		action.setName("24 Hour Report Submitted");
		actions.add(action);
		
		return actions;
	}

	public RuleAuthoringService getRuleAuthoringService() {
		return ruleAuthoringService;
	}

	public void setRuleAuthoringService(RuleAuthoringService ruleAuthoringService) {
		this.ruleAuthoringService = ruleAuthoringService;
	}

	public RuleDeploymentService getRuleDeploymentService() {
		return ruleDeploymentService;
	}

	public void setRuleDeploymentService(RuleDeploymentService ruleDeploymentService) {
		this.ruleDeploymentService = ruleDeploymentService;
	}

	public RuleExecutionService getRuleExecutionService() {
		return ruleExecutionService;
	}

	public void setRuleExecutionService(RuleExecutionService ruleExecutionService) {
		this.ruleExecutionService = ruleExecutionService;
	}
	
	/* 
	 * !REVISIT This method is added to render valid values for the attributes selected on the AdverseEvent object. 
	 *  
	 * */
	
	public String getValidValues(int fieldIndex)
	{
		HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
		request.setAttribute("fieldIndex", fieldIndex);
		return getOutputFromJsp("/pages/rule/createOptions");
	}
	
	/* 
	 * This method is used to retrieve the Sponsor Names based on the partial sponserName passed to it.
	 * 
	 */  
	public List<String> matchSponsors(String sponsorName)
	{
		// REVISIT: Replace this with the SponsorDao.
		
		List sponsorCodeRefData = (List) getConfigurationProperty().getMap().get("sponsorCodeRefData");
		
		List<String> sponsors = new ArrayList<String>();
		
		Iterator sponsorsItr = sponsorCodeRefData.iterator();
				
		while (sponsorsItr.hasNext())
		{
			Lov sponsor = (Lov) sponsorsItr.next();
			
			if (sponsorName != null && sponsor.getDesc().toLowerCase().indexOf(sponsorName.toLowerCase(), 0) != -1)
			{
				sponsors.add(sponsor.getDesc());
			}
		}
		
		return sponsors;
	}
}
