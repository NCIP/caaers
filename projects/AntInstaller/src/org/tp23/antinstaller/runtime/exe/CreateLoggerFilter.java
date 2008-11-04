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

import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.runtime.SimpleLogger;


/**
 * Creates a suitable logger for the install.  The logging does not
 * throw exceptions since it is mainly for debug and we dont want to 
 * stop an install just because logging is not working
 * @author Paul Hinds
 * @version $Id: CreateLoggerFilter.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class CreateLoggerFilter implements ExecuteFilter {

	public static final String LOG_FILE_NAME = "ant.install.log";
	
	/**
	 */
	public void exec(InstallerContext ctx){
		SimpleLogger logger = new SimpleLogger();
		ctx.setLogger( logger );
		try {
			String defaultName = "./ant.install.log";
			// @since 0.7.1 RFE-1154368 for installs from CD where ./ is not writable
			File defaultFile = new File(defaultName);
			try{
				if(!defaultFile.exists())defaultFile.createNewFile();
			}
			catch(Exception e){
				;// ignore canWrite() will return false
			}
			if(defaultFile.canWrite()){
				logger.setFileName(defaultName);
			} else {
				String tempDir = ctx.getFileRoot().getAbsolutePath();
				logger.setFileName(tempDir+System.getProperty("file.separator")+LOG_FILE_NAME);
			}
			ctx.log("Ant basedir:"+ctx.getFileRoot());
		}
		catch (Throwable ex1) {
			ex1.printStackTrace();
			logger.close();
			// swallow exceptions
		}
	}

}
