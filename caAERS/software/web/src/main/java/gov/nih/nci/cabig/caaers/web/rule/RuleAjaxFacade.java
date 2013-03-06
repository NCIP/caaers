/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule;

import com.semanticbits.rules.brxml.*;
import com.semanticbits.rules.ui.DomainObject;
import com.semanticbits.rules.ui.Field;
import com.semanticbits.rules.ui.RuleUi;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.utils.ranking.RankBasedSorterUtils;
import gov.nih.nci.cabig.caaers.utils.ranking.Serializer;
import gov.nih.nci.cabig.caaers.web.rule.author.CreateRuleCommand;
import gov.nih.nci.cabig.caaers.web.rule.author.CreateRuleController;
import org.apache.axis.utils.StringUtils;
import org.directwebremoting.WebContextFactory;
import org.springframework.web.servlet.mvc.AbstractFormController;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

/**
 * All the DWR methods specific to rules will be here
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author Biju Joseph
 */
public class RuleAjaxFacade {
    private StudyDao studyDao;

    private OrganizationDao organizationDao;

    private TreatmentAssignmentDao treatmentAssignmentDao;
    
    private CaaersRulesEngineService caaersRulesEngineService;

    /**
     * Will filter the studies based on Sponsor and partial study short title match.
     * @param text Study Short titile
     * @param sponsorId  Organization id of the sponsor organization.
     * @return
     */
    public List<Study> matchStudies(String text, String sponsorId) {
    	if(StringUtils.isEmpty(sponsorId)) return null;
    	
    	StudyQuery studyQuery = new StudyQuery();
    	studyQuery.filterStudiesWithMatchingText(text);
    	studyQuery.joinStudyOrganization();
    	studyQuery.filterByDataEntryStatus(true);
    	studyQuery.filterByNonAdministrativelyComplete();
    	studyQuery.filterBySponsorOrganizationId(Integer.parseInt(sponsorId));
    	List<Study> studies = searchStudies(studyQuery);
        studies = RankBasedSorterUtils.sort(studies , text, new Serializer<Study>(){
            public String serialize(Study object) {
                if(object.getPrimaryIdentifierValue() != null) {
                    return object.getPrimaryIdentifierValue() + " " + object.getShortTitle();
                }
                return object.getShortTitle();
            }
        });
        return studies;
    }

  

    /**
     * Will find the studies based on study sites.
     * @param text  - Study name (partial)
     * @param institutionId  StudySites&apos;s organization ID.
     * @return
     */
    public List<Study> matchStudiesByInstitution(String text, String institutionId) {
    	if(StringUtils.isEmpty(institutionId)) return null;

        //BJ : Basically we should join study sites, but not an issue in this case as user is going to pick study. 
    	StudyQuery studyQuery = new StudyQuery();
    	studyQuery.filterStudiesWithMatchingText(text);
    	studyQuery.joinStudyOrganization();
    	studyQuery.filterByDataEntryStatus(true);
    	studyQuery.filterByNonAdministrativelyComplete();
    	studyQuery.filterByOrganizationId(Integer.parseInt(institutionId));
        List<Study> studies = searchStudies(studyQuery);
        studies = RankBasedSorterUtils.sort(studies , text, new Serializer<Study>(){
            public String serialize(Study object) {
                if(object.getPrimaryIdentifierValue() != null) {
                    return object.getPrimaryIdentifierValue() + " " + object.getShortTitle();
                }
                return object.getShortTitle();
            }
        });
        return studies;
    	
    }

    /**
     * Wil search the studies, then returns a list of reduced studies.
     * @param studyQuery  - The query
     * @return  A list of reduced study objects having (id, short title and primaryIdentifier).
     */
    public List<Study> searchStudies(StudyQuery studyQuery){
       List<Study> studies = studyDao.find(studyQuery);
    	Map<Integer, Study> studyMap = new HashMap<Integer, Study>();
    	for(Study study: studies){
    		studyMap.put(study.getId(), study);
    	}
    	return ObjectTools.reduceAll(new ArrayList<Study>(studyMap.values()), "id", "shortTitle", "primaryIdentifierValue");
    }

    
//    BJ:Find no usage of this (so to be removed)
//    public List<CtcTerm> fetchTerms() throws Exception {
//        List<CtcTerm> terms = ctcTermDao.getBySubname(extractSubnames("%"), null, null);
//        // cut down objects for serialization
//        for (CtcTerm term : terms) {
//            term.getCategory().setTerms(null);
//            term.getCategory().getCtc().setCategories(null);
//        }
//        return terms;
//    }

    /**
     * This will access the spring managed object from the session (RuleSet) and will update the
     * object with Condition.
     * 
     * Then will forward to a jsp to get the html for that condition and will return that.
     * 
     */
    public String addRule() {
        CreateRuleCommand createRuleCommand = getAuthorRuleCommand();
        RuleSet ruleSet = (RuleSet) createRuleCommand.getRuleSet();
        Rule newRule = new Rule();
        MetaData metaData = new MetaData();
        newRule.setMetaData(metaData);

        Condition condition = newCondition();
        newRule.setCondition(condition);

        // Action action = new Action();
        List<String> action = new ArrayList<String>();
        newRule.setAction(action);

        ruleSet.getRule().add(newRule);

        // Set the name as the name field has been removed from UI.
        Integer ruleCount = ruleSet.getRule().size() - 1;
        Integer ruleNumber = ++ruleCount;
        newRule.getMetaData().setName("Rule-" + ruleNumber);
        // Done setting the rule name.

        HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
        request.setAttribute("ruleCount", ruleSet.getRule().size() - 1);
        request.setAttribute(AbstractFormController.DEFAULT_COMMAND_NAME, createRuleCommand);

        return getOutputFromJsp("/pages/rule/addRule");
    }

