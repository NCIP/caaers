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
package org.tp23.antinstaller.runtime;

import java.io.File;
import java.util.ResourceBundle;

import org.tp23.antinstaller.InstallException;
import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.renderer.MessageRenderer;
import org.tp23.antinstaller.runtime.exe.ExecuteFilter;
import org.tp23.antinstaller.runtime.exe.ExecuteRunnerFilter;
import org.tp23.antinstaller.runtime.exe.FilterChain;
import org.tp23.antinstaller.runtime.exe.FilterFactory;
import org.tp23.antinstaller.runtime.exe.FinalizerFilter;
import org.tp23.antinstaller.runtime.exe.ScriptFilterChain;
import org.tp23.antinstaller.selfextract.SelfExtractor;



/**
 * This is the Applications entry point, it has a main method to run the
 * installer. It is here that the command line options are parsed and it
 * is determined which type of install (swing or text) will be run.
 * <p>Reads the config, determines the runner, runs it and outputs the
 * properties file,  The Ant targets are then called by the AntRunner.
 * This class also builds the internal Objects from the XML config file.</p>
 * <p>This class can also be called by external tools to launch the installer
 * currently two options are provided to lauch from Jars. </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: ExecInstall.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class ExecInstall {

	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.Res");
	
	private final InstallerContext ctx = new InstallerContext();
	private FilterChain chain;
	/**
	 * @param antRunner
	 * @param configLoader
	 */
	public ExecInstall(FilterChain chain){
		this.chain=chain;
	}



	/**
	 * Execute the installer, this reads the config fetches a runner and runs the install.
	 * Once the install pages have finished an AntRunner is used to run Ant
	 * @param override String  can be set to commandline, awt or swing to override the installers default
	 */
	public void exec() {
	
		ExecuteFilter[] filters = null;
		try {
			chain.init(ctx);
			filters = chain.getFilters();
		}
		catch (Exception e) {
			// This is a developer error or the package has not been built correctly
			// It should never happen in a tested build
			e.printStackTrace();
			System.exit(1); // called manually since in Win it was not shutting down properly
		}
loop:	for (int i = 0; i < filters.length; i++) {
			try{
				filters[i].exec(ctx);
			}
			catch (ExecuteRunnerFilter.AbortException abort){
				MessageRenderer vLogger = ctx.getMessageRenderer();
				vLogger.printMessage(abort.getMessage());
				ctx.log("Aborted");
				FinalizerFilter ff = (FinalizerFilter)filters[filters.length-1];
				ff.exec(ctx);
				System.exit(1);
			}
			catch (Exception ex) {

				// write errors to the log
				ctx.log("Installation error:"+ex.getMessage()+":"+ex.getClass().toString());
				boolean verbose = true; // be verbose if we cant load the config
				if(ctx.getInstaller()!=null)verbose =  ctx.getInstaller().isVerbose();
				if(verbose){
					ctx.log(ex);
				}

				// write detailed errors to stdout for the GUI screens and text
				if (ctx.getRunner() instanceof TextRunner) {
					if(verbose)ex.printStackTrace();
				}
				else {
					if(verbose)ex.printStackTrace(System.err);
					else {
						System.err.println("Installation failed:"+ex.getClass().getName());
    					System.err.println(ex.getMessage());	
					}
				}
				
				// report the error to the user
				MessageRenderer vLogger = ctx.getMessageRenderer();
				if(vLogger!=null){
					vLogger.printMessage(res.getString("installationFailed") + "\n" + ex.getMessage());
					//Fixed BUG:1295944 vLogger.printMessage("Install failed\n" + ex.getMessage());
				}				
				if(ctx.getRunner()!=null){
					ctx.getRunner().fatalError();
					break loop;
				}
				else {  // the screens did not even start e.g. XML config error
					System.exit(1);
				}
			}
		}
	}





	/**
	 * <p>Runs the installer.
	 * 
	 * This install can be run from a set of files for example from a CD.
	 * @see org.tp23.antinstaller.selfextract.NonExtractor
	 * @see org.tp23.antinstaller.selfextract.SelfExtractor
	 * 
	 * @param args String[]  args are "default" or "swing" or "text" followed by the root directory of the install
	 */
	public static void main(String[] args) {
		
		try {
			FilterChain chain = FilterFactory.factory("/org/tp23/antinstaller/runtime/exe/script.fconfig");		
						ExecInstall installExec = new ExecInstall(new ScriptFilterChain());
				String override = null;
				if(args.length != 2){
					printUsage();
				}
				installExec.ctx.setUIOverride(args[0]);
				installExec.setInstallRoot(new File(args[1]));
				installExec.exec();
		}
		catch (InstallException e) {
			System.out.println("Cant load filter chain:/org/tp23/antinstaller/runtime/exe/script.fconfig");
			e.printStackTrace();
		}
	}

	private static void printUsage(){
		System.out.println("Usage java -cp $CLASSPATH org.tp23.antinstaller.ExecInstall [text|swing]? [install root]");
	}


	/**
	 * Sets the UI override from the command line
	 * @param installRoot
	 */
	public void setUIOverride(String override) {
		ctx.setUIOverride(override);
	}
	/**
	 * This is generated by the Main class which knows where it has
	 * extracted or where it has run from
	 * @param installRoot
	 */
	public void setInstallRoot(File installRoot) {
		ctx.setFileRoot(installRoot);
	}

	/**
	 * This is AntInstalls temporary space which will generally be deleted
	 * except in debug mode when it is left to view the build process.
	 * installRoot and tempRoot can be the same if the directory
	 * is a new empty directory
	 * @param installRoot
	 */
	public void setTempRoot(File tempDir) {
		addShutdownHook(tempDir);
	}
	/**
	 * This shutdown hook is to facilitate debugging the app can be left open
	 * in the GUI view and the resources will not be deleted.  Upon exit
	 * temporary files will be removed.  This is required because the 
	 * deleteOnExit() method fails if the directory is filled with files.
	 * @param tempDir
	 */
	private void addShutdownHook(final File tempDir){
		Runnable hook = new Runnable(){
			public void run(){
				if(ctx.getInstaller()!=null &&
				   ctx.getInstaller().isDebug())return;
				if(tempDir!=null && tempDir.exists() && tempDir.isDirectory()){
					SelfExtractor.deleteRecursive(tempDir);
				}
			}
		};
		Thread cleanUp = new Thread(hook);
        cleanUp.setDaemon(true);
        Runtime.getRuntime().addShutdownHook(cleanUp);
	}

}
