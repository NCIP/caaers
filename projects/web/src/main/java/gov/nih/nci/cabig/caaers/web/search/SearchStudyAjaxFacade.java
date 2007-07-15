package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.StudyService;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;

import gov.nih.nci.cabig.caaers.dao.RoutineAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.view.CsvView;
public class SearchStudyAjaxFacade {
	private static final Log log = LogFactory.getLog(SearchStudyAjaxFacade.class);

	private StudyService studyService;    
	private StudyDao studyDao;
	private ParticipantDao participantDao;
	private AdverseEventDao adverseEventDao;
	private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	private RoutineAdverseEventReportDao routineAdverseEventReportDao;
	
	
	public SearchStudyAjaxFacade() {
	}
	
	public SearchStudyAjaxFacade(StudyDao studyDao,
			ParticipantDao participantDoa, AdverseEventDao adverseEventDao,
			ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao,
			RoutineAdverseEventReportDao routineAdverseEventReportDao) {
		this.studyDao = studyDao;
		this.participantDao = participantDoa;
		this.adverseEventDao = adverseEventDao;
		this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
		this.routineAdverseEventReportDao = routineAdverseEventReportDao;
	}

    public Object build(TableModel model, Collection studies) throws Exception 
    {
        Table table = model.getTableInstance();
        table.setTableId("assembler");
        table.setForm("assembler");
        table.setItems(studies);
        table.setVar("study");
        table.setAction(model.getContext().getContextPath() + "/pages/search/study");
        table.setTitle("");
        table.setShowPagination(false);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(true);
        table.setSortable(true);
        //table.setRowsDisplayed(10);
        //table.setShowPagination(true);
        //table.setShowExports(true);
        //table.setShowStatusBar(true);
        model.addTable(table);
        
       
        Export export = model.getExportInstance();
        export.setView(TableConstants.VIEW_CSV);
        export.setViewResolver(TableConstants.VIEW_CSV);
        export.setImageName(TableConstants.VIEW_CSV);
        export.setText(TableConstants.VIEW_CSV);
        export.addAttribute(CsvView.DELIMITER, "|");
        export.setFileName("caaers_studies.txt");
        model.addExport(export);
      
        
        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);        
        model.addRow(row);
          
        Column columnPrimaryIdentifier = model.getColumnInstance();
        columnPrimaryIdentifier.setSortable(false);
        columnPrimaryIdentifier.setProperty("primaryIdentifier");
        columnPrimaryIdentifier.setTitle("Primary ID");
        columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.study.StudyLinkDisplayCell");        
        model.addColumn(columnPrimaryIdentifier);
        
        Column columnShortTitle = model.getColumnInstance();
        columnShortTitle.setProperty("shortTitle");
        model.addColumn(columnShortTitle);
        
        Column columnSponsorCode = model.getColumnInstance();
        columnSponsorCode.setTitle("Sponsor");
        columnSponsorCode.setProperty("primarySponsorCode");
        model.addColumn(columnSponsorCode);
        
        Column columnPhaseCode = model.getColumnInstance();
        columnPhaseCode.setTitle("Phase");
        columnPhaseCode.setProperty("phaseCode");
        model.addColumn(columnPhaseCode);
        
        Column columnStatusCode = model.getColumnInstance();
        columnStatusCode.setProperty("status");
        model.addColumn(columnStatusCode);
                        
