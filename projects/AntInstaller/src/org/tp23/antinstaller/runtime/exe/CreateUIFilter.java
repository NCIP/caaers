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

import java.io.IOException;
import java.util.StringTokenizer;

import org.tp23.antinstaller.InstallException;
import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.runtime.Runner;
import org.tp23.antinstaller.runtime.SwingRunner;
import org.tp23.antinstaller.runtime.TextRunner;


/**
 * Creates the Runner instance for the execution and sets up an appropriate
 * message renderer.
 * @author Paul Hinds
 * @version $Id: CreateUIFilter.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class CreateUIFilter implements ExecuteFilter {

	/**
	 * @see org.tp23.antinstaller.runtime.exe.ExecuteFilter#exec(org.tp23.antinstaller.InstallerContext)
	 */
	public void exec(InstallerContext ctx) throws InstallException {
		try {
			if(ctx.getInstaller().isVerbose())ctx.log("Creating UI classes");
			ctx.setRunner(getRunner(ctx));
			ctx.log("Created UI classes");
		}
		catch (IOException e) {
			throw new InstallException("Not to create the user interface",e);
		}
		catch (InstallException e) {
			throw new InstallException("Not to create the user interface",e);
		}
	}
	/**
	 * Determines which Runner to use text or swing
	 * @param override String if this paramter is not null it will be used. If
	 * swing and there is no graphics environment the install will fail, if it is left
	 * as null a check is made to see if there is a Graphics Environment and swing is used
	 * if there are no errors, if there are errors the system falls back to the text console
	 *
	 * @throws IOException
	 * @return Runner
	 */
	private Runner getRunner(InstallerContext ctx) throws IOException, InstallException {
		boolean useSwing = true;
		if(ctx.getUIOverride() != null){
			if (ctx.getUIOverride().equalsIgnoreCase("swing")){
				if(isUi("swing",ctx.getInstaller().getUi())){
					return new SwingRunner(ctx);
				}else{
					throw new InstallException("Not a permited UI override, swing is not allowed");
				}
			}
			if (ctx.getUIOverride().equalsIgnoreCase("text")){
				if(isUi("text",ctx.getInstaller().getUi())){
					return new TextRunner(ctx);
				}else{
					throw new InstallException("Not a permited UI override, text is not allowed");
				}
			}
		}
		//else do stuff to work out if there is a graphics context
		try{
			java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
		} catch (Throwable e){
			System.out.println("No graphics environment available, reverting to text");
			System.out.println();
			return new TextRunner(ctx);
		}
		return new SwingRunner(ctx);
	}
	private boolean isUi(String ui,String commaSeparatedUiList){
		StringTokenizer st = new StringTokenizer(commaSeparatedUiList,",");
		while(st.hasMoreTokens()){
			if(st.nextToken().equals(ui))return true;
		}
		return false;
	}

}
