/* 
 * Copyright 2005 Paul Hinds
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tp23.antinstaller;

import gov.nih.nci.cagrid.antinstaller.utils.ClassPathModifier;
import gov.nih.nci.cagrid.antinstaller.utils.StringUtilities;
import gov.nih.nci.cagrid.antinstaller.utils.TomcatUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.taskdefs.Execute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.tp23.antinstaller.page.Page;
import org.tp23.antinstaller.renderer.AntOutputRenderer;
import org.tp23.antinstaller.renderer.MessageRenderer;
import org.tp23.antinstaller.runtime.Logger;
import org.tp23.antinstaller.runtime.Runner;
import org.tp23.antinstaller.runtime.exe.AntLauncherFilter;
/**
 *
 * <p>A single InstallerContext is created by the ExecInstall class and
 * exist for the duration of the Install screens and the runing of
 * the Ant Script. </p>
 * @author Paul Hinds
 * @version $Id: InstallerContext.java,v 1.6 2006/11/28 23:15:38 kumarvi Exp $
 */
public class InstallerContext {

	/**
	 * This is the prefix for environment variables, unlike Ant this is fixed to
	 * the common prefix of "env".  If you dont like this complain to the bug reports
	 * on sourceforge
	 */
	public static final String ENV_PREFIX = "env.";
	/**
	 * This is the prefix for Java system property variables.
	 * This is fixed to "java."
	 */
	public static final String JAVA_PREFIX = "java.";
	
	/**
	 * Added by kumarvi
	 */
	/**
	 * This is the prefix for Custom property variables.
	 * This is fixed to "custom."
	 */
	public static final String CUSTOM_PREFIX = "custom.";
	
	public static final String GRID_ENV_PREFIX ="gridenv.";
	
	public static final String DOES_NOT_EXIST = "doesnotexist";
	
	public static final String GRID_ENV_PROPERTIES_FILE_NAME=".gridenv.properties";
	
	
	
	
	/**
	 * End of addition by kumarvi
	 */
	

	private Logger logger = null;
	private Installer installer = null;
	private MessageRenderer messageRenderer = null;
    private AntOutputRenderer antOutputRenderer = null;
    private Runner runner = null;
    private Page currentPage = null;
    private java.io.File fileRoot = null;
    private BuildListener buildListener = null;
    private AntLauncherFilter antRunner = null;
    private String uIOverride = null;
    
    // called after the Ant part has been run
    private boolean installedSucceded = false;
     
	public InstallerContext() {
	}

	public void setInstallSucceded(boolean installedSucceded){
		this.installedSucceded=installedSucceded;
	}
	public boolean isInstallSucceded(){
		return installedSucceded;
	}
	
	public void log(String message){
		if(logger!=null)logger.log(message);
	}
	public void log(Throwable message){
		if(logger!=null)logger.log(message);
	}

	/**
	 * Check to see if the system is windoze to be able to return the correct prompted
	 * directories.  This method should be IsNotWindows since it assumes anything
	 * that is not windows is Unix
	 * @return boolean true if not windows in the os.name System Property
	 */
	public static boolean isUnix(){
		return System.getProperty("os.name").toLowerCase().indexOf("windows") == -1;
	}

	/**
	 * Use the standard Ant way to load the environment variables, this is not all inclusive
	 * (but will be come Java 1.5 I imagine)
	 * @throws IOException
	 * @return Properties
	 */
	public static Properties getEnvironment(){
		Properties props = new Properties();
		try {
			Vector osEnv = Execute.getProcEnvironment();
			for (Enumeration e = osEnv.elements(); e.hasMoreElements(); ) {
				String entry = (String) e.nextElement();
				int pos = entry.indexOf('=');
				if (pos != -1) {
					props.put(ENV_PREFIX + entry.substring(0, pos),
							  entry.substring(pos + 1));
				}
			}
		}
		catch (Exception ex) {
			// swallow exceptions so this can be loaded statically
			// bit of a bugger if you need the environment on Mac OS 9 but not all apps
			// do so we don't want to die inother situations
			System.out.println("Can't load environment:"+ex.getClass()+","+ex.getMessage());
		}
		Properties javaSysProps = System.getProperties();
		Iterator iter = javaSysProps.keySet().iterator();
		while (iter.hasNext()) {
			Object key = (Object)iter.next();
			props.put(JAVA_PREFIX+key.toString(),javaSysProps.get(key));
		}
		return props;
	}
	
