package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;
import org.springframework.beans.factory.annotation.Required;

public class SearchStudyAjaxFacade {

    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;

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
        columnPrimaryIdentifier.setTitle("Primary ID");
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
            columnPrimaryIdentifier.setTitle("Primary ID");
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

    private List<StudySearchableAjaxableDomainObject> getObjects(String type, String text, int organizationID, boolean hideIncomplete) {
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
        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);
        return studySearchableAjaxableDomainObjects;
    }


    @Required
    public void setStudySearchableAjaxableDomainObjectRepository(StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository) {
        this.studySearchableAjaxableDomainObjectRepository = studySearchableAjaxableDomainObjectRepository;
    }
}