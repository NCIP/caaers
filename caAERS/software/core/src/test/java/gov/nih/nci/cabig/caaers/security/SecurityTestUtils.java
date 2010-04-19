package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.context.ApplicationContext;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * @author Rhett Sutphin
 */
public class SecurityTestUtils {
    public static void switchUser(String userName, String... roles) {
        GrantedAuthority[] authorities = new GrantedAuthority[roles.length];
        for (int i = 0; i < roles.length; i++) {
            authorities[i] = new GrantedAuthorityImpl(roles[i]);
        }
        Authentication auth = new TestingAuthenticationToken(userName, "ignored", authorities);
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public static void switchToSuperuser() {
        switchUser("SYSTEM", "ROLE_caaers_super_user");
    }

    public static void switchToNoUser() {
        switchUser(null);
    }

    public static boolean enableAuthorization(boolean on, ApplicationContext context) {
        AuthorizationSwitch sw = (AuthorizationSwitch) context.getBean("authorizationSwitch");
        if (sw == null) throw new RuntimeException("Authorization switch not found");
        boolean current = sw.isOn();
        sw.setOn(on);
        return current;
    }

    public static Throwable getRootCause(Exception ex) {
        Throwable t = ex;
        do {
            t = t.getCause();
        } while (t.getCause() != null);
        return t;
    }

    public static void insertCSMPolicy(DataSource dataSource) throws Exception {
        applyPolicy(DatabaseOperation.CLEAN_INSERT, dataSource);
    }

    public static void deleteCSMPolicy(DataSource dataSource) throws Exception {
        applyPolicy(DatabaseOperation.DELETE_ALL, dataSource);
    }

    private static void applyPolicy(DatabaseOperation op, DataSource dataSource)
                    throws SQLException, IOException, DatabaseUnitException {
        DatabaseDataSourceConnection conn = null;
        try {
            conn = new DatabaseDataSourceConnection(dataSource);
            FlatXmlDataSet data = new FlatXmlDataSet(
                            Thread
                                            .currentThread()
                                            .getContextClassLoader()
                                            .getResourceAsStream(
                                                            "gov/nih/nci/cabig/caaers/security/testdata/CSM_policy.xml"));
            op.execute(conn, data);
        } finally {
            if (conn != null) conn.close();
        }
    }

}
