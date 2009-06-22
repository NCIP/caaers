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
package org.tp23.antinstaller.runtime.exe;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Diagnostics;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.input.DefaultInputHandler;
import org.apache.tools.ant.launch.Locator;
import org.tp23.antinstaller.InstallException;
import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.antmod.ProjectHelper3;
import org.tp23.antinstaller.selfextract.NonExtractor;
import org.tp23.antinstaller.selfextract.SelfExtractor;
/**
 *
 * <p>This AntRunner runs Ant builds directly from a Jar without having to extract
 * the build.xml to temporary space.</p>
 * <p> </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: AntProjectFilter.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class AntProjectFilter implements ExecuteFilter{

	private static String antVersion = null;
	
	/** The Ant Home property - from default Launcher  */
	public static final String ANTHOME_PROPERTY = "ant.home";

	/** The location of a per-user library directory - from default Launcher */
	public static final String USER_LIBDIR = ".ant/lib";

	public AntProjectFilter() {
	}

	/**
	 * run Ant
	 *
	 * @param installer Installer
	 * @param ctx InstallerContext
	 * @param secretProperties Properties
	 * @throws InstallException
	 * @todo Implement this org.tp23.antinstaller.runtime.AntRunner method
	 */
	public void exec(InstallerContext ctx) throws InstallException {
		if(ctx.getInstaller().isVerbose())ctx.log("Starting Ant Project");

		try {
			
			Project project = new Project();
			appendClassPath();
			setAntHome(ctx);
			project.setCoreLoader(this.getClass().getClassLoader());
			
			DefaultLogger antLogger = new DefaultLogger();
			antLogger.setOutputPrintStream(ctx.getAntOutputRenderer().getOut());
			antLogger.setErrorPrintStream(ctx.getAntOutputRenderer().getErr());
			antLogger.setMessageOutputLevel(Project.MSG_INFO);
			BuildListener bl = ctx.getBuildListener();
			if(bl!=null)project.addBuildListener(bl);
			project.addBuildListener(antLogger);
			
			// irrelevant really but might help someone on a command line
			project.setInputHandler(new DefaultInputHandler());
			project.fireBuildStarted();
			
			project.init();
			project.setUserProperty("ant.version", getAntVersion());


			// add properties
			// N.B. properties are not loaded from the file it exists for debugging installers
			String arg;
			String value;
			Map properties = ctx.getInstaller().getResultContainer().getAllProperties();
			Iterator iter = properties.keySet().iterator();
			while (iter.hasNext()) {
				arg = (String) iter.next();
				value = (String) properties.get(arg);
				project.setUserProperty(arg, value);
			}
			
			// From here we immitate Main
			try {
			    Diagnostics.validateVersion();
			} catch (Throwable exc) {
				// minimal messages for the benefit of the command line install
				System.err.println("Version error:"+exc.getClass()+","+exc.getMessage());
			    return;
			}
			 
			ProjectHelper helper = new ProjectHelper3();
			project.addReference("ant.projectHelper", helper);
			
			File buildXml = new File(ctx.getFileRoot(),"build.xml");
			if(buildXml.exists()){
				helper.parse(project, buildXml);
				project.setUserProperty("ant.file",buildXml.getAbsolutePath());
			} else {
				URL buildIS = this.getClass().getResource("/build.xml");
				helper.parse(project, buildIS);
				project.setUserProperty("ant.file",buildIS.toExternalForm());
			}
			
			File enclosingJar = SelfExtractor.getEnclosingJar(this);
			project.setUserProperty(NonExtractor.ANTINSTALLER_JAR_PROPERTY,enclosingJar.getAbsolutePath());
			System.out.println(NonExtractor.ANTINSTALLER_JAR_PROPERTY+enclosingJar.getAbsolutePath());

			//what is this !?! project.setKeepGoingMode(keepGoingMode);

			project.setBaseDir(ctx.getFileRoot());

			project.executeTargets(ctx.getInstaller().getTargets());
			ctx.setInstallSucceded(true);
			ctx.log("Ant finished");
		}
		catch (Throwable e) {
			throw new InstallException("Error running the install",e);
		} finally{
			ctx.getRunner().antFinished();
		}
	}


	public static synchronized String getAntVersion() throws BuildException {
		 if (antVersion == null) {
			 try {
				 Properties props = new Properties();
				 InputStream in =
					 AntProjectFilter.class.getResourceAsStream("/org/apache/tools/ant/version.txt");
				 props.load(in);
				 in.close();

				 StringBuffer msg = new StringBuffer();
				 msg.append("Apache Ant version ");
				 msg.append(props.getProperty("VERSION"));
				 msg.append(" compiled on ");
				 msg.append(props.getProperty("DATE"));
				 antVersion = msg.toString();
			 } catch (IOException ioe) {
				 throw new BuildException("Could not load the version information:"
										  + ioe.getMessage());
			 } catch (NullPointerException npe) {
				 throw new BuildException("Could not load the version information.");
			 }
		 }
		 return antVersion;
	 }
	
	/**
	 * Append extra Ant jars to the classpath the original classpath 
	 * is not removed incase the installer is launched from a script
	 *
	 */
	private static void appendClassPath(){
		try {
			// now update the class.path property
			StringBuffer baseClassPath
				= new StringBuffer(System.getProperty("java.class.path"));
			if (baseClassPath.charAt(baseClassPath.length() - 1)
				== File.pathSeparatorChar) {
				baseClassPath.setLength(baseClassPath.length() - 1);
			}
			URL[] jars = getLibPaths();
			for (int i = 0; i < jars.length; ++i) {
				baseClassPath.append(File.pathSeparatorChar);
				baseClassPath.append(Locator.fromURI(jars[i].toString()));
			}

			System.setProperty("java.class.path", baseClassPath.toString());

			URLClassLoader loader = new URLClassLoader(jars);
			Thread.currentThread().setContextClassLoader(loader);
		}
		catch (MalformedURLException e) {
			// swallow exception, normally all resources are already loaded
			System.err.println("Invalid Jar path");
		}
	}
	
	
	/**
	 * Ant home is not a requirement but can exist prior to loading
	 * the default Ant mechanism of using the current Jars parent
	 * is consipicuously absent, do not rely on ANT_HOME out side of a 
	 * controlled environment (e.g. a normal install)
	 */
	private static void setAntHome(InstallerContext ctx){
		String antHomeProperty = System.getProperty(ANTHOME_PROPERTY);
		if(antHomeProperty==null){
			System.setProperty(ANTHOME_PROPERTY, ctx.getFileRoot().getAbsolutePath());
		}
	}
	
	/**
	 * To maintain compatability with previous verisons currently the only
	 * Ant command line argument supported is the -lib parameter with the value
	 * "antlib"
	 * @throws MalformedURLException
	 */
	private static URL[] getLibPaths() throws MalformedURLException{
		List libPathURLs = new ArrayList();
		
		// add all Jars from the ./antlib directory at the time of the build
		// this is NOT based on ANT_HOME
		URL[] libJars = Locator.getLocationURLs(new File("antlib"));

		// add all the Jars from ~/.ant/lib
		// this is probably irrelevant for a normal install
		URL[] userJars = Locator.getLocationURLs(new File(USER_LIBDIR));

		// Now try and find JAVA_HOME
		File toolsJar = Locator.getToolsJar();

		int jarsLength = libJars.length + userJars.length + (toolsJar!=null?1:0);
		URL[] allJars = new URL[jarsLength];
		int i = 0;
		if(toolsJar!=null){
			allJars[i++]=toolsJar.toURL();
		}
		if(libJars.length!=0){
			System.arraycopy(libJars,0,allJars,i,libJars.length);
			i+=libJars.length;
		}
		if(userJars.length!=0){
			System.arraycopy(userJars,0,allJars,i,userJars.length);
			//i+=userJars.length;
			//assert(allJars.length=i-1);
		}
		return libJars;
	}
	

}
