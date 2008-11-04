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

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.tp23.antinstaller.InstallException;
import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.antmod.Launcher;
import org.tp23.antinstaller.renderer.MessageRenderer;
/**
 *
 * <p>Runs the Ant script using the Apache Ant launcher.</p>
 * <p>This runner uses a modified version of the Apache launcher to provide us
 * with feed back as to the state of the install </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @todo get better feed back and progress for the targets that have run successfully
 * @todo this should be an interface not a class
 * @author Paul Hinds
 * @version $Id: AntLauncherFilter.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class AntLauncherFilter implements ExecuteFilter {

	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.Res");


	public AntLauncherFilter() {
	}

	public void exec(InstallerContext ctx) throws InstallException {
		if(ctx.getInstaller().isVerbose())ctx.log("Starting Ant Launcher");

		try {
			MessageRenderer vLogger = ctx.getMessageRenderer();
			
			//TODO this should be refactored to installer 
			List argsList = ctx.getInstaller().getTargets();
			
			String[] argsArr = new String[argsList.size()+4];
			argsList.toArray(argsArr);
			
			if(ctx.getInstaller().isVerbose())ctx.log("Running targets:"+printArray(argsArr));
			System.out.println("Targets:"+printArray(argsArr));

			argsArr[argsArr.length-2]="-lib";
			argsArr[argsArr.length-1]="antlib";

			argsArr[argsArr.length-4]="-buildfile";
			argsArr[argsArr.length-3]=ctx.getFileRoot().getAbsolutePath()+
				System.getProperty("file.separator")+
				"build.xml";

			//Launcher uses stdout and stderr by default
			System.setOut(ctx.getAntOutputRenderer().getOut());
			System.setErr(ctx.getAntOutputRenderer().getErr());
			
			Map properties = ctx.getInstaller().getResultContainer().getAllProperties();
			
			Launcher launcher = new Launcher(properties);
			int ok = launcher.run(argsArr, ctx);
			if(ok!=0) {
				vLogger.printMessage(res.getString("Failed"));
				//the default ctx.setInstallSucceded(false);
			}
			else {
				ctx.setInstallSucceded(true);
			}
			ctx.log("Ant finished");
		}
		catch (Throwable ex) {
			throw new InstallException("Error running the install");
		} finally{
			ctx.getRunner().antFinished();
		}
	}
	/**
	 * Used for debug to print the targets to system.out
	 * @param args Object[]
	 * @return String
	 */
	private String printArray(Object[] args){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < args.length-4; i++) {
			if(i>0)sb.append(',');
			sb.append(args[i]);
		}
		return sb.toString();
	}
}
