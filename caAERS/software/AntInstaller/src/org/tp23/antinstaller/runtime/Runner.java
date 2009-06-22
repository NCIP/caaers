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

import org.tp23.antinstaller.InstallException;



/**
 *
 * <p>A Runner runs the user interaction screens, not ant.
 * The base interface for TextRunner,AWTRunner and SwingRunner </p>
 * <p>Instances of this interface should have a constructor that takes
 * an Installer as an argument</p>
 * Due to historic bad naming convention there exists an AntRunner which has
 * no connection to this interface. 
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: Runner.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */

public interface Runner {

	/**
	 * Renders the installer screens.  This method should block until
	 * the UI has finished
	 * @throws InstallException
	 * @return boolean false implies user aborted
	 */
	public boolean runInstaller() throws InstallException;

	/**
	 * Called after Ant has finished so the Runner can clean up or provide feedback
	 */
	public void antFinished();

	/**
	 * Called if the install failed for some reason and can not continue;
	 *
	 */
	public void fatalError();
}
