package gov.nih.nci.cabig.caaers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.acegisecurity.intercept.method.aspectj.AspectJSecurityInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor;
import org.springframework.web.context.request.WebRequest;

import edu.nwu.bioinformatics.commons.DateUtils;
import edu.nwu.bioinformatics.commons.StringUtils;
import edu.nwu.bioinformatics.commons.testing.DbTestCase;
import edu.nwu.bioinformatics.commons.testing.HsqlDataTypeFactory;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.security.StudyParticipantAssignmentAspect;
import gov.nih.nci.cabig.caaers.security.stub.AspectJSecurityInterceptorStub;
import gov.nih.nci.cabig.ctms.audit.DataAuditInfo;

/**
 * @author Rhett Sutphin
 */
/* TODO: much of this class is shared with PSC. Refactor into a shared library. */
public abstract class CaaersDbTestCase extends DbTestCase {
    protected final Log log = LogFactory.getLog(getClass());
    
    private static RuntimeException acLoadFailure = null;
    private static ApplicationContext applicationContext = null;

    protected WebRequest webRequest = new StubWebRequest();

    private boolean shouldFlush = true;

    //security stub interceptor
    protected static StudyParticipantAssignmentAspect aspect;
	protected static AspectJSecurityInterceptor interceptor ;
    protected static AspectJSecurityInterceptor stubInterceptor;

    private static final DataAuditInfo INFO = new gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo("dun", "127.1.2.7", DateUtils.createDate(2004, Calendar.NOVEMBER, 2),
                    "/studycalendar/zippo");
    
    
    protected void setUpAuthorization() throws Exception {
    	//let the AspectJ runtime load properly
    	ApplicationContext ctx = getDeployedApplicationContext();
    	
        if(aspect == null){
   		 	aspect = (StudyParticipantAssignmentAspect) ctx.getBean("studyParticipantAssignmentAspect");
   		 	interceptor = aspect.getSecurityInterceptor();
   		 	stubInterceptor = new  AspectJSecurityInterceptorStub();
   	 	}
   	 	aspect.setSecurityInterceptor(interceptor); //this step is for safety, sometimes due to error in testcase, tearDown may not work
   	 	SecurityTestUtils.insertCSMPolicy(getDataSource());
   	 	SecurityTestUtils.switchToSuperuser();
     
    }
    
    protected void setUpAuditing() {
		DataAuditInfo.setLocal(INFO);
	}
    
    protected void setUpSession(){
    	beginSession();
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setUpAuthorization();
        setUpAuditing();
        setUpSession();
        
    }
    
