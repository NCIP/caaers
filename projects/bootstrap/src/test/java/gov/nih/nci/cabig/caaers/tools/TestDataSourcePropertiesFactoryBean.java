package gov.nih.nci.cabig.caaers.tools;

/**
 * @author Rhett Sutphin
*/
public class TestDataSourcePropertiesFactoryBean extends DataSourceSelfDiscoveringPropertiesFactoryBean {
    public static final String APPLICATION_NAME = "stromboli";

    public TestDataSourcePropertiesFactoryBean() {
        setApplicationDirectoryName(APPLICATION_NAME);
    }

    @Override
    protected void computeProperties() {
        properties.setProperty("computed", "42");
        properties.setProperty("applicationDirectoryName", getApplicationDirectoryName());
    }
}
