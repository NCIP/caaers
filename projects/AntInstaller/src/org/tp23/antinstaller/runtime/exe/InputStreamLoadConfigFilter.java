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

import org.tp23.antinstaller.InstallException;
import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.runtime.ConfigurationException;


/**
 * @author Paul Hinds
 * @version $Id: InputStreamLoadConfigFilter.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class InputStreamLoadConfigFilter extends LoadConfigFilter {

	public static final String INSTALLER_CONFIG_RESOURCE = "/"+INSTALLER_CONFIG_FILE;

	public void exec(InstallerContext ctx) throws InstallException {
		this.ctx=ctx;

		try {
			readConfig(ctx.getFileRoot(),this.getClass().getResourceAsStream(INSTALLER_CONFIG_RESOURCE));
			ctx.setInstaller(installerConfig);
			ctx.log("Config loaded");
		}
		catch (IOException e) {
			throw new InstallException("Not able to load and read the AntInstaller config",e);
		}
		catch (ConfigurationException e) {
			throw new InstallException("Not able to load and read the AntInstaller config",e);
		}
	}
}
