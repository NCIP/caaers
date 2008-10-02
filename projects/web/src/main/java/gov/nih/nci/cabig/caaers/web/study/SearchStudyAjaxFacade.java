package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudyAjaxableDomainObjectQuery;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SearchStudyAjaxFacade {

    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;

    public Object build(TableModel model, Collection studySearchableAjaxableDomainObjects) throws Exception {
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


        Column columnPrimaryIdentifier = model.getColumnInstance();
        columnPrimaryIdentifier.setProperty("primaryIdentifierValue");
        columnPrimaryIdentifier.setTitle("Primary ID");
        columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.study.StudyLinkDisplayCell");
        model.addColumn(columnPrimaryIdentifier);

        Column columnShortTitle = model.getColumnInstance();
        columnShortTitle.setProperty("shortTitle");
        columnShortTitle.setSortable(Boolean.TRUE);
        columnShortTitle.setCell("gov.nih.nci.cabig.caaers.web.study.StudyLinkDisplayCell");
        model.addColumn(columnShortTitle);

        Column columnSponsorCode = model.getColumnInstance();
        columnSponsorCode.setTitle("Funding Sponsor");
        columnSponsorCode.setProperty("primarySponsorCode");
        columnSponsorCode.setSortable(Boolean.TRUE);
        model.addColumn(columnSponsorCode);

        Column columnPhaseCode = model.getColumnInstance();
        columnPhaseCode.setTitle("Phase");
        columnPhaseCode.setProperty("phaseCode");
        model.addColumn(columnPhaseCode);
        columnPhaseCode.setSortable(Boolean.TRUE);

        Column columnStatusCode = model.getColumnInstance();
        columnStatusCode.setProperty("status");
        model.addColumn(columnStatusCode);
        columnStatusCode.setSortable(Boolean.TRUE);

        return model.assemble();
    }

    public String getTable(Map parameterMap, String type, String text, HttpServletRequest request) {
        StudySearchableAjaxableDomainObjectQuery studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();

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

        try {
            List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);

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


    @Required
    public void setStudySearchableAjaxableDomainObjectRepository(StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository) {
        this.studySearchableAjaxableDomainObjectRepository = studySearchableAjaxableDomainObjectRepository;
    }
}