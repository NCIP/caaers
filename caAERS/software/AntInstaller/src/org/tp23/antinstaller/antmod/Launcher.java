/*
 * Copyright  2003-2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.tp23.antinstaller.antmod;

import gov.nih.nci.cagrid.antinstaller.utils.ClassPathModifier;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.tools.ant.launch.LaunchException;
import org.apache.tools.ant.launch.Locator;
import org.tp23.antinstaller.InstallerContext;



/**
 *  This is a launcher for Ant.
 *
 * This file has been modified by Paul Hinds for Antinstaller and is not the same
 * as the one delivered with Ant 1.6
 *
 * @since Ant 1.6
 * @version $Id: Launcher.java,v 1.2 2006/11/28 23:17:49 kumarvi Exp $
 */
public class Launcher {
	/** The Ant Home property */
	public static final String ANTHOME_PROPERTY = "ant.home";


	/** The Ant Library Directory property */
	public static final String ANTLIBDIR_PROPERTY = "ant.library.dir";


	/** The location of a per-user library directory */
	public static final String USER_LIBDIR = ".ant/lib";


	/** The startup class that is to be run */
	public static final String MAIN_CLASS = "org.apache.tools.ant.Main";

	private final Map allProperties;



/**
	 * Addtional Constructor to pass password properties to Ant
	 * without saving them to a file.
	 * Added by Paul Hinds
	 * @param allProperties Properties
	 */
	public Launcher(Map allProperties) {
		this.allProperties = allProperties;
	}



/**
	 * Run the launcher to launch Ant
	 *
	 * @param args the command line arguments
	 *
	 * @exception MalformedURLException if the URLs required for the classloader
	 *            cannot be created.
	 */
	public int run(String[] args, InstallerContext cxt) throws LaunchException, MalformedURLException {

		try {

			String antHomeProperty = System.getProperty(ANTHOME_PROPERTY);
			File antHome = null;

			File jarDir = null;

			File sourceJar = Locator.getClassSource(getClass());
			jarDir = sourceJar.getParentFile();

			if (antHomeProperty != null) {
				antHome = new File(antHomeProperty);
			}

			if (antHome == null || !antHome.exists()) {
				antHome = jarDir.getParentFile();
				System.setProperty(ANTHOME_PROPERTY, antHome.getAbsolutePath());
			}

			if (!antHome.exists()) {
				throw new LaunchException("Ant home is set incorrectly or ant could not be located");
			}

			List libPaths = new ArrayList();
			List argList = new ArrayList();
			String[] newArgs;

			for (int i = 0; i < args.length; ++i) {
				if (args[i].equals("-lib")) {
					if (i == args.length - 1) {
						throw new LaunchException("The -lib argument must be followed by a library location");
					}
					libPaths.add(args[++i]);
				}
				else {
					argList.add(args[i]);
				}
			}

			if (libPaths.size() == 0) {
				newArgs = args;
			}
			else {
				newArgs = (String[]) argList.toArray(new String[0]);
			}

			List libPathURLs = new ArrayList();
			for (Iterator i = libPaths.iterator(); i.hasNext(); ) {
				String libPath = (String) i.next();
				StringTokenizer myTokenizer
					= new StringTokenizer(libPath, System.getProperty("path.separator"));
				while (myTokenizer.hasMoreElements()) {
					String elementName = myTokenizer.nextToken();
					File element = new File(elementName);
					if (elementName.indexOf("%") != -1 && !element.exists()) {
						continue;
					}
					if (element.isDirectory()) {
						// add any jars in the directory
						URL[] dirURLs = Locator.getLocationURLs(element);
						for (int j = 0; j < dirURLs.length; ++j) {
							libPathURLs.add(dirURLs[j]);
						}
					}

					libPathURLs.add(element.toURL());
				}
			}

			URL[] libJars = (URL[]) libPathURLs.toArray(new URL[0]);

			// Now try and find JAVA_HOME
			//File toolsJar = Locator.getToolsJar();--> This was original line
			
			File installRoot = InstallerContext.getLatestInstallDir();
			File resources = new File(installRoot,"resources");
			File custom_libs = new File(resources,"custom_libs");
			File toolsJar = new File(custom_libs,"tools.jar");
			
			
			
			
			
			

			// determine ant library directory for system jars: use property
			// or default using location of ant-launcher.jar
			File antLibDir = null;
			String antLibDirProperty = System.getProperty(ANTLIBDIR_PROPERTY);
			if (antLibDirProperty != null) {
				antLibDir = new File(antLibDirProperty);
			}
			if ( (antLibDir == null) || !antLibDir.exists()) {
				antLibDir = jarDir;
				System.setProperty(ANTLIBDIR_PROPERTY, antLibDir.getAbsolutePath());
			}
			URL[] systemJars = Locator.getLocationURLs(antLibDir);

			File userLibDir
				= new File(System.getProperty("user.home"), USER_LIBDIR);
			URL[] userJars = Locator.getLocationURLs(userLibDir);


			int numJars = libJars.length + userJars.length + systemJars.length;
			if (toolsJar != null) {
				numJars++;
			}
			URL[] jars = new URL[numJars];
			System.arraycopy(libJars, 0, jars, 0, libJars.length);
			System.arraycopy(userJars, 0, jars, libJars.length, userJars.length);
			System.arraycopy(systemJars, 0, jars, userJars.length + libJars.length,
							 systemJars.length);

			if (toolsJar != null) {
				jars[jars.length - 1] = toolsJar.toURL();
			}


			// now update the class.path property
			StringBuffer baseClassPath
				= new StringBuffer(System.getProperty("java.class.path"));
			if (baseClassPath.charAt(baseClassPath.length() - 1)
				== File.pathSeparatorChar) {
				baseClassPath.setLength(baseClassPath.length() - 1);
			}

			for (int i = 0; i < jars.length; ++i) {
				baseClassPath.append(File.pathSeparatorChar);
				baseClassPath.append(Locator.fromURI(jars[i].toString()));
			}

			System.setProperty("java.class.path", baseClassPath.toString());
			
			//System.out.println("Using classpath: "+baseClassPath.toString());
			
			/**
			 * This is added to do away with the dependecy on jdk
			 * Added by Kumarvi 15 NOV 2006
			 */
			
			try{
			 	//File installRoot = InstallerContext.getLatestInstallDir();
				//File resources = new File(installRoot,"resources");
				//File custom_libs = new File(resources,"custom_libs");
				File tools_jar = new File(custom_libs,"tools.jar");
				File lib = new File(resources,"lib");
				File cert_task_jar = new File(lib,"certificate_tasks.jar");
				File ext = new File(lib,"ext");
				File core_jar = new File(ext,"caGrid-1.0-core.jar");
				File grid_ca_jar = new File(ext,"caGrid-1.0-gridca-1.0.jar");
				File jglobus_jar = new File(ext,"cog-jglobus.jar");
				File jce_jar = new File(ext,"jce-jdk13-125.jar");
				ClassPathModifier.addFile(tools_jar);
				ClassPathModifier.addFile(cert_task_jar);
				ClassPathModifier.addFile(core_jar);
				ClassPathModifier.addFile(grid_ca_jar);
				ClassPathModifier.addFile(jglobus_jar);
				ClassPathModifier.addFile(jce_jar);
			 
		 }catch(Exception e){
			 
			 e.printStackTrace();
		 }
           
		 /**
		  * End of addition
		  */
			

			URLClassLoader loader = new URLClassLoader(jars);
			Thread.currentThread().setContextClassLoader(loader);
			try {
				Main main = new Main();
				Properties props = new Properties();
				props.putAll(allProperties);
				return main.startAnt(newArgs, props, null, cxt);
			}
			catch (Throwable t) {
				t.printStackTrace();
				return 1;
			}
		}
		catch (Throwable ex) {
			// Essentially all of the above is nice to have as far as AntInstaller is concerned
			// ant.home may not be available when installing and application on a client that does not
			// have and never will have Ant.  However the code is left since sometimes AntInstaller can be used
			// for a general gui for Ant builds and features such and ANT_HOME/lib are useful
			try {
				System.setProperty(ANTHOME_PROPERTY, new File(".").getAbsolutePath());
				Main main = new Main();
				// fix for bug:1177191
				// remove the -lib as discovered by Mark Anderson
				String[] newArgs = new String[args.length-2];
				for(int i=0,n=0;i<args.length;i++){
					if(args[i].equals("-lib")){
						i++;
						continue;
					}
					newArgs[n++]=args[i];
				}
				Properties props = new Properties();
				props.putAll(allProperties);
				return main.startAnt(newArgs, props, null, cxt);
			}
			catch (Throwable t) {
				t.printStackTrace();
				return 1;
			}
		}
	}
}
