package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;
import gov.nih.nci.cabig.caaers.web.participant.AssignParticipantController;
import gov.nih.nci.cabig.caaers.web.participant.AssignParticipantStudyCommand;
import gov.nih.nci.cabig.caaers.CaaersSystemException;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;
import org.springframework.beans.factory.annotation.Required;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class SearchStudyAjaxFacade {

    private Class<?>[] CONTROLLERS = {AssignParticipantController.class};
    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;
    private static final Log log = LogFactory.getLog(SearchStudyAjaxFacade.class);

    public Object build(TableModel model, Collection studySearchableAjaxableDomainObjects) throws Exception {
        addTable(model, studySearchableAjaxableDomainObjects);
        addPrimaryIdColumn(model);
        addShorTitleColumn(model);
        addSponsorColumn(model);
        addPhaseCodeColumn(model);
        addStatusColumn(model);
        return model.assemble();
    }

    private void addStatusColumn(TableModel model) {
        Column columnStatusCode = model.getColumnInstance();
        columnStatusCode.setProperty("status");
        model.addColumn(columnStatusCode);
        columnStatusCode.setSortable(Boolean.TRUE);
    }

    private void addPhaseCodeColumn(TableModel model) {
        Column columnPhaseCode = model.getColumnInstance();
        columnPhaseCode.setTitle("Phase");
        columnPhaseCode.setProperty("phaseCode");
        model.addColumn(columnPhaseCode);
        columnPhaseCode.setSortable(Boolean.TRUE);
    }

    private void addSponsorColumn(TableModel model) {
        Column columnSponsorCode = model.getColumnInstance();
        columnSponsorCode.setTitle("Funding Sponsor");
        columnSponsorCode.setProperty("primarySponsorCode");
        columnSponsorCode.setSortable(Boolean.TRUE);
        model.addColumn(columnSponsorCode);
    }

    private void addShorTitleColumn(TableModel model) {
        Column columnShortTitle = model.getColumnInstance();
        columnShortTitle.setProperty("shortTitle");
        columnShortTitle.setSortable(Boolean.TRUE);
        columnShortTitle.setCell("gov.nih.nci.cabig.caaers.web.study.StudyLinkDisplayCell");
        model.addColumn(columnShortTitle);
    }

    private void addPrimaryIdColumn(TableModel model) {
        Column columnPrimaryIdentifier = model.getColumnInstance();
        columnPrimaryIdentifier.setProperty("primaryIdentifierValue");
        columnPrimaryIdentifier.setTitle("Study ID");
        columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.study.StudyLinkDisplayCell");
        model.addColumn(columnPrimaryIdentifier);
    }

    private void addTable(TableModel model, Collection studySearchableAjaxableDomainObjects) {
        Table table = model.getTableInstance();
        table.setTableId("ajaxTable");
        table.setForm("assembler");
        table.setItems(studySearchableAjaxableDomainObjects);
        table.setAction(model.getContext().getContextPath() + "/assembler.run");
        table.setTitle("");
        table.setShowPagination(true);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(true);
        table.setSortable(false);
        table.setSortRowsCallback("gov.nih.nci.cabig.caaers.web.table.SortRowsCallbackImpl");

        table.setAutoIncludeParameters(false);
        model.addTable(table);

        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);
        model.addRow(row);
    }

    public String getTable(Map parameterMap, String type, String text, HttpServletRequest request) {
        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = getObjects(type, text);

        try {

            Context context = null;
            if (parameterMap == null) {
                context = new HttpServletRequestContext(request);
            } else {
                context = new HttpServletRequestContext(request, parameterMap);
            }

            TableModel model = new TableModelImpl(context);
            return build(model, studySearchableAjaxableDomainObjects).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public String getTableForAssignParticipant(Map parameterMap, String type, String text, HttpServletRequest request) {

        int organizationID;
        try {
            organizationID = Integer.parseInt((String) parameterMap.get("organizationID"));
        } catch (Exception e) {
            organizationID = 0;
        }

        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = getObjects(type, text, organizationID, true);

        // filter objects
        Object command = extractCommand();
        if (command instanceof AssignParticipantStudyCommand) {
            AssignParticipantStudyCommand c = (AssignParticipantStudyCommand)command;
            List<StudySearchableAjaxableDomainObject> _s = new ArrayList<StudySearchableAjaxableDomainObject>();
            boolean isTheSameSite = c.getLoggedInOrganizations().contains(c.getOrganization());
            Set<String> orgCodes = new HashSet<String>();

            for (Organization o : c.getLoggedInOrganizations()) {
                orgCodes.add(o.getNciInstituteCode());
            }

            for (StudySearchableAjaxableDomainObject s : studySearchableAjaxableDomainObjects) {
                boolean isGood = false;

                    if (isTheSameSite) {
                            // if the Participant's Site is the same as Loggedin user, show all studies where this site is just a StudySite
                            for (StudySiteAjaxableDomainObject ss : s.getStudySites()) {
                                if (ss.getNciInstituteCode().equals(c.getOrganization().getNciInstituteCode())) {
                                    isGood = true;
                                }
                            }
                    } else {
                            // if the Participant's Site is other than Loggedin user, show all studies where this site is just a StudySite
                            if (orgCodes.contains(s.getCoordinatingCenterCode()) || orgCodes.contains(s.getPrimarySponsorCode())) {
                                isGood = true;
                            }
                    }

                if (isGood) _s.add(s);
            }

            studySearchableAjaxableDomainObjects = _s;
        }
        //

        try {

            Context context = null;
            if (parameterMap == null) {
                context = new HttpServletRequestContext(request);
            } else {
                context = new HttpServletRequestContext(request, parameterMap);
            }

            TableModel model = new TableModelImpl(context);
            addTable(model, studySearchableAjaxableDomainObjects);


            Column columnPrimaryIdentifier = model.getColumnInstance();
            columnPrimaryIdentifier.setProperty("primaryIdentifierValue");
            columnPrimaryIdentifier.setSortable(true);
            columnPrimaryIdentifier.setTitle("Study ID");
            model.addColumn(columnPrimaryIdentifier);

            Column columnShortTitle = model.getColumnInstance();
            columnShortTitle.setProperty("shortTitle");
            columnShortTitle.setSortable(Boolean.TRUE);
            model.addColumn(columnShortTitle);

            addSponsorColumn(model);
            addPhaseCodeColumn(model);
            addStatusColumn(model);

            Column columnStudySite = model.getColumnInstance();
            columnStudySite.setProperty("shortTitle");
            columnStudySite.setSortable(Boolean.TRUE);
            columnStudySite.setTitle("Study Sites");
            columnStudySite.setCell("gov.nih.nci.cabig.caaers.web.search.cell.SelectedStudySiteCell");
            model.addColumn(columnStudySite);


            return model.assemble().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    private List<StudySearchableAjaxableDomainObject> getObjects(String type, String text) {
        return getObjects(type, text, 0, false);
    }

    public List<StudySearchableAjaxableDomainObject> getObjects(String type, String text, int organizationID, boolean hideIncomplete) {
        StudySearchableAjaxableDomainObjectQuery studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();

        if (organizationID > 0)
            studySearchableAjaxableDomainObjectQuery.filterStudiesByStudySiteBySiteId(organizationID);
        
        studySearchableAjaxableDomainObjectQuery.filterByDataEntryStatus(hideIncomplete);

        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        String sType;
        String sText;
        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();

            if ("st".equals(sType)) {
                studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingShortTitleOnly(sText);
            } else if ("idtf".equals(sType)) {
                studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingIdentifierOnly(sText);
            }
        }

        
        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery,type, text);
        return studySearchableAjaxableDomainObjects;
    }


    @Required
    public void setStudySearchableAjaxableDomainObjectRepository(StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository) {
        this.studySearchableAjaxableDomainObjectRepository = studySearchableAjaxableDomainObjectRepository;
    }

    private Object extractCommand() {
        WebContext webContext = WebContextFactory.get();
        Object command = null;
        for (Class<?> controllerClass : CONTROLLERS) {
            String formSessionAttributeName = controllerClass.getName() + ".FORM.command";
            command = webContext.getSession().getAttribute(formSessionAttributeName);
            if (command == null) {
                log.debug("Command not found using name " + formSessionAttributeName);
            } else {
                log.debug("Command found using name " + formSessionAttributeName);
                break;
            }
        }
        if (command == null) {
            throw new CaaersSystemException("Could not find command in session");
        } else {
            return command;
        }
    }

    public Class<?>[] getCONTROLLERS() {
        return CONTROLLERS;
    }

    public void setCONTROLLERS(Class<?>[] CONTROLLERS) {
        this.CONTROLLERS = CONTROLLERS;
    }

}