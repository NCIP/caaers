package gov.nih.nci.cabig.caaers.web.search;


import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.ParticipantAjaxableDomainObjectRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;
import org.extremecomponents.table.view.CsvView;
import org.springframework.beans.factory.annotation.Required;

public class SearchParticipantAjaxFacade {
    private static final Log log = LogFactory.getLog(SearchParticipantAjaxFacade.class);
   
    private ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository;

    public SearchParticipantAjaxFacade() {
    }



    public Object buildParticipant(final TableModel model, final Collection participants) throws Exception {
        Table table = model.getTableInstance();
        table.setTableId("ajaxTable");
        table.setForm("assembler");
        table.setItems(participants);
        table.setAction(model.getContext().getContextPath() + "/pages/search/participant");
        table.setTitle("");
        table.setShowPagination(true);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(true);
        table.setSortable(true);
        table.setSortRowsCallback("gov.nih.nci.cabig.caaers.web.table.SortRowsCallbackImpl");

        table.setShowPagination(true);
        model.addTable(table);

        Export export = model.getExportInstance();
        export.setView(TableConstants.VIEW_CSV);
        export.setViewResolver(TableConstants.VIEW_CSV);
        export.setImageName(TableConstants.VIEW_CSV);
        export.setText(TableConstants.VIEW_CSV);
        export.addAttribute(CsvView.DELIMITER, "|");
        export.setFileName("caaers_participants.txt");
        model.addExport(export);

        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);
        model.addRow(row);

        Column columnPrimaryIdentifier = model.getColumnInstance();
        columnPrimaryIdentifier.setSortable(false);
        columnPrimaryIdentifier.setProperty("primaryIdentifierValue");
        columnPrimaryIdentifier.setTitle("Primary ID");
        columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantLinkDisplayCell");
        model.addColumn(columnPrimaryIdentifier);

        Column columnFirstName = model.getColumnInstance();
        columnFirstName.setProperty("firstName");
        model.addColumn(columnFirstName);

        Column columnLastName = model.getColumnInstance();
        columnLastName.setProperty("lastName");
        model.addColumn(columnLastName);

        Column colummGender = model.getColumnInstance();
        colummGender.setProperty("gender");
        model.addColumn(colummGender);

        Column colummRace = model.getColumnInstance();
        colummRace.setProperty("race");
        model.addColumn(colummRace);

        Column colummEthnicity = model.getColumnInstance();
        colummEthnicity.setProperty("ethnicity");
        model.addColumn(colummEthnicity);

        Column columnStudyPrimaryIdentifier = model.getColumnInstance();
        //columnStudyPrimaryIdentifier.setProperty("test");
        //columnStudyPrimaryIdentifier.setSortable(false);
        columnStudyPrimaryIdentifier.setTitle("Associated Study ID(s)");
        columnStudyPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantStudyLinkDisplayCell");
        model.addColumn(columnStudyPrimaryIdentifier);

