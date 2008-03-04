/**
 * 
 */
package gov.nih.nci.cabig.caaers.tools;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class CSMPolicyUpdater {

    /**
     * @param args
     */
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Usage: <insert|delete> <data-file-name>");
            System.exit(1);
        }
        String opName = args[0];
        String fileName = args[1];

        try {

            CaaersDataSourcePropertiesFactoryBean b = new CaaersDataSourcePropertiesFactoryBean();
            String databaseConfigName = System.getProperty("db");
            if (databaseConfigName != null) {
                b.setDatabaseConfigurationName(databaseConfigName);
            }
            Properties props = b.getProperties();
            props.list(System.out);

            String driver = getProperty(props, "datasource.driver");
            String url = getProperty(props, "datasource.url");
            String username = getProperty(props, "datasource.username");
            String password = getProperty(props, "datasource.password");

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            try {
                DatabaseConnection dbConn = new DatabaseConnection(conn);
                FlatXmlDataSet data = new FlatXmlDataSet(new FileInputStream(fileName));

                DatabaseOperation op = DatabaseOperation.INSERT;
                if ("delete".equals(opName)) {
                    op = DatabaseOperation.DELETE;
                }
                op.execute(dbConn, data);
            } catch (Exception ex) {
                throw new RuntimeException("Error inserting policy: " + ex.getMessage(), ex);
            } finally {
                try {
                    conn.close();
                } catch (Exception ex) {
                    System.err.println("Error closing connection: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }

    private static String getProperty(Properties props, String propName) {
        String propValue = props.getProperty(propName);
        if (propValue == null) {
            throw new RuntimeException(propName + " not found.");
        }
        return propValue;
    }

}
