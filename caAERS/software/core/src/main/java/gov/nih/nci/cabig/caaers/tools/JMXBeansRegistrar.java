package gov.nih.nci.cabig.caaers.tools;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.jmx.StatisticsService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Registers JMX beans used to monitor the application.
 * 
 * @author dkrylov
 * 
 */
public final class JMXBeansRegistrar implements InitializingBean,
		DisposableBean {

	private static final Logger log = Logger.getLogger(JMXBeansRegistrar.class);

	private List<SessionFactory> sessionFactories = new ArrayList<SessionFactory>();

	public void afterPropertiesSet() throws Exception {
		try {
			MBeanServer server = ManagementFactory.getPlatformMBeanServer();

			for (SessionFactory sf : sessionFactories) {
				ObjectName on = prepareObjectName(sf);
				StatisticsService stats = new StatisticsService();
				stats.setSessionFactory(sf);
				server.registerMBean(stats, on);
			}
		} catch (RuntimeException e) {
			log.error("Unable to register MBeans");
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
	}

	/**
	 * @return
	 * @throws MalformedObjectNameException
	 * @throws NullPointerException
	 */
	private ObjectName prepareObjectName(SessionFactory sessionFactory)
			throws MalformedObjectNameException, NullPointerException {
		Hashtable tb = new Hashtable();
		tb.put("type", "statistics");
		tb.put("sessionFactory", "Session Factory #"
				+ sessionFactories.indexOf(sessionFactory));
		ObjectName on = new ObjectName("hibernate", tb); // MBean object
		// name
		return on;
	}

	public void destroy() throws Exception {
		try {
			MBeanServer server = ManagementFactory.getPlatformMBeanServer();
			for (SessionFactory sf : sessionFactories) {
				ObjectName on = prepareObjectName(sf);
				server.unregisterMBean(on);
			}
		} catch (Exception e) {
			log.error("Unable to unregister MBeans");
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
	}

	public List<SessionFactory> getSessionFactories() {
		return sessionFactories;
	}

	public void setSessionFactories(List<SessionFactory> sessionFactories) {
		this.sessionFactories = sessionFactories;
	}
}