        return model.assemble();
    }


    @SuppressWarnings("finally")
    private List<ParticipantAjaxableDomainObject> constructExecuteParticipantQuery(final String type, final String text) {

        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        log.debug("type :: " + type);
        log.debug("text :: " + text);
        String sType, sText;
        Map<String, String> propValue = new HashMap<String, String>();
        
        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = new ArrayList<ParticipantAjaxableDomainObject>();

        // map the html properties to the model properties
        Map<String, String> m = new HashMap<String, String>();
        m.put("prop0", "studyIdentifier");
        m.put("prop1", "studyShortTitle");
        m.put("prop2", "participantIdentifier");
        m.put("prop3", "participantFirstName");
        m.put("prop4", "participantLastName");
        m.put("prop5", "participantEthnicity");
        m.put("prop6", "participantGender");
        m.put("prop7", "participantDateOfBirth");

        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();
            // Create a map of property key ,and search criteria
            propValue.put(m.get(sType), sText);
        }
        
        try {
			query.filterParticipants(propValue);
//			query.filterByStudyIdentifierValue(propValue.get("studyIdentifier"));
//			query.filterByStudyShortTitle(propValue.get("studyShortTitle"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Query Parsing Error : constructExecuteParticipantQuery", e);
		}
 
		participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(query);
        return participantAjaxableDomainObjects;
        /*
        try {
            participants = participantDao.searchParticipant(propValue);
        }
        catch (Exception e) {
            throw new RuntimeException("Formatting Error", e);
        }
        finally {
            return participants;
        }
        */
    }


    /*
      * Ajax Call hits this method to generate table
      */
    public String getParticipantTable(final Map parameterMap, final String type, final String text, final HttpServletRequest request) {

        //List<Participant> participants = new ArrayList<Participant>();
    	List<ParticipantAjaxableDomainObject> participants= new ArrayList<ParticipantAjaxableDomainObject>();
        if (type != null && text != null) {
            participants = constructExecuteParticipantQuery(type, text);
        }
        log.debug("Participants :: " + participants.size());

        Context context = null;
        if (parameterMap == null) {
            context = new HttpServletRequestContext(request);
        } else {
            context = new HttpServletRequestContext(request, parameterMap);
        }

        TableModel model = new TableModelImpl(context);
        // LimitFactory limitFactory = new TableLimitFactory(context);
        // Limit limit = new TableLimit(limitFactory);
        // limit.setRowAttributes(totalRows, DEFAULT_ROWS_DISPLAYED);
        // model.setLimit(limit);

        try {
            return buildParticipant(model, participants).toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Builds a table
     *
     * @param items          - A list consisting of item that should be painted in each row
     * @param actionPath     - The path of the actions
     * @param exportFileName - The file to which the table data to be exported
     * @param cvObjects      - A ColumnValue Object, which has the details of the columns in the table.
     */
    @SuppressWarnings("unchecked")
    public Object buildTable(final TableModel model, final Collection items, final String actionPath,
                             final String exportFileName, final ColumnValueObject... cvObjects) throws Exception {
        Table table = model.getTableInstance();
        table.setTableId("ajaxTable");
        table.setForm("assembler");
        table.setItems(items);
        table.setAction(model.getContext().getContextPath() + actionPath);
        table.setTitle("");
        table.setShowPagination(false);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(true);
        table.setSortRowsCallback("gov.nih.nci.cabig.caaers.web.table.SortRowsCallbackImpl");

        table.setSortable(true);
        model.addTable(table);
        if (StringUtils.isNotEmpty(exportFileName)) {
            Export export = model.getExportInstance();
            export.setView(TableConstants.VIEW_CSV);
            export.setViewResolver(TableConstants.VIEW_CSV);
            export.setImageName(TableConstants.VIEW_CSV);
            export.setText(TableConstants.VIEW_CSV);
            export.addAttribute(CsvView.DELIMITER, "|");
            export.setFileName(exportFileName);
            model.addExport(export);
        }
        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);
        model.addRow(row);
        for (ColumnValueObject cv : cvObjects) {
            Column col = model.getColumnInstance();
            col.setTitle(cv.title);
            col.setProperty(cv.propertyName);
            col.setSortable(cv.sortable);
            if (StringUtils.isNotEmpty(cv.alias)) {
                col.setAlias(cv.alias);
            }
            if (StringUtils.isNotEmpty(cv.format)) {
                col.setFormat(cv.format);
            }
            if (StringUtils.isNotEmpty(cv.cellDisplay)) {
                col.setCellDisplay(cv.cellDisplay);
            }
            if (StringUtils.isNotEmpty(cv.cellType)) {
                col.setCell(cv.cellType);
            }
            model.addColumn(col);
        }

        return model.assemble();
    }


    @SuppressWarnings("finally")
    private List<ParticipantAjaxableDomainObject> getParticipants(final String type, final String text) {

        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        log.debug("type :: " + type);
        log.debug("text :: " + text);
        String sType, sText;

        List<ParticipantAjaxableDomainObject> participants = new ArrayList<ParticipantAjaxableDomainObject>();
        
        
        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        //ParticipantQuery participantQuery = new ParticipantQuery();
        //participantQuery.leftJoinFetchOnIdentifiers();
        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();
            
            if (sType.equals("firstName")) {
            	query.filterByFirstName(sText);
            } else if (sType.equals("identifier")) {
                query.filterByLastName(sText);
            } else if (sType.equals("lastName")) {
                query.filterByParticipantIdentifierValue(sText);
            }
        }

        try {
            participants = participantAjaxableDomainObjectRepository.findParticipants(query);
        }
        catch (Exception e) {
            throw new RuntimeException("Formatting Error", e);
        }
        finally {
            return participants;
        }
    }

    public String buildParticipantTable(final Map parameterMap, final String type, final String text, final HttpServletRequest request) {

        List<ParticipantAjaxableDomainObject> participants = new ArrayList<ParticipantAjaxableDomainObject>();
        if (type != null && text != null) {
            participants = getParticipants(type, text);
        }

        Context context = null;
        if (parameterMap == null) {
            context = new HttpServletRequestContext(request);
        } else {
            context = new HttpServletRequestContext(request, parameterMap);
        }

        TableModel model = new TableModelImpl(context);
        // LimitFactory limitFactory = new TableLimitFactory(context);
        // Limit limit = new TableLimit(limitFactory);
        // limit.setRowAttributes(totalRows, DEFAULT_ROWS_DISPLAYED);
        // model.setLimit(limit);
        
        try {
            return buildPartcipantTable(model, participants).toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }

    public Object buildPartcipantTable(final TableModel model, final List<ParticipantAjaxableDomainObject> participants) throws Exception {
        Table table = model.getTableInstance();
        table.setTableId("ajaxTable");
        table.setForm("assembler");
        table.setItems(participants);
        table.setAction(model.getContext().getContextPath() + "/pages/search/participant");
        table.setTitle("");
        table.setShowPagination(true);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(true);
        table.setSortable(true);
        table.setSortRowsCallback("gov.nih.nci.cabig.caaers.web.table.SortRowsCallbackImpl");

        table.setShowPagination(true);
        model.addTable(table);

        Export export = model.getExportInstance();
        export.setView(TableConstants.VIEW_CSV);
        export.setViewResolver(TableConstants.VIEW_CSV);
        export.setImageName(TableConstants.VIEW_CSV);
        export.setText(TableConstants.VIEW_CSV);
        export.addAttribute(CsvView.DELIMITER, "|");
        export.setFileName("caaers_participants.txt");
        model.addExport(export);

        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);
        model.addRow(row);

        Column columnFirstName = model.getColumnInstance();
        columnFirstName.setProperty("firstName");
        columnFirstName.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantLinkDisplayCell");
        model.addColumn(columnFirstName);

        Column columnLastName = model.getColumnInstance();
        columnLastName.setProperty("lastName");
        model.addColumn(columnLastName);

        Column columnPrimaryIdentifier = model.getColumnInstance();
        columnPrimaryIdentifier.setSortable(false);
        columnPrimaryIdentifier.setProperty("primaryIdentifier.value");
        columnPrimaryIdentifier.setTitle("Primary ID");
        columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantLinkDisplayCell");
        model.addColumn(columnPrimaryIdentifier);

        return model.assemble();

    }

    @Required
	public void setParticipantAjaxableDomainObjectRepository(
			ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository) {
		this.participantAjaxableDomainObjectRepository = participantAjaxableDomainObjectRepository;
	}
}