	/**
	 * Added by kumarvi for custom properties
	 * @return
	 */
	public  static Properties getCustomPropertiesXX(){
		return new Properties();
	}
	
	
	public  static Properties getCustomProperties(){
		
		Properties prosTobeReturned = new Properties();
		Properties props = new Properties();
		
		File file = getLatestInstallDir();
		File resourcesFolder = new File(file,"resources");
		
		
		
		if(!resourcesFolder.exists()){
			return prosTobeReturned;
		}
		FileFilter ff = new PropertyFileFilter();
		File[] files = resourcesFolder.listFiles(ff);
		
		if((files.length<1)){
			
			return prosTobeReturned;
		}
		File propertyFile = files[0];
		
		
		String propertyFileName = propertyFile.getName();
		
		/**
		 * Now check if this file exist in the user.home directory
		 */
		
		String userHome = System.getProperty("user.home");
		
		File fileInUserHome = new File(userHome,propertyFileName);
		boolean fileInUserHomeExist = fileInUserHome.exists();
		
		//System.out.println("Property file exist ?"+fileInUserHomeExist);
		/**
		 * Now let us see which file should we use to load the properties
		 */
		if(fileInUserHomeExist){
			try{
			FileInputStream fis_UserHomePropFile = new FileInputStream(fileInUserHome);
			FileInputStream fis_resourcePropFile = new FileInputStream(propertyFile);
			Properties p1 = new Properties();
			Properties p2 = new Properties();
			p1.load(fis_UserHomePropFile);
			//System.out.println("p1 Size:"+p1.keySet().size());
			//System.out.println("Frpm p1:");
			//p1.list(System.out);
			p2.load(fis_resourcePropFile);
			//System.out.println("p2 Size:"+p2.keySet().size());
			//System.out.println("Frpm p2:");
			//p2.list(System.out);
			
			if(p1.keySet().equals(p2.keySet())){
				//Nothing changed so get the prop from user dir
				//System.out.println("loading from user home");
				fis_UserHomePropFile = new FileInputStream(fileInUserHome);
				props.load(fis_UserHomePropFile);
			}else{
				//System.out.println("loading from resource home");
				fis_resourcePropFile = new FileInputStream(propertyFile);
				props.load(fis_resourcePropFile);
				//System.out.println("Right after lolad Size of key set:"+props.keySet().size());
			}
			
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}else{
			try{
				//System.out.println("Should be called when file does not exist in user  home");
				FileInputStream fis_resourcePropFile = new FileInputStream(propertyFile);
				props.load(fis_resourcePropFile);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		Iterator iter = props.keySet().iterator();
		//System.out.println("Size of key set:"+props.keySet().size());
		while (iter.hasNext()) {
			Object key = (Object)iter.next();
			
			prosTobeReturned.put(CUSTOM_PREFIX+key.toString(),props.get(key));
			//System.out.println("Let us see:"+props.get(key));
		}
		
		
		
		return prosTobeReturned;
		
	}
	
	public static File getLatestInstallDir(){
		String tempDirName = System.getProperty("java.io.tmpdir");
		
		//System.out.println("Using temp dir:"+tempDirName);
		File tempDir = new File (tempDirName);
		FileFilter antFilter = new AntInstallerFileFilter();
		File[] antinstalls = tempDir.listFiles(antFilter);
		long base = 1;
		File latestFile = null;
		for(int i=0;i<antinstalls.length;i++){
			File f = antinstalls[i];
			long lastmodified = f.lastModified();
			if(lastmodified>base){
				base = lastmodified;
				latestFile = f;
			}
			
		}
		
		return latestFile;
	}
	public static String getCustomPropertyFileName(){
		String fileName = DOES_NOT_EXIST;
		File file = getLatestInstallDir();
		File resourcesFolder = new File(file,"resources");
		
		//System.out.println("File Path"+resourcesFolder.getAbsolutePath());
		FileFilter ff = new PropertyFileFilter();
		File[] files = resourcesFolder.listFiles(ff);
		if(files.length>0){
		   File  f = files[0];
		   fileName = f.getName();
		}
		
		return fileName;
	}
	
	public static Properties getGridEnviornementProperties(){
		Properties props = new Properties();
		/**
		 * First check if the file exist in user home
		 * If the file exist in user home then load the property file
		 * from this file and return.
		 * 
		 * If this file does not exist in user home then load
		 * the properties from resource folder. (gridenv.hiddenproperties)
		 */
		
			String userHome = System.getProperty("user.home");
		
			File fileInUserHome = new File(userHome,GRID_ENV_PROPERTIES_FILE_NAME);
			boolean fileInUserHomeExist = fileInUserHome.exists();
			if(fileInUserHomeExist){
				System.out.println("Loading properties:");
				props= loadGridEnvProperties(fileInUserHome);
			}else{
				System.out.println("Init Grid properties:");
				props = initGridEnvProperties();
			}
			synchGridProperties(props);
		return props;
	}
	
	private static Properties loadGridEnvProperties(File propFile){
		Properties props = new Properties();
		Properties propsToBeReturned = new Properties();
		try{
			FileInputStream fis = new FileInputStream(propFile);
			props.load(fis);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		Iterator iter = props.keySet().iterator();
		//System.out.println("Size of key set:"+props.keySet().size());
		while (iter.hasNext()) {
			Object key = (Object)iter.next();
			
			propsToBeReturned.put(GRID_ENV_PREFIX+key.toString(),props.get(key));
			//System.out.println("Let us see:"+props.get(key));
		}
		propsToBeReturned.list(System.out);
		
		return propsToBeReturned;
	}
	
	private static void synchGridProperties(Properties props){
		//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		//System.out.println("Calling Synch Properties");
		//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		String catalina_home = System.getenv("CATALINA_HOME");
		System.out.println("Catalina Home from env"+catalina_home);
		String grid_catalina_home=(String) props.get(GRID_ENV_PREFIX+"CATALINA_HOME");
		
		
		if((StringUtilities.isBlank(catalina_home))&&(StringUtilities.isBlank(grid_catalina_home))){
			props.put(GRID_ENV_PREFIX+"tomcat.exist", "false");
		}else{
			   if(!StringUtilities.isBlank(catalina_home)){
				props.put(GRID_ENV_PREFIX+"tomcat.exist","true");
				props.put(GRID_ENV_PREFIX+"CATALINA_HOME", catalina_home);
			   }else{
				   props.put(GRID_ENV_PREFIX+"tomcat.exist","true");
				   props.put(GRID_ENV_PREFIX+"CATALINA_HOME", grid_catalina_home);
			   }
		}
		
		String globus_location = System.getenv("GLOBUS_LOCATION");
		//System.out.println("Globus location from system:"+globus_location);
		String grid_globus_location = (String)props.get(GRID_ENV_PREFIX+"GLOBUS_LOCATION");
		if((StringUtilities.isBlank(globus_location))&&(StringUtilities.isBlank(grid_globus_location))){
			props.put(GRID_ENV_PREFIX+"globus.exist", "false");
		}else{
				if(!StringUtilities.isBlank(globus_location)){
					props.put(GRID_ENV_PREFIX+"globus.exist","true");
					props.put(GRID_ENV_PREFIX+"GLOBUS_LOCATION",globus_location);
				}else{
					props.put(GRID_ENV_PREFIX+"globus.exist","true");
					props.put(GRID_ENV_PREFIX+"GLOBUS_LOCATION",grid_globus_location);
				}
		}
		
		String ant_home = System.getenv("ANT_HOME");
		String grid_ant_home = (String)props.get(GRID_ENV_PREFIX+"ANT_HOME");
		if((StringUtilities.isBlank(ant_home))&&(StringUtilities.isBlank(grid_ant_home))){
			props.put(GRID_ENV_PREFIX+"ant.exist", "false");
		}else{
				if(!StringUtilities.isBlank(ant_home)){
					props.put(GRID_ENV_PREFIX+"ant.exist","true");
					props.put(GRID_ENV_PREFIX+"ANT_HOME",ant_home);
				}else{
					props.put(GRID_ENV_PREFIX+"ant.exist","true");
					props.put(GRID_ENV_PREFIX+"ANT_HOME",grid_ant_home);
				}
		}
		
		/**
		 * Check the system for globus installation on globus
		 */
		
		String tomcat_present = (String)props.getProperty(GRID_ENV_PREFIX+"tomcat.exist");
		
		//System.out.println("Tomcat Present:"+tomcat_present);
		String globus_on_tomcat = "false";
		String non_sec_globus_on_tomcat = "false";
		String sec_globus_on_tomcat="false";
		if(tomcat_present.equalsIgnoreCase("true")){
			String tomcat_home = (String)props.get(GRID_ENV_PREFIX+"CATALINA_HOME");
			//System.out.println("Tomcat Home:"+tomcat_home);
			File tomcat_home_dir = new File(tomcat_home);
			File webappdir = new File(tomcat_home_dir,"webapps");
			File wsrf = new File(webappdir,"wsrf");
			
			if(wsrf.exists()){
				//System.out.println("WSRF Found:"+"YES");
				globus_on_tomcat="true";
				non_sec_globus_on_tomcat = "true";
				File common = new File(tomcat_home,"common");
				File lib = new File(common,"lib");
				if(lib.exists()){
					//System.out.println("LIB Found:"+"YES");
					File jglobus = new File(lib,"cog-jglobus.jar");
					File jgss = new File(lib,"jgss.jar");
					  if(jglobus.exists()&&jgss.exists()){
						  //System.out.println("Jglobus and jgss found");
						  sec_globus_on_tomcat="true";
						  non_sec_globus_on_tomcat = "false";
					  }
				}
			}
		}
		//System.out.println("Now setting up properties");
		props.put(GRID_ENV_PREFIX+"globus.on.tomcat", globus_on_tomcat);
		
		props.put(GRID_ENV_PREFIX+"non.sec.globus.on.tomcat", non_sec_globus_on_tomcat);
		props.put(GRID_ENV_PREFIX+"sec.globus.on.tomcat", sec_globus_on_tomcat);
		
		String tomcat_https_enabled ="false";
		if(tomcat_present.equalsIgnoreCase("true")){
			String tomcat_home = (String)props.get(GRID_ENV_PREFIX+"CATALINA_HOME");
			System.out.println("Fetched value of Tomcat Home:"+tomcat_home);
			try{
				File installRoot = InstallerContext.getLatestInstallDir();
				
				File resources = new File(installRoot,"resources");
				
				File lib = new File(resources,"lib");
				File jdom_jar = new File(lib,"jdom-1.0.jar");
				
				System.out.println("Jdom Jar exist:"+ jdom_jar.exists());
				if(jdom_jar.exists()){
				ClassPathModifier.addFile(jdom_jar);
					if(TomcatUtils.httpsEnabled(tomcat_home)){
					tomcat_https_enabled="true";
					}
				}
				
				}catch(Exception ex){
					ex.printStackTrace();
				}
				 
					
			
		}
		props.put(GRID_ENV_PREFIX+"tomcat.https.enabled", tomcat_https_enabled);
		
		
		String installer_web_root = System.getProperty("installer.web.root");
		boolean isblank = StringUtilities.isBlank(installer_web_root);
		if(!isblank){
			props.put(GRID_ENV_PREFIX+"installer.web.root",installer_web_root);
		}
		
		
		
		String user_home = System.getProperty("user.home");
		props.put(GRID_ENV_PREFIX+"user.local.home",user_home);
		storeGridEnvProperties(props);
		
		
		
	}
	
	private static Properties initGridEnvProperties(){
		/**
		 * Init the properties from this class only.
		 */
		Properties props = new Properties();
		
		String catalina_home = System.getenv("CATALINA_HOME");
		String globus_location = System.getenv("GLOBUS_LOCATION");
		String ant_home = System.getenv("ANT_HOME");
		
		if(catalina_home==null){
			props.put(GRID_ENV_PREFIX+"tomcat.exist", "false");
		}else{
			props.put(GRID_ENV_PREFIX+"tomcat.exist","true");
			props.put(GRID_ENV_PREFIX+"CATALINA_HOME", catalina_home);
		}
		
		if(globus_location==null){
			props.put(GRID_ENV_PREFIX+"globus.exist", "false");
		}else{
			props.put(GRID_ENV_PREFIX+"globus.exist","true");
			props.put(GRID_ENV_PREFIX+"GLOBUS_LOCATION",globus_location);
		}
		
		if(ant_home==null){
			props.put(GRID_ENV_PREFIX+"ant.exist", "false");
		}else{
			props.put(GRID_ENV_PREFIX+"ant.exist","true");
			props.put(GRID_ENV_PREFIX+"ANT_HOME",ant_home);
		}
		
		
		
		
		
		
		return props;
	}
	
	
	
	
	
	private static void  storeGridEnvProperties(Properties props){
		//logger.info("Calling storeGridEnvProperties");
		Properties toBeStoredProperties = new Properties();
		
		//logger.info("Size of the  grid env properties before storing:"+gridEnvProperties.keySet().size());
		Iterator iter = props.keySet().iterator();
		while (iter.hasNext()) {
			Object key = (Object)iter.next();
			String keyName = getGridKey((String)key);
			String value = props.getProperty((String)key);
			toBeStoredProperties.put(keyName, value);
			
		}
		
		try {
			File gridPropertiesFile = new File(System.getProperty("user.home"), GRID_ENV_PROPERTIES_FILE_NAME);
			toBeStoredProperties.store(new FileOutputStream(gridPropertiesFile),"caGrid Installer - AutoGenerated properties");
		}
		catch (Throwable ex) {
			//logger.info("Something went wrong with grid env properties:"+ex.getMessage());
		}
	}
	
	private static String getGridKey(String key){
		String str = key;
		if(key.startsWith(GRID_ENV_PREFIX)){
			str = key.substring(8);
		}
		return str;
	}
	

	// Bean methods
	public Installer getInstaller() {
		return installer;
	}

	public String getMinJavaVersion() {
		return installer.getMinJavaVersion();
	}

	public MessageRenderer getMessageRenderer() {
		return messageRenderer;
	}

	public void setMessageRenderer(MessageRenderer messageRenderer) {
		this.messageRenderer = messageRenderer;
		this.messageRenderer.setInstallerContext(this);
	}
	
    public AntOutputRenderer getAntOutputRenderer() {
		return antOutputRenderer;
    }
    
    public void setAntOutputRenderer(AntOutputRenderer antOutputRenderer) {
		this.antOutputRenderer = antOutputRenderer;
    }
    
    public Page getCurrentPage() {
		return currentPage;
    }
    
    public void setCurrentPage(Page currentPage) {
		this.currentPage = currentPage;
    }

	public File getFileRoot() {
		return fileRoot;
	}

	public void setFileRoot(File fileRoot) {
		this.fileRoot = fileRoot;
	}

	public org.apache.tools.ant.BuildListener getBuildListener() {
		return buildListener;
	}

	public void setBuildListener(org.apache.tools.ant.BuildListener buildListener) {
		this.buildListener = buildListener;
	}

	public AntLauncherFilter getAntRunner() {
		return antRunner;
	}

	public void setAntRunner(AntLauncherFilter antRunner) {
		this.antRunner = antRunner;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public Runner getRunner() {
		return runner;
	}

	public void setRunner(Runner runner) {
		this.runner = runner;
	}

	public void setInstaller(Installer installer) {
		this.installer = installer;
	}

	public String getUIOverride() {
		return uIOverride;
	}

	public void setUIOverride(String override) {
		uIOverride = override;
	}
	
	

	
	
}