        return model.assemble();
    }
    
    
    public Object buildParticipant(TableModel model, Collection participants) throws Exception 
    {	
        Table table = model.getTableInstance();
        table.setTableId("assembler");
        table.setForm("assembler");
        table.setItems(participants);
        table.setAction(model.getContext().getContextPath() + "/pages/search/participant");
        table.setTitle("");
        table.setShowPagination(false);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(true);
        table.setSortable(true);
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
        columnPrimaryIdentifier.setProperty("test");
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
        columnStudyPrimaryIdentifier.setProperty("test");
        columnStudyPrimaryIdentifier.setSortable(false);
        columnStudyPrimaryIdentifier.setTitle("Associated Study ID(s)");
        columnStudyPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantStudyLinkDisplayCell");        
        model.addColumn(columnStudyPrimaryIdentifier);
                        
        return model.assemble();
    }
    
    public Object buildAdverseEvent(TableModel model, Collection adverseEvents) throws Exception 
    {
        Table table = model.getTableInstance();
        table.setTableId("assembler");
        table.setForm("assembler");
        table.setItems(adverseEvents);
        table.setAction(model.getContext().getContextPath() + "/pages/search/adverseEvent");
        table.setTitle("");
        table.setShowPagination(false);
        table.setShowExports(true);
        table.setOnInvokeAction("buildTable('searchForm')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(true);
        table.setSortable(true);
        model.addTable(table);
        
        Export export = model.getExportInstance();
        export.setView(TableConstants.VIEW_CSV);
        export.setViewResolver(TableConstants.VIEW_CSV);
        export.setImageName(TableConstants.VIEW_CSV);
        export.setText(TableConstants.VIEW_CSV);
        export.addAttribute(CsvView.DELIMITER, "|");
        export.setFileName("caaers_aes.txt");
        model.addExport(export);
        
        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);
        model.addRow(row);
        
       
        Column columnstudyIdentifier = model.getColumnInstance();        
        columnstudyIdentifier.setProperty("test");
        columnstudyIdentifier.setTitle("Study ID");
        columnstudyIdentifier.setSortable(false);
        columnstudyIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.StudyLinkDisplayCell");        
        model.addColumn(columnstudyIdentifier);
        
        Column columnSponsorId = model.getColumnInstance();        
        columnSponsorId.setProperty("test");
        columnSponsorId.setTitle("Sponsor");
        columnSponsorId.setSortable(false);
        columnSponsorId.setCell("gov.nih.nci.cabig.caaers.web.search.SponsorLinkDisplayCell");        
        model.addColumn(columnSponsorId);
        
        Column columnAeType = model.getColumnInstance();        
        columnAeType.setProperty("test");
        columnAeType.setTitle("AE Type");
        columnAeType.setStyle("width:8px");
        columnAeType.setSortable(false);
        columnAeType.setCell("gov.nih.nci.cabig.caaers.web.search.AeTypeDisplayCell");        
        model.addColumn(columnAeType);
        
        Column columnCtcCategory = model.getColumnInstance();
        columnCtcCategory.setTitle("CTC Category");
        columnCtcCategory.setAlias("category");
        columnCtcCategory.setProperty("ctcTerm.category.name");
        model.addColumn(columnCtcCategory);
        
        Column columnCtcTerm = model.getColumnInstance();
        columnCtcTerm.setTitle("CTC Term");
        columnCtcTerm.setAlias("ctcTerm");
        columnCtcTerm.setProperty("ctcTerm.term");
        model.addColumn(columnCtcTerm);
        
        Column columnGrade = model.getColumnInstance();
        columnGrade.setTitle("Grade");
        columnGrade.setStyle("width:6px");
        columnGrade.setAlias("grade");
        columnGrade.setProperty("grade.code");
        model.addColumn(columnGrade);
        
        Column medDRACode = model.getColumnInstance();        
        medDRACode.setProperty("ctcTerm.ctepCode");
        medDRACode.setAlias("ctepCode");
        medDRACode.setTitle("MedDRA Code");      
        model.addColumn(medDRACode);
        
        Column aeStartDate = model.getColumnInstance();        
        aeStartDate.setProperty("test");
        aeStartDate.setSortable(false);
        aeStartDate.setCell("gov.nih.nci.cabig.caaers.web.search.AeDetectionDateDisplayCell");
        aeStartDate.setTitle("Detection Date");      
        model.addColumn(aeStartDate);
        
        return model.assemble();
    }
    
   
    public Object buildExpeditedReport(TableModel model, Collection expeditedReports) throws Exception 
    {
        Table table = model.getTableInstance();
        table.setTableId("assembler");
        table.setForm("assembler");
        table.setItems(expeditedReports);
        table.setAction(model.getContext().getContextPath() + "/pages/search/report");
        table.setTitle("");
        table.setShowPagination(false);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(true);
        table.setSortable(true);
        model.addTable(table);
        
        Export export = model.getExportInstance();
        export.setView(TableConstants.VIEW_CSV);
        export.setViewResolver(TableConstants.VIEW_CSV);
        export.setImageName(TableConstants.VIEW_CSV);
        export.setText(TableConstants.VIEW_CSV);
        export.addAttribute(CsvView.DELIMITER, "|");
        export.setFileName("caaers_expedited_reports.txt");
        model.addExport(export);
        
        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);        
        model.addRow(row);
        
        Column columnPrimaryAeTerm = model.getColumnInstance();        
        columnPrimaryAeTerm.setProperty("adverseEvents[0].ctcTerm.term");
        columnPrimaryAeTerm.setTitle("Primary Ctc term");
        columnPrimaryAeTerm.setAlias("term");        
        model.addColumn(columnPrimaryAeTerm);
        
        Column ctcGrade = model.getColumnInstance();        
        ctcGrade.setProperty("adverseEvents[0].grade.code");
        ctcGrade.setAlias("grade");
        ctcGrade.setTitle("Grade");      
        model.addColumn(ctcGrade);
        
        Column attributionCode = model.getColumnInstance();        
        attributionCode.setProperty("adverseEvents[0].attributionSummary.displayName");
        attributionCode.setTitle("Attribution");
        attributionCode.setAlias("attribution");
        model.addColumn(attributionCode);
        
        Column aeDetectionDate = model.getColumnInstance();        
        aeDetectionDate.setProperty("detectionDate");
        aeDetectionDate.setTitle("Detection Date");
        aeDetectionDate.setCell(TableConstants.DATE);
        aeDetectionDate.setFormat("MM/dd/yyyy");
        model.addColumn(aeDetectionDate);
        
        
        Column columnstudyIdentifier = model.getColumnInstance();        
        columnstudyIdentifier.setProperty("test");
        columnstudyIdentifier.setSortable(false);
        columnstudyIdentifier.setTitle("Study ID");
        columnstudyIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.StudyLinkDisplayCellExpedited");        
        model.addColumn(columnstudyIdentifier);
        
        Column columnParticipantIdentifier = model.getColumnInstance();
        columnParticipantIdentifier.setSortable(false);
        columnParticipantIdentifier.setProperty("primaryIdentifier");
        columnParticipantIdentifier.setTitle("Participant ID");
        columnParticipantIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantLinkDisplayCellExpedited");        
        model.addColumn(columnParticipantIdentifier);
        
        
        
        
        
        /*
        Column columnPrimaryIdentifier = model.getColumnInstance();        
        columnPrimaryIdentifier.setProperty("status.displayName");
        columnPrimaryIdentifier.setTitle("Status");
        //columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantLinkDisplayCell");        
        model.addColumn(columnPrimaryIdentifier);
        */                
        return model.assemble();
    }
    
    public Object buildRoutineReport(TableModel model, Collection expeditedReports) throws Exception 
    {
        Table table = model.getTableInstance();
        table.setTableId("assembler");
        table.setForm("assembler");
        table.setItems(expeditedReports);
        table.setAction(model.getContext().getContextPath() + "/pages/search/report");
        table.setTitle("");
        table.setShowPagination(false);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(true);
        table.setSortable(true);
        model.addTable(table);
        
        Export export = model.getExportInstance();
        export.setView(TableConstants.VIEW_CSV);
        export.setViewResolver(TableConstants.VIEW_CSV);
        export.setImageName(TableConstants.VIEW_CSV);
        export.setText(TableConstants.VIEW_CSV);
        export.addAttribute(CsvView.DELIMITER, "|");
        export.setFileName("caaers_routine_reports.txt");
        model.addExport(export);
        
        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);        
        model.addRow(row);
        
        /*
        Column columnPrimaryIdentifier = model.getColumnInstance();        
        columnPrimaryIdentifier.setProperty("primaryIdentifier");
        columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.study.StudyLinkDisplayCell");        
        model.addColumn(columnPrimaryIdentifier);
        */
        
        
        Column columnPrimaryAeTerm = model.getColumnInstance();        
        columnPrimaryAeTerm.setProperty("adverseEvents[0].ctcTerm.term");
        columnPrimaryAeTerm.setTitle("Primary Ctc term");
        columnPrimaryAeTerm.setAlias("term");
        //columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantLinkDisplayCell");        
        model.addColumn(columnPrimaryAeTerm);
        /*
        Column medDRACode = model.getColumnInstance();        
        medDRACode.setProperty("ctcTerm.ctepCode");
        medDRACode.setTitle("MedDRA Code");      
        model.addColumn(medDRACode);
        */
        
        Column ctcGrade = model.getColumnInstance();        
        ctcGrade.setProperty("adverseEvents[0].grade.code");
        ctcGrade.setTitle("Grade");  
        ctcGrade.setAlias("grade");
        model.addColumn(ctcGrade);
        
        Column attributionCode = model.getColumnInstance();        
        attributionCode.setProperty("adverseEvents[0].attributionSummary.displayName");
        attributionCode.setTitle("Attribution"); 
        attributionCode.setAlias("attribution");
        model.addColumn(attributionCode);
        
        Column columnObservationDate = model.getColumnInstance();        
        columnObservationDate.setProperty("adverseEvents[0].ctcTerm.term");
        columnObservationDate.setSortable(false);
        columnObservationDate.setTitle("Observation Dates");
        columnObservationDate.setCell("gov.nih.nci.cabig.caaers.web.search.ObservationDateDisplayCell");        
        model.addColumn(columnObservationDate);
        
        Column columnstudyIdentifier = model.getColumnInstance();   
        columnstudyIdentifier.setSortable(false);
        columnstudyIdentifier.setProperty("test");
        columnstudyIdentifier.setTitle("Study ID");
        columnstudyIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.StudyLinkDisplayCellRoutine");        
        model.addColumn(columnstudyIdentifier);
        
        Column columnParticipantIdentifier = model.getColumnInstance();
        columnParticipantIdentifier.setSortable(false);
        columnParticipantIdentifier.setProperty("primaryIdentifier");
        columnParticipantIdentifier.setTitle("Participant ID");
        columnParticipantIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantLinkDisplayCellRoutine");        
        model.addColumn(columnParticipantIdentifier);
        
        /*
        Column aeDetectionDate = model.getColumnInstance();        
        aeDetectionDate.setProperty("detectionDate");
        aeDetectionDate.setTitle("Detection Date");
        aeDetectionDate.setCell(TableConstants.DATE);
        aeDetectionDate.setFormat("MM/dd/yyyy");
        model.addColumn(aeDetectionDate);
        
        Column columnPrimaryIdentifier = model.getColumnInstance();        
        columnPrimaryIdentifier.setProperty("status.displayName");
        columnPrimaryIdentifier.setTitle("Status");
        //columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantLinkDisplayCell");        
        model.addColumn(columnPrimaryIdentifier);
        */                
        return model.assemble();
    }
    
   
   @SuppressWarnings("finally")
   private List<Study> constructExecuteStudyQuery(String type, String text)
   {
	   StringTokenizer typeToken = new StringTokenizer(type, ",");
	   StringTokenizer textToken = new StringTokenizer(text, ",");
	   log.debug("type :: " + type);
	   log.debug("text :: " + text);
	   String sType, sText;
	   Map<String,String> propValue = new HashMap<String,String>();
	   List<Study> studies = new ArrayList<Study>(); 
	    
	   // map the html properties to the model properties
	   Map<String,String> m = new HashMap<String,String>();
	   m.put("prop0" ,"studyIdentifier");
	   m.put("prop1", "studyShortTitle");
	   m.put("prop2", "participantIdentifier");
	   m.put("prop3", "participantFirstName");
	   m.put("prop4", "participantLastName");
	   m.put("prop5", "participantEthnicity");
	   m.put("prop6", "participantGender");
	   m.put("prop7", "participantDateOfBirth");
	    
	   while(typeToken.hasMoreTokens() && textToken.hasMoreTokens())
	   {
   		sType = typeToken.nextToken();
   		sText = textToken.nextToken();
   		// Create a map  of property key ,and search criteria 
   		propValue.put(m.get(sType),sText);
	   }
	   
	   try {
		studies = studyDao.searchStudy(propValue);
	   } catch (Exception e) {
		   log.debug("EXCEPTION ::" + e.getMessage());
		   throw new RuntimeException("Formatting Error", e);
	   }
	   finally{
		   return studies;
	   }
   }
   
   @SuppressWarnings("finally")
   private List<Participant> constructExecuteParticipantQuery(String type, String text)
   {
	   
	   StringTokenizer typeToken = new StringTokenizer(type, ",");
	   StringTokenizer textToken = new StringTokenizer(text, ",");
	   log.debug("type :: " + type);
	   log.debug("text :: " + text);
	   String sType, sText;
	   Map<String,String> propValue = new HashMap<String,String>();
	   List<Participant> participants = new ArrayList<Participant>(); 
	   
	   // map the html properties to the model properties
	   Map<String,String> m = new HashMap<String,String>();
	   m.put("prop0" ,"studyIdentifier");
	   m.put("prop1", "studyShortTitle");
	   m.put("prop2", "participantIdentifier");
	   m.put("prop3", "participantFirstName");
	   m.put("prop4", "participantLastName");
	   m.put("prop5", "participantEthnicity");
	   m.put("prop6", "participantGender");
	   m.put("prop7", "participantDateOfBirth");
	   
	   
	   while(typeToken.hasMoreTokens() && textToken.hasMoreTokens())
	   {
   		sType = typeToken.nextToken();
   		sText = textToken.nextToken();
   		// Create a map  of property key ,and search criteria 
   		propValue.put(m.get(sType),sText);
	   }

	   try {
			participants = participantDao.searchParticipant(propValue);
		   } catch (Exception e) {
			   throw new RuntimeException("Formatting Error", e);
		   }
		   finally{
			   return participants;
		   }
   }
   
   
   @SuppressWarnings("finally")
   private List<AdverseEvent> constructExecuteAdverseEventQuery(String type, String text)
   {
	   
	   StringTokenizer typeToken = new StringTokenizer(type, ",");
	   StringTokenizer textToken = new StringTokenizer(text, ",");
	   log.debug("type :: " + type);
	   log.debug("text :: " + text);
	   String sType, sText;
	   Map<String,String> propValue = new HashMap<String,String>();
	   List<AdverseEvent> adverseEvents = new ArrayList<AdverseEvent>(); 
	   
	   
	   // map the html properties to the model properties
	   Map<String,String> m = new HashMap<String,String>();
	   m.put("prop0" ,"ctcCategory");
   	   m.put("prop1" ,"ctcTerm");
   	   m.put("prop2" ,"ctcMeddra");
   	   m.put("prop3" ,"grade");
   	   m.put("prop4" ,"studyIdentifier");
	   m.put("prop5", "studyShortTitle");
	   m.put("prop6", "participantIdentifier");
	   m.put("prop7", "participantFirstName");
	   m.put("prop8", "participantLastName");
	   m.put("prop9", "participantEthnicity");
	   m.put("prop10", "participantGender");
	   m.put("prop11", "participantDateOfBirth");
	   
	   
	   
   	 while(typeToken.hasMoreTokens() && textToken.hasMoreTokens())
	   {
 		sType = typeToken.nextToken();
 		sText = textToken.nextToken();
 		// Create a map  of property key ,and search criteria 
 		propValue.put(m.get(sType),sText);
	   }

	   try {
		   adverseEvents = adverseEventDao.searchAdverseEvents(propValue);
		   } catch (Exception e) {
			   throw new RuntimeException("Formatting Error", e);
		   }
		   finally{
			   return adverseEvents;
		   }
   }
   
   @SuppressWarnings("finally")
   private List<ExpeditedAdverseEventReport> constructExecuteExpeditedReportQuery(String type, String text)
   {
	   
	   StringTokenizer typeToken = new StringTokenizer(type, ",");
	   StringTokenizer textToken = new StringTokenizer(text, ",");
	   log.debug("type :: " + type);
	   log.debug("text :: " + text);
	   String sType, sText;
	   Map<String,String> propValue = new HashMap<String,String>();
	   List<ExpeditedAdverseEventReport> expeditedReports = new ArrayList<ExpeditedAdverseEventReport>(); 
	   
	   
	   // map the html properties to the model properties
	   Map<String,String> m = new HashMap<String,String>();
	   m.put("prop0" ,"expeditedDate");
	   m.put("prop1" ,"ctcTerm");
	   m.put("prop2" ,"ctcCategory");
	   m.put("prop3", "ctcCtepCode");
	   m.put("prop4" ,"studyIdentifier");
	   m.put("prop5", "studyShortTitle");
	   m.put("prop6", "participantIdentifier");
	   m.put("prop7", "participantFirstName");
	   m.put("prop8", "participantLastName");
	   m.put("prop9", "participantEthnicity");
	   m.put("prop10", "participantGender");
	   m.put("prop11", "participantDateOfBirth");
	   
	   
	   while(typeToken.hasMoreTokens() && textToken.hasMoreTokens())
	   {
   		sType = typeToken.nextToken();
   		sText = textToken.nextToken();
   		// Create a map  of property key ,and search criteria 
   		propValue.put(m.get(sType),sText);
	   }
	   
	   try {
		   expeditedReports = expeditedAdverseEventReportDao.searchExpeditedReports(propValue);
		   } catch (Exception e) {
			   throw new RuntimeException("Formatting Error", e);
		   }
		   finally{
			   return expeditedReports;
		   }

   }
   
   @SuppressWarnings("finally")
   private List<RoutineAdverseEventReport> constructExecuteRoutineReportQuery(String type, String text)
   {
	   
	   StringTokenizer typeToken = new StringTokenizer(type, ",");
	   StringTokenizer textToken = new StringTokenizer(text, ",");
	   log.debug("type :: " + type);
	   log.debug("text :: " + text);
	   String sType, sText;
	   Map<String,String> propValue = new HashMap<String,String>();
	   List<RoutineAdverseEventReport> routineReports = new ArrayList<RoutineAdverseEventReport>(); 
	   
	   
	   // map the html properties to the model properties
	   Map<String,String> m = new HashMap<String,String>();
	   m.put("prop0" ,"date");
	   m.put("prop1" ,"ctcTerm");
	   m.put("prop2" ,"ctcCategory");
	   m.put("prop3", "ctcCtepCode");
	   m.put("prop4" ,"studyIdentifier");
	   m.put("prop5", "studyShortTitle");
	   m.put("prop6", "participantIdentifier");
	   m.put("prop7", "participantFirstName");
	   m.put("prop8", "participantLastName");
	   m.put("prop9", "participantEthnicity");
	   m.put("prop10", "participantGender");
	   m.put("prop11", "participantDateOfBirth");
	   
	   
	   while(typeToken.hasMoreTokens() && textToken.hasMoreTokens())
	   {
   		sType = typeToken.nextToken();
   		sText = textToken.nextToken();
   		// Create a map  of property key ,and search criteria 
   		propValue.put(m.get(sType),sText);
	   }
	   
	   
	   try {
		   routineReports = routineAdverseEventReportDao.searchRoutineReports(propValue);
		   } catch (Exception e) {
			   throw new RuntimeException("Formatting Error", e);
		   }
		   finally{
			   return routineReports;
		   }
   }
    
   /*
    * 
    * Ajax Call hits this method to generate table
    */ 
   public String getTable(Map parameterMap, String type, String text, HttpServletRequest request) 
   {
       
	   // Use this code to view the contents of parameterMap
	   if (parameterMap != null) {

			for (Object key : parameterMap.keySet()) {
				log.debug(key.toString() + " -- " + parameterMap.get(key));
			}
		}
   		
		List<Study> studies = new ArrayList<Study>();
		if(type != null && text !=null){
			studies = constructExecuteStudyQuery(type,text);
		}
		log.debug("Studies:?: " + studies.size());
	
		
       Context context = null;
       if (parameterMap == null) {
         context = new HttpServletRequestContext(request);
       } else {
         context = new HttpServletRequestContext(request, parameterMap);
       }

       TableModel model = new TableModelImpl(context);
       try {
         return build(model, studies).toString();
       } catch (Exception e) {
         e.printStackTrace();
       }

       return "";
   }
   
   /*
    * 
    * Ajax Call hits this method to generate table
    */ 
   public String getParticipantTable(Map parameterMap, String type, String text, HttpServletRequest request) 
   {
       
		List<Participant> participants = new ArrayList<Participant>();
		if(type != null && text !=null){
			participants = constructExecuteParticipantQuery(type,text);
		}
		log.debug("Participants :: " + participants.size());
	
		
       Context context = null;
       if (parameterMap == null) {
         context = new HttpServletRequestContext(request);
       } else {
         context = new HttpServletRequestContext(request, parameterMap);
       }

       TableModel model = new TableModelImpl(context);
       try {
         return buildParticipant(model, participants).toString();
       } catch (Exception e) {
         e.printStackTrace();
       }

       return "";
   }
   
   /*
    * 
    * Ajax Call hits this method to generate table
    */ 
   public String getAdverseEventTable(Map parameterMap, String type, String text, HttpServletRequest request) 
   {
	   
	  
		List<AdverseEvent> adverseEvents = new ArrayList<AdverseEvent>();
		if(type != null && text !=null){
			adverseEvents = constructExecuteAdverseEventQuery(type,text);
		}
		log.debug("AdverseEvents :: " + adverseEvents.size());
	
		
       Context context = null;
       if (parameterMap == null) {
         context = new HttpServletRequestContext(request);
       } else {
         context = new HttpServletRequestContext(request, parameterMap);
       }

       TableModel model = new TableModelImpl(context);
       try {
         return buildAdverseEvent(model, adverseEvents).toString();
       } catch (Exception e) {
         e.printStackTrace();
       }

       return "";
   }
   
   /*
    * 
    * Ajax Call hits this method to generate table
    */ 
   public String getExpeditedReportTable(Map parameterMap, String type, String text, HttpServletRequest request) 
   {	
		List<ExpeditedAdverseEventReport> expeditedReports = new ArrayList<ExpeditedAdverseEventReport>();
		if(type != null && text !=null){
			expeditedReports = constructExecuteExpeditedReportQuery(type, text);
		}
		log.debug("AdverseEvents :: " + expeditedReports.size());
	
		
       Context context = null;
       if (parameterMap == null) {
         context = new HttpServletRequestContext(request);
       } else {
         context = new HttpServletRequestContext(request, parameterMap);
       }

       TableModel model = new TableModelImpl(context);
       try {
         return buildExpeditedReport(model, expeditedReports).toString();
       } catch (Exception e) {
         e.printStackTrace();
       }

       return "";
   }
   
   /*
    * 
    * Ajax Call hits this method to generate table
    */ 
   public String getRoutineReportTable(Map parameterMap, String type, String text, HttpServletRequest request) 
   {
	   
		List<RoutineAdverseEventReport> routineReports = new ArrayList<RoutineAdverseEventReport>();
		if(type != null && text !=null){
			routineReports = constructExecuteRoutineReportQuery(type, text);
		}
		log.debug("AdverseEvents :: " + routineReports.size());

		
       Context context = null;
       if (parameterMap == null) {
         context = new HttpServletRequestContext(request);
       } else {
         context = new HttpServletRequestContext(request, parameterMap);
       }

       TableModel model = new TableModelImpl(context);
       try {
         return buildRoutineReport(model, routineReports).toString();
       } catch (Exception e) {
         e.printStackTrace();
       }

       return "";
   }
   
   
   
	public StudyService getStudyService() {
		return studyService;
	}

	public void setStudyService(StudyService studyService) {
		this.studyService = studyService;
	}

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public ParticipantDao getParticipantDao() {
		return participantDao;
	}

	public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}

	public AdverseEventDao getAdverseEventDao() {
		return adverseEventDao;
	}


	public void setAdverseEventDao(AdverseEventDao adverseEventDao) {
		this.adverseEventDao = adverseEventDao;
	}


	public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao() {
		return expeditedAdverseEventReportDao;
	}


	public void setExpeditedAdverseEventReportDao(
			ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
		this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
	}


	public RoutineAdverseEventReportDao getRoutineAdverseEventReportDao() {
		return routineAdverseEventReportDao;
	}


	public void setRoutineAdverseEventReportDao(
			RoutineAdverseEventReportDao routineAdverseEventReportDao) {
		this.routineAdverseEventReportDao = routineAdverseEventReportDao;
	}
	
	
	
	
	
	
	
}
