package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.StudyService;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

public class SearchStudyAjaxFacade {
	private static final Log log = LogFactory.getLog(SearchStudyAjaxFacade.class);

	private StudyService studyService;    
	private StudyDao studyDao;
	private ParticipantDao participantDao;
	private AdverseEventDao adverseEventDao;
	private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;

    public Object build(TableModel model, Collection studies) throws Exception 
    {
        Table table = model.getTableInstance();
        table.setTableId("assembler");
        table.setItems(studies);
        table.setAction(model.getContext().getContextPath() + "/pages/search/study");
        table.setTitle("");
        table.setShowPagination(false);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(false);
        table.setSortable(true);
        model.addTable(table);
        
        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);        
        model.addRow(row);
          
        Column columnPrimaryIdentifier = model.getColumnInstance();        
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
        table.setItems(participants);
        table.setAction(model.getContext().getContextPath() + "/pages/search/participant");
        table.setTitle("");
        table.setShowPagination(false);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(false);
        table.setSortable(true);
        model.addTable(table);
        
        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);        
        model.addRow(row);
        
        /*
        Column columnPrimaryIdentifier = model.getColumnInstance();        
        columnPrimaryIdentifier.setProperty("primaryIdentifier");
        columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.study.StudyLinkDisplayCell");        
        model.addColumn(columnPrimaryIdentifier);
        */
        
        Column columnPrimaryIdentifier = model.getColumnInstance();        
        columnPrimaryIdentifier.setProperty("primaryIdentifier");
        columnPrimaryIdentifier.setTitle("Participant ID");
        columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantLinkDisplayCell");        
        model.addColumn(columnPrimaryIdentifier);
        
        Column columnFirstName = model.getColumnInstance();
        columnFirstName.setProperty("firstName");
        model.addColumn(columnFirstName);
        
        Column columnLastName = model.getColumnInstance();
        columnLastName.setProperty("lastName");
        model.addColumn(columnLastName);
        /*
        Column colummDateOfBirth = model.getColumnInstance();
        colummDateOfBirth.setProperty("dateOfBirth");
        model.addColumn(colummDateOfBirth);
        */
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
        columnStudyPrimaryIdentifier.setTitle("Associated Study ID(s)");
        columnStudyPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantStudyLinkDisplayCell");        
        model.addColumn(columnStudyPrimaryIdentifier);
                        
        return model.assemble();
    }
    
    public Object buildAdverseEvent(TableModel model, Collection adverseEvents) throws Exception 
    {
        Table table = model.getTableInstance();
        table.setTableId("assembler");
        table.setItems(adverseEvents);
        table.setAction(model.getContext().getContextPath() + "/pages/search/adverseEvent");
        table.setTitle("");
        table.setShowPagination(false);
        table.setShowExports(true);
        table.setOnInvokeAction("buildTable('searchForm')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(false);
        table.setSortable(true);
        model.addTable(table);
        
        /*
        Export export = model.getExportInstance();
        export.setView(TableConstants.VIEW_CSV);
        export.setViewResolver(TableConstants.VIEW_CSV);
        export.setImageName(TableConstants.VIEW_CSV);
        export.setText(TableConstants.VIEW_CSV);
        export.setFileName("caaers.txt");
        model.addExport(export);
        */
        
        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);
        model.addRow(row);
        
       
        Column columnstudyIdentifier = model.getColumnInstance();        
        columnstudyIdentifier.setProperty("test");
        columnstudyIdentifier.setTitle("Study ID");
        columnstudyIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.StudyLinkDisplayCell");        
        model.addColumn(columnstudyIdentifier);
        
        Column columnSponsorId = model.getColumnInstance();        
        columnSponsorId.setProperty("test");
        columnSponsorId.setTitle("Sponsor ID");
        columnSponsorId.setCell("gov.nih.nci.cabig.caaers.web.search.SponsorLinkDisplayCell");        
        model.addColumn(columnSponsorId);
        
        Column columnAeType = model.getColumnInstance();        
        columnAeType.setProperty("test");
        columnAeType.setTitle("AE Type");
        columnAeType.setStyle("width:8px");
        columnAeType.setCell("gov.nih.nci.cabig.caaers.web.search.AeTypeDisplayCell");        
        model.addColumn(columnAeType);
        
        Column columnCtcCategory = model.getColumnInstance();
        columnCtcCategory.setTitle("CTC Category");
        columnCtcCategory.setProperty("ctcTerm.category.name");
        model.addColumn(columnCtcCategory);
        
        Column columnCtcTerm = model.getColumnInstance();
        columnCtcTerm.setTitle("CTC Term");
        columnCtcTerm.setProperty("ctcTerm.term");
        model.addColumn(columnCtcTerm);
        
        Column columnGrade = model.getColumnInstance();
        columnGrade.setTitle("Grade");
        columnGrade.setStyle("width:6px");
        columnGrade.setProperty("grade.code");
        model.addColumn(columnGrade);
        
        Column medDRACode = model.getColumnInstance();        
        medDRACode.setProperty("ctcTerm.ctepCode");
        medDRACode.setTitle("MedDRA Code");      
        model.addColumn(medDRACode);
        
        Column aeStartDate = model.getColumnInstance();        
        aeStartDate.setProperty("test");
        aeStartDate.setCell("gov.nih.nci.cabig.caaers.web.search.AeDetectionDateDisplayCell");
        aeStartDate.setTitle("Detection Date");      
        model.addColumn(aeStartDate);
        
        Column primary = model.getColumnInstance();        
        primary.setProperty("test");
        primary.setTitle("Primary");      
        model.addColumn(primary);
        /*
        Column columnAssociatedReportTicketNum = model.getColumnInstance();        
        columnAssociatedReportTicketNum.setProperty("test");
        columnAssociatedReportTicketNum.setTitle("Associated Report Ticket #");      
        model.addColumn(columnAssociatedReportTicketNum);
        
        Column columnAssociatedReportType = model.getColumnInstance();        
        columnAssociatedReportType.setProperty("test");
        columnAssociatedReportType.setTitle("Associated Report Type");      
        model.addColumn(columnAssociatedReportType);
        */                
        return model.assemble();
    }
    
   
    public Object buildExpeditedReport(TableModel model, Collection expeditedReports) throws Exception 
    {
        Table table = model.getTableInstance();
        table.setTableId("assembler");
        table.setItems(expeditedReports);
        table.setAction(model.getContext().getContextPath() + "/pages/search/report");
        table.setTitle("");
        table.setShowPagination(false);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(false);
        table.setSortable(true);
        model.addTable(table);
        
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
        model.addColumn(ctcGrade);
        
        Column attributionCode = model.getColumnInstance();        
        attributionCode.setProperty("adverseEvents[0].attributionSummary.displayName");
        attributionCode.setTitle("Attribution");      
        model.addColumn(attributionCode);
        
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
                        
        return model.assemble();
    }
    
   
   private List<Study> constructExecuteStudyQuery(String type, String text)
   {
	   
	   
	   StringTokenizer typeToken = new StringTokenizer(type, ",");
	   StringTokenizer textToken = new StringTokenizer(text, ",");
	   log.debug("type :: " + type);
	   log.debug("text :: " + text);
	   String sType, sText;
	   List<String> props = new ArrayList<String>();
	   List<String> values = new ArrayList<String>();
	   String[] theValues = new String[0];
	   
	   
	   // map the html properties to the model properties
	   Map<String,String> m = new HashMap<String,String>();
	   m.put("prop0" ,"identifier.value");
	   m.put("prop1", "o.shortTitle");
	   m.put("prop2", "pIdentifier.value");
	   m.put("prop3", "p.firstName");
	   m.put("prop4", "p.lastName");
	   m.put("prop5", "p.ethnicity");
	   m.put("prop6", "p.gender");
	   
	   
	   
	   while(typeToken.hasMoreTokens() && textToken.hasMoreTokens())
	   {
   		sType = typeToken.nextToken();
   		sText = textToken.nextToken();
   		
   		props.add(m.get(sType));
   		values.add(sText);
	   
	   }
	   
	   return studyDao.getByCriteria(values.toArray(theValues), props);
   }
   
   private List<Participant> constructExecuteParticipantQuery(String type, String text)
   {
	   
	   StringTokenizer typeToken = new StringTokenizer(type, ",");
	   StringTokenizer textToken = new StringTokenizer(text, ",");
	   log.debug("type :: " + type);
	   log.debug("text :: " + text);
	   String sType, sText;
	   List<String> props = new ArrayList<String>();
	   List<String> values = new ArrayList<String>();
	   String[] theValues = new String[0];
	   
	   
	   // map the html properties to the model properties
	   Map<String,String> m = new HashMap<String,String>();
	   m.put("prop0" ,"sIdentifier.value");
	   m.put("prop1", "s.shortTitle");
	   m.put("prop2" ,"identifier.value");
	   m.put("prop3", "o.firstName");
	   m.put("prop4", "o.lastName");
	   m.put("prop5", "o.ethnicity");
	   m.put("prop6", "o.gender");
	   
	   
	   
	   while(typeToken.hasMoreTokens() && textToken.hasMoreTokens())
	   {
   		sType = typeToken.nextToken();
   		sText = textToken.nextToken();
   		
   		props.add(m.get(sType));
   		values.add(sText);
	   
	   }
	   
	   return participantDao.getByCriteria(values.toArray(theValues), props);
   }
   
   
   private List<AdverseEvent> constructExecuteAdverseEventQuery(String type, String text)
   {
	   
	   StringTokenizer typeToken = new StringTokenizer(type, ",");
	   StringTokenizer textToken = new StringTokenizer(text, ",");
	   log.debug("type :: " + type);
	   log.debug("text :: " + text);
	   String sType, sText;
	   List<String> props = new ArrayList<String>();
	   List<String> values = new ArrayList<String>();
	   String[] theValues = new String[0];
	   
	   
	   // map the html properties to the model properties
	   Map<String,String> m = new HashMap<String,String>();
	   m.put("prop0" ,"notimplemented");
	   m.put("prop1" ,"notimplemented");
	   m.put("prop2" ,"identifier.value");
	   m.put("prop3" ,"notimplemented");
	   m.put("prop4" ,"study.shortTitle");
	   m.put("prop5" ,"ctcCategory.name");
	   m.put("prop6" ,"ctcTerm.term");
	   m.put("prop7" ,"ctcTerm.ctepCode");
	   m.put("prop8" ,"grade_code");
	   m.put("prop9" ,"notimplemented");
	   m.put("prop10" ,"notimplemented");
	   
	   
	   while(typeToken.hasMoreTokens() && textToken.hasMoreTokens())
	   {
   		sType = typeToken.nextToken();
   		sText = textToken.nextToken();
   		
   		props.add(m.get(sType));
   		values.add(sText);
	   
	   }
	   
	   return adverseEventDao.getByCriteria(values.toArray(theValues), props);
   }
   
   private List<ExpeditedAdverseEventReport> constructExecuteExpeditedReportQuery(String type, String text)
   {
	   
	   StringTokenizer typeToken = new StringTokenizer(type, ",");
	   StringTokenizer textToken = new StringTokenizer(text, ",");
	   log.debug("type :: " + type);
	   log.debug("text :: " + text);
	   String sType, sText;
	   List<String> props = new ArrayList<String>();
	   List<String> values = new ArrayList<String>();
	   String[] theValues = new String[0];
	   
	   
	   // map the html properties to the model properties
	   Map<String,String> m = new HashMap<String,String>();
	   m.put("prop0" ,"ctcTerm.term");
	   m.put("prop1", "status_code");
	   m.put("prop2", "ctcTerm.ctepCode");
	   
	   
	   while(typeToken.hasMoreTokens() && textToken.hasMoreTokens())
	   {
   		sType = typeToken.nextToken();
   		sText = textToken.nextToken();
   		
   		props.add(m.get(sType));
   		values.add(sText);
	   
	   }
	   
	   return expeditedAdverseEventReportDao.getByCriteria(values.toArray(theValues), props);
   }
    
   /*
    * 
    * Ajax Call hits this method to generate table
    */ 
   public String getTable(Map parameterMap, String type, String text, HttpServletRequest request) 
   {
	   
	   System.out.println("HUGO");
       if(parameterMap != null)
       {
       	//String a = parameterMap.get("assembler_p").toString();
       }
       
   		
		List<Study> studies = null;
		studies = constructExecuteStudyQuery(type,text);
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
       if(parameterMap != null)
       {
       	//String a = parameterMap.get("assembler_p").toString();
       }
       
   		
		List<Participant> participants = null;
		participants = constructExecuteParticipantQuery(type,text);
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
	   
	   //System.out.println("AdverseEventTable");
       if(parameterMap != null)
       {
       	//String a = parameterMap.get("assembler_p").toString();
       }
       
   		
		List<AdverseEvent> adverseEvents = null;
		adverseEvents = constructExecuteAdverseEventQuery(type,text);
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
	   
	   log.debug("Ajax Call to construct expedited report table");
       if(parameterMap != null)
       {
    	  for (String pk: (Set<String>)parameterMap.keySet()){
    		  log.debug(pk);
    	  }
       	//String a = parameterMap.get("assembler_p").toString();
       }
       
   		
		List<ExpeditedAdverseEventReport> expeditedReports = null;
		expeditedReports = constructExecuteExpeditedReportQuery(type, text);
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
	
	
	
	
	
}
