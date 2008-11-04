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

import org.tp23.antinstaller.DefaultPropertiesFileRenderer;
import org.tp23.antinstaller.ExplicitPropertiesFileRenderer;
import org.tp23.antinstaller.InstallException;
import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.PropertiesFileRenderer;
import org.tp23.antinstaller.input.ResultContainer;


/**
 * @author Paul Hinds
 * @version $Id: PropertyPrinterFilter.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class PropertyPrinterFilter implements ExecuteFilter {

	/**
	 * @see org.tp23.antinstaller.runtime.exe.ExecuteFilter#exec(org.tp23.antinstaller.InstallerContext)
	 */
	public void exec(InstallerContext ctx) throws InstallException {
		printProperties(ctx);
		ResultContainer results = ctx.getInstaller().getResultContainer();
		results.setProperty(PropertiesFileRenderer.FILE_ROOT_PROPERTY,
							ctx.getFileRoot().getAbsolutePath());
		
		if(ctx.getInstaller().isVerbose())ctx.log("Properties printed:"+PropertiesFileRenderer.PROPERTIES_FILE_NAME);
	}
	/**
	 *
	 * @param installer Installer
	 * @throws IOException
	 */
	private void printProperties(InstallerContext ctx) {
		PropertiesFileRenderer propRenderer;
		if(ctx.getInstaller().isVerbose()){
			propRenderer = new ExplicitPropertiesFileRenderer();
		}
		else{
			propRenderer = new DefaultPropertiesFileRenderer();
		}
		propRenderer.renderProperties(ctx.getInstaller(), ctx.getFileRoot());
	}

}
