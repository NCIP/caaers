package gov.nih.nci.cabig.caaers.tools.hibernate;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author Rhett Sutphin
 */
/*
 * TODO: this class is shared with PSC (there it is called StudyCalendarNamingStrategy). Refactor
 * into a shared library.
 */
public class WonderfulNamingStrategy extends ImprovedNamingStrategy {
	
	private String[] uppercaseColumns;
	private String uppercaseColumnNames;
	
    @Override
    public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName) {
        return columnName(propertyName) + "_id";
    }

    @Override
    public String classToTableName(String className) {
        return pluralize(super.classToTableName(className));
    }
    
   
    
    @Override
    public String logicalColumnName(String columnName, String propertyName) {
    	
    	if(ArrayUtils.contains(uppercaseColumns, columnName)) 
        	return super.logicalColumnName(StringUtils.upperCase(columnName), propertyName);

    	return super.logicalColumnName(columnName, propertyName);
    }
    
    private String pluralize(String name) {
        StringBuilder p = new StringBuilder(name);
        if (name.endsWith("y")) {
            p.deleteCharAt(p.length() - 1);
            p.append("ies");
        } else {
            p.append('s');
        }
        return p.toString();
    }
    
    @Required
    public String getUppercaseColumnNames() {
		return uppercaseColumnNames;
	}
    
    @Required
    public void setUppercaseColumnNames(String uppercaseColumnNames) {
    	this.uppercaseColumnNames = uppercaseColumnNames;
		this.uppercaseColumns = StringUtils.split(uppercaseColumnNames, ',');
	}
}