    protected void tearDownSession(){
    	endSession();
    }
    protected void tearDownAuthorization() throws Exception{
    	SecurityTestUtils.switchToNoUser();
        SecurityTestUtils.deleteCSMPolicy(getDataSource());	
        aspect.setSecurityInterceptor(interceptor);
    }
    protected void tearDownAuditing() {
    	DataAuditInfo.setLocal(null);
	}
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        tearDownSession();
        tearDownAuthorization();
        tearDownAuditing();
    }

    @Override
    public void runBare() throws Throwable {
        setUp();
        try {
            runTest();
        } catch (Throwable throwable) {
            shouldFlush = false;
            throw throwable;
        } finally {
            tearDown();
        }
    }

    private void beginSession() {
        log.info("-- beginning CaaersDbTestCase interceptor session --");
        for (OpenSessionInViewInterceptor interceptor : interceptors()) {
            interceptor.preHandle(webRequest);
        }
    }

    private void endSession() {
        log.info("--    ending CaaersDbTestCase interceptor session --");
        for (OpenSessionInViewInterceptor interceptor : reverseInterceptors()) {
            if (shouldFlush) {
                interceptor.postHandle(webRequest, null);
            }
            interceptor.afterCompletion(webRequest, null);
        }
    }

    protected void interruptSession() {
        endSession();
        log.info("-- interrupted CaaersDbTestCase session --");
        beginSession();
    }

    private List<OpenSessionInViewInterceptor> interceptors() {
        return Arrays.asList((OpenSessionInViewInterceptor) getApplicationContext().getBean(
                        "openSessionInViewInterceptor"));
    }

    private List<OpenSessionInViewInterceptor> reverseInterceptors() {
        List<OpenSessionInViewInterceptor> interceptors = interceptors();
        Collections.reverse(interceptors);
        return interceptors;
    }

    @Override
    protected DataSource getDataSource() {
        return (DataSource) getApplicationContext().getBean("dataSource");
    }

    public  ApplicationContext getApplicationContext() {
        return getDeployedApplicationContext();
    }

    @Override
    protected IDataTypeFactory createDataTypeFactory() {
        String productName = ((String) getJdbcTemplate().execute(new ConnectionCallback() {
            public Object doInConnection(Connection con) throws SQLException, DataAccessException {
                return con.getMetaData().getDatabaseProductName();
            }
        })).toLowerCase();
        if (productName.contains("oracle")) {
            return new OracleDataTypeFactory();
        } else if (productName.contains("hsql")) {
            return new HsqlDataTypeFactory();
        } else {
            return new DefaultDataTypeFactory();
        }
    }

    protected final void dumpResults(final String sql) {
        List<Map<String, String>> rows = new JdbcTemplate(getDataSource()).query(sql,
                        new ColumnMapRowMapper() {
                            @Override
                            protected Object getColumnValue(ResultSet rs, int index)
                                            throws SQLException {
                                Object value = super.getColumnValue(rs, index);
                                return value == null ? "null" : value.toString();
                            }
                        });
        StringBuffer dump = new StringBuffer(sql).append('\n');
        if (rows.size() > 0) {
            Map<String, Integer> colWidths = new HashMap<String, Integer>();
            for (String colName : rows.get(0).keySet()) {
                colWidths.put(colName, colName.length());
                for (Map<String, String> row : rows) {
                    colWidths.put(colName, Math.max(colWidths.get(colName), row.get(colName)
                                    .length()));
                }
            }

            for (String colName : rows.get(0).keySet()) {
                StringUtils.appendWithPadding(colName, colWidths.get(colName), false, dump);
                dump.append("   ");
            }
            dump.append('\n');

            for (Map<String, String> row : rows) {
                for (String colName : row.keySet()) {
                    StringUtils.appendWithPadding(row.get(colName), colWidths.get(colName), false,
                                    dump);
                    dump.append(" | ");
                }
                dump.append('\n');
            }
        }
        dump.append("  ").append(rows.size()).append(" row")
                        .append(rows.size() != 1 ? "s\n" : "\n");

        System.out.print(dump);
    }
    
    
    public synchronized  ApplicationContext getDeployedApplicationContext() {
        if (acLoadFailure == null && applicationContext == null) {
            // This might not be the right place for this
            try {
                SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            } catch (NamingException e) {
                throw new RuntimeException("", e);
            }

            try {
                log.debug("Initializing test version of deployed application context");
                applicationContext = new ClassPathXmlApplicationContext(getConfigLocations());
            } catch (RuntimeException e) {
                acLoadFailure = e;
                throw e;
            }
        } else if (acLoadFailure != null) {
            throw new CaaersSystemException(
                "Application context loading already failed.  Will not retry.  " +
                    "Original cause attached.", acLoadFailure);
        }
        return applicationContext;
    }
    
    /**
     * The sub classes(testclasses) can override the config locations at runtime. 
     * @return
     */
    public  final String[] getConfigLocations() {
        return new String[] {
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml", 
            "classpath*:applicationContext-test.xml"
        };
    }


    private static class StubWebRequest implements WebRequest {
        public String getParameter(final String paramName) {
            return null;
        }

        public String[] getParameterValues(final String paramName) {
            return null;
        }

        public Map getParameterMap() {
            return Collections.emptyMap();
        }

        public Locale getLocale() {
            return null;
        }

        public Object getAttribute(final String name, final int scope) {
            return null;
        }

        public void setAttribute(final String name, final Object value, final int scope) {
        }

        public void removeAttribute(final String name, final int scope) {
        }

        public void registerDestructionCallback(final String name, final Runnable callback,
                        final int scope) {
        }

        public String getSessionId() {
            return null;
        }

        public Object getSessionMutex() {
            return null;
        }
    }
}
