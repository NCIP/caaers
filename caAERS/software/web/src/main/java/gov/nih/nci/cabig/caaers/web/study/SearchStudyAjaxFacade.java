package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.query.StudySitesQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
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
    private StudyRepository studyRepository;
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
        columnStatusCode.setProperty("study.status");
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
        columnShortTitle.setTitle("Short Title");
        columnShortTitle.setProperty("shortTitle");
        columnShortTitle.setSortable(Boolean.TRUE);
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
        table.setShowPagination(Configuration.LAST_LOADED_CONFIGURATION.isAuthenticationModeLocal());
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        //only support filtering & sorting in local authentication mode. 
        table.setFilterable(Configuration.LAST_LOADED_CONFIGURATION.isAuthenticationModeLocal());
        table.setSortable(Configuration.LAST_LOADED_CONFIGURATION.isAuthenticationModeLocal());
        if(Configuration.LAST_LOADED_CONFIGURATION.isAuthenticationModeLocal()){
        	table.setRowsDisplayed(100);
        }
        table.setSortRowsCallback("gov.nih.nci.cabig.caaers.web.table.SortRowsCallbackImpl");

        table.setAutoIncludeParameters(false);
        model.addTable(table);

        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);
        model.addRow(row);
    }

    public String getStudiesTable(Map parameterMap, String type, String text, HttpServletRequest request) {
/*
        List<Study> studies = getStudyObjects(type, text);

        try {

            Context context = null;
            if (parameterMap == null) {
                context = new HttpServletRequestContext(request);
            } else {
                context = new HttpServletRequestContext(request, parameterMap);
            }

            TableModel model = new TableModelImpl(context);
            return build(model, studySites).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
*/
        return "";
    }

    // ASSIGN Study Search
    public String getTableForAssignParticipant(Map parameterMap, String type, String text, HttpServletRequest request) {
        int organizationID;
        try {
            organizationID = Integer.parseInt((String) parameterMap.get("organizationID"));
        } catch (Exception e) {
            organizationID = 0;
        }

        List<StudySite> studySites = getStudySites(type, text, organizationID, true);

        try {

            Context context = null;
            if (parameterMap == null) {
                context = new HttpServletRequestContext(request);
            } else {
                context = new HttpServletRequestContext(request, parameterMap);
            }

            TableModel model = new TableModelImpl(context);
            addTable(model, studySites);


            Column columnPrimaryIdentifier = model.getColumnInstance();
            columnPrimaryIdentifier.setProperty("study.primaryIdentifierValue");
            columnPrimaryIdentifier.setSortable(true);
            columnPrimaryIdentifier.setTitle("Study ID");
            model.addColumn(columnPrimaryIdentifier);

            Column columnShortTitle = model.getColumnInstance();
            columnShortTitle.setTitle("Short Title");
            columnShortTitle.setProperty("study.shortTitle");
            columnShortTitle.setSortable(Boolean.TRUE);
            model.addColumn(columnShortTitle);

            Column columnStatusCode = model.getColumnInstance();
            columnStatusCode.setTitle("Status");
            columnStatusCode.setProperty("study.status");
            model.addColumn(columnStatusCode);
            columnStatusCode.setSortable(Boolean.TRUE);

            Column columnPhaseCode = model.getColumnInstance();
            columnPhaseCode.setTitle("Phase");
            columnPhaseCode.setProperty("study.phaseCode");
            model.addColumn(columnPhaseCode);
            columnPhaseCode.setSortable(Boolean.TRUE);

            Column columnSponsorCode = model.getColumnInstance();
            columnSponsorCode.setTitle("Funding Sponsor");
            columnSponsorCode.setProperty("study.primarySponsorCode");
            columnSponsorCode.setSortable(Boolean.TRUE);
            model.addColumn(columnSponsorCode);

            Column columnStudySite = model.getColumnInstance();
            columnStudySite.setTitle("Study Sites");
            columnStudySite.setProperty("organization.name");
            columnStudySite.setCell("gov.nih.nci.cabig.caaers.web.search.cell.SelectedStudySiteCell");
            model.addColumn(columnStudySite);


            return model.assemble().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    private List<StudySite> getObjects(String type, String text) {
        return getStudySites(type, text, 0, false);
    }

    public List<StudySite> getStudySites(String type, String text, int organizationID, boolean hideIncomplete) {
        StudySitesQuery studySitesQuery = new StudySitesQuery();

        if (organizationID > 0)
            studySitesQuery.filterByOrganizationId(organizationID);
        studySitesQuery.filterByDataEntryComplete(hideIncomplete);

        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        String sType;
        String sText;
        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();

            if ("st".equals(sType)) {
                studySitesQuery.filterStudiesWithMatchingShortTitleOnly(sText);
            } else if ("idtf".equals(sType)) {
                studySitesQuery.filterStudiesWithMatchingIdentifierOnly(sText);
            }
        }

        List<StudySite> studySites = studyRepository.search(studySitesQuery, type, text, true);
        return studySites;
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

    public StudyRepository getStudyRepository() {
        return studyRepository;
    }

    public void setStudyRepository(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }
}