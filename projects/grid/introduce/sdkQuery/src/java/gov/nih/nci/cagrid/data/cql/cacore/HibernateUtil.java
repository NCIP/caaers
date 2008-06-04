package gov.nih.nci.cagrid.data.cql.cacore;



import java.util.Hashtable;

import javax.naming.NamingException;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;


public class HibernateUtil {

    public static final ThreadLocal session = new ThreadLocal();
    public static Hashtable sessionFactoryMap = new Hashtable();

    private static RuntimeException acLoadFailure = null;
    private static ApplicationContext applicationContext = null;
    
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory;
        try {
            String sessionFactoryId = "caaers";
            
            
            if (sessionFactoryMap.get(sessionFactoryId) == null ){
                System.out.println("Building  New SessionFactory ..." + sessionFactoryId);
                sessionFactory = (SessionFactory)getApplicationContext().getBean("sessionFactory");
                sessionFactoryMap.put(sessionFactoryId,sessionFactory);
            } else {
                System.out.println("Getting  Existing SessionFactory ..." + sessionFactoryId);
                sessionFactory = (SessionFactory)sessionFactoryMap.get(sessionFactoryId);
            }
            
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }
    /*
    public static Session currentSession(String hibernateConfig,String dataBaseURL, String schemaOrUser) throws HibernateException {
        Session s = (Session) session.get();
        // Open a new Session, if this Thread has none yet
        if (s == null) {
            s = getSessionFactory(hibernateConfig,dataBaseURL,schemaOrUser).openSession();
            session.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        session.set(null);
        if (s != null) {
            s.close();
            System.out.println("Closing session");
        }
    }
    */
	public static String[] getConfigLocations() {
        return new String[] {
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-configProperties.xml",
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-spring.xml",
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-db.xml",
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-dao.xml"
        		
        };
    }
	
	public synchronized static ApplicationContext getApplicationContext() {
            /*
            try {
                SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            } catch (NamingException e) {
                throw new RuntimeException("", e);
            }
            */

            try {

                applicationContext = new ClassPathXmlApplicationContext(getConfigLocations());
            } catch (RuntimeException e) {
                acLoadFailure = e;
                throw e;
            }
        return applicationContext;
    }
}
