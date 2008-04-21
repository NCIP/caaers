package gov.nih.nci.cagrid.data.cql.cacore;

import edu.internet2.middleware.grouper.GroupNotFoundException;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.QueryModifier;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.MalformedQueryException;
import gov.nih.nci.cagrid.data.QueryProcessingException;
import gov.nih.nci.cagrid.data.cql.CQLQueryProcessor;
import gov.nih.nci.cagrid.data.mapping.Mappings;
import gov.nih.nci.cagrid.data.utilities.CQLResultsCreationUtil;
import gov.nih.nci.cagrid.data.utilities.ResultsCreationException;
import gov.nih.nci.cagrid.gridgrouper.client.GridGrouper;
import gov.nih.nci.cagrid.gridgrouper.grouper.GrouperI;
import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.comm.client.ClientSession;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.globus.wsrf.config.ContainerConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/** 
 *  HQLCoreQueryProcessor
 *  Implementation of CQL against a caCORE data source using HQL queries
 * 
 * @author <A HREF="MAILTO:ervin@bmi.osu.edu">David W. Ervin</A>
 * 
 * @created May 2, 2006 
 * @version $Id$ 
 */
public class LocalHQLCoreQueryProcessor extends CQLQueryProcessor {
	public static final String DEFAULT_LOCALHOST_CACORE_URL = "http://localhost:8080/cacore31/server/HTTPServer";
	public static final String APPLICATION_SERVICE_URL = "appserviceUrl";
	public static final String USE_CSM_FLAG = "useCsmSecurity";
	public static final String DEFAULT_USE_CSM_FLAG = String.valueOf(false);
	public static final String CSM_CONTEXT_NAME = "csmContextName";
	public static final String CASE_INSENSITIVE_QUERYING = "queryCaseInsensitive";
	public static final String USE_CASE_INSENSITIVE_DEFAULT = String.valueOf(false);
	public static final String GROUPER_URL = "gridGrouperUrl";
	public static final String GROUPER_GROUP = "gridGrouperGroup";
	
	private static Logger LOG = Logger.getLogger(HQLCoreQueryProcessor.class);
	
	private static Object sessionMutex = new Object(); 
	
	
	public LocalHQLCoreQueryProcessor() {
		super();
	}
	

