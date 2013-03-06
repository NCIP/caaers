package gov.nih.nci.cabig.caaers.security;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.ctms.acegi.csm.authorization.AuthorizationSwitch;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElementPrivilegeContext;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
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
        switchUser("SYSTEM", "caaers_super_user");
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

    public static CaaersSecurityFacade switchToCaaersSecurityFacadeMock(final CaaersSecurityFacade facade){
        return  SecurityTestUtils.CaaersSecurityFacadeImplMock.getInstance(facade);
    }

    public static void switchToCaaersSecurityFacade(){
          CaaersSecurityFacade f = CaaersSecurityFacadeImpl.getInstance();
        if(f instanceof SecurityTestUtils.CaaersSecurityFacadeImplMock) ((CaaersSecurityFacadeImplMock) f).reset();
    }

    static class CaaersSecurityFacadeImplMock extends CaaersSecurityFacadeImpl{

       private static CaaersSecurityFacadeImplMock _lastMock;
        
       private static CaaersSecurityFacade _old;
       private static CaaersSecurityFacade _new;

       public static CaaersSecurityFacade getInstance(CaaersSecurityFacade facade){

         if(_lastMock == null){
             _lastMock = new CaaersSecurityFacadeImplMock();
             _old = instance;
         }

         _new = facade ;
         instance = _lastMock;
         return _lastMock;

       }

       private CaaersSecurityFacadeImplMock(){

       }


        public boolean checkAuthorization(Authentication auth, String objectId, String privilege){
            if(_new != null) return _new.checkAuthorization(auth, objectId, privilege);
            return true;
        }
        public boolean checkAuthorization(Authentication auth, String objectPrivilege){
           if(_new != null) return _new.checkAuthorization(auth, objectPrivilege);
            return true;
        }

        public List<IndexEntry> getAccessibleStudyIds(String loginId){
          if(_new != null) return _new.getAccessibleStudyIds(loginId);
          return null;
        }
        public  List<IndexEntry> getAccessibleOrganizationIds(String loginId){
            if(_new != null) return _new.getAccessibleOrganizationIds(loginId);
          return null;
        }
        public Collection<String> getRoles(String userLoginName, Organization org){
          if(_new != null) return _new.getRoles(userLoginName, org);
          return null;
        }
        public Collection<String> getRoles(String userLoginName, Study study){
          if(_new != null) return _new.getRoles(userLoginName, study);
          return null;
        }

   
        public void clearUserCache(String userName){
             if(_new != null) _new.clearUserCache(userName);
        }

        public void reset(){
            instance = _old;
        }


    }

}


