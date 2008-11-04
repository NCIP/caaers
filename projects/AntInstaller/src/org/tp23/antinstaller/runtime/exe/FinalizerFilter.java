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

import org.tp23.antinstaller.InstallException;
import org.tp23.antinstaller.InstallerContext;


/**
 * This filter is called at the end of the install. This is not the 
 * last Java operation since a shutDownHook is present to delete temporary
 * files and in the GUI version the screen should not dissappear.
 * All filter chains must end in a FinalizerFilter or subclass
 * and teh exec method must not throw an Exception.
 * @author Paul Hinds
 * @version $Id: FinalizerFilter.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class FinalizerFilter implements ExecuteFilter {
	/**
	 * @see org.tp23.antinstaller.runtime.exe.ExecuteFilter#exec(org.tp23.antinstaller.InstallerContext)
	 */
	public void exec(InstallerContext ctx){
		try {
			ctx.log("Finalizing");
			ctx.getLogger().close();
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
