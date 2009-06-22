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
package org.tp23.antinstaller.renderer;

import org.tp23.antinstaller.InstallerContext;
/**
 *
 * <p>Abstract message renderer asking user questions or providing feedback</p>
 * <p> </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: MessageRenderer.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public interface MessageRenderer {

	public void setInstallerContext(InstallerContext ctx);
	/**
	 * Print a message for which there is not option to reply
	 * @param message String
	 */
	public void printMessage(String message);
	/**
	 * ask the user for Yes or No
	 * @param message String
	 * @return boolean  true = Yes  false = No
	 */
	public boolean prompt(String message);
}
