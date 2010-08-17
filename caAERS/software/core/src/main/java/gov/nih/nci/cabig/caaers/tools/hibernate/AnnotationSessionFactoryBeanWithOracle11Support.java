/**
 * 
 */
package gov.nih.nci.cabig.caaers.tools.hibernate;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.hibernate.dialect.Oracle9iDialect;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

/**
 * This class was introduced in order to resolve the problem introduced by
 * upgrading to Hibernate 3.2.7. If Oracle 11g is used, automated dialect
 * detection will fail in Hibernate 3.2.7; Hibernate throws an exception for any
 * Oracle version above 10. I could not find any easy way to set the
 * <code>hibernate.dialect</code> on <code>auditSessionFactory</code>. Instead,
 * this class will detect a situation when Oracle is used and dialect is not
 * explicitly set, and in this case it will forcibly set the dialect to Oracle
 * 9. <br>
 * <br>
 * See <b>http://opensource.atlassian.com/projects/hibernate/browse/HHH-3159</b>
 * and <b>http://www.google.com/codesearch/p?hl=en#2RoPTt8R44g/trunk/
 * hibernateAdapter
 * /lib/hibernate-3.2.5ga/src/org/hibernate/dialect/DialectFactory
 * .java&q=DialectFactory&sa=N&cd=2&ct=rc&t=1&l=130</b>
 * 
 * @author dkrylov
 * 
 */
public class AnnotationSessionFactoryBeanWithOracle11Support extends
		AnnotationSessionFactoryBean {

	private static final String HIBERNATE_DIALECT = "hibernate.dialect";

	private static final String ORACLE = "oracle";

	private String driverClassName;

	private static final Logger log = Logger
			.getLogger(AnnotationSessionFactoryBeanWithOracle11Support.class);

	/* (non-Javadoc)
	 * @see org.springframework.orm.hibernate3.AbstractSessionFactoryBean#setDataSource(javax.sql.DataSource)
	 */
	@Override
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		super.setDataSource(dataSource);
		try {
			driverClassName = (String) PropertyUtils.getSimpleProperty(
					dataSource, "driverClassName");
		} catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e), e);
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.orm.hibernate3.LocalSessionFactoryBean#setHibernateProperties(java.util.Properties)
	 */
	@Override
	public void setHibernateProperties(Properties hibernateProperties) {
		if (driverClassName != null
				&& driverClassName.toLowerCase().contains(ORACLE)
				&& hibernateProperties != null
				&& hibernateProperties.getProperty(HIBERNATE_DIALECT) == null) {
			hibernateProperties.setProperty(HIBERNATE_DIALECT, Oracle9iDialect.class.getName());
		}
		super.setHibernateProperties(hibernateProperties);
	}

}
