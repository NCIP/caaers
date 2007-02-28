package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.StudyService;

import java.util.ArrayList;
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

public class SearchStudyAjaxFacade {

	private StudyService studyService;    

    public Object build(TableModel model, Collection studies) throws Exception 
    {
        Table table = model.getTableInstance();
        table.setTableId("assembler");
        table.setItems(studies);
        table.setAction(model.getContext().getContextPath() + "/assembler.run");
        table.setTitle("");
        table.setShowPagination(false);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(true);
        table.setSortable(false);
        model.addTable(table);
        
        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);       
        model.addRow(row);
         
        Column columnShortTitle = model.getColumnInstance();
        columnShortTitle.setProperty("shortTitle");
        model.addColumn(columnShortTitle);

        
        Column columnLongTitle = model.getColumnInstance();
        columnLongTitle.setProperty("longTitle");
        model.addColumn(columnLongTitle);

        Column columnDesc = model.getColumnInstance();
        columnDesc.setProperty("description");
        model.addColumn(columnDesc);
        
        Column columnPhaseCode = model.getColumnInstance();
        columnPhaseCode.setProperty("phaseCode");
        model.addColumn(columnPhaseCode);
        
        Column columnMonitorCode = model.getColumnInstance();
        columnMonitorCode.setProperty("monitorCode");
        model.addColumn(columnMonitorCode);
                
        /* Column columnTerm = model.getColumnInstance();
        columnTerm.setProperty("term");
        model.addColumn(columnTerm);

        Column columnBorn = model.getColumnInstance();
        columnBorn.setProperty("born");
        columnBorn.setCell(TableConstants.DATE);
        model.addColumn(columnBorn);

        Column columnDied = model.getColumnInstance();
        columnDied.setProperty("died");
        columnDied.setCell(TableConstants.DATE);
        model.addColumn(columnDied);

        Column columnCareer = model.getColumnInstance();
        columnCareer.setProperty("career");
        model.addColumn(columnCareer); */

        return model.assemble();
    }
    
    public String getTable(Map parameterMap, String type, String text, HttpServletRequest request) 
    {
        //WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        // PresidentsDao presidentsDao = (PresidentsDao) webApplicationContext.getBean("presidentsDao");
    	
    	Study study = new Study();
    	StringTokenizer typeToken = new StringTokenizer(type, ",");
    	StringTokenizer textToken = new StringTokenizer(text, ",");
    	String sType = null;
    	String sText = null;
    	while(typeToken.hasMoreTokens() && textToken.hasMoreTokens())
    	{
    		sType = typeToken.nextToken();
    		sText = textToken.nextToken();
    		
    		if ("st".equals(sType))
    			study.setShortTitle(sText);
    		else if ("lt".equals(sType))
    			study.setLongTitle(sText);
    		else if ("d".equals(sType))
    			study.setDescription(sText);
    		else if ("psc".equals(sType))
    			study.setPrimarySponsorCode(sText);
    		else if ("pc".equals(sType))
    			study.setPhaseCode(sText);
        	
    	}
    	
        if(parameterMap != null)
        {
        	String a = parameterMap.get("assembler_p").toString();
        	System.out.println(a);
        }
    	
		List<Study> studies = null;
		try {
			studies = studyService.search(study);
		} catch (Exception e1) {	
			e1.printStackTrace();
		}
		
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
     
     public String a(HttpServletRequest request)
     {
    	 Context context = null;
		 context = new HttpServletRequestContext(request);
     
		 TableModel model = new TableModelImpl(context);
		 Object viewData = null;
		 try {
			viewData = build(model, new ArrayList());	          
		 } catch (Exception e) {
			e.printStackTrace();
		 }       
		 return viewData.toString(); 
    	 
    	// Study study = new Study();
     	// Collection studies = null;
 		/*try {
 			// studies = studyService.search(study);
 			studies = new ArrayList<Study>();
 			Study st = new Study();
 			st.setShortTitle("shortTitleText");
 			st.setLongTitle("longTitleText");
 			studies.add(st);
 		} catch (Exception e1) {			
 			e1.printStackTrace();
 		} */

         /*Context context = null;
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

         return ""; */
    	 
    	//return "help me123";
     }
    
	public StudyService getStudyService() {
		return studyService;
	}

	public void setStudyService(StudyService studyService) {
		this.studyService = studyService;
	} 
	
	
}
