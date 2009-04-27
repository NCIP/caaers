package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.rule.author.CreateRuleCommand;
import gov.nih.nci.cabig.caaers.web.rule.author.CreateRuleController;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.WebContextFactory;
import org.drools.repository.PackageItem;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.servlet.mvc.AbstractFormController;

import com.semanticbits.rules.api.BusinessRulesExecutionService;
import com.semanticbits.rules.api.RepositoryService;
import com.semanticbits.rules.api.RuleAuthoringService;
import com.semanticbits.rules.api.RuleDeploymentService;
import com.semanticbits.rules.api.RulesEngineService;
import com.semanticbits.rules.brxml.Action;
import com.semanticbits.rules.brxml.Column;
import com.semanticbits.rules.brxml.Condition;
import com.semanticbits.rules.brxml.FieldConstraint;
import com.semanticbits.rules.brxml.LiteralRestriction;
import com.semanticbits.rules.brxml.MetaData;
import com.semanticbits.rules.brxml.Rule;
import com.semanticbits.rules.brxml.RuleSet;
import com.semanticbits.rules.ui.DomainObject;
import com.semanticbits.rules.ui.Field;
import com.semanticbits.rules.ui.RuleUi;

/**
 * All the DWR methods specific to rules will be here
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class RuleAjaxFacade {
    private StudyDao studyDao;

    private CtcTermDao ctcTermDao;

    private RuleAuthoringService ruleAuthoringService;

    private BusinessRulesExecutionService businessRulesExecutionService;

    private RulesEngineService rulesEngineService;

    private RuleDeploymentService ruleDeploymentService;
    
    private RepositoryService repositoryService;

    private ConfigProperty configurationProperty;

    // private SiteDao siteDao;

    private OrganizationDao organizationDao;

    private ReportDefinitionDao reportDefinitionDao;

    private TreatmentAssignmentDao treatmentAssignmentDao;

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(ConfigProperty configProperty) {
        this.configurationProperty = configProperty;
    }

    /*
     * This method retrieves studies based on the Sponsor Name and Partial Study name
     */
    public List<Study> matchStudies(String text, String sponsorName) {
        List<Study> studies = studyDao.getBySubnames(extractSubnames(text));

        if (sponsorName == null) {
            return null;
        }

        for (Iterator<Study> it = studies.iterator(); it.hasNext();) {
            Study study = it.next();

            if (!sponsorName.equals(study.getPrimaryFundingSponsorOrganization().getName())) {
                it.remove();
            }
        }

        // cut down objects for serialization
        List<Study> reducedStudies = new ArrayList<Study>(studies.size());
        for (Study study : studies) {
            reducedStudies.add(buildReduced(study, Arrays.asList("id", "shortTitle", "primaryIdentifierValue")));
        }

        return reducedStudies;
    }

    /*
     * This method retrieves studies based on the Site Name and Partial Study name
     */

    public List<Study> matchStudiesByInstitution(String text, String institutionName) {
        List<Study> studies = studyDao.getBySubnames(extractSubnames(text));

        if (institutionName == null) {
            return null;
        }
        List<Study> newStudyList = new ArrayList<Study>();

        for (Iterator<Study> it = studies.iterator(); it.hasNext();) {
            Study study = it.next();

            List<StudyOrganization> studyOrgs = study.getStudyOrganizations();

            // loop thru each study site and get Site .
            // if site name = institutionName
            // add this study to new list

            for (Iterator<StudyOrganization> ssit = studyOrgs.iterator(); ssit.hasNext();) {
                StudyOrganization studyOrganization = ssit.next();
                Organization org = studyOrganization.getOrganization();
                if (institutionName.equals(org.getName())) {
                    newStudyList.add(study);
                    break;
                }
            }

        }

        // cut down objects for serialization
        List<Study> reducedStudies = new ArrayList<Study>(newStudyList.size());
        for (Study study : newStudyList) {
            reducedStudies.add(buildReduced(study, Arrays.asList("id", "shortTitle", "primaryIdentifierValue")));
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
     * This will access the spring managed object from the session (RuleSet) and will update the
     * object with Condition.
     * 
     * Then will forward to a jsp to get the html for that condition and will return that.
     * 
     */
    public String addRule(String name, String organizationName) {
        CreateRuleCommand createRuleCommand = getAuthorRuleCommand();
        RuleSet ruleSet = (RuleSet) createRuleCommand.getRuleSet();
        Rule newRule = new Rule();
        MetaData metaData = new MetaData();
        metaData.setName(name);
        newRule.setMetaData(metaData);

        Condition condition = newCondition();
        newRule.setCondition(condition);

        // Action action = new Action();
        List<String> action = new ArrayList<String>();
        newRule.setAction(action);

        ruleSet.getRule().add(newRule);

        Organization org = organizationDao.getByName(organizationName);

        // get report defnitions
        List<ReportDefinition> reportDefinitions = org.getReportDefinitions();

        // cut down objects for serialization
        List<ReportDefinition> reducedReportDefinitions = new ArrayList<ReportDefinition>(
                        reportDefinitions.size());
        for (ReportDefinition reportDefinition : reportDefinitions) {
            reducedReportDefinitions.add(reportDefinition);
        }
        
        /**
         * Get REport definitions of CTEP for DCP studies , because DCP uses CTEP 
         * report definitions also . TEMP fix
         */
        
        if (organizationName.equals("Division of Cancer Prevention")) {
        	org = organizationDao.getByName("Cancer Therapy Evaluation Program");
        	reportDefinitions = org.getReportDefinitions();
            for (ReportDefinition reportDefinition : reportDefinitions) {
                reducedReportDefinitions.add(reportDefinition);
            }        	
        } 
        
        // System.out.println("add rule create successfully ....");

        HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
        request.setAttribute("ruleCount", ruleSet.getRule().size() - 1);
        request.setAttribute("reportDefinitions", reducedReportDefinitions);
        request.setAttribute(AbstractFormController.DEFAULT_COMMAND_NAME, createRuleCommand);

        return getOutputFromJsp("/pages/rule/addRule");
    }

    public List<RuleAjaxObject> getAjaxObjects(String fieldName, String filterCrieteria) {
        System.out.println(" in get ajax object ..");
        List<RuleAjaxObject> ajaxObjects = new ArrayList<RuleAjaxObject>();
        if (fieldName.equals("reportDefinitionName")) {
            Organization org = organizationDao.getByName(filterCrieteria);
            List<ReportDefinition> reportDefinitions = org.getReportDefinitions();

            // cut down objects for serialization

            for (ReportDefinition reportDefinition : reportDefinitions) {
                ajaxObjects.add(new RuleAjaxObject(reportDefinition.getName(), reportDefinition
                                .getName()));

            }
        }

        if (fieldName.equals("treatmentAssignmentCode")) {
            // System.out.println("TAC filter is -"+filterCrieteria+"-");
            List<TreatmentAssignment> assignments = new ArrayList<TreatmentAssignment>();
            if (filterCrieteria.equals("")) {
                assignments = treatmentAssignmentDao.getAll();
            } else {
                Study study = studyDao.getByShortTitle(filterCrieteria);
                assignments = study.getTreatmentAssignments();
            }
            // cut down objects for serialization

            for (TreatmentAssignment treatmentAssignment : assignments) {
                ajaxObjects.add(new RuleAjaxObject(treatmentAssignment.getCode(),
                                treatmentAssignment.getCode()));

            }
        }
        /*
         * if (fieldName.equals("outcomeIdentifier")) { OutcomeType[] outcomeTypes =
         * OutcomeType.values(); for (int i=0 ; i<outcomeTypes.length; i++) { OutcomeType
         * outcomeType = outcomeTypes[i]; ajaxObjects.add(new
         * RuleAjaxObject(outcomeType.getCode()+"",outcomeType.getDisplayName())); }
         *  }
         */
        return ajaxObjects;
    }

    public String addCondition(int ruleCount) {
        CreateRuleCommand createRuleCommand = getAuthorRuleCommand();
        RuleSet ruleSet = (RuleSet) createRuleCommand.getRuleSet();
        Rule rule = ruleSet.getRule().get(ruleCount);
        Column column = newColumn();
        /*
         * Random r = new Random(); int id = r.nextInt(); column.setId(id);
         */
        rule.getCondition().getColumn().add(column);

        HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
        request.setAttribute("ruleCount", ruleCount);
        request.setAttribute("columnCount", rule.getCondition().getColumn().size() - 1);

        // System.out.println("sending .. " );

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
        RuleSet ruleSet = (RuleSet) createRuleCommand.getRuleSet();
        Rule rule = ruleSet.getRule().get(ruleCount);

        rule.getCondition().getColumn().get(columnCount).setMarkedDelete(true);

        return rule.getCondition().getColumn().get(columnCount) != null;

        // return rule.getCondition().getColumn().remove(columnCount) != null;

    }

    public Boolean removeRule(int ruleCount) throws Exception {
        CreateRuleCommand createRuleCommand = getAuthorRuleCommand();
        RuleSet ruleSet = (RuleSet) createRuleCommand.getRuleSet();
        ruleSet.getRule().get(ruleCount).setMarkedDelete(true);

        Rule r = ruleSet.getRule().get(ruleCount);
        String rName = r.getMetaData().getName();

        rulesEngineService.deleteRule(ruleSet.getName(), rName);

        return ruleSet.getRule().get(ruleCount) != null;
    }

    private CreateRuleCommand getAuthorRuleCommand() {
        HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
        String commandName = CreateRuleController.class.getName() + ".FORM.command";
        CreateRuleCommand createRuleCommand = (CreateRuleCommand) request.getSession()
                        .getAttribute(commandName);
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

    public void unDeployRuleSet(String ruleSetName) throws RemoteException {
        String bindUri = ruleSetName;

        try {
            getRuleDeploymentService().registerRuleSet(bindUri, ruleSetName);
        } catch (Exception e) {
            // A hack... for the first time this exception will be there...ignore...

        }
        getRuleDeploymentService().deregisterRuleSet(bindUri);
        PackageItem item = repositoryService.getRulesRepository().loadPackage(bindUri);
        item.updateCoverage("Not Enabled");
        repositoryService.getRulesRepository().save();

    }

    public void deployRuleSet(String ruleSetName) throws RemoteException {
        String bindUri = ruleSetName;

        try {
            getRuleDeploymentService().deregisterRuleSet(bindUri);
        } catch (Exception e) {
            // A hack... for the first time this exception will be there...ignore...
        }

        try {
            getRuleDeploymentService().registerRuleSet(bindUri, ruleSetName);
            PackageItem item = repositoryService.getRulesRepository().loadPackage(bindUri);
            item.updateCoverage("Enabled");
            repositoryService.getRulesRepository().save();

            // getRuleDeploymentService().registerRuleSet(bindUri, ruleSetName);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error deploying ruleset", e);
        }

        // rs.setCoverage("Deployed");

        /*
         * 
         * try {
         * 
         * getRuleDeploymentService().registerRuleSet(bindUri, ruleSetName);
         * 
         * repositoryService = (RepositoryServiceImpl)RuleServiceContext.getInstance(); RuleSet
         * ruleSet = repositoryService.getRuleSet(ruleSetName); ruleSet.setStatus("Deployed");
         * String path = ruleSet.getMetaData().getCategory().get(0).getPath();
         * 
         * if (path.indexOf("/SPONSOR/") > 0 ) { rulesEngineService.saveRulesForSponsor(ruleSet,
         * path.split("/")[1]); } else if (path.indexOf("/INSTITUTION/") > 0 ) {
         * rulesEngineService.saveRulesForInstitution(ruleSet, path.split("/")[1]); } else if
         * (path.indexOf("/SPONSOR_DEFINED_STUDY/") > 0 ) {
         * rulesEngineService.saveRulesForSponsorDefinedStudy(ruleSet, path.split("/")[1],
         * path.split("/")[2]);
         *  } else if (path.indexOf("/INSTITUTION_DEFINED_STUDY/") > 0 ) {
         * rulesEngineService.saveRulesForInstitutionDefinedStudy(ruleSet, path.split("/")[1],
         * path.split("/")[2]);
         *  } } catch (Exception e) { e.printStackTrace(); throw new RemoteException ("Error
         * updating rule status " , e );
         *  }
         */
    }

    public void exportRuleSet(String ruleSetName) throws RemoteException {
        /*
         * String tempDir = System.getProperty("java.io.tmpdir"); try { //File ruleSetFile1 =
         * File.createTempFile(ruleSetName,"export.xml"); try {
         * getRulesEngineService().exportRule(ruleSetName, tempDir); File file = new
         * File(tempDir+File.separator+RuleUtil.getStringWithoutSpaces(ruleSetName)+".xml");
         * FileInputStream fileIn = new FileInputStream(file); OutputStream out =
         * response.getOutputStream(); out.setContentType("application/octet");
         * out.setContentLength((int) file.length());
         * 
         * byte[] buffer = new byte[2048]; int bytesRead = fileIn.read(buffer); while (bytesRead >=
         * 0) { if (bytesRead > 0) out.write(buffer, 0, bytesRead); bytesRead = in.read(buffer); }
         * out.flush(); out.close(); in.close();
         * 
         * //Reader input = new BufferedReader( new
         * FileReader(tempDir+File.separator+RuleUtil.getStringWithoutSpaces(ruleSetName)+".xml"));
         *  } catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); throw
         * new RemoteException ("Error exporting ruleset ",e); }
         * 
         * 
         * 
         * //input = new BufferedReader( new FileReader(xmlFile) ); } catch (IOException e) { //
         * TODO Auto-generated catch block e.printStackTrace(); }
         *  /* DataSourceSelfDiscoveringPropertiesFactoryBean b = new
         * DataSourceSelfDiscoveringPropertiesFactoryBean(); Properties props = b.getProperties();
         * //props.list(System.out);
         * 
         * String repoLocation = props.getProperty("rules.repository");
         * 
         * String osName = System.getProperty("os.name");
         * 
         * if (!osName.toLowerCase().contains("windows")) { repoLocation =
         * repoLocation.substring(5,repoLocation.length()); } else { repoLocation =
         * repoLocation.substring(7,repoLocation.length()); }
         * 
         * 
         * String exportLocation = repoLocation + File.separator + "export";
         * 
         * File f = new File(exportLocation); try { f.mkdir();
         * 
         * getRulesEngineService().exportRule(ruleSetName, exportLocation); } catch (Exception e) { //
         * TODO Auto-generated catch block e.printStackTrace(); throw new RemoteException ("Error
         * exporting ruleset ",e); }
         */

    }

    public void fireRules(String bindUri, String mode) throws RemoteException {
    	List<Object> list = new ArrayList<Object>();
        StudySDO study = new StudySDO();
        study.setShortTitle("AML/MDS 9911");
        list.add(study);
        
        if ("1".equals(mode)) {
            list.add(getSuccessful());
        } else if ("2".equals(mode)) {
            list.add(getNonSuccessful());
        } else if ("3".equals(mode)) {
            list.add(getSuccessfulAgain());
        }
        
        
        
        businessRulesExecutionService.fireRules(bindUri, list);
    }

    public void fireAERules() throws RemoteException {
        String bindUri = "CAAERS_AE_RULES";
        ExpeditedAdverseEventReport adverseEventReport = null;
        StudySDO studySDO = null;
        ArrayList<Object> list = new ArrayList<Object>();

        // XXX: there's no way this code could work -- adverseEventReport is never initialized.
        AdverseEvent adverseEvent = adverseEventReport.getAdverseEvents().get(0);
        // TODO: This code is *exactly* the same as in CreateAdverseEventCommand.
        // Don't do that -- put it in a shared utility library
        if (adverseEvent != null) { // Little over defensive
            studySDO = new StudySDO();
            Study study = adverseEventReport.getAssignment().getStudySite().getStudy();
            studySDO.setShortTitle(study.getShortTitle());
            list.add(studySDO);
            AdverseEventSDO adverseEventSDO = new AdverseEventSDO();

            // ATTRIBUTION
            // adverseEventSDO.setAttribution(adverseEventReport.get); // Where to get this from --
            // ask Rhett

            // PHASE -- // Where to get this from -- ask Rhett
            String phase = adverseEventReport.getAssignment().getStudySite().getStudy()
                            .getPhaseCode();
            adverseEventSDO.setPhase(phase);

            // EXPECTED
            boolean expected = adverseEvent.getExpected();
            adverseEventSDO.setExpected((String.valueOf(expected)));

            // GRADE
            int grade = adverseEvent.getGrade().getCode();
            // adverseEventSDO.setGrade(String.valueOf(grade));
            adverseEventSDO.setGrade(new Integer(grade));

            // CATEGORY
            CtcCategory category = adverseEvent.getAdverseEventCtcTerm().getCtcTerm().getCategory();
            adverseEventSDO.setCategory(category.getName());

            // CTC TERM
            CtcTerm ctcTerm = adverseEvent.getAdverseEventCtcTerm().getCtcTerm();
            adverseEventSDO.setTerm(ctcTerm.getFullName());

            // YES
            int hospitalization = adverseEvent.getHospitalization().getCode();
            Boolean isHospitalization = (hospitalization == Hospitalization.NONE.getCode()) ? Boolean.FALSE
                            : Boolean.TRUE;

            adverseEventSDO.setHospitalization(isHospitalization.toString());
        }
        businessRulesExecutionService.fireRules(bindUri, list);
    }

    private AdverseEventSDO getSuccessful() {
        AdverseEventSDO adverseEventSDO = new AdverseEventSDO();
        // adverseEventSDO.setGrade("3");
        adverseEventSDO.setGrade(new Integer(3));
        adverseEventSDO.setHospitalization("No");
        adverseEventSDO.setAttribution("3");
        return adverseEventSDO;
    }

    private AdverseEventSDO getSuccessfulAgain() {
        AdverseEventSDO adverseEventSDO = new AdverseEventSDO();
        // adverseEventSDO.setGrade("1");
        adverseEventSDO.setGrade(new Integer(1));
        return adverseEventSDO;
    }

    private AdverseEventSDO getNonSuccessful() {
        AdverseEventSDO adverseEventSDO = new AdverseEventSDO();
        // adverseEventSDO.setGrade("0");
        adverseEventSDO.setGrade(new Integer(0));
        return adverseEventSDO;
    }

    // TODO: move this somewhere shared. Or, better, obviate it.
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
            destination.setPropertyValue(property, source.getPropertyValue(property));
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

 

    /*
     * !REVISIT This method is added to render valid values for the attributes selected on the
     * AdverseEvent object.
     * 
     */

    public String getValidValues(int domainObjectIndex, int fieldIndex) {
        HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
        request.setAttribute("domainObjectIndex", domainObjectIndex);
        request.setAttribute("fieldIndex", fieldIndex);
        return getOutputFromJsp("/pages/rule/createOptions");
    }

    /*
     * This method is used to retrieve the Sponsor Names based on the partial sponserName passed to
     * it.
     * 
     */
    public List<String> matchSponsors(String sponsorName) {
        // REVISIT: Replace this with the SponsorDao.

        List<Organization> orgs = organizationDao.getBySubnames(extractSubnames(sponsorName));
        List<String> sponsors = new ArrayList<String>();
        for (Organization org : orgs) {
            sponsors.add(org.getName());
        }

        /*
         * List sponsorCodeRefData = (List)
         * getConfigurationProperty().getMap().get("sponsorCodeRefData");
         * 
         * List<String> sponsors = new ArrayList<String>();
         * 
         * Iterator sponsorsItr = sponsorCodeRefData.iterator();
         * 
         * while (sponsorsItr.hasNext()) { Lov sponsor = (Lov) sponsorsItr.next();
         * 
         * if (sponsorName != null &&
         * sponsor.getDesc().toLowerCase().indexOf(sponsorName.toLowerCase(), 0) != -1) {
         * sponsors.add(sponsor.getDesc()); } }
         */
        return sponsors;
    }

    /*
     * This method is used to retrieve the Sponsor Names based on the partial sponserName passed to
     * it.
     * 
     */
    public List<Organization> matchSites(String text) {
        // System.out.println("in match sites...");
        List<Organization> sites = organizationDao.getBySubnames(extractSubnames(text));

        // cut down objects for serialization
        return ObjectTools.reduceAll(sites, "id", "name", "nciInstituteCode");
    }

    /*
     * This method returns a list of Field names based on the Domain object. This is only used for
     * rules UI
     */
    public List<Field> getFieldNames(int domainObjectIndex) {
        ServletContext servletContext = WebContextFactory.get().getServletContext();

        RuleUi ruleUi = (RuleUi) servletContext.getAttribute("ruleUi");

        if (ruleUi != null && ruleUi.getCondition() != null && ruleUi.getCondition().size() > 0
                        && ruleUi.getCondition().get(0).getDomainObject() != null
                        && ruleUi.getCondition().get(0).getDomainObject().size() > 0) {
            if (ruleUi.getCondition().get(0).getDomainObject().size() > domainObjectIndex) {
                List<Field> fields = ruleUi.getCondition().get(0).getDomainObject().get(
                                domainObjectIndex).getField();
                return fields;
            }
        }

        return null;
    }

    /*
     * This method returns a Rule UI domain object based on the index
     */
    public DomainObject getRulesDomainObject(int domainObjectIndex, String filter) {

        ServletContext servletContext = WebContextFactory.get().getServletContext();

        RuleUi ruleUi = (RuleUi) servletContext.getAttribute("ruleUi");

        if (ruleUi != null && ruleUi.getCondition() != null && ruleUi.getCondition().size() > 0
                        && ruleUi.getCondition().get(0).getDomainObject() != null
                        && ruleUi.getCondition().get(0).getDomainObject().size() > 0) {
            if (ruleUi.getCondition().get(0).getDomainObject().size() > domainObjectIndex) {
                DomainObject domainObject = ruleUi.getCondition().get(0).getDomainObject().get(
                                domainObjectIndex);

                List<Field> fields = new ArrayList<Field>();
                List<Field> fields2 = new ArrayList<Field>();

                for (Field field : domainObject.getField()) {
                    if (field.getFilter().equals("") || field.getFilter().equals(filter)) {
                        fields.add(field);
                    } else {
                        fields2.add(field);
                    }
                }
                fields.addAll(fields2);

                domainObject.setField(fields);

                return domainObject;
            }
        }

        return null;
    }

    public ReportDefinitionDao getReportDefinitionDao() {
        return reportDefinitionDao;
    }

    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
        this.reportDefinitionDao = reportDefinitionDao;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public RulesEngineService getRulesEngineService() {
        return rulesEngineService;
    }

    public void setRulesEngineService(RulesEngineService rulesEngineService) {
        this.rulesEngineService = rulesEngineService;
    }

    public void setTreatmentAssignmentDao(TreatmentAssignmentDao treatmentAssignmentDao) {
        this.treatmentAssignmentDao = treatmentAssignmentDao;
    }

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public BusinessRulesExecutionService getBusinessRulesExecutionService() {
		return businessRulesExecutionService;
	}

	public void setBusinessRulesExecutionService(
			BusinessRulesExecutionService businessRulesExecutionService) {
		this.businessRulesExecutionService = businessRulesExecutionService;
	}
    
}
