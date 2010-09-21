package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.UiAttribute;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;

import java.util.List;

import junit.framework.TestCase;

public class AdvancedSearchTest extends TestCase{
	/**
	 * validates advancedSearch-ui.xml
	 * @throws Exception
	 */
	public void testAdvancedSearchUiXML() throws Exception {
		AdvancedSearchUi advancedSearchUi = AdvancedSearchUiUtil.loadAdvancedSearchUi();
		CommandToSQL commandToSQL = new CommandToSQL();
		
		List<SearchTargetObject> searchTargetObjects = advancedSearchUi.getSearchTargetObject();
		
		for (SearchTargetObject searchTargetObject:searchTargetObjects) {
			String queryClassName = searchTargetObject.getQueryClassName();
			AbstractQuery query = (AbstractQuery)Class.forName(queryClassName).newInstance();
			assertEquals(query.getClass().getName(),queryClassName);
			// invoke fields
			String tableAlias = searchTargetObject.getTableAlias();
			commandToSQL.invokeField(query, tableAlias);
			
			List<DependentObject> dependentObjects = searchTargetObject.getDependentObject();
			for (DependentObject dependentObject:dependentObjects) {
				//invoke join methods 
				String joinMethodName = dependentObject.getJoinByMethod();
				boolean isOuterJoinRequired = dependentObject.isOuterJoinRequired();
				if (joinMethodName != null) {
					commandToSQL.invokeMethod(query, joinMethodName, new Class[0], new Object[0]);
					if (isOuterJoinRequired) {
						commandToSQL.invokeMethod(query, CommandToSQL.OUTER_JOIN_PREFIX+joinMethodName, new Class[0], new Object[0]);
					}
				}
				
				List<UiAttribute> uiAttributes = dependentObject.getUiAttribute();
				for (UiAttribute uiAttribute:uiAttributes) {
					//invoke filter methods 
					String filterMethodName = uiAttribute.getFilterMethod();
					String dataType = uiAttribute.getDataType();
					Class[] par=new Class[2];
					Object[] obj = new Object[2];
					if (dataType.equals("String") && !uiAttribute.getFieldType().equals("date-field")) {
						par[0]=String.class;
						obj[0] = "string";
					} else if (dataType.equals("String") && uiAttribute.getFieldType().equals("date-field")) {
						par[0]=String.class;
						obj[0] = "10/10/2010";
					} else if (dataType.equals("Integer")) {
						par[0]=Integer.class;
						obj[0] = Integer.parseInt("1");					
					} else if (dataType.equals("boolean")) {
						par[0]=Boolean.TYPE;
						obj[0] = Boolean.parseBoolean("true");					
					} else {
						throw new Exception ("unknown data type in uiAttribute " + dataType);
					}
					par[1] = String.class;
					obj[1] = "=";

					commandToSQL.invokeMethod(query,filterMethodName,par,obj);
				}
				
				List<ViewColumn> vcs = dependentObject.getViewColumn();
				for (ViewColumn vc:vcs) {
					if (vc.getFilterMethod() != null) {
						commandToSQL.invokeMethod(query, vc.getFilterMethod(), new Class[0], new Object[0]);
					}
				}
			}
			
		}
	}
}
