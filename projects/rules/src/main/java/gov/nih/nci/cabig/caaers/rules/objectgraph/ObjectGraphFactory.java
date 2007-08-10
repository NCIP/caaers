package gov.nih.nci.cabig.caaers.rules.objectgraph;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class ObjectGraphFactory {
	
	private  static ObjectGraphFactory instance;
	private ObjectGraph objectGraph;
	
	/**
	 * 
	 * @throws Exception
	 */
	private ObjectGraphFactory () throws Exception {
		System.out.println("getting..");
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("object-graph.xml");

		Unmarshaller unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.rules.objectgraph").createUnmarshaller();
		objectGraph = (ObjectGraph)unmarshaller.unmarshal(inputStream);
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static ObjectGraphFactory getInstance() throws Exception {
		
		if (instance == null ) {
			instance = getNewInstance();			
		}
		
		return instance;
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private static synchronized ObjectGraphFactory getNewInstance() throws Exception{
		
		if (instance == null ) {
			instance = new ObjectGraphFactory() ;
		}
		
		return instance;

	}
	
	/**
	 * 
	 * @return
	 */
	public ObjectGraph getObjectGraph() {
		return objectGraph;
	}
	
	/**
	 * 
	 * @param sourceObjectType
	 * @param targetObjectType
	 * @return
	 */
	public NavigationPath findNavigationPath (String sourceObjectType, String targetObjectType) {
		for (NavigationPath navigationPath:objectGraph.getNavigationPath()) {
			if (navigationPath.getSourceObjectType().equals(sourceObjectType) && navigationPath.getTargetObjectType().equals(targetObjectType)) {
				return navigationPath;
			}
		}
		
		if (sourceObjectType.equals(targetObjectType)) {
			NavigationPath navigationPath = new NavigationPath();
			navigationPath.setSourceObjectType(sourceObjectType);
			navigationPath.setTargetObjectType(targetObjectType);
			return navigationPath;
		}
		
		return null;
	}
}
