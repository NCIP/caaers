package gov.nih.nci.cabig.caaers.tools;

import org.apache.commons.lang.StringUtils;

/**
 * @author Rhett Sutphin
 */
public class DatabaseConfigurationAccessor {
    private String databaseConfigurationName;
    public static final String DEFAULT_DB_CONFIGURATION_NAME = "datasource";

    public String getDatabaseConfigurationName() {
        return StringUtils.isBlank(databaseConfigurationName)
            ? DEFAULT_DB_CONFIGURATION_NAME
            : databaseConfigurationName;
    }

    public void setDatabaseConfigurationName(String databaseConfigurationName) {
        this.databaseConfigurationName = databaseConfigurationName;
    }
}