	public CQLQueryResults processQuery(CQLQuery cqlQuery) 
		throws MalformedQueryException, QueryProcessingException {
		
		System.out.println("IN THE PROCESSOR");
		
		CQLQueryResults results = null;
		/*
		if (useCsmSecurity()) {
			synchronized (sessionMutex) {
				applyUserSecurity();
				results = process(cqlQuery);
				clearUserSecurity();
			}
		} else {
			results = process(cqlQuery);			
		}
		*/


		
		
		String gridGrouperGroup=getConfiguredParameters().getProperty("gridGrouperGroup");
		String gridGrouperUrl=getConfiguredParameters().getProperty("gridGrouperUrl");
		
		System.out.println("Grouper  url " + gridGrouperUrl);
		System.out.println("Grouper  group name" + gridGrouperGroup);
		
		String identity = org.globus.wsrf.security.SecurityManager.getManager().getCaller();
		GrouperI grouper = new GridGrouper(gridGrouperUrl);
		boolean isMember = false;
		
		try {
			isMember = grouper.isMemberOf(identity, gridGrouperGroup);
		} catch (GroupNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (isMember) {
			results = process(cqlQuery);
		} else {
			throw new QueryProcessingException( " Access restricted to  group - " + gridGrouperGroup);
		}
		
		//results = process(cqlQuery);
		return results;
	}
	
	
	private CQLQueryResults process(CQLQuery cqlQuery) 
		throws MalformedQueryException, QueryProcessingException {
		List coreResultsList = queryCoreService(cqlQuery);
		String targetName = cqlQuery.getTarget().getName();
		Mappings mappings = null;
		try {
			mappings = getClassToQnameMappings();
		} catch (Exception ex) {
			throw new QueryProcessingException("Error getting class to qname mappings: " + ex.getMessage(), ex);
		}
		CQLQueryResults results = null;
		// decide on type of results
		boolean objectResults = cqlQuery.getQueryModifier() == null ||
			(!cqlQuery.getQueryModifier().isCountOnly() 
				&& cqlQuery.getQueryModifier().getAttributeNames() == null 
				&& cqlQuery.getQueryModifier().getDistinctAttribute() == null);
		if (objectResults) {
			try {
				results = CQLResultsCreationUtil.createObjectResults(coreResultsList, targetName, mappings);
			} catch (ResultsCreationException ex) {
				throw new QueryProcessingException(ex.getMessage(), ex);
			}
		} else {
			QueryModifier mod = cqlQuery.getQueryModifier();
			if (mod.isCountOnly()) {
				// parse the value as a string to long.  This covers returning
				// integers, shorts, and longs
				Long val = Long.valueOf(coreResultsList.get(0).toString());
				results = CQLResultsCreationUtil.createCountResults(val.longValue(), targetName);
			} else {
				// attributes distinct or otherwise
				String[] names = null;
				if (mod.getDistinctAttribute() != null) {
					names = new String[] {mod.getDistinctAttribute()};
				} else {
					names = mod.getAttributeNames();
				}
				results = CQLResultsCreationUtil.createAttributeResults(
					coreResultsList, targetName, names);
			}
		}
		System.out.println("SENDING RESULTS ...");
		return results;
	}
	/*
	private Document loadDocument(String filename){
        Document doc = null;
        try {
            SAXBuilder builder = new SAXBuilder();
           // InputStream stream = ClassLoader.getSystemResourceAsStream(filename);
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            doc = builder.build(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
	*/
	protected List queryCoreService(CQLQuery query) 
		throws MalformedQueryException, QueryProcessingException {
		// get the caCORE application service
		//ApplicationService service = getApplicationService();
		
		// see if the target has subclasses
		//boolean subclassesDetected = SubclassCheckCache.hasClassProperty(query.getTarget().getName(), service);
		boolean subclassesDetected = false;
		// should the query be made case insensitive
		boolean caseInsensitive = useCaseInsensitiveQueries();
		
		// generate the HQL to perform the query
		String hql = null;
		if (subclassesDetected) {
			// simplify the query by removing modifiers
			CQLQuery simpleQuery = new CQLQuery();
			simpleQuery.setTarget(query.getTarget());
			hql = CQL2HQL.translate(simpleQuery, true, caseInsensitive);
		} else {
			hql = CQL2HQL.translate(query, false, caseInsensitive);
		}
		System.out.println("Executing HQL: " + hql);
		LOG.debug("Executing HQL:" + hql);
		
		// process the query
		HQLCriteria hqlCriteria = new HQLCriteria(hql);
		List targetObjects = null;
		
		Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		
		try {
			session=sessionFactory.openSession();
            System.out.println( hqlCriteria.getHqlString());
            System.out.println( "Executing main target Object Query .." ) ; 
            
			//targetObjects = coreService.query(hqlCriteria, query.getTarget().getName());
            targetObjects = session.createQuery(hqlCriteria.getHqlString()).list();
			System.out.println( "Done query - " + targetObjects.size()) ;
			
			if (subclassesDetected && query.getQueryModifier() != null) {
				targetObjects = applyQueryModifiers(targetObjects, query.getQueryModifier());
			}
			
			
		} catch (Exception ex) {
			throw new QueryProcessingException("Error invoking core query method: " + ex.getMessage(), ex);
		} finally {
			session.close();
		}
		
		// possibly post-process the query
		
		
		return targetObjects;
	}
	
	
	private List applyQueryModifiers(List rawObjects, QueryModifier mods) throws Exception {
		List processed = new LinkedList();
		Iterator rawIter = rawObjects.iterator();
		if (mods.getDistinctAttribute() != null) {
			Set distinctValues = new HashSet();
			while (rawIter.hasNext()) {
				Object o = rawIter.next();
				Object value = accessNamedProperty(o, mods.getDistinctAttribute());
				distinctValues.add(value);
			}
			// convert the single objects to object arrays
			Iterator distinctIter = distinctValues.iterator();
			while (distinctIter.hasNext()) {
				processed.add(new Object[] {distinctIter.next()});
			}
		} else if (mods.getAttributeNames() != null) {
			String[] names = mods.getAttributeNames();
			while (rawIter.hasNext()) {
				Object o = rawIter.next();
				Object[] values = new Object[names.length];
				for (int i = 0; i < names.length; i++) {
					values[i] = accessNamedProperty(o, names[i]);
				}
				processed.add(values);
			}
		} else {
			processed = rawObjects;
		}
		
		if (mods.isCountOnly()) {
			List countList = new ArrayList(1);
			countList.add(new Integer(processed.size()));
			processed = countList;
		}
		return processed;
	}
	
	
	private Object accessNamedProperty(Object o, String name) throws Exception {
		Field namedField = ClassAccessUtilities.getNamedField(o.getClass(), name);
		if (namedField != null) {
			return namedField.get(o);
		}
		// no named field?  Check for a getter
		Method getter = ClassAccessUtilities.getNamedGetterMethod(o.getClass(), name);
		if (getter != null) {
			return getter.invoke(o, new Object[] {});
		}
		// getting here means the field was not found
		throw new NoSuchFieldException("No field " + name + " found on " + o.getClass().getName());
	}
	

	
	
	private Mappings getClassToQnameMappings() throws Exception {
		// get the mapping file name
		//System.out.println("BASE .... "+ ContainerConfig.getBaseDirectory());
		//String filename = ServiceConfigUtil.getClassToQnameMappingsFile();
		String filename =  ContainerConfig.getBaseDirectory()+ File.separator + "etc"+File.separator+"cagrid_CaaersDataService"+File.separator+"classToQname.xml";
		Mappings mappings = (Mappings) Utils.deserializeDocument(filename, Mappings.class);
		return mappings;
	}
	
	
	private String getAppserviceUrl() {
		String url = getConfiguredParameters().getProperty(APPLICATION_SERVICE_URL);
		return url;
	}
	
	
	private String getCallerId() {
		String caller = org.globus.wsrf.security.SecurityManager.getManager().getCaller();
		return caller;
	}
	
	
	private boolean useCsmSecurity() {
		String useCsmValue = getConfiguredParameters().getProperty(USE_CSM_FLAG);
		if (useCsmValue == null) {
			useCsmValue = DEFAULT_USE_CSM_FLAG;
		}
		return Boolean.valueOf(useCsmValue).booleanValue();
	}
	
	
	private String getCsmContextName() {
		String contextName = getConfiguredParameters().getProperty(CSM_CONTEXT_NAME);
		if (contextName == null || contextName.trim().length() == 0) {
			return getAppserviceUrl();
		}
		return contextName;
	}
	
	
	private boolean useCaseInsensitiveQueries() {
		String caseInsensitiveValue = getConfiguredParameters().getProperty(
			CASE_INSENSITIVE_QUERYING);
		if (caseInsensitiveValue == null) {
			caseInsensitiveValue = USE_CASE_INSENSITIVE_DEFAULT;
		}
		return Boolean.valueOf(caseInsensitiveValue).booleanValue();
	}
    
    
    public String getConfigurationUiClassname() {
        return HQLCoreQueryProcessorConfigUi.class.getName();
    }
	
	private void applyUserSecurity() throws QueryProcessingException {
		ClientSession securitySession = ClientSession.getInstance();
		try {
			boolean loggedIn = securitySession.startSession(getCallerId(), getCsmContextName());
			if (!loggedIn) {
				throw new QueryProcessingException("CSM security did not log in user");
			}
		} catch (ApplicationException ex) {
			throw new QueryProcessingException("Error initializing CSM security: " + ex.getMessage(), ex);
		}
	}
	
	
	private void clearUserSecurity() {
		ClientSession securitySession = ClientSession.getInstance();
		securitySession.terminateSession();
	}
	
	public Properties getRequiredParameters() {
		Properties params = new Properties();
		params.setProperty(APPLICATION_SERVICE_URL, DEFAULT_LOCALHOST_CACORE_URL);
		params.setProperty(USE_CSM_FLAG, DEFAULT_USE_CSM_FLAG);
		params.setProperty(CSM_CONTEXT_NAME, "");
		params.setProperty(CASE_INSENSITIVE_QUERYING, USE_CASE_INSENSITIVE_DEFAULT);
		params.setProperty(GROUPER_URL, "");
		params.setProperty(GROUPER_GROUP, "");
		return params;
	}
}
