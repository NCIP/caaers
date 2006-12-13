package gov.nih.nci.cabig.caaers;

import edu.nwu.bioinformatics.commons.StringUtils;
import edu.nwu.bioinformatics.commons.testing.DbTestCase;
import edu.nwu.bioinformatics.commons.testing.HsqlDataTypeFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Locale;

/**
 * @author Rhett Sutphin
 */
/* TODO: much of this class is shared with PSC.  Refactor into a shared library. */
public class CaaersDbTestCase extends DbTestCase {
    protected final Log log = LogFactory.getLog(getClass());

    protected WebRequest webRequest = new StubWebRequest();
    private boolean shouldFlush = true;

    protected void setUp() throws Exception {
        super.setUp();
        beginSession();
    }

    protected void tearDown() throws Exception {
        endSession();
        super.tearDown();
    }

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
        return Arrays.asList(
            (OpenSessionInViewInterceptor) getApplicationContext().getBean("openSessionInViewInterceptor")
        );
    }

    private List<OpenSessionInViewInterceptor> reverseInterceptors() {
        List<OpenSessionInViewInterceptor> interceptors = interceptors();
        Collections.reverse(interceptors);
        return interceptors;
    }

    protected DataSource getDataSource() {
        return (DataSource) getApplicationContext().getBean("dataSource");
    }

    public static ApplicationContext getApplicationContext() {
        return CaaersTestCase.getDeployedApplicationContext();
    }

    // XXX: This is sort of a hack, but it works.  (A more declarative solution would be better.)
    protected IDataTypeFactory createDataTypeFactory() {
        Properties hibProps = (Properties) getApplicationContext().getBean("hibernateProperties");
        String dialectName = hibProps.getProperty("hibernate.dialect").toLowerCase();
        if (dialectName.contains("oracle")) {
            return new OracleDataTypeFactory();
        } else if (dialectName.contains("hsql")) {
            return new HsqlDataTypeFactory();
        } else {
            return new DefaultDataTypeFactory();
        }
    }

    protected final void dumpResults(String sql) {
        List<Map<String, String>> rows = new JdbcTemplate(getDataSource()).query(
            sql,
            new ColumnMapRowMapper() {
                protected Object getColumnValue(ResultSet rs, int index) throws SQLException {
                    Object value = super.getColumnValue(rs, index);
                    return value == null ? "null" : value.toString();
                }
            }
        );
        StringBuffer dump = new StringBuffer(sql).append('\n');
        if (rows.size() > 0) {
            Map<String, Integer> colWidths = new HashMap<String, Integer>();
            for (String colName : rows.get(0).keySet()) {
                colWidths.put(colName, colName.length());
                for (Map<String, String> row : rows) {
                    colWidths.put(colName, Math.max(colWidths.get(colName), row.get(colName).length()));
                }
            }

            for (String colName : rows.get(0).keySet()) {
                StringUtils.appendWithPadding(colName, colWidths.get(colName), false, dump);
                dump.append("   ");
            }
            dump.append('\n');

            for (Map<String, String> row : rows) {
                for (String colName : row.keySet()) {
                    StringUtils.appendWithPadding(row.get(colName), colWidths.get(colName), false, dump);
                    dump.append(" | ");
                }
                dump.append('\n');
            }
        }
        dump.append("  ").append(rows.size()).append(" row").append(rows.size() != 1 ? "s\n" : "\n");

        System.out.print(dump);
    }

    private static class StubWebRequest implements WebRequest {
        public String getParameter(String paramName) {
            return null;
        }

        public String[] getParameterValues(String paramName) {
            return null;
        }

        public Map getParameterMap() {
            return Collections.emptyMap();
        }

        public Locale getLocale() {
            return null;
        }

        public Object getAttribute(String name, int scope) {
            return null;
        }

        public void setAttribute(String name, Object value, int scope) {
        }

        public void removeAttribute(String name, int scope) {
        }

        public void registerDestructionCallback(String name, Runnable callback, int scope) {
        }

        public String getSessionId() {
            return null;
        }

        public Object getSessionMutex() {
            return null;
        }
    }
}