    public List<RuleAjaxObject> getAjaxObjects(String fieldName, String filterCrieteria) {
       CreateRuleCommand createRuleCommand = getAuthorRuleCommand();
        List<RuleAjaxObject> ajaxObjects = new ArrayList<RuleAjaxObject>();
        if (fieldName.equals("reportDefinitionName")) {

            for (ReportDefinition reportDefinition : createRuleCommand.getReportDefinitions()) {
                ajaxObjects.add(new RuleAjaxObject(reportDefinition.getName(), reportDefinition.getName()));
            }
        }

        if (fieldName.equals("treatmentAssignmentCode")) {
            // System.out.println("TAC filter is -"+filterCrieteria+"-");
            List<TreatmentAssignment> assignments = new ArrayList<TreatmentAssignment>();
            if (filterCrieteria.equals("")) {
                assignments = treatmentAssignmentDao.getAll();
            } else {
                Study study = studyDao.getById(Integer.parseInt(filterCrieteria));
                assignments = study.getTreatmentAssignments();
            }
            // cut down objects for serialization

            for (TreatmentAssignment treatmentAssignment : assignments) {
                ajaxObjects.add(new RuleAjaxObject(treatmentAssignment.getCode(),treatmentAssignment.getCode()));
            }
        }
        
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
        
        rule.getCondition().getColumn().remove(columnCount);
        return true;
        //return rule.getCondition().getColumn().get(columnCount) != null;

        // return rule.getCondition().getColumn().remove(columnCount) != null;

    }

    public Boolean removeRule(int ruleCount) throws Exception {
        CreateRuleCommand createRuleCommand = getAuthorRuleCommand();
        RuleSet ruleSet = (RuleSet) createRuleCommand.getRuleSet();
        ruleSet.getRule().get(ruleCount).setMarkedDelete(true);

//        Rule r = ruleSet.getRule().get(ruleCount);
//        String rName = r.getMetaData().getName();
//        caaersRulesEngineService.deleteRule(ruleSet.getName(), rName);
//
//        ruleSet.getRule().remove(ruleCount);

        return true;
        //return ruleSet.getRule().get(ruleCount) != null;
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

    /**
     * Will undeploy the rule-set identified by the bind URI
     * @param ruleSetName
     * @throws RemoteException
     */
    public void unDeployRuleSet(String ruleSetName) throws RemoteException {
        caaersRulesEngineService.unDeployRuleSet(ruleSetName);
    }

    /**
     * Will deploy the rule-set identified by the bind URI
     * @param ruleSetName - The bind URI
     * @throws RemoteException
     */
    public void deployRuleSet(String ruleSetName) throws RemoteException {
    	caaersRulesEngineService.deployRuleSet(ruleSetName);
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

    
    /**
     * This method will be called to fetch the sponsors.
     * It was added as we needed the ID to be displayed in the returned values. Earlier only the name of the organization was
     * displayed.
     * @param text
     * @return List<Organization>
     * Note:- Used by selectRuleType.jsp
     */
    public List<Organization> matchOrganization(final String text) {
    	OrganizationQuery query = new OrganizationQuery();
    	query.filterByOrganizationNameOrNciCode(text);
        List<Organization> orgs = organizationDao.getBySubnames(query);
        orgs = RankBasedSorterUtils.sort(orgs , text, new Serializer<Organization>(){
            public String serialize(Organization object) {
                return object.getFullName();
            }
        });
        return ObjectTools.reduceAll(orgs, "id", "name", "nciInstituteCode", "externalId");
    }

    /*
     * This method is used to retrieve the Sponsor Names based on the partial sponserName passed to
     * it.
     * 
     */
    public List<Organization> matchSites(String text) {
        // System.out.println("in match sites...");
    	OrganizationQuery query = new OrganizationQuery();
    	query.filterByOrganizationNameOrNciCode(text);
        List<Organization> sites = organizationDao.getBySubnames(query);
        sites = RankBasedSorterUtils.sort(sites , text, new Serializer<Organization>(){
            public String serialize(Organization object) {
                return object.getFullName();
            }
        });

        // cut down objects for serialization
        return ObjectTools.reduceAll(sites, "id", "name", "nciInstituteCode");
    }

    
//  BJ: could not find any references to this method, so commenting it. (later to be removed)
//    /*
//     * This method returns a list of Field names based on the Domain object. This is only used for
//     * rules UI
//     */
//    public List<Field> getFieldNames(int domainObjectIndex) {
//        ServletContext servletContext = WebContextFactory.get().getServletContext();
//
//        RuleUi ruleUi = (RuleUi) servletContext.getAttribute("ruleUi");
//
//        if (ruleUi != null && ruleUi.getCondition() != null && ruleUi.getCondition().size() > 0
//                        && ruleUi.getCondition().get(0).getDomainObject() != null
//                        && ruleUi.getCondition().get(0).getDomainObject().size() > 0) {
//            if (ruleUi.getCondition().get(0).getDomainObject().size() > domainObjectIndex) {
//                List<Field> fields = ruleUi.getCondition().get(0).getDomainObject().get(
//                                domainObjectIndex).getField();
//                return fields;
//            }
//        }
//
//        return null;
//    }

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


    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void setTreatmentAssignmentDao(TreatmentAssignmentDao treatmentAssignmentDao) {
        this.treatmentAssignmentDao = treatmentAssignmentDao;
    }
	
	public void setCaaersRulesEngineService(CaaersRulesEngineService caaersRulesEngineService){
    	this.caaersRulesEngineService = caaersRulesEngineService;
    }
    
    public CaaersRulesEngineService getCaaersRulesEngineService(){
    	return caaersRulesEngineService;
    }
    
}
