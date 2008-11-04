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
package org.tp23.antinstaller.selfextract;

import java.io.File;

import org.tp23.antinstaller.InstallException;
import org.tp23.antinstaller.runtime.ExecInstall;
import org.tp23.antinstaller.runtime.exe.FilterChain;
import org.tp23.antinstaller.runtime.exe.FilterFactory;
import org.tp23.antinstaller.runtime.exe.NonExtractorFilterChain;


/**
 * This class is a replacement for the SelfExtractor that provides a similar
 * function to the SelfExtractor but avoids the need to extract all the
 * files prior to running the build.  When using this extractor the project
 * is run from the Jar but It is the Ant builds responsibility to access
 * resources from within the Jar the Jar itself can be referenced using 
 * the Ant property "antinstaller.jar".  The build file is automatically read
 * from the Jar.
 * @author Paul Hinds
 * @version $Id: NonExtractor.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class NonExtractor extends SelfExtractor{
	/** used by AntProjectFilter */
	public static final String ANTINSTALLER_JAR_PROPERTY = "antinstaller.jar";
	/**
	 * Run method to use from the command line. This is fired via an entry in the 
	 * MANIFEST.MF in the Jar
	 *@param  args  The command line arguments
	 */
	public static void main(String[] args) {
		try {
			boolean verbose = false;
			SelfExtractor extractor = null;
			extractor = new NonExtractor();
			FilterChain chain = FilterFactory.factory("/org/tp23/antinstaller/runtime/exe/nonextractor.fconfig");
			ExecInstall installExec = new ExecInstall(chain);
			if(args.length>0)installExec.setUIOverride(args[0]);
			// create temporary space for the build to be removed on exit
			File temp = extractor.makeTempDir();
			installExec.setTempRoot(temp);
			installExec.setInstallRoot(temp);
			installExec.exec();
		}
		catch (InstallException e) {
			System.out.println("Cant load filter chain:/org/tp23/antinstaller/runtime/exe/nonextractor.fconfig");
			e.printStackTrace();
		}
	}
}
