/**
 * 
 */
package gov.nih.nci.security.acegi.grid.authorization;

import gov.nih.nci.cagrid.authorization.GridGroupName;
import gov.nih.nci.cagrid.gridgrouper.grouper.GrouperI;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.dao.GroupSearchCriteria;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public class DefaultGridGroupSearch implements GridGroupSearch {
	
	private static final Log logger = LogFactory.getLog(DefaultGridGroupSearch.class);
	
	
    private UserProvisioningManager userProvisioningManager;
    private String csmApplicationContextName;
    private GridGrouperClientFactory gridGrouperClientFactory;
    
    /*
     * The timeout on the thread that will check group membership
     * against a particular GridGrouperInstance.
     */
    private long gridGrouperTimeout = 10000;
    
	public long getGridGrouperTimeout() {
		return gridGrouperTimeout;
	}


	public void setGridGrouperTimeout(long gridGrouperTimeout) {
		this.gridGrouperTimeout = gridGrouperTimeout;
	}

	public UserProvisioningManager getUserProvisioningManager() {
		return userProvisioningManager;
	}


	public void setUserProvisioningManager(
			UserProvisioningManager userProvisioningManager) {
		this.userProvisioningManager = userProvisioningManager;
	}


	public String getCsmApplicationContextName() {
		return csmApplicationContextName;
	}


	public void setCsmApplicationContextName(String csmApplicationContextName) {
		this.csmApplicationContextName = csmApplicationContextName;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.web.security.GridGroupSearch#getGridGroupNames(java.lang.String)
	 */
	public List<String> getGridGroupNames(String identity){
		List<String> arrayList = new ArrayList<String>();
		
		//Get all groups
		UserProvisioningManager mgr = getUserProvisioningManager();
		Application appProt = new Application();
		appProt.setApplicationName(getCsmApplicationContextName());
		Group grpProt = new Group();
		grpProt.setApplication(appProt);
		GroupSearchCriteria sc = new GroupSearchCriteria(grpProt);
		List groups = mgr.getObjects(sc);
		
		//Group groups by GridGrouper URL
		Map<String, Set<String>> groupsByUrl = new HashMap<String, Set<String>>();
		if(groups != null){
			for(Iterator i = groups.iterator(); i.hasNext();){
				Group group = (Group)i.next();
				String groupName = group.getGroupName();
				if(GridGroupName.isGridGroupName(groupName)){
					GridGroupName gridGroupName = null;
					try {
						gridGroupName = new GridGroupName(groupName);
					} catch (MalformedURLException ex) {
						throw new RuntimeException("Error processing group name: " + ex.getMessage(), ex);
					}
					Set<String> names = groupsByUrl.get(gridGroupName.getUrl());
					if(names == null){
						names = new HashSet<String>();
						groupsByUrl.put(gridGroupName.getUrl(), names);
					}
					names.add(gridGroupName.getName());
				}
			}
		}
		
		//Select groups of which the user is a member
		for(String url : groupsByUrl.keySet()){
			Set<String> names = groupsByUrl.get(url);
			GridGrouperThread t = new GridGrouperThread(identity, url, names);
			t.start();
			try {
				t.join(getGridGrouperTimeout());
			} catch (InterruptedException ex) {
				logger.warn("Search thread interrupted: " + ex.getMessage(), ex);
			}
			
			if(!t.isDone()){
				logger.warn("Call to " + url + " did not complete");
			}else if(t.getException() != null){
				Exception ex = t.getException();
				logger.error("Call to " + url + " failed: " + ex.getMessage(), ex);
			}else{
				List l = t.getGroupNames();
				logger.debug("Call to " + url + " succeeded. User is member of " + l.size() + " groups.");
				arrayList.addAll(l);
			}
		}
		
		return arrayList;
	}
	
	private class GridGrouperThread extends Thread{
		private String identity;
		private String url;
		private Set<String> names;
		private boolean done;
		private Exception exception;
		private List<String> groupNames = new ArrayList<String>();
		
		GridGrouperThread(String identity, String url, Set<String> names){
			this.identity = identity;
			this.url = url;
			this.names = names;
			
		}
		
		Exception getException(){
			return exception;
		}
		boolean isDone(){
			return done;
		}
		public List<String> getGroupNames(){
			return this.groupNames;
		}
		
		public void run(){
			try{
				GrouperI client = getGridGrouperClientFactory().newGridGrouperClient(url);
				
				for(String name : this.names){
					if(client.isMemberOf(identity, name)){
						getGroupNames().add(name);
					}
				}
				
				this.done = true;
			}catch(Exception ex){
				this.exception = ex;
			}
		}
	}

	public GridGrouperClientFactory getGridGrouperClientFactory() {
		return gridGrouperClientFactory;
	}


	public void setGridGrouperClientFactory(
			GridGrouperClientFactory gridGrouperClientFactory) {
		this.gridGrouperClientFactory = gridGrouperClientFactory;
	}	

}
