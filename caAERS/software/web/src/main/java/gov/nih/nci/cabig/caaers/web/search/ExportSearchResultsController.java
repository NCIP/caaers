package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.SearchDao;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.Search;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.CriteriaParameter;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SaveSearch;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SelectedColumn;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;
import gov.nih.nci.cagrid.data.style.CreationHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;


public class ExportSearchResultsController extends AbstractCommandController {
	
	private SearchDao searchDao;
	private AdvancedSearchUi advancedSearchUi;
	private ParticipantDao participantDao;
	private static final String XLS_SEARCH_RESULTS_FILENAME = "xlsSearchResults.xls";
	public ExportSearchResultsController(){
		setCommandClass(AdvancedSearchCommand.class);
		
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("advancedSearch-ui.xml");
        Unmarshaller unmarshaller;
		try {
			unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.web.search.ui").createUnmarshaller();
			advancedSearchUi = (AdvancedSearchUi) unmarshaller.unmarshal(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void generateOutput(HttpServletResponse response)throws Exception{
		
		String tempDir = System.getProperty("java.io.tmpdir");
		File file = new File(tempDir+File.separator+XLS_SEARCH_RESULTS_FILENAME);
		FileInputStream fileIn = new FileInputStream(file);
		
		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", "attachment; filename=" + XLS_SEARCH_RESULTS_FILENAME);
		response.setHeader("Content-length", String.valueOf(file.length()));
		response.setHeader("Pragma", "private");
		response.setHeader("Cache-control","private, must-revalidate");
		
		OutputStream out = response.getOutputStream();
		
		byte[] buffer = new byte[2048];
		int bytesRead = fileIn.read(buffer);
		while (bytesRead >= 0) {
		  if (bytesRead > 0)
		    out.write(buffer, 0, bytesRead);
		    bytesRead = fileIn.read(buffer);
		}
		out.flush();
		out.close();
		fileIn.close();
		
	}
	
	private void initializeCommandObject(AdvancedSearchCommand command) throws Exception{
		String loginId = SecurityUtils.getUserLoginName();
		List<Search> searchList = searchDao.getByLoginAndName(loginId, "exportSearch");
		
		Unmarshaller unmarshaller;
		SaveSearch savedSearch = null;
		try {
			unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.web.search.ui").createUnmarshaller();
			//advancedSearchUi = (AdvancedSearchUi) unmarshaller.unmarshal(inputStream);
			StringReader reader = new StringReader(searchList.get(0).getCriteriaXml()); 
			savedSearch = (SaveSearch) unmarshaller.unmarshal(reader);
			for(SearchTargetObject targetObject: advancedSearchUi.getSearchTargetObject()){
				if(targetObject.getClassName().equals(savedSearch.getTargetClassName()))
					command.setSearchTargetObject(targetObject);
			}
			for(CriteriaParameter criteriaParameter: savedSearch.getCriteriaParameter()){
				AdvancedSearchCriteriaParameter parameter = new AdvancedSearchCriteriaParameter();
				parameter.setObjectName(criteriaParameter.getObjectName());
				parameter.setDependentObjectName(criteriaParameter.getDependentObjectName());
				parameter.setAttributeName(criteriaParameter.getAttributeName());
				parameter.setPredicate(criteriaParameter.getPredicate());
				parameter.setValue(criteriaParameter.getValue());
				parameter.setDisplayValue(criteriaParameter.getDisplayValue());
				command.getCriteriaParameters().add(parameter);
			}
			// Setup the view selected.
			for(SelectedColumn selectedColumn: savedSearch.getSelectedColumn()){
				DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByName(command.getSearchTargetObject(), 
						selectedColumn.getDependentObjectClassName());
				dObject.setInView(true);
				for(ViewColumn vCol: dObject.getViewColumn()){
					if(selectedColumn.getColumnAttribute().equals(vCol.getColumnAttribute()))
						vCol.setSelected(true);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Delete the "exportSearch" saved in the database
		searchDao.deleteByLoginIdAndName(loginId, "exportSearch");
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object arg2, BindException arg3) throws Exception {
		AdvancedSearchCommand command = (AdvancedSearchCommand) arg2;
		//initializeCommandObject(command);
		//createQueryAndFetchData(command);
		List<AdvancedSearchRow> searchResultsRowList = (List<AdvancedSearchRow>) request.getSession().getAttribute("searchResultsRowList");
		List<ViewColumn> resultsViewColumnList = (List<ViewColumn>) request.getSession().getAttribute("resultsViewColumnList");
		createExcelFile(searchResultsRowList, resultsViewColumnList);
		generateOutput(response);
		return null;
	}
	
	private void createExcelFile(List<AdvancedSearchRow> searchResultsRowList, List<ViewColumn> resultsViewColumnList) throws Exception{
		String tempDir = System.getProperty("java.io.tmpdir");
		HSSFWorkbook wb = new HSSFWorkbook();
	    //Workbook wb = new XSSFWorkbook();
	    //CreationHelper createHelper = wb.getCreationHelper();
	    HSSFSheet sheet = wb.createSheet("new sheet");

	    // Create a row and put some cells in it. Rows are 0 based.
	    HSSFRow row = sheet.createRow((short)0);

	    int viewColumnCount = 0;
	    for(ViewColumn viewColumn: resultsViewColumnList){
	    	row.createCell((short) viewColumnCount).setCellValue(viewColumn.getColumnTitle());
	    	viewColumnCount++;
	    }
	    
	    int rowCount = 1;
	    for(AdvancedSearchRow searchRow: searchResultsRowList){
	    	int columnCount = 0;
	    	row = sheet.createRow((short) rowCount++);
	    	for(AdvancedSearchColumn searchColumn: searchRow.getColumnList()){
	    		if(searchColumn.getLengthyValue() != null)
	    			row.createCell((short) columnCount++).setCellValue(searchColumn.getLengthyValue().toString());
	    		else if(searchColumn.getValue() != null)
	    			row.createCell((short) columnCount++).setCellValue(searchColumn.getValue().toString());
	    		else
	    			row.createCell((short) columnCount++).setCellValue("");
	    	}
	    }

	    // Write the output to a file
	    FileOutputStream fileOut = new FileOutputStream(tempDir + File.separator + XLS_SEARCH_RESULTS_FILENAME);
	    wb.write(fileOut);
	    fileOut.close();
	}
	
	private void createQueryAndFetchData(AdvancedSearchCommand command) throws Exception{
		List<ViewColumn> resultsViewColumnList = new ArrayList<ViewColumn>();
		
		// Setup up the isInView attribute of the dependentObject if any of the attribute is selected.
		for(DependentObject dObject: command.getSearchTargetObject().getDependentObject()){
			dObject.setInView(false);
			for(ViewColumn viewColumn: dObject.getViewColumn()){
				if(viewColumn.isSelected())
					dObject.setInView(true);
			}
		}
		List<AdvancedSearchCriteriaParameter> parameters = new ArrayList<AdvancedSearchCriteriaParameter>();
		for(AdvancedSearchCriteriaParameter p: command.getCriteriaParameters()){
			if(p.getAttributeName()!= null && !p.getAttributeName().equals("") && !p.getAttributeName().equals("none") && !p.isDeleted())
					parameters.add(p);
		}
		String query = "";
		query = CommandToSQL.transform(command.getSearchTargetObject(), parameters, true);
		command.setHql(query);
		
		List<Object> singleObjectList = new ArrayList<Object>();
		List<Object[]> multipleObjectList = new ArrayList<Object[]>();
		if(CommandToSQL.isMultipleViewQuery(command.getSearchTargetObject())){
			multipleObjectList = (List<Object[]>) participantDao.search(new HQLQuery(query));
			processMultipleObjectsList(multipleObjectList, command);
			//command.setNumberOfResults(singleObjectList.size());
			for(DependentObject dObject: command.getSearchTargetObject().getDependentObject())
				if(dObject.isInView()){
					for(ViewColumn vColumn: dObject.getViewColumn())
						if(vColumn.isSelected())
							resultsViewColumnList.add(vColumn);
				}
		}
		else{
			singleObjectList = (List<Object>) participantDao.search(new HQLQuery(query));
			//command.setNumberOfResults(singleObjectList.size());
			command.setAdvancedSearchRowList(processSingleObjectList(singleObjectList, command.getSearchTargetObject().getDependentObject().get(0)));
			for(DependentObject dObject: command.getSearchTargetObject().getDependentObject())
				if(dObject.isInView()){
					for(ViewColumn vColumn: dObject.getViewColumn())
						if(vColumn.isSelected())
							resultsViewColumnList.add(vColumn);
				}
		}
		command.setResultsViewColumnList(resultsViewColumnList);
		command.setNumberOfResults(command.getAdvancedSearchRowList().size());
	}
	
	public List<AdvancedSearchRow> processSingleObjectList(List<Object> objectList, DependentObject dObject){
		List<AdvancedSearchRow> rowList = new ArrayList<AdvancedSearchRow>();
		for(Object object: objectList){
			AdvancedSearchRow row = new AdvancedSearchRow();
			AdvancedSearchColumn column = null;
			BeanWrapper wrapper = new BeanWrapperImpl(object);
			
			for(ViewColumn viewColumn: dObject.getViewColumn()){
				if(viewColumn.isSelected()){
					column = new AdvancedSearchColumn();
					column.setColumnHeader(viewColumn.getColumnTitle());
					if(wrapper.getPropertyValue(viewColumn.getColumnAttribute()) != null)
						column.setValue(wrapper.getPropertyValue(viewColumn.getColumnAttribute()).toString());
					else
						column.setValue(wrapper.getPropertyValue(viewColumn.getColumnAttribute()));
					row.getColumnList().add(column);
				}
			}
			rowList.add(row);
		}
		return rowList;
	}
	
	/**
	 * This method processes the result object list when there are multiple objects in the view
	 */
	public void processMultipleObjectsList(List<Object[]> objectList, AdvancedSearchCommand command){
		command.setNumberOfResults(objectList.size());
		List<AdvancedSearchRow> rowList = new ArrayList<AdvancedSearchRow>();
		for(Object[] objectArr: objectList){
			AdvancedSearchRow row = new AdvancedSearchRow();
			AdvancedSearchColumn column = null;
			int i = 0;
			for(DependentObject dObject: command.getSearchTargetObject().getDependentObject()){
				if(dObject.isInView()){
					Object obj = objectArr[i++];
					BeanWrapper wrapper = new BeanWrapperImpl(obj);
					for(ViewColumn viewColumn: dObject.getViewColumn()){
						if(viewColumn.isSelected()){
							column = new AdvancedSearchColumn();
							column.setColumnHeader(viewColumn.getColumnTitle());
							if(wrapper.getPropertyValue(viewColumn.getColumnAttribute()) != null)
								column.setValue(wrapper.getPropertyValue(viewColumn.getColumnAttribute()).toString());
							else
								column.setValue(wrapper.getPropertyValue(viewColumn.getColumnAttribute()));
							row.getColumnList().add(column);
						}
					}
				}
			}
			rowList.add(row);
		}
		command.setAdvancedSearchRowList(rowList);
	}
	
	public void setSearchDao(SearchDao searchDao){
    	this.searchDao = searchDao;
    }
	
	/**
	 * @param participantDao the participantDao to set
	 */
	public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}
}